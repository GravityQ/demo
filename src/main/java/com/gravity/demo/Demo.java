package com.gravity.demo;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qijunlin
 * @date 2019/9/29 3:20 下午
 */

public class Demo {
//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(() -> {
//                try {
//                    Thread.sleep(100);
//                    if (Counter.addOne() == 100) {
//                        System.out.println("counter = 100");
//                    }else {
//                        System.out.println(Counter.counter);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            thread.start();
//        }
//        AtomicLong atomicLong = new AtomicLong();
//        long counter = atomicLong.incrementAndGet();
//        System.out.println(counter);
//    }

    public static class Counter {
        private static long counter = 0;

        public static long addOne() {
            return ++counter;
        }
    }
    public static void main(String[] args) {
        System.out.println(new Date());
    }
}
