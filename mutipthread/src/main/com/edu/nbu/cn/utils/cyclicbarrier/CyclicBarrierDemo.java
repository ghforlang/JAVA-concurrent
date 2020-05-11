package com.edu.nbu.cn.utils.cyclicbarrier;

import lombok.AllArgsConstructor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int threadNum = 5;
        //threadNum -参与的线程总数，barrierAction - 最后到达的线程要做的任务
        //适用于一组线程等待所有线程完成任务后再继续执行下一次任务;使用场景，多线程计算数据，最后合并计算结果
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务!");
            }
        });

        for(int i= 0;i<threadNum ; i++){
            new CbThread(barrier).start();
        }
    }


    @AllArgsConstructor
    static class CbThread extends Thread{
        CyclicBarrier barrier;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");

                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
