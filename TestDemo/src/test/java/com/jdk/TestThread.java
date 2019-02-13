package com.jdk;

import sun.plugin2.liveconnect.JSExceptions;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

public class TestThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//测试Callable
//        Callable<String> callable = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                Thread.sleep(2000);
//                return "hello Thread!";
//            }
//        };
//        FutureTask<String> task = new FutureTask<>(callable);
//        Thread thread = new Thread(task);
//        thread.start();
//        System.out.println(task.get());//task.get()会等待任务执行完毕

//        测试Daemon
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        thread.setDaemon(true);//添加这个代码后，解决了thread死循环造成的程序死循环
//        thread.start();
//        //main() 属于非守护线程。使用 setDaemon() 方法将一个线程设置为守护线程。
//        //当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
//        System.out.println("main 执行完毕");

//        线程中断
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("子线程开始执行");
//                    Thread.sleep(2000);
//                    System.out.println("子线程执行完毕");
//                } catch (InterruptedException e) {
//                    System.out.println("发生中断");
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                boolean interrupted = interrupted();//默认是false，发送中断后会置为true
//                while (!interrupted()) {//由于boolean是基本类型，interrupted不会更新
//                    System.out.println("子线程开始执行");
//                    System.out.println("子线程执行完毕");
//                }
//                System.out.println("中断结束子线程2");
//            }
//        };
////        thread.start();
//        thread2.start();
////        Thread.currentThread().interrupt();//不起作用，线程不能中断自己
//        System.out.println("main 执行完毕");
////        thread.interrupt();//中断子线程的睡眠
//        Thread.sleep(2000);
//        thread2.interrupt();//如果没有sleep/wait等方法，则无法中断；只能修改中断标识位

////        线程池中断
//        ExecutorService executorService = Executors.newCachedThreadPool();
////        executorService.execute(()->{
////            try {
////                    System.out.println("子线程开始执行");
////                    Thread.sleep(2000);
////                    System.out.println("子线程执行完毕");
////                } catch (InterruptedException e) {
////                    System.out.println("发生中断");
////                    e.printStackTrace();
////                }
////        });
////        executorService.execute(()->{
////                while (!interrupted()) {//由于boolean是基本类型，interrupted不会更新
////                    System.out.println("子线程开始执行");
////                    System.out.println("子线程执行完毕");
////                }
////                System.out.println("中断结束子线程2");
////        });
//        Future<?> submit = executorService.submit(() -> {
//            int i = 0;
//            try {
//                while (i < 100) {//由于boolean是基本类型，interrupted不会更新
//                    System.out.println("子线程开始执行");
//                    System.out.println("子线程执行完毕");
//
//                    Thread.sleep(100);
//                    System.out.print(i);
//                    i++;
//                }
//            } catch (InterruptedException e) {
//                System.out.println("submit提交的任务，可以由cancle进行中断");
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(1000);
//        submit.cancel(true);//中断submit提交的任务
//
////        executorService.shutdownNow();
//        executorService.shutdown();//等所有线程执行完毕后关闭
//// 如果不执行shutdown，executorService会一直执行，进程不会结束


//        测试join(付款业务)
//        Thread thread = new Thread(() -> {
//            try {
//                    Thread.sleep(1000);
//                    System.out.println("付款成功");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println("开始支付订单!");
//        thread.start();
//        thread.join(2000);//使用线程的isAlive()方法判断是否执行完毕
//        System.out.println("订单支付完成");

////        测试wait  notify 与 await signal
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        PayHelper2 payHelper = new PayHelper2();
//        final String payWay = "aliPay";
//        //发起支付请求
//        Future<Boolean> future = executorService.submit(() -> {
//            boolean b = false;
//            try {
//                b = payHelper.payRequest("aliPay");//会挂起当前执行的线程
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return b;
//        });
//        Future<Boolean> future1 = executorService.submit(() -> {
//            boolean b = false;
//            try {
//                b = payHelper.payRequest("weChat");//会挂起当前执行的线程
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return b;
//        });
//        Thread.sleep(100);
//        payHelper.payOrder(payWay);
//        executorService.shutdown();
//        System.out.println("支付宝支付状态：" + future.get());
//        System.out.println("微信支付状态：" + future1.get());


