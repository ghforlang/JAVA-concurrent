package com.edu.nbu.cn.utils.countdownlatch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class WaitingWorker implements Runnable{
    private List<String> outPut;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;


    @Override
    public void run() {
        readyThreadCounter.countDown();
        System.out.println("readyThreadCounter = " + readyThreadCounter.getCount());
        try {
            callingThreadBlocker.await();
            System.out.println(Thread.currentThread().getName() + "开始执行!");
            outPut.add(Thread.currentThread().getName() + " countDown");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            completedThreadCounter.countDown();
        }
    }
}
