package com.edu.nbu.cn.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用countDownLatch，单个countDownLatch达不到目的，需要使用两个
 */
public class CollectionProblem5 {
    private List<Integer> list = new ArrayList<>();

    public  void add(int i){
        list.add(i);
    }

    public  int size(){
        return list.size();
    }


    public static void main(String[] args) {
        CollectionProblem5 cp = new CollectionProblem5();
        CountDownLatch cdl = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(1);
        new Thread(() ->{
//            if(cp.size() != 5){
                try {
                    cdl.await();
                    cdl2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 end");
//            }
        },"t2").start();

        new Thread(() ->{
            for(int i=0;i<10;i++){
                cp.add(i);
                System.out.println("add " + i);
                if(cp.size() == 5){
                    cdl.countDown();
                    try {
                        cdl2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        },"t1").start();
    }
}
