package com.edu.nbu.cn.ticketseller;

import java.util.Vector;

/**
 * 去除Sychronized之后，代码会报outOfBoundException,也就是说也会出现多卖的情况；
 */
public class TicketSellerVector {
    static Vector<String> tickets = new Vector<>();

    static{
        for(int i=0;i<1000;i++){
            tickets.add("票编号 " + i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                synchronized (tickets){
                    while(tickets.size() > 0){
                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "卖了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
