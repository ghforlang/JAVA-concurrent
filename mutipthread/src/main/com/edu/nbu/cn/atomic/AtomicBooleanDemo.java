package com.edu.nbu.cn.atomic;

import com.edu.nbu.cn.thread.BooleanDemoThread;

public class AtomicBooleanDemo {

    public static void main(String[] args) {
        BooleanDemoThread c1 = new BooleanDemoThread("c1");
        c1.start();
        BooleanDemoThread c2 = new BooleanDemoThread("c2");
        c2.start();
    }



}
