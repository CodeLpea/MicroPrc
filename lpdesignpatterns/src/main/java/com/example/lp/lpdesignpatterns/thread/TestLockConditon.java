package com.example.lp.lpdesignpatterns.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 *Lock,Condition同步锁的使用
 * 可以搭配形成生产者，消费者
* */
public class TestLockConditon {

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();
        final boolean[] status = {true};

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + "拿到锁了");
                        try {
                            if (status[0]) {
                                System.out.println(Thread.currentThread().getName() + "等待信号");
                                condition.await();
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        status[0] = false;
                        condition.signal();
                        System.out.println(Thread.currentThread().getName() + "拿到信号11111111111");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }


            }
        }, "线程1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {

                    System.out.println(Thread.currentThread().getName() + "拿到锁了");
                    if (!status[0]) {
                        System.out.println(Thread.currentThread().getName() + "等待信号");
                        condition.await();
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    status[0] = true;
                    condition.signal();
                    System.out.println(Thread.currentThread().getName() + "发出信号22222222222222");
                } catch (Exception e) {
                } finally {
                    reentrantLock.unlock();
                }

            }
        }, "线程2").start();
    }

}
