package com.edu.nbu.cn.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalDemo {

    private static TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(1);
    public static void main(String[] args) {
        parent.set("parent-value-1");
        testRunnable();
        testCallable();
        executor.shutdownNow();
        System.out.println("is shutdown : " + executor.isShutdown());
    }

    private static void  testRunnable(){
        Runnable task = new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + " : " + parent.get());
        },"t1");

        TtlRunnable ttlRunnable = TtlRunnable.get(task);
        executor.submit(ttlRunnable);
    }

    private static  void  testCallable(){
        Callable task = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " : " + parent.get());
                return parent.get();
            }
        };
        TtlCallable ttlCallable = TtlCallable.get(task);
        executor.submit(ttlCallable);
    }
}
