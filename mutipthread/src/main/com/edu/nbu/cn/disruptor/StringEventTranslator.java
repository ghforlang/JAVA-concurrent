package com.edu.nbu.cn.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

public class StringEventTranslator implements EventTranslatorOneArg<StringEvent,String> {

    @Override
    public void translateTo(StringEvent stringEvent, long l, String s) {
        stringEvent.setValue(s);
    }

}
