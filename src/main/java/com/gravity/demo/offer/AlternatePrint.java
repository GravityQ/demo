package com.gravity.demo.offer;

/**
 * 两个线程交替打印
 *
 * @author qijunlin
 * @date 2020/9/19 4:36 下午
 */

public class AlternatePrint {

    public Object lock = new Object();
    public volatile boolean adone = false;
    public int index = 1;

    class A implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (adone) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("A:" + index);
                    adone = true;
                    index++;
                    lock.notifyAll();
                }
            }

        }
    }

    class B implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (!adone) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B:" + index);
                    adone = false;
                    index++;
                    lock.notifyAll();
                }
            }

        }
    }

    public static void main(String[] args) {
        AlternatePrint print = new AlternatePrint();
        Thread threadA = new Thread(print.new A());
        Thread threadB = new Thread(print.new B());
        threadA.start();
        threadB.start();
    }
}
