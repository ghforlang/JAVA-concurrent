package com.edu.nbu.cn.problem;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProducerAndConsumer1 {
    static class MyCollection{
        LinkedList<String> myList = new LinkedList<>();
        private  int  count = 0;


        public synchronized void put(String i){
            while(myList.size() == 10){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            myList.add(i);
            ++count;
            this.notifyAll();
        }

        @SneakyThrows
        public synchronized String get(){
            while (myList.size() == 0){
                this.wait();
            }
            --count;
            this.notifyAll();
            return myList.removeFirst();
        }

        public  int count(){
            return count;
        }

    }

    public static void main(String[] args) {
        MyCollection mc = new MyCollection();

        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            producers.add(new Thread(() ->{
                for (int j = 0; j < 25; j++) {
                    mc.put(Thread.currentThread().getName() + " produce  " + j);
                }
            },"producer" + i));
        }

        List<Thread> consumers = new ArrayList<>();
        for(int i=0;i<10;i++){
            consumers.add(new Thread(() ->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " consume  " + mc.get());
                }

            },"consumer" + i));
        }

        producers.forEach(t -> t.start());
        consumers.forEach(t -> t.start());
    }
}
