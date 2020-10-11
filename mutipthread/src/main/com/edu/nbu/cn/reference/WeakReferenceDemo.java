package com.edu.nbu.cn.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用只要gc就会被回收，主要用在容器、threadLocal
 * 为何使用弱引用？
 * 弱是强引用，被引用对象被清理后，容器内引用者依然保持着强引用，会导致该部分内存不会被回收，造成内存泄露；
 * 为保证被引用对象被清理后，所有引用者均不再引用该对象，可使用弱引用，下一次gc会回收该对象内存。
 * 以ThreadLocal为例，使用完之后，必须remove，否则还会存在内存泄露的风险.
 * 原因在于ThreacLocalMap的key被置null(弱引用失效)之后，value值仍被引用，但是却访问不到，所以出现内存泄露
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        WeakReference<M> weakReference = new WeakReference<>(new M());
        System.out.println(weakReference.get());
        System.gc();

        //gc后，弱引用被回收,打印null
        System.out.println(weakReference.get());
    }
}
