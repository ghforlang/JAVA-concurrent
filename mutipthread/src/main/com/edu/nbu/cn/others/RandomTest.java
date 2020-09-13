package com.edu.nbu.cn.others;

import java.util.Random;

public class RandomTest {
    //随机种子只是随机算法的起源数字；为空默认是当前系统时间戳；种子相同的Random对象，生成的随机数序列是一样的；
    private static final Random r1 = new Random(1000);
    private static final Random r2 = new Random();

    public static void main(String[] args) {
        testRandom1000();
        System.out.println("*************************");
//        testRandomInt();
        System.out.println("*************************");
//        testMathRandom();
    }

    public static void testRandom1000(){
        for (int i = 0; i < 10; i++) {
            System.out.println(r1.nextInt(10));
        }
    }

    public static void testRandomInt(){
        for (int i = 0; i <10 ; i++) {
            System.out.println(r2.nextInt(1000));
        }
    }

    public static void testMathRandom(){
        for (int i = 0; i <10 ; i++) {
            System.out.println(Math.random());
        }
    }
}
