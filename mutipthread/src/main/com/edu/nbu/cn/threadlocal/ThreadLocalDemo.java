package com.edu.nbu.cn.threadlocal;

public class ThreadLocalDemo {
    //ThreadLocal与InheritableThreadLocal区别
    private static final ThreadLocal<String> INHERIT_THREAD_LOCAL = new InheritableThreadLocal();
    private static final ThreadLocal<String> STR_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<String> STRING_THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        STR_THREAD_LOCAL.set("str_value_2");
//        INHERIT_THREAD_LOCAL.set("inheritable_value_1");
        STRING_THREAD_LOCAL.set("parent_value_2");
//        testThreadLocal();
//        testInheritableThreadLocal();
    }

    private static void testThreadLocal(){
        new Thread(() ->{
            System.out.println(STRING_THREAD_LOCAL.get());
        },"child1").start();
    }


    private static void testInheritableThreadLocal(){
        new Thread(() ->{
            System.out.println(INHERIT_THREAD_LOCAL.get());
        },"child2").start();
    }
}
