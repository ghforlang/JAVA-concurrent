package com.edu.nbu.cn.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * complex answer
 */
public class CollectionProblem7 {

    private List<Integer> list = new ArrayList<>();
    static Thread t2 = null,t1 = null;
    public  void add(int i){
        list.add(i);
    }

    public  int size(){
        return list.size();
    }

    public static void main(String[] args) {
        CollectionProblem5 cp = new CollectionProblem5();
        Semaphore s1 = new Semaphore(1);
        t2= new Thread(() ->{
            try {
                s1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end");
            s1.release();
        },"t2");

        t1 = new Thread(() ->{
            for(int i=0;i<10;i++){
                cp.add(i);
                System.out.println("add " + i);
                if(cp.size() == 5){
                    s1.release();
                    try {
                        s1.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        t2.start();
        t1.start();
    }
}
