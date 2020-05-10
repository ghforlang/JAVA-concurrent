package com.edu.nbu.cn.atomic;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Getter
public class AtomicIntegerFieldUpdaterDemo {
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> aifu = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterDemo.class,"count");

    //属性必须为volatile，保证线程可见性
    private volatile int count = 100;

    //AtomicLongFieldUpdater与 AtomicIntegerFieldUpdater类似，不再冗举;其他方法与atomicInteger类似，同样不再冗举
//    private static AtomicIntegerFieldUpdater<IntegerModel> aifu2 = AtomicIntegerFieldUpdater.newUpdater(IntegerModel.class,"count");


    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        System.out.println(aifu.compareAndSet(demo,100,200));
        System.out.println("after compareAndSet : " + demo.getCount() + "aifu : " + aifu.get(demo));

        //IllegalAccessException : can not access a member of ...
//        IntegerModel im = new IntegerModel();
//        im.setCount(100);
//        System.out.println(aifu2.compareAndSet(im,100,200));
//        System.out.println("after compareAndSet : " + im.getCount() + "aifu2 : " + aifu2.get(im));

    }

    @Getter
    @Setter
    static class IntegerModel{
        private volatile Integer count;
        private String desc;
    }

}
