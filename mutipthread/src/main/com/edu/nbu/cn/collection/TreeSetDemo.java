package com.edu.nbu.cn.collection;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {

    static Set<String> treeSet = new TreeSet();

    public static void main(String[] args) {
        treeSet.add("t");
        treeSet.add("r");
        treeSet.add("e");
        treeSet.add("e");

        for(String s : treeSet){
            System.out.println(s);
        }
    }
}
