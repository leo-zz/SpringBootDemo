package com.jdk;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class TestLock {

    //不加锁
    public void func0() {
        for (int i = 0; i < 1000; i++) {
            System.out.print(i + " ");
        }
    }

    public void func1() {
        //锁定当前对象
        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
                System.out.print(i + " ");
            }
        }
    }

    //等价于func1，锁定当前对象
    public synchronized void func2() {
        for (int i = 0; i < 1000; i++) {
            System.out.print(i + " ");
        }
    }

    //锁定当前类，所有类下的对象都同步
    public void func3() {
        synchronized (TestLock.class) {
            for (int i = 0; i < 1000; i++) {
                System.out.print(i + " ");
            }
        }
    }

    //同步的静态类，所有类下的对象都同步
    public static synchronized void func4() {
        for (int i = 0; i < 1000; i++) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {

        //Test Synchronized
//        TestLock testLock1 = new TestLock();
//        TestLock testLock2 = new TestLock();
//        ExecutorService service = Executors.newCachedThreadPool();

        //不同步的情况 非线性安全
//        service.execute(() -> testLock1.func0());
//        service.execute(() -> testLock1.func0());

        //同步  线性安全
//        service.execute(() -> testLock1.func1());
//        service.execute(() -> testLock1.func1());

        //非线性安全
//        service.execute(() -> testLock1.func0());
//        service.execute(() -> testLock1.func0());

        //非线性安全
//        service.execute(() -> testLock1.func1());
//        service.execute(() -> testLock2.func1());

        //非线性安全
//        service.execute(() -> testLock1.func2());
//        service.execute(() -> testLock2.func2());

        //同步 线性安全
//        service.execute(() -> testLock1.func3());
//        service.execute(() -> testLock2.func3());

        //同步  线性安全
//        service.execute(() -> TestLock.func4());
//        service.execute(() -> testLock2.func4());


        //Test ReentrantLock
//        LockExample lockExample1 = new LockExample();
//        LockExample lockExample2 = new LockExample();
//        ExecutorService service = Executors.newFixedThreadPool(2);
//
//        //线性安全  线性安全
////        service.execute(()->lockExample1.func());
////        service.execute(()->lockExample1.func());
//
//        //非线性安全  线性安全
//        service.execute(()->lockExample1.func());
//        service.execute(()->lockExample2.func());
//
//        service.shutdown();

        //Test AtomicInteger
//        AtomicInteger atomicInteger = new AtomicInteger(5);
//        int i = atomicInteger.incrementAndGet();
//        System.out.println(i);
//        int i1 = atomicInteger.addAndGet(5);
//        System.out.println(i1);

        //Test ThreadLocal
//        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
//        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
//        ExecutorService service = Executors.newFixedThreadPool(2);
//        System.out.println("begin");
//        service.execute(() -> {
//            threadLocal1.set("hello leo!");
//            threadLocal2.set("nice to meet you!");
//            System.out.println("thred1 set");
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("threadLocal1.get(): "+threadLocal1.get());
//            System.out.println("threadLocal2.get(): "+threadLocal2.get());
//
//        });
//        service.execute(() -> {
//            threadLocal1.set("bye leo!");
//            threadLocal2.set("i'll miss you");
//            System.out.println("thred2 set");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("threadLocal1.get(): "+threadLocal1.get());
//            System.out.println("threadLocal2.get(): "+threadLocal2.get());
//        });
//        System.out.println("over");
//        service.shutdown();

//        测试ReentrantReadWriteLock
        Logger logger = Logger.getLogger(TestLock.class.getName());
        HashMap<String, String> map = new HashMap<>();
        ExecutorService service = Executors.newCachedThreadPool();

        map.put("a1","b1");
        map.put("a2","b2");

        /**The read lock may be held simultaneously by multiple reader threads,
         * so long as there are no writers. The write lock is exclusive.
        */
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        //给map加读锁，多个线程可以同时读，但是读锁释放前拿不到写锁
        service.execute(()->{
            logger.info("线程1开始读取map数据");
            readLock.lock();
            logger.info("线程1拿到map的读锁，开始读取数据");
            try {
                Thread.sleep(3000);
                String a1 = map.get("a1");
                logger.info("线程1读取到的数据为"+a1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
            logger.info("线程1释放map的读锁");
        });
        service.execute(()->{
            logger.info("线程2开始读取map数据");
            readLock.lock();
            logger.info("线程2拿到map的读锁，开始读取数据");
            try {
                Thread.sleep(3000);
                String a2 = map.get("a2");
                logger.info("线程2读取到的数据为"+a2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
            logger.info("线程2释放map的读锁");
        });


        //在读锁全部释放之前，无法获取写锁，同时只能有一个线程拿到写锁
        service.execute(()->{
            logger.info("线程3开始写入map数据");
            writeLock.lock();
            logger.info("线程3拿到map的写锁，开始写入数据");
            try {
                Thread.sleep(3000);
                map.put("a3","b3");
                logger.info("线程3写入到的数据为b3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
            logger.info("线程3释放map的写锁");
        });
        service.execute(()->{
            logger.info("线程4开始写入map数据");
            writeLock.lock();
            logger.info("线程4拿到map的写锁，开始读取数据");
            try {
                Thread.sleep(3000);
                map.put("a4","b4");
                logger.info("线程4写入的数据为b4");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
            logger.info("线程4释放map的写锁");
        });

        //写锁释放之前，无法拿到读锁；写锁全部释放之后，多个线程可以同时获取读锁。
        service.execute(()->{
            logger.info("线程5开始读取map数据");
            readLock.lock();
            logger.info("线程5拿到map的读锁，开始读取数据");
            try {
                Thread.sleep(3000);
                String a5 = map.get("a3");
                logger.info("线程5读取到的数据为"+a5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
            logger.info("线程5释放map的读锁");
        });

        service.execute(()->{
            logger.info("线程6开始读取map数据");
            readLock.lock();
            logger.info("线程6拿到map的读锁，开始读取数据");
            try {
                Thread.sleep(3000);
                String a6= map.get("a4");
                logger.info("线程6读取到的数据为"+a6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
            logger.info("线程6释放map的读锁");
        });
        service.shutdown();
    }

    static class LockExample {
        ReentrantLock lock = new ReentrantLock();

        public void func() {
            lock.lock();
            try {
                for (int i = 0; i < 1000; i++) System.out.print(i + "  ");
            } finally {
                lock.unlock();
            }
        }
    }


}


