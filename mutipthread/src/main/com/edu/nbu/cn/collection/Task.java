package com.edu.nbu.cn.collection;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed {

    private String name;
    private Long delayTime;

    Task(String name,Long delayTime){
        this.name = name;
        this.delayTime = delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
            return -1;
        }else if(getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", delayTime=" + delayTime +
                '}';
    }
}
