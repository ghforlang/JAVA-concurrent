package com.edu.nbu.cn.thread.singleton;

import java.util.Objects;


/**
 * 错误的懒汉模式
 */
public class WrongSingleTon2 {
    private static WrongSingleTon2 INSTANCE;

    public static WrongSingleTon2 getInstance() throws InterruptedException {
        if(Objects.isNull(INSTANCE)){
            Thread.sleep(2000L);
            INSTANCE = new WrongSingleTon2();
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws InterruptedException {
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
