package com.edu.nbu.cn.utils;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 阶段器，用于解决控制多个线程分阶段共同 完成任务的场景
 */
public class PhaserDemo {
     static MarriagePhaser phaser = new MarriagePhaser();
     static Random random = new Random();

    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for(int i=0;i<5;i++){
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser{

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch(phase){
                case 0:
                    System.out.println("所有人都到齐了! " + registeredParties);
                    return false;
                case 1:
                    System.out.println("所有人都吃完了! " + registeredParties);
                    return false;
                case 2:
                    System.out.println("所有人都离开了! " + registeredParties);
                    return false;
                case 3:
                    System.out.println("新郎新娘抱抱! " + registeredParties);
                    return false;
                 default: return true;
            }
        }
    }

    static class Person implements Runnable{
        private String name;

        public Person(String name){
            this.name = name;
        }

        public void arrive(){
            milliSleep(random.nextInt(1000));
            System.out.println( name + " is arrived!");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            milliSleep(random.nextInt(1000));
            System.out.println( name + " is leaved!");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat(){
            milliSleep(random.nextInt(1000));
            System.out.println( name + " is eatiing!");
            phaser.arriveAndAwaitAdvance();
        }

        public void hug(){
            milliSleep(random.nextInt(1000));
            if(name.equals("新郎") || name.equals("新娘")){
                System.out.println( name + " is hug!");
                phaser.arriveAndAwaitAdvance();
            }else{
                phaser.arriveAndDeregister();
            }
        }

        private  void milliSleep(int milliTime){
            try {
                TimeUnit.MILLISECONDS.sleep((long)milliTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

}
