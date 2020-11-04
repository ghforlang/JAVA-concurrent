package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * lamda 改造
 */
public class DisruptorDemo3 {
    private static StringEventFactory factory = new StringEventFactory();
    private static int bufferSize = 1024 * 1024;
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static WaitStrategy waitStrategy = new SleepingWaitStrategy();

    public static void main(String[] args) {
        Disruptor<StringEvent> disruptor = new Disruptor(StringEvent::new,bufferSize,threadFactory, ProducerType.SINGLE,waitStrategy);
        disruptor.handleEventsWith((event,sequence,endOfBatch) ->{
            System.out.println(Thread.currentThread().getName() + "-" + event.toString());
        });

        disruptor.start();
        disruptor.publishEvent((event,sequence) ->{
            event.setValue("test");
        });
        //便捷方法，减少用户代码量
        disruptor.shutdown();
    }
}
