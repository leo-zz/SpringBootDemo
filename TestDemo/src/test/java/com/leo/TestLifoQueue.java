package com.leo;

import org.junit.Test;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TestLifoQueue {

    @Test
    /**
     *  综合比较：
     *  新增：
     *  List新增是在集合末尾增加元素。
     *  LifoQueue是在集合开始位置增加元素。
     *  取出：
     *  List取出元素后不会移除该元素，且可以在指定位置，集合头、尾部增加。
     *  LifoQueue取出元素后会移除该元素，相当于是从集合头部取出元素。
     */
    public void testLifo(){
        LinkedList<String> list = new LinkedList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");

        String first = list.getFirst();
        System.out.println(first);

        String last = list.getLast();
        System.out.println(last);
        //将List集合转换成后进先出的Queue
        Queue<String> queue = Collections.asLifoQueue(list);

        String s = queue.poll();
        System.out.println(s);
        queue.add("s5");
        String s2 = queue.poll();
        System.out.println(s2);
        String s3 = queue.poll();
        System.out.println(s3);


    }
}
