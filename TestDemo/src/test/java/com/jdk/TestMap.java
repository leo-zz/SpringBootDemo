package com.jdk;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class TestMap {

    private int[] testNonConcurrent(int count) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>();
        int num = 15;
        //开始put操作
        System.out.println("begin");
        long begin = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < 1000000; j++) {
                map.put("a" + i + " " + j, "b" + i + j);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("应插入条数据，15000000条，实际插入" + map.size() + "条数据");
        System.out.println("第" + count + "次插入耗时：" + (end - begin));
        //返回的int数组中，分别为该次测试执行耗时，测试执行的put操作次数。
        int[] ints = {(int) (end - begin), 15000000};
        return ints;
    }


    private int[] testHashtable(int cLevel, int count) throws InterruptedException {
        //此处修改并发度
        int concurrentLevel = cLevel;
        final Hashtable<String, String> map = new Hashtable<>();
        //并发线程的数量
        int num = concurrentLevel;
        int num2 = 15000000 / num;
        //开始并发put操作
        System.out.println("begin");
        long begin = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            final Integer integer = i;
            //jdk1.8 lambd风格
            new Thread(() -> {
                for (int j = 0; j < num2; j++) {
                    map.put("a" + integer + j, "b" + integer + j);
                }
                countDownLatch.countDown();
            }).start();
        }
        //等待所有线程完成写入操作
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("应插入条数据，" + num * num2 + "条，实际插入" + map.size() + "条数据");
        System.out.println("第" + count + "次" + num + "个线程并发执行，耗时：" + (end - begin));
        //返回的int数组中，分别为该次测试执行耗时，测试执行的put操作次数。
        int[] ints = {(int) (end - begin), num * num2};
        return ints;
    }

    private int[] testConcurrentHashMap18(int cLevel, int count) throws InterruptedException {
        //此处修改并发度
        int concurrentLevel = cLevel;
        //concurrentLevel等于segment[]的长度，initialCapacity>=concurrentLevel
        int initialCapacity = concurrentLevel;
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(initialCapacity, 0.75f, concurrentLevel);
        //此处修改并发线程的数量
        int num = concurrentLevel;
        int num2 = (1000000 * 15 / concurrentLevel);//每个线程执行put操作的次数
        //开始并发put操作
        System.out.println("begin");
        long begin = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            final Integer integer = i;
            //jdk1.8 lambd风格
            new Thread(() -> {
                for (int j = 0; j < num2; j++) {
                    map.put("a" + integer + j, "b" + integer + j);
                }
                countDownLatch.countDown();
            }).start();
        }
        //等待所有线程完成写入操作
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("应插入条数据，" + num * num2 + "条，实际插入" + map.size() + "条数据");
        System.out.println("第" + count + "次" + num + "个线程并发执行，耗时：" + (end - begin));
        //返回的int数组中，分别为该次测试执行耗时，测试执行的put操作次数。
        int[] ints = {(int) (end - begin), num * num2};
        return ints;
    }

    @Test
    public void test() throws InterruptedException {

        int time=10;//所有测试执行10次
        int totalConsume;//10次操作的总耗时
        int totalCount;//10次操作中put操作总计执行的次数
        double consumePerPut;//单次put操作耗时的平均时长

        //执行10次testNonConcurrent
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testNonConcurrent(i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("HashMap（JDK1.8）单线程处理1500万条数据的平均耗时为："+totalConsume/time
                +",单次put操作的耗时"+(consumePerPut*1000)+"微秒");


        //并发线程数为1时，执行10次testHashtable
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testHashtable(1,i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("Hashtable（JDK1.8）单线程处理约1500万条数据的put操作的平均耗时为："+totalConsume/time
                +"，单次put操作的耗时："+(consumePerPut*1000)+"微秒");


        //并发线程数为16时，执行10次testHashtable
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testHashtable(16,i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("Hashtable（JDK1.8）"+16+"个线程处理约1500万条数据的put操作的平均耗时为："+totalConsume/time
                +"，单次put操作的耗时："+(consumePerPut*1000)+"微秒");





        //并发线程数为32时，执行10次testHashtable
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testHashtable(32,i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("Hashtable（JDK1.8）"+32+"个线程处理约1500万条数据的put操作的平均耗时为："+totalConsume/time
                +"，单次put操作的耗时："+(consumePerPut*1000)+"微秒");



        //并发线程数为1时，执行10次testConcurrentHashMap18
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testConcurrentHashMap18(1,i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("ConcurrentHashMap（JDK1.8）单线程处理约1500万条数据的put操作的平均耗时为："+totalConsume/time
                +"，单次put操作的耗时："+(consumePerPut*1000)+"微秒");


        //并发线程数为16时，执行10次testConcurrentHashMap18
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testConcurrentHashMap18(16,i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("ConcurrentHashMap（JDK1.8）"+16+"个线程处理约1500万条数据的put操作的平均耗时为："+totalConsume/time
                +"，单次put操作的耗时："+(consumePerPut*1000)+"微秒");


        //并发线程数为32时，执行10次testConcurrentHashMap18
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testConcurrentHashMap18(32,i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("ConcurrentHashMap（JDK1.8）"+32+"个线程处理约1500万条数据的put操作的平均耗时为："+totalConsume/time
                +"，单次put操作的耗时："+(consumePerPut*1000)+"微秒");


        //并发线程数为64时，执行10次testConcurrentHashMap18
        totalConsume=0;
        totalCount=0;
        consumePerPut=0.0d;
        for (int i=0;i<time;i++){
            int[] ints = testConcurrentHashMap18(64,i + 1);
            totalConsume+=ints[0];
            totalCount+=ints[1];
        }
        //求这10次操作耗时的平均值，并求单次put操作耗时的平均值
        consumePerPut=(double)totalConsume/totalCount;
        System.out.println("ConcurrentHashMap（JDK1.8）"+64+"个线程处理约1500万条数据的put操作的平均耗时为："+totalConsume/time
                +"，单次put操作的耗时："+(consumePerPut*1000)+"微秒");
    }
}
