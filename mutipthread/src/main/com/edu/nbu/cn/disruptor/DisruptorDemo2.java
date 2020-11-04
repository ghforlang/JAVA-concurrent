package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * translator
 */
public class DisruptorDemo2 {
    private static StringEventFactory factory = new StringEventFactory();
    private static int bufferSize = 1024 * 1024;
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static StringEventHandler eventHandler = new StringEventHandler();
    private static WaitStrategy waitStrategy = new SleepingWaitStrategy();
    private static StringEventTranslator translator = new StringEventTranslator();

    public static void main(String[] args) {
        Disruptor<StringEvent> disruptor = new Disruptor(factory,bufferSize,threadFactory, ProducerType.SINGLE,waitStrategy);
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();

        //便捷方法，减少用户代码量
        publishEvent(disruptor);
        disruptor.shutdown();
    }

    public static void publishEvent(Disruptor<StringEvent> disruptor){
        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(translator,"test");
    }
}
