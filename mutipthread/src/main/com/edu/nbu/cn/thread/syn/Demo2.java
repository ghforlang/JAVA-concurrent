package com.edu.nbu.cn.thread.syn;

/**
 * 有序性：写方法加锁，读方法不加锁;这里用volatile不行，只保证可见性，不保证顺序性
 */
public class Demo2 {
    private String name;
    private /*volatile */int account;

    public synchronized void set(String name ,int account) throws InterruptedException {
        this.name = name;
        Thread.sleep(2000L);
        this.account = account;
        System.out.println(name + ":" + account);
    }


    /**
     * 业务不需要严格同步，或者允许脏读，可以不加锁(能不加锁就不要加锁)
     * @param name
     * @return
     */
    public /*synchronized */ int get(String name){
        System.out.println(account);
        return account;
    }

    public static void main(String[] args) throws InterruptedException {
        Demo2 d = new Demo2();

        new Thread(() -> {
            try {
                d.set("张三",100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> d.get("张三")).start();
    }
}
