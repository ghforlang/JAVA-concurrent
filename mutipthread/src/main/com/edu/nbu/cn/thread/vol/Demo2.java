package com.edu.nbu.cn.thread.vol;

/**
 * 简单举例,神奇
 */
public class Demo2 {
    private volatile int count = 12;

    public void m1(){
        try {
            synchronized (this){
                count ++;
            }
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2(){
        try {
            synchronized (this){
                count --;
            }
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo2 d = new Demo2();
        for(int i=0;i<15;i++){
            for(int j=0;j<500;j++){
                new Thread(d::m1).start();
                new Thread(d::m2).start();
            }
            //很神奇，变量同步需要花费不少时间,变量加volatile之后，情况会有所好转；
            // 以至于第二个count值永远是12，而第一个值却不固定.
            System.out.println(d.count);
            Thread.sleep(2000L);
            System.out.println(d.count);
        }
    }
}
