package com.edu.nbu.cn.thread;

public class CcThread implements Runnable {

    private String name;
    private static boolean exists = true;

    public CcThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        if(exists){
            System.out.println(name + " 开始执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " do something");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " 执行完毕");
            exists = !exists;
        }else{
            System.out.println(name + "什么也做不了");
        }
    }
}
