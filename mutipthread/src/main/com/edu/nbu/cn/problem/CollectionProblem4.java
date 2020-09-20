package com.edu.nbu.cn.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * right notify之后还需要wait才能让出锁。
 */
public class CollectionProblem4 {
    private List<Integer> list = new ArrayList<>();


    public  void add(int i){
        list.add(i);
    }

    public  int size(){
        return list.size();
    }

    public static void main(String[] args) {
        CollectionProblem4 cp = new CollectionProblem4();
        Object lock = new Object();
        new Thread(() ->{
            System.out.println("t2 is running");
            synchronized (lock){
                if(cp.size() != 5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
            System.out.println("t2 stopped");
        },"t2").start();

        new Thread(() ->{
            synchronized (lock){
                for(int i=0;i<10;i++){
                    cp.add(i);
                    System.out.println("add " + i);
                    if(cp.size() == 5){
                        //wrong
                        // notify不释放锁，所以无法保证t2可以获得锁，所以notify之后必须wait，保证t2可以获得锁
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
