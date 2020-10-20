package com.edu.nbu.cn.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 通常用的比较少，主要用于jvm或者用于堆外内存回收
 */
public class PhantomReferenceDemo {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue  referenceQueue = new ReferenceQueue();

    public static void main(String[] args) {
         PhantomReference<M> phantomReference = new PhantomReference<>(new M(),referenceQueue);

         new Thread(() ->{
             while (true){
                 LIST.add(new byte[1024][1024]);
                 try {
                     Thread.sleep(200L);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println(phantomReference.get());
             }
         }).start();

         new Thread(() ->{
             while(true){
                 //用于进行通知,有值说明某一虚引用被回收
                 Reference<? extends M> poll = referenceQueue.poll();
                 if(poll != null){
                     System.out.println("----虚引用对象被jvm回收----");
                 }
             }
         }).start();
    }
}
