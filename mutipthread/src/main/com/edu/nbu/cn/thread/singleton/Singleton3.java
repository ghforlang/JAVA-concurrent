package com.edu.nbu.cn.thread.singleton;

import java.util.Objects;

/**
 * 双重检查单例
 */
public class Singleton3 {

    /**
     * 超高并发环境下，可能会出现指令重排导致单例失效；安全起见，加volatile
     */
    private static volatile Singleton3 INSTANCE;

    public static Singleton3 getInstance(){
        //第一次检查无需加锁
        if(Objects.isNull(INSTANCE)){
            //拿到锁之后再做检查，若已经有其他线程做了初始化，那么直接返回
            synchronized (Singleton3.class){
                if(Objects.isNull(INSTANCE)){
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread( () ->{
                System.out.println(getInstance());
            }).start();
        }
    }
}
