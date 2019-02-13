package com.jdk;

import java.util.concurrent.*;

public class TestJUC {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //CountDownLatch测试
//        int totalThread = 10;
//        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < totalThread; i++) {
//            executorService.execute(() -> {
//                System.out.print("run..");
//                countDownLatch.countDown();
//            });
//        }
//        countDownLatch.await();
//        System.out.println("end");
//        executorService.shutdown();
        //测试CyclicBarrier
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//
//        for (int i = 0; i < 10; i++) {
//            final Integer integer = i;
//            executorService.execute(() -> {
//                System.out.println("before" + integer);
//                try {
//                    cyclicBarrier.await();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println("after" +integer);
//            });
//        }
//        executorService.shutdown();

        //测试Semaphore
//        int clientCount = 3;
//        int totalRequestCount = 10;
//        Semaphore semaphore = new Semaphore(clientCount);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final Integer in = i;
//            executorService.execute(() -> {
//                try {
//                    System.out.println("begin" + in);
//                    semaphore.acquire();
//                    System.out.println("available:"+in+"  "+semaphore.availablePermits() + " ");
//                    System.out.println("after" + in);
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    semaphore.release();
//                    System.out.println("finish"+in);
//                }
//            });
//        }
//        executorService.shutdown();

        //FutureTask测试1：同一个FutureTask对象不能执行两次
//        FutureTask<String> futureTask = new FutureTask<>(() -> {
//            System.out.println("exec callback1");
//            return "leo";
//        });
//        try {
//            futureTask.run();
//            String s = futureTask.get();
//            System.out.println(" first:" + s);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//
//        ExecutorService service = Executors.newFixedThreadPool(1);
//        Future<?> future = service.submit(() -> {
//            System.out.println("exec callback2");
//            return "BING";
//        });
//        Object o = null;
//        try {
//            o = future.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("second:" + o);
//        service.shutdown();

        //FutureTask测试2：  FutureTask的get()方法会等到FutureTask执行完毕
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int result = 0;
//                for (int i = 0; i < 100; i++) {
//                    Thread.sleep(100);
//                    result += i;
//                }
//                return result;
//            }
//        });
//
//        Thread computeThread = new Thread(futureTask);
//        computeThread.start();
//        //等同于
////         futureTask.run();
//
//        Thread otherThread = new Thread(() -> {
//            System.out.println("other task is running...");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        otherThread.start();
//
//        //futureTask.run(); 之后才能get到数据
//        try {
//            System.out.println(futureTask.get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        BlockingQueue测试
//       final  ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);
//        class Producer extends Thread {
//            int i;
//            public Producer(int i) {
//                this.i = i;
//            }
//            @Override
//            public void run() {
//                try {
//                    blockingQueue.put("product"+i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("produce..  "+ i);
//            }
//        }
//        class Consumer extends Thread {
//            @Override
//            public void run() {
//                String product="";
//                try {
//                    product = blockingQueue.take();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("consume..  " +product);
//            }
//        }
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        for (int i=0;i<3;i++){
//            service.execute(new Producer(i));
//        }
//        for (int i=0;i<5;i++){
//            service.execute(new Consumer());
//        }
//        for (int i=0;i<2;i++){
//            service.execute(new Producer(i+3));
//        }
//        service.shutdown();

        //ForkJoin测试
//        ForkJoinExample example = new ForkJoinExample(1, 10000);
//        ForkJoinPool joinPool = new ForkJoinPool();
//        ForkJoinTask<Integer> joinTask = joinPool.submit(example);
////        joinPool.execute(example);//不适用execute方法，因为其没有返回值。
//        Integer integer = joinTask.get();
//        System.out.println("result: "+ integer);

        //使用CountDownLatch实现join的效果，等待所有
//        int thread = 3 ;
//        long start = System.currentTimeMillis();
//        final CountDownLatch countDown = new CountDownLatch(thread);
//        for (int i= 0 ;i<thread ; i++){
//            new Thread(()->{
//                    System.out.println(Thread.currentThread().getName()+"thread run");
//                    try {
//                        Thread.sleep(2000);
//                        countDown.countDown();
//                        System.out.println(Thread.currentThread().getName()+"thread end");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//            }).start();
//        }
//        countDown.await();
//        long stop = System.currentTimeMillis();
//        System.out.println("main over total time={}"+(stop-start));

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3) ;

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"thread run");
                try {
                    cyclicBarrier.await() ;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"thread end do something");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"thread run");
                try {
                    cyclicBarrier.await() ;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"thread end do something");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"thread run");
                try {
                    Thread.sleep(5000);
                    cyclicBarrier.await() ;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"thread end do something");
            }
        }).start();

        System.out.println(Thread.currentThread().getName()+"main thread");
    }

    static class ForkJoinExample extends RecursiveTask<Integer>{
        int threshold =5;
        int first;
        int last;

        public ForkJoinExample(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        protected Integer compute() {
            int result=0;
            if(last-first<threshold){
                for(int i=first;i<=last;i++){
                    result+=i;
                }
            }
            else {
                int middle=first+(last-first)/2;
                ForkJoinExample example1 = new ForkJoinExample(first, middle);
                ForkJoinExample example2 = new ForkJoinExample(middle+1,last );
                example1.fork();
                example2.fork();
                result=example1.join()+example2.join();
            }
            return result;
        }
    }


}
