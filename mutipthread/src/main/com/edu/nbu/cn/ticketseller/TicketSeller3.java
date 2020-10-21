package com.edu.nbu.cn.ticketseller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 线性并发容器，尽量采用Queue,少用list或者set
 */
public class TicketSeller3 {

    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static{
        for(int i=0;i<10000;i++){
            tickets.add("票编号 " + i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(true){
                    String s = tickets.poll();
                    if(s == null){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "卖了票" + s);
                }
            }).start();
        }
    }
}
