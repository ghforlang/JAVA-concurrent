package com.edu.nbu.cn.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 通常用的比较少，主要用于jvm
 */
public class PhantomReferenceDemo {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue  referenceQueue = new ReferenceQueue();

    public static void main(String[] args) {
         PhantomReference<M> phantomReference = new PhantomReference<>(new M(),referenceQueue);

    }
}
