package com.edu.nbu.cn.reference;

import java.lang.ref.SoftReference;

/**
 * 通常用于缓存，堆内存不够用则回收SoftReference
 */
public class SoftReferenceDemo {

    /**
     * 配合jvm参数 -Xms10m -Xmx10m
     * @param args
     */
    public static void main(String[] args) {
        SoftReference reference = new SoftReference<>(new byte[1024*1024*5]);

        System.out.println(reference.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(reference.get());

        //超过6M 就报OOM
        byte[] b = new byte[1024 * 1024 * 6];
        // 超过堆内存设定大小，SoftReference被回收
        System.out.println(reference.get());

    }
}
