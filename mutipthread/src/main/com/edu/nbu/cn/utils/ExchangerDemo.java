package com.edu.nbu.cn.utils;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    //Exchanger 支持仅支持两个线程之间交换数据；相当于是两个线程之间交换对象的栅栏，线程进入前各自拥有数据，离开时彼此拥有之前由对方持有的数据
    //参考博客 https://www.jianshu.com/p/c523826b2c94

    public static void main(String[] args) throws InterruptedException {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Thread producerThread = new Thread(new Producer(buffer1,exchanger));
        Thread consumerThread = new Thread(new Consumer(buffer2,exchanger));

        producerThread.start();
        consumerThread.start();
    }

    @AllArgsConstructor
    static class Producer extends Thread{
        /**
         * 生产者、消费者交换的数据结构
         */
       private List<String> buffer;
       private Exchanger<List<String>> exchanger;

        @Override
        public void run() {
            for(int i=1;i<5;i++){
                System.out.println("生产者第" + i + "次提供!");
                for(int j=1;j<=3;j++){
                    System.out.println("生产者装入" + i + "--" + j);
                    buffer.add("buffer:" + i + "--" + j);
                }

                System.out.println("生产者装满，等待与消费者交换。。。。");
                try {
                    exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @AllArgsConstructor
    static class Consumer extends Thread{
        private List<String> buffer;

        private final Exchanger<List<String>> exchanger;

        @Override
        public void run() {
            while (true) {
                for (int i = 1; i < 5; i++) {
                    //调用exchange()与消费者进行数据交换
                    try {
                        buffer = exchanger.exchange(buffer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("消费者第" + i + "次提取");
                    for (int j = 1; j <= 3 ; j++) {
                        System.out.println("消费者 : " + buffer.get(0));
                        buffer.remove(0);
                    }
                }
            }
        }
    }
}
