package com.edu.nbu.cn;

import com.edu.nbu.cn.thread.CcThread;


//ABA存在的风险 ：https://blog.csdn.net/u012813201/article/details/72841801
public class MainClass {
    public static void main(String[] args) {
//        Thread c1 = new Thread(new CcThread("c1"));
//        c1.start();
//        Thread c2 = new Thread(new CcThread("c2"));
//        c2.start();
        shiftOperationTest();
    }

    public static  void shiftOperationTest(){
        int a = 1,b=2,c=3,d=4;
        System.out.println("a = " + a + ",b=" + b + ",c=" + c + ",d=" + d);
        a = a <<1;
        b = 1<<b;
        c = -1 << c; //位移运算符优先级高于算数运算符
        d = 1 << d;
        int e = 0;
        e =  e << 29;
         System.out.println("a = " + a + ",b=" + b + ",c=" + c + ",d=" + d + ",e=" + e);
    }

}
