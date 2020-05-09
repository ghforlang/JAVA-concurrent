package com.edu.nbu.cn.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class BooleanDemoThread extends Thread{
    private String name;
    /**
     * 必须声明为static，否则不生效?断点模式也可生效?
     */
    private static AtomicBoolean exists = new AtomicBoolean(false);

    public BooleanDemoThread(String name){
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        if(exists.compareAndSet(false,true)){
            System.out.println(name + " 开始执行");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " do something");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " 执行完毕");
            exists.set(false);
        }else{
            System.out.println(name + "什么也做不了");
        }
    }
}
