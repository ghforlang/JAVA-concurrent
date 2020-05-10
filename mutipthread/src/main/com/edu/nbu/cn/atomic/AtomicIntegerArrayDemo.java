package com.edu.nbu.cn.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    private static  int[] array = {1,2,3,4,5};
    private static long[] longArr = {1L,2L,3L,4L,5L};

    //AtomicLongArray用法与AtomicIntegerArray一致，不再冗述

    public static void main(String[] args) {
        AtomicIntegerArray aia = new AtomicIntegerArray(array);
        System.out.println("array : " + aia);
        System.out.println(aia.accumulateAndGet(0,2,(u,v) -> u*v));//22345
        System.out.println("after accumulateAndGet : " + aia);

        System.out.println(aia.addAndGet(1,2));//24345
        System.out.println("after addAndGet : " + aia);

        System.out.println(aia.compareAndSet(2,3,6));//24645
        System.out.println("after compareAndSet : " + aia);

        System.out.println(aia.updateAndGet(3,x -> 2*x));
        System.out.println("after updateAndGet : " + aia);//24845

        System.out.println(aia.getAndIncrement(4));
        System.out.println("after getAndIncrement : " + aia);//24846
    }
}
