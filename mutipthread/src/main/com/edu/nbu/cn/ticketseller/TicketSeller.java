package com.edu.nbu.cn.ticketseller;

import java.util.ArrayList;
import java.util.List;

/**
 * 非线程安全，可能会出现超买的情况
 */
public class TicketSeller {
    static List<String> ticketList = new ArrayList<>();

    static{
        for(int i=0;i<100000;i++){
            ticketList.add("票编号 " + i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(ticketList.size() > 0){
                    System.out.println(Thread.currentThread().getName() + "卖了--" + ticketList.remove(0));
                }
            }).start();
        }
    }
}
