package com.edu.nbu.cn.thread;

public class MyThread extends Thread{

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " "+ System.currentTimeMillis()  + " is Running！");
    }
}
