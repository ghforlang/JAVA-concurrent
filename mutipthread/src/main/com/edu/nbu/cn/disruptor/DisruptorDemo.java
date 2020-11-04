package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DisruptorDemo {

    private static StringEventFactory factory = new StringEventFactory();
    private static int bufferSize = 1024 * 1024;
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static WaitStrategy waitStrategy = new SleepingWaitStrategy();
    private static StringEventHandler eventHandler = new StringEventHandler();

    private static StringEventTranslator translator = new StringEventTranslator();

    public static void main(String[] args) {
        Disruptor<StringEvent> disruptor = new Disruptor<StringEvent>(factory,bufferSize,threadFactory);
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();

        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();

        try{
            StringEvent event = ringBuffer.get(sequence);
            event.setValue("test");
        }finally {
            //或者通过publishEvent的方式发布事件
            ringBuffer.publish(sequence);
            disruptor.shutdown();
        }

    }

    public static void publishEvent(Disruptor<StringEvent> disruptor){
        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(translator,"test");
    }


}
