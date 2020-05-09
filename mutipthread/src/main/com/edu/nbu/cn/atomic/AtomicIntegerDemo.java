package com.edu.nbu.cn.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    //lazySet均非线程安全，如果可以接受一段时间内读到的数据是旧数据且对性能要求极高可以考虑使用，否则不推荐使用;

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(123);
        System.out.println(ai.get());//123

        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());//0

        System.out.println(ai.compareAndSet(123,321));//321
        System.out.println("after compareAndSet1 : " + ai.get());//321
        System.out.println(ai.compareAndSet(12,321));//false
        System.out.println("after compareAndSet2 : " + ai.get());//321
        System.out.println(ai.getAndAdd(3));//321
        System.out.println("after getAndAdd : " + ai.get());//324
        System.out.println(ai.addAndGet(3));//327
        System.out.println("after addAndGet : " + ai.get());//327
        System.out.println(ai.getAndDecrement());//327
        System.out.println("after getAndDecrement : " + ai.get());//326
        System.out.println(ai.decrementAndGet());//325
        System.out.println("after decrementAndGet : " + ai.get());//325

        //支持二元运算
        System.out.println(ai.accumulateAndGet(2,(x,y) -> x *y));//650
        System.out.println("after accumulateAndGet : " + ai.get());//650
        System.out.println(ai.getAndAccumulate(2,(x,y) -> x *y));//650
        System.out.println("after getAndAccumulate : " + ai.get());//1300

        //一元运算
        System.out.println(ai.getAndUpdate(x -> x*2));//1300
        System.out.println("after getAndUpdate : " + ai.get());//2600

        ai.lazySet(1000);
        System.out.println("after lazySet : " + ai.get());


    }
}
