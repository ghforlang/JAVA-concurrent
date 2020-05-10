package com.edu.nbu.cn.utils.countdownlatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://www.jianshu.com/p/4de503edc819
public class LotsOfThreadInParallel {
    //强制数千个线程尝试并行执行某些逻辑
    public static void main(String[] args) throws InterruptedException {
        LotsOfThreadInParallel lotip = new LotsOfThreadInParallel();
        lotip.testLotsOfThreadInParallel();
    }

    public void testLotsOfThreadInParallel() throws InterruptedException {
        List<String> outputs = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);

        List<Thread> workers = Stream.generate(() -> new Thread(new WaitingWorker(outputs,readyThreadCounter,callingThreadBlocker,completedThreadCounter))).limit(5).collect(Collectors.toList());
        workers.forEach(Thread::start);
        //Causes the current thread to wait until the latch has counted down to Zero
        //主线程挂起，直至count down值为0,保证所有线程已准备就绪
        readyThreadCounter.await();
        //防止线程异常，可采用带超时时间的await方法 readyThreadCounter.await(3000,TimeUnit.MILLISECONDS);
        System.out.println(readyThreadCounter.getCount());;
        outputs.add("workers ready");
        //多个线程同时被唤醒
        callingThreadBlocker.countDown();

        //保证所有线程都执行完毕
        completedThreadCounter.await();
        outputs.add("workers completed");
        outputs.forEach(v -> System.out.println(v));
    }
}
