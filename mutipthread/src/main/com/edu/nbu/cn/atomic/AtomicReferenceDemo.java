package com.edu.nbu.cn.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
    public static void main(String[] args) {
        atomicReference.compareAndSet(0,1);
        System.out.println("after compareAndGet : " + atomicReference.get());


    }
}
