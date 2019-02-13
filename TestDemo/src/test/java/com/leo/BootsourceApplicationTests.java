//package com.leo;
//
//import org.junit.Test;
//import org.springframework.util.StopWatch;
//
//import java.util.concurrent.*;
//
////@RunWith(SpringRunner.class)
////@SpringBootTest
//public class BootsourceApplicationTests {
//
//	@Test
//	public void contextLoads() {
//
//	}
//
//	@Test
//	public void testStopWatch() throws InterruptedException {
//		StopWatch stopWatch = new StopWatch();
//
//		stopWatch.start("打开冰箱门");
//		Thread.sleep(1000);
//		stopWatch.stop();
//		System.out.println(stopWatch.getTaskInfo()[0].getTaskName()+"耗时："+ stopWatch.getTaskInfo()[0].getTimeMillis());
//
//		stopWatch.start("放入大象");
//		Thread.sleep(3000);
//		stopWatch.stop();
//		System.out.println(stopWatch.getLastTaskName()+"耗时："+stopWatch.getLastTaskTimeMillis());
//
//
//		stopWatch.start("关上冰箱门");
//		long start3 = System.currentTimeMillis();
//		Thread.sleep(1500);
//		stopWatch.stop();
//		long end3 = System.currentTimeMillis();
//		System.out.println(stopWatch.getLastTaskName()+"耗时："+stopWatch.getLastTaskTimeMillis()+"与"+(end3-start3));
//	}
//
//
//	//测试线程池和lambda表达式
//	//当i>corePoolSize时，会报错误cannot be cast to java.lang.Comparable。
//	//等准备多线程、并发的知识点时再针对性了解
////	@Test
//	public void lambdaAndExecutor() throws InterruptedException {
//		BlockingQueue<Runnable> queue=new PriorityBlockingQueue<Runnable>();
//		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8, 10, 10, TimeUnit.SECONDS, queue);
//		int[] j={7};
//		CountDownLatch countDownLatch = new CountDownLatch(j[0]);
//		for (int i=0;i<7;i++){
////			j[0]=j[0]+1;
//			poolExecutor.execute(()->{
//				j[0]=j[0]-1;
//				System.out.println(Thread.currentThread().getName()+"执行任务"+j[0]);
//				countDownLatch.countDown();
//			});
//		}
//		countDownLatch.await();
//
//	}
//
//}
