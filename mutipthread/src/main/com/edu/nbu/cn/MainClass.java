package com.edu.nbu.cn;

import com.edu.nbu.cn.thread.CcThread;


//ABA存在的风险 ：https://blog.csdn.net/u012813201/article/details/72841801
public class MainClass {
    public static void main(String[] args) {
        Thread c1 = new Thread(new CcThread("c1"));
        c1.start();
        Thread c2 = new Thread(new CcThread("c2"));
        c2.start();
    }
}
