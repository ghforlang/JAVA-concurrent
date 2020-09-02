package com.edu.nbu.cn.thread.singleton;

import java.util.Objects;

/**
 * 懒汉模式
 */
public class Singleton2 {

    private static  Singleton2 INSTANCE;

    public static synchronized Singleton2 getInstance() throws InterruptedException {
        if(Objects.isNull(INSTANCE)){
            Thread.sleep(2000L);
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                try {
                    System.out.println(getInstance());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
