package com.edu.nbu.cn.thread.syn;

/**
 * 出现异常sychronized锁会释放，参考第二个monitorexit指令
 */
public class Demo4 {

    public synchronized void divide(){
        int  i = 5;
        System.out.println(Thread.currentThread().getName() + " divide start");
        while(true){
            System.out.println(Thread.currentThread().getName() + " i = " + i--);
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int j = 5 / i ;
        }
    }

    public synchronized void sum(){
        System.out.println(Thread.currentThread().getName() + " sum start!");
        System.out.println(Thread.currentThread().getName() + " sum end!");
    }

    public static void main(String[] args) {
        Demo4 d = new Demo4();
        new Thread(d::divide,"t1").start();
        new Thread(d::sum,"t2").start();
    }
}
