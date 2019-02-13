package com.jdk;

import org.junit.Test;

import java.util.*;
import java.lang.ref.WeakReference;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

/**
 * @author skywang
 * @desc WeakHashMap测试程序
 * @email kuiwu-wang@163.com
 */
public class TestWeakHashMap {

    public static void main(String[] args) throws Exception {
        testWeakHashMapAPIs();
    }

    private static void testWeakHashMapAPIs() {
        // 初始化3个“弱键”
        String w1 = new String("one");
        String w2 = new String("two");
        String w3 = new String("three");
        // 新建WeakHashMap
        Map wmap = new WeakHashMap();

        // 添加键值对
        wmap.put(w1, "w1");
//        wmap.put(w2, "w2");
//        wmap.put(w3, "w3");

//        // 打印出wmap
//        System.out.printf("\nwmap:%s\n", wmap);
//
//        // containsKey(Object key) :是否包含键key
//        System.out.printf("contains key two : %s\n", wmap.containsKey("two"));
//        System.out.printf("contains key five : %s\n", wmap.containsKey("five"));

        // containsValue(Object value) :是否包含值value
//        System.out.printf("contains value 0 : %s\n", wmap.containsValue(new Integer(0)));

        // remove(Object key) ： 删除键key对应的键值对
//        wmap.remove("three");

//        System.out.printf("wmap: %s\n", wmap);


        // ---- 测试 WeakHashMap 的自动回收特性 ----

        // 将w1设置null。
        // 这意味着“弱键”w1再没有被其它对象引用，调用gc时会回收WeakHashMap中与“w1”对应的键值对
        w1 = null;
        // 内存回收。这里，会回收WeakHashMap中与“w1”对应的键值对
        System.gc();

        // 遍历WeakHashMap
//        Set set = wmap.entrySet();
//        Iterator iter = set.iterator();
//        while (iter.hasNext()) {
//            Map.Entry en = (Map.Entry) iter.next();
//            System.out.printf("next : %s - %s\n", en.getKey(), en.getValue());
//        }
//        // 打印WeakHashMap的实际大小
        System.out.printf(" after gc WeakHashMap size:%s\n", wmap.size());
    }

    @Test
    public  void testOther(){

        boolean first=false;
        boolean second=true;
        System.out.println("invoke testOther");
        if(second&&remove());
    }

    private boolean remove(){
        System.out.println("invoke remove()");
        return true;
    }
}