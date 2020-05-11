package com.edu.nbu.cn.utils.cyclicbarrier;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;


@AllArgsConstructor
public class Gamers implements Runnable{
    private String name;
    private CyclicBarrier barrier;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(5)));
            System.out.println(name + " 开始加载 --------------等待其他玩家加载成功!");
            barrier.await();
            System.out.println(name + " 加载成功 --------------全部玩家加载成功，比赛开始!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
