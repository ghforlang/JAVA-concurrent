package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;

public class DisruptorDemo_MutiConsumer {
    private static StringEventFactory factory = new StringEventFactory();
    private static int bufferSize = 1024 * 1024;
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static StringEventHandler eventHandler = new StringEventHandler();
    private static WaitStrategy waitStrategy = new SleepingWaitStrategy();
    private static StringEventTranslator translator = new StringEventTranslator();

    public static void main(String[] args) {
        Disruptor<StringEvent> disruptor = new Disruptor(factory,bufferSize,threadFactory, ProducerType.MULTI,waitStrategy);

        StringEventHandler h1 = new StringEventHandler();
        StringEventHandler h2 = new StringEventHandler();
        disruptor.handleEventsWith(h1,h2);
        disruptor.start();

        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int threadNum = i;
            executor.submit(() ->{
                System.out.printf("Thread %s ready to start!\n",threadNum);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });


            for(int j=0;j<100;j++){
                ringBuffer.publishEvent((event,value) ->{
                    event.setValue(threadNum + "");
                    System.out.println("生产了" + threadNum);
                });
            }
        }

        executor.shutdown();;
        //便捷方法，减少用户代码量
        disruptor.shutdown();
    }
}
