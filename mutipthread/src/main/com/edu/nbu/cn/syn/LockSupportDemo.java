package com.edu.nbu.cn.syn;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    // park 等待许可，unpark获得许可
    // 一次性许可
    // unpark与park顺序不定,即，可以先park后unpark，也可以先unpark后park
    //linux环境下，利用Posix线程库pthread中的mutex（互斥量）,condition（条件变量）来实现;mutex和condition保护一个_counter的变量，park时，变量被置0，unpark时，变量被置1
    //与wait、notify区别：
    // 面向主体不一样，lockSupport针对Thread,可指定具体的线程唤醒；wait则是阻塞当前线程，notify则是随机唤醒一个线程或者唤醒所有线程
    // 实现机制不一样
    public static void main(String[] args) {
        LockSupportThread lockThread = new LockSupportThread();
        lockThread.start();
        OtherThread otherThread = new OtherThread(lockThread);
        otherThread.start();
    }
}

class LockSupportThread extends Thread{
    @Override
    public void run() {
        System.out.println("LockSupportThread is parking！");
        //默认无限等待，直至获取许可
        LockSupport.park();
        System.out.println("LockSupportThread is unparking");
    }
}

class OtherThread extends Thread{
    private Object obj;
    public OtherThread(Object obj){
        this.obj = obj;
    }
    @Override
    public void run() {
        System.out.println("before unpark");
        LockSupport.unpark((Thread)obj);
        System.out.println("afer unpark");
    }
}