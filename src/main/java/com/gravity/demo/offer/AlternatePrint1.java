package com.gravity.demo.offer;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition
 * 两个线程交替打印
 *
 * @author qijunlin
 * @date 2020/9/19 4:36 下午
 */

public class AlternatePrint1 {
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    int index = 1;

    class A implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                lock.lock();
                System.out.println("A:" + index);
                index++;
                conditionB.signal();
                conditionA.await();
                lock.unlock();
            }
        }

    }

    class B implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                lock.lock();
                System.out.println("B:" + index);
                index++;
                conditionA.signal();
                conditionB.await();
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        AlternatePrint1 print = new AlternatePrint1();
        Thread threadA = new Thread(print.new A());
        Thread threadB = new Thread(print.new B());
        threadA.start();
        threadB.start();
    }
}
