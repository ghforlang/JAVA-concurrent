package com.edu.nbu.cn.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    //为解决CAS的ABA问题,可以明确知道变量中途被更改了几次,如果只关心是否更改过，可以使用AtomicMarkableReference
    //构造器内部实例化一个pair对象，用于记录对象引用和时间戳信息，实际使用时要保证时间戳唯一（一般做成自增），否则还是会出现aba问题
    private static final Integer INIT_NUM = 1000;
    private static final Integer UPDATE_NUN = 100;
    private static final Integer TEM_NUM = 200;

   private static  AtomicStampedReference<Integer> asr = new AtomicStampedReference(INIT_NUM,0);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() ->{
            int value = asr.getReference();
            int stamp = asr.getStamp();
            System.out.println(Thread.currentThread().getName() + "- 当前值 ：" + value + " 当前版本号 ：" + stamp);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(asr.compareAndSet(value,UPDATE_NUN,stamp,stamp + 1)){
                System.out.println(Thread.currentThread().getName() + "- 当前值 ：" + asr.getReference() + " 当前版本号 ：" + asr.getStamp());
            }else{
                System.out.println(Thread.currentThread().getName() + "- 当前值 ：" + asr.getReference() + " 当前版本号 ：" + asr.getStamp());
                System.out.println("版本号不同，更新失败!");
            }
        },"线程A").start();


        new Thread(() ->{
            //让出cpu，保证线程A先执行
          Thread.yield();
            int value = asr.getReference();
            int stamp = asr.getStamp();
            System.out.println(Thread.currentThread().getName() + " : 当前值为：" + value + " 版本号为：" + stamp);
            boolean result = asr.compareAndSet(asr.getReference(), TEM_NUM, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " : 当前值为：" + asr.getReference() + " 版本号为：" + asr.getStamp() + "结果：" + result);
            result = asr.compareAndSet(asr.getReference(), INIT_NUM, stamp, stamp + 1);
            //思考:为什么会失败?
            System.out.println(Thread.currentThread().getName() + " : 当前值为：" + asr.getReference() + " 版本号为：" + asr.getStamp() + "结果：" + result);
        },"线程B").start();
    }
}
