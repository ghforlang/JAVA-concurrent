package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;

public class DisruptorDemo_ExceptionHandler {
    private static StringEventFactory factory = new StringEventFactory();
    private static int bufferSize = 1024 * 1024;
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static WaitStrategy waitStrategy = new SleepingWaitStrategy();

    public static void main(String[] args) {
        Disruptor<StringEvent> disruptor = new Disruptor(factory,bufferSize,threadFactory, ProducerType.MULTI,waitStrategy);

        EventHandler<StringEvent> eventHandler = (event, l, endOfBatch) ->{
            throw new Exception("消费者异常");
        };
        disruptor.handleEventsWith(eventHandler);
        disruptor.handleExceptionsFor(eventHandler).with(new ExceptionHandler<StringEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, StringEvent event) {
                System.out.println("handleEventException " + event.toString() + "\n");
                ex.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("handleOnStartException" + ex.getCause());
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("handleOnShutdownException" + ex.getCause());
            }
        });

        disruptor.start();
        disruptor.publishEvent((event,sequence) ->{
            event.setValue("test");
        });

        //便捷方法，减少用户代码量
        disruptor.shutdown();
    }
}
