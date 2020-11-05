package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.EventHandler;

public class StringEventHandler implements EventHandler<StringEvent> {

    @Override
    public void onEvent(StringEvent stringEvent, long l, boolean b) throws Exception {
        System.out.println( " has bean handled by" + "[" +  Thread.currentThread().getName() + "]" + l);
    }
}
