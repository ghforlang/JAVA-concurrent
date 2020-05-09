package com.edu.nbu.cn.atomic;

import com.edu.nbu.cn.thread.BooleanDemoThread;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDemo {

    private static AtomicBoolean initialized = new AtomicBoolean(false);

    public static void main(String[] args) {
//        BooleanDemoThread c1 = new BooleanDemoThread("c1");
//        c1.start();
//        BooleanDemoThread c2 = new BooleanDemoThread("c2");
//        c2.start();

        AtomicBooleanDemo demo = new AtomicBooleanDemo();
        demo.init();

        System.out.println("after init :  " + initialized.get());//true
        initialized.getAndSet(false);
        System.out.println("after getAndSet : " + initialized.get());//false
        initialized.lazySet(true);
        System.out.println("after lazySet : " + initialized.get());
        initialized.set(false);
        System.out.println("after set : " + initialized.get());
        System.out.println(initialized.weakCompareAndSet(false,true));
        System.out.println("after weakCompareAndSet : " + initialized.get());
    }


    /**
     * 使用atomicBoolean高效并发处理"只初始化一次"
     */
    public void init(){
        if(initialized.compareAndSet(false,true)){
            System.out.println("初始化开始!");
        }
    }


}