//        //yield() setProprity
//        Thread thread = new Thread("lowProprity") {
//            @Override
//            public void run() {
//                try {
//                    setPriority(3);//1-10之间，值越大，优先级越高
//                    System.out.println(Thread.currentThread().getName()+"子线程开始执行");
//                    //提醒调度器当前线程可以将它的CPU时间片让位于其他线程，但是调度器不一定会接受这种提醒。
//                    //很少有需要用到yield()的业务场景，它主要用在调试和测试用途，比如条件竞争的BUG重现方面。
//                    yield();
////                    Thread.sleep(0);
//                    System.out.println(Thread.currentThread().getName()+"子线程执行完毕");
//                } catch (Exception e) {
//                    System.out.println("发生中断");
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        Thread thread2 = new Thread("highProprity") {
//            @Override
//            public void run() {
//                setPriority(4);
//                System.out.println(Thread.currentThread().getName()+"开始执行");
//                int i=0;
//                while (i<10000){
//                    i++;
//                }
//                System.out.println(Thread.currentThread().getName()+"子线程执行完毕");
//            }
//        };
//        thread.start();
//        thread2.start();
//        System.out.println("main 执行完毕");
//        Thread.sleep(2000);


//        //交替打印奇偶数
//        PrintNum printNum = new PrintNum();
//        ExecutorService service = Executors.newCachedThreadPool();
//        service.execute(()->{
//            try {
//                printNum.printEven();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        service.execute(()->{
//            try {
//                printNum.printOdd();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        service.shutdown();
//        System.out.println("结束任务");


//        测试管道通信

        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        try {
            reader.connect(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread sender = new Thread(() -> {
            try {
                System.out.println("发送信息");
                writer.write("hello world form writer!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("发送完毕，关闭writer");
                    writer.flush();
                    ;
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread receiver = new Thread(() -> {
            System.out.println("reader开始读取");
            char[] chars = new char[100];
            StringBuilder stringBuilder = new StringBuilder();
            try {
                int read = 0;
                do {
                    read = reader.read(chars);
                    if (read > 0) {
                        stringBuilder.append(chars);
                    }
                } while (read != -1);
                System.out.println("reader读取信息："+stringBuilder);
            } catch (IOException e) {
                try {
                    System.out.println("关闭reader");
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        });

        receiver.start();
        sender.start();
        System.out.println("执行完毕");

    }

    //使用Sychronized语句块进行同步
    static class PayHelper {
        boolean aliPayResult = false;
        boolean weChatPayResult = false;
        boolean payHandle = false;

        synchronized boolean payRequest(String payWay) throws InterruptedException {
            System.out.println("发起支付" + payWay + "请求");
            wait();//释放对象锁
//            sleep(10000); //不会释放object的对象锁，该对象的所有操作都不能执行。
            if (payHandle) {
                System.out.println("支付" + payWay + "完成");
                return "aliPay".equals(payWay) ? aliPayResult : weChatPayResult;
            } else {
                System.out.println("支付" + payWay + "失败");
                return false;
            }

        }

        synchronized void payOrder(String payWay) {
            System.out.println("调用" + payWay + "支付接口");
            payHandle = true;
            if ("aliPay".equals(payWay)) {
                aliPayResult = true;
            } else {
                weChatPayResult = true;
            }
            System.out.println(payWay + "支付接口调用完成");
//            notify();//随机通知一个wait()的线程
            notifyAll();//通知所有等待该对象的线程
        }

        void testLock() {
            synchronized (this) {
                System.out.println("invoke testLock");
            }
        }
//        与上者等价，只有加了synchronized，执行时才会判断锁的状态。
//        没有加synchronized，随便执行，即使没有获取到锁
//       synchronized void testLock(){
//            synchronized (this){
//                System.out.println("invoke testLock");
//            }
//        }
    }

    //使用ReentrantLock进行同步
    static class PayHelper2 {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        boolean aliPayResult = false;
        boolean weChatPayResult = false;
        boolean payHandle = false;

        boolean payRequest(String payWay) throws InterruptedException {
            lock.lock();
            try {
                System.out.println("发起支付" + payWay + "请求");
                condition.await();//等同于object的wait()
                if (payHandle) {
                    System.out.println("支付" + payWay + "完成");
                    return "aliPay".equals(payWay) ? aliPayResult : weChatPayResult;
                } else {
                    System.out.println("支付" + payWay + "失败");
                    return false;
                }
            } finally {
                lock.unlock();
            }
        }

        void payOrder(String payWay) {
            lock.lock();
            try {
                System.out.println("调用" + payWay + "支付接口");
                payHandle = true;
                if ("aliPay".equals(payWay)) {
                    aliPayResult = true;
                } else {
                    weChatPayResult = true;
                }
                System.out.println(payWay + "支付接口调用完成");
                condition.signalAll();//等同于Object的notifyAll()
            } finally {
                lock.unlock();
            }
        }
    }

    static class PrintNum {
        int num = 0;

        public synchronized void printOdd() throws InterruptedException {
            while (num<100000)
            if (num % 2 != 0) {
                System.out.println("printOdd"+num);
                num++;
                notify();
            } else {
                wait();
            }
        }

        public synchronized void printEven() throws InterruptedException {
            while (num<100000)
            if (num % 2 == 0) {
                System.out.println("printEven:"+num);
                num++;
                notify();
            } else {
                wait();
            }

        }

    }
}
