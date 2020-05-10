package com.edu.nbu.cn.atomic;

import lombok.AllArgsConstructor;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterDemo {

    private static  AtomicReferenceFieldUpdater<Dog,Integer> updater =  AtomicReferenceFieldUpdater.newUpdater(Dog.class,Integer.class,"age");

    public static void main(String[] args) {
        Dog dog = new Dog(100,"张三");
        System.out.println(updater.compareAndSet(dog,100,200));
        System.out.println(updater.get(dog));

        int result = updater.accumulateAndGet(dog,100,(x,y) ->x*y);
        System.out.println(result);
        System.out.println(updater.get(dog));


    }

    @AllArgsConstructor
    static class Dog{
        volatile Integer age;
        String name;
    }

}
