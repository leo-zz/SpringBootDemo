import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: leo-zz
 * @Date: 2019/4/2 13:17
 */
public class TimerTest {

    @Test
    public void testTimer() throws InterruptedException {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("java自带的定时器：每2s执行一次,"+new Date()+Thread.currentThread().getName());
            }
        };
        Timer timer = new Timer();
        //1s后开始执行，每2s执行一次
        System.out.println(new Date());
        timer.schedule(timerTask,1000,2000);
        Thread.sleep(100*1000); //测试方法中main线程结束了，应用就结束了。
    }

    //main方法执行结束后，如果还有其他非守护线程未结束，jvm也不会结束。
//    public static void main(String[] args) {
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("java自带的定时器：每2s执行一次,"+new Date()+Thread.currentThread().getName());
//            }
//        };
//        Timer timer = new Timer();
//        //1s后开始执行，每2s执行一次
//        System.out.println(new Date());
//        timer.schedule(timerTask,1000,2000);
//    }

}
