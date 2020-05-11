package com.edu.nbu.cn.utils.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GamerInitUsedCyclicBarrier {

    public static void main(String[] args) {
        String[] players = {"张博","sss","小风" ,"鸡哥" ,"脑弟"};
        ExecutorService service = Executors.newFixedThreadPool(5) ;
        final CyclicBarrier barrier = new CyclicBarrier(5);
        for(int i=0;i<5;i++){
            service.execute(new Gamers(players[i],barrier));
        }
        service.shutdown();
    }

}
