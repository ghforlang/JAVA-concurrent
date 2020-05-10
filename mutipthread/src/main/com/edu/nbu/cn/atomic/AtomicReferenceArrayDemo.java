package com.edu.nbu.cn.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicReferenceArrayDemo {
    private static Integer[] array = {1,2,3,4,5};

    private static AtomicReferenceArray<Integer> ara = new AtomicReferenceArray<Integer>(5);

    public static void main(String[] args) {
        ara.set(0,10);
        System.out.println("after set : " + ara.get(0));
        System.out.println(ara.compareAndSet(0,10,20));
        System.out.println("after compareAndSet : " + ara.get(0));
    }

}
