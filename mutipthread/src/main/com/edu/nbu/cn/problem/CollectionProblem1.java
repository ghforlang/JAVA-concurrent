package com.edu.nbu.cn.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * wrong answer
 */
public class CollectionProblem1 {

    private List<Integer> list = new ArrayList<>();

    public void add(int i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        CollectionProblem1 cp = new CollectionProblem1();
        new Thread(() ->{
            for(int i=0;i<10;i++){
                cp.add(i);
                System.out.println("add " + i);
            }

        },"t1").start();

        new Thread(() ->{
            while(true){
                if (cp.size() == 5){
                    System.out.println("t2 end");
                    break;
                }
            }
        },"t2").start();
    }

}
