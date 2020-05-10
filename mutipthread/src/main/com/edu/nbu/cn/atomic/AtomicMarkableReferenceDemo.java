package com.edu.nbu.cn.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceDemo {
    //为解决CAS的ABA问题,可以明确知道变量中途被更改了几次,如果只关心是否更改过，可以使用AtomicMarkableReference
    //构造器内部实例化一个pair对象，用于记录对象引用和时间戳信息，实际使用时要保证时间戳唯一（一般做成自增），否则还是会出现aba问题
    private static final Integer INIT_NUM = 1000;
    private static final Integer UPDATE_NUN = 100;
    private static final Integer TEM_NUM = 200;
    private static final Boolean INIT_MARK = false;

    private static AtomicMarkableReference<Integer> amr = new AtomicMarkableReference(INIT_NUM,INIT_MARK);

    public static void main(String[] args){

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + " ： 初始值为：" + INIT_NUM + " , 标记为： " + INIT_MARK);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (amr.compareAndSet(INIT_NUM, UPDATE_NUN, amr.isMarked(), Boolean.TRUE)) {
                System.out.println(Thread.currentThread().getName() + " ： 修改后的值为：" + amr.getReference() + " , 标记为： " + amr.isMarked());
            }else{
                System.out.println(Thread.currentThread().getName() +  " CAS返回false");
            }
        },"线程A").start();


        new Thread(() ->{
            //让出cpu，保证线程A先执行
            Thread.yield();
            int value = amr.getReference();
            System.out.println(Thread.currentThread().getName() + " : 当前值为：" + value + " 标记为：" + amr.isMarked());
            amr.compareAndSet(amr.getReference(), TEM_NUM, amr.isMarked(), true);
            System.out.println(Thread.currentThread().getName() + " : 当前值为：" + amr.getReference() + " 标记为：" + amr.isMarked());
        },"线程B").start();
    }
}
