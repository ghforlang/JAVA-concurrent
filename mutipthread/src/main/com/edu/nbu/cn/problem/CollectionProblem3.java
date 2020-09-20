package com.edu.nbu.cn.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * wrong answer
 *
 */
public class CollectionProblem3 {
    private   List<Integer> list = new ArrayList<>();

    public synchronized void add(int i){
        list.add(i);
    }

    public synchronized int size(){
        return list.size();
    }

    public static void main(String[] args) {
        CollectionProblem1 cp = new CollectionProblem1();

        new Thread(() ->{
            System.out.println("t2 is running!");
            while(true){
                System.out.println("cp.size " + cp.size());
                if (cp.size() == 5){
                    System.out.println("t2 end");
                    break;
                }
            }
        },"t2").start();


        new Thread(() ->{
            for(int i=0;i<10;i++){
                cp.add(i);
                System.out.println("add " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"t1").start();

    }
}
