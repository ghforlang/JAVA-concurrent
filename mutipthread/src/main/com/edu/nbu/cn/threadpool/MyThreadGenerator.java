package com.edu.nbu.cn.threadpool;

import com.edu.nbu.cn.thread.MyThread;

import java.util.ArrayList;
import java.util.List;

public class MyThreadGenerator {

    public static List<MyThread> generateThreads(int n){
        List<MyThread> threadList = new ArrayList<>();
        while(n-- > 0){
            threadList.add(new MyThread("t" + n));
        }
        return threadList;
    }
}
