package com.edu.nbu.cn.syn;

import java.util.concurrent.locks.LockSupport;

//连续两次park、unpark会导致线程直接阻塞
public class LockSupportDemo2 {

    public static void main(String[] args) {
        Thread demoThread = new Thread(new DemoThread());
        demoThread.start();
        for (int i = 0; i <2 ; i++) {
            System.out.println("开始线程唤醒!");
            LockSupport.unpark(demoThread);
            System.out.println("结束线程唤醒!");
        }

    }

    static class DemoThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<2;i++){
                System.out.println("开始线程阻塞!");
                LockSupport.park();
                System.out.println("结束线程阻塞!");
            }
        }
    }
}
