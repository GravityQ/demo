package com.gravity.demo.offer;

public class Sample {

    public static void main(String[] args) {
        int i = agg(10);
        System.out.println(i);
    }

    public static int agg(int ori) {

        try {
            ori = ori + 1;
            return ori;
        } finally {
            ori = ori + 1;
        }

    }
}
