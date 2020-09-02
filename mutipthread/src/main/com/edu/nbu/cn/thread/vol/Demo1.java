package com.edu.nbu.cn.thread.vol;

/**
 * volatile 线程间可见性；防止指令重排;缓存一致性协议(MESI)
 */
public class Demo1 {

    /**
     * 去除volatile，t1则不会终止
     */
    private volatile boolean flag = true;

    public  void m(){
        System.out.println("is running!");
        while(flag){
            //do something
        }
        System.out.println("end!");
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 d = new Demo1();
        new Thread(d::m,"t1").start();
        //休眠2s
        Thread.sleep(2000L);
        d.flag = false;
        System.out.println("main thread is running!");
    }
}
