package com.edu.nbu.cn;

import com.edu.nbu.cn.thread.CcThread;

public class MainClass {
    public static void main(String[] args) {
        Thread c1 = new Thread(new CcThread("c1"));
        c1.start();
        Thread c2 = new Thread(new CcThread("c2"));
        c2.start();
    }
}
