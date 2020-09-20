package com.edu.nbu.cn.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用lockSupport ，等同于CountDownLatch，也需要使用两个
 */
public class CollectionProblem6 {
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
        t2= new Thread(() ->{
//            if(cp.size() != 5){
//                LockSupport.park();
//                System.out.println("t2 end");
//            }
            LockSupport.park();
            LockSupport.unpark(t1);
            System.out.println("t2 end");
        },"t2");

        t1 = new Thread(() ->{
            for(int i=0;i<10;i++){
                cp.add(i);
                System.out.println("add " + i);
                if(cp.size() == 5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        },"t1");

        t2.start();
        t1.start();
    }
}
