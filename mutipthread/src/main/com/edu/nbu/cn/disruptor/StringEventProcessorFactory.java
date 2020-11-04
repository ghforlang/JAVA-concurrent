package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.dsl.EventProcessorFactory;

public class StringEventProcessorFactory implements EventProcessorFactory {

    @Override
    public EventProcessor createEventProcessor(RingBuffer ringBuffer, Sequence[] sequences) {
        return new StringEventProcessor();
    }
}
