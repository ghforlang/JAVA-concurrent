package com.edu.nbu.cn.others;

public class SafePoint {
    private int x,y;


    public static void main(String[] args) {
        SafePoint sp = new SafePoint(1,2);

        //拷贝函数
        SafePoint sp1 = new SafePoint(sp.get());
    }


    /**
     * 私有拷贝函数，避免竞态条件，这是私有构造函数捕获模式
     * @param a
     */
    private SafePoint(int[] a){
        this(a[0],a[1]);
    }

    public SafePoint(SafePoint p){
        this(p.get());
    }

    public SafePoint(int x,int y){
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get(){
        return new int[]{x,y};
    }

    public synchronized void set(int x,int y){
        this.x = x;
        this.y = y;
    }

}
