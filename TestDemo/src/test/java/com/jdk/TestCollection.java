package com.jdk;


import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class TestCollection {

    public static void main(String[] args) {
//        String[] strs={"a1","a2","a3"};
//        for (String str:strs
//                ) {
//            System.out.println(str);
//        }
        ArrayList<String> strings = new ArrayList<>();
//        strings.add("a1");
//        strings.add("a2");
//        strings.add("a3");
//        strings.add("a4");
//        strings.add("a5");
//        strings.add("a6");
//        strings.add("a7");
//        strings.add("a8");
//        strings.add("a9");
//        strings.add("a10");
//        strings.add("a11");
//        strings.add("a12");
//        for (String str:strings
//                ) {
//            System.out.println(str);
//        }
//        List<Boolean> booleans = Arrays.asList(true, false, true);
//        for (Boolean bool:booleans
//                ) {
//            System.out.println(bool);
//        }
//        int[] ints={1,2,3,4};
//        for (int inta:ints
//                ) {
//            System.out.println(inta);
//        }
//        Integer[] interss={1,2,3,4};
//        for (Integer inta:interss
//                ) {
//            System.out.println(inta);
//        }
//        List<int[]> ints1 = Arrays.asList(ints);//int数组使用asList，会把int[]作为单个元素，而不是int[]中的元素。
//        for (int[] a:ints1
//                ) {
//            System.out.println(a);
//        }
//        List<Integer> integers = Arrays.asList(interss);
//        for (Integer inta:integers
//                ) {
//            System.out.println(inta);
//        }
//        List<Integer> integers1 = Arrays.asList(1, 2, 3, 4);
//        for (Integer inta:integers1
//                ) {
//            System.out.println(inta);
//        }

        //测试序列化和反序列化
//            try {
//                File f=new File("C:\\Users\\j2eeLe\\Desktop\\1.txt");
//                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
//                oos.writeObject(strings);
//                oos.flush();
//                oos.close();
////                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
////                ArrayList<String> o = (ArrayList<String>)ois.readObject();
////                ois.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }

//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        int j=100;
//        final CountDownLatch countDownLatch = new CountDownLatch(j);
//
////        final ArrayList<String> strings1 = new ArrayList<>(); 不同步
////        final Vector<String> strings1 = new Vector<>();  同步
////        final CopyOnWriteArrayList<String> strings1  =new CopyOnWriteArrayList<>(); 同步，读写分离
        LinkedList<String> strings1 = new LinkedList<>();
//
//
//        for (int i=0;i<j;i++){
//            System.out.println(i);
//            final Integer integer=i;
//            //lambda
//            new Thread(()->{
//                /**
//                 *  并发插入没有同步的问题
//                 *  容量没有及时扩充，导致插入失败
//                 *  插入丢失()
//                 *
//                 */
//                    strings1.add("b"+integer);
//                    countDownLatch.countDown();
//            }).start();
//        }
//        try {
//            countDownLatch.await();
//            for (String str:strings1
//                 ) {
//                System.out.println(str);
//            }
//            System.out.println("size:"+strings1.size());
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());

//        List<String> strings2 = Collections.synchronizedList(strings1);


        //测试map的扩容操作
        //DEFAULT_INITIAL_CAPACITY为16，DEFAULT_LOAD_FACTOR为0.75，因此初始的容量最大值
        //threshold=16*0.75=12
        HashMap<String, String> map = new HashMap<>(5);
//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
//        Hashtable<Object, Object> map = new Hashtable<>();//同步
//        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();//同步
        map.put("a1", "阿里巴巴");
        map.put("a2", "美团");
        map.put("a3", "美团");
        map.put("a4", "美团");
        map.put("a5", "阿里巴巴");
        map.put("a6", "美团");
        map.put("a7", "美团");
        map.put("a8", "美团");
        map.put("a9", "阿里巴巴");
        map.put("a10", "美团");
        map.put("a11", "美团");
        map.put("a12", "美团");
        map.put("a13", "美团");
        Object a1 = map.get("a1");
//
//        int size = map.size();
//        System.out.println(size);

//        //自定义缓存
//        TestCollection a = new TestCollection();
//        LinkedCache<String, String> cache = a.new LinkedCache<>();
//        cache.put("a1","阿里");
//        cache.put("a2","腾讯");
//        cache.put("a3","百度");
//        cache.get("a1");
//        cache.put("a4","美团");//由于a1刚访问，a2是未访问元素中存活时间最长的，因此a2被移除
//
//        //Map的遍历 应该用keySet，而不是entrySet
//        Set<Map.Entry<String, String>> entries = cache.entrySet();
//        for (Map.Entry<String, String> entry:entries
//             ) {
//            System.out.println(entry.getKey()+"    "+entry.getValue());
//        }

        //WeakHashMap的用法没测出来
//        StringBuffer AL = new StringBuffer("阿里");
//        String a1=new String("a1");
////        String a2=new String("a2");
////        String a3=new String("a3");
//        WeakHashMap<StringBuffer, String> cache = new WeakHashMap<>();
//        cache.put(AL,a1);
////        cache.put(a2,AL);
////        cache.put(a3,AL);
//        System.out.println(cache.size());
////        a1=null;
//        AL=null;
//        System.gc();
////        System.out.println(cache.get(a1));
////        System.out.println(cache.get("a1"));
//        //不遍历打印出来，显示的size不一定更新
////        Iterator<String> iterator = cache.keySet().iterator();
////        while (iterator.hasNext()){
////            String next = iterator.next();
////            System.out.println(next);
////        }
//        System.out.println(cache.size());

        //测试 虚引用
//        Object a=new TestCollection();
//        WeakReference<Object> wr=new WeakReference<>(a);
//
//        System.out.println(wr.get());
//        a=null;
//        System.gc();
//        System.out.println(wr.get());

        String[] strings2 = new String[3];
        System.out.println(strings2.length);//即使数组中没有存放数据，数组也是有长度的


        HashSet<String> set = new HashSet<>();
        set.add("a1");


    }


    class LinkedCache<K, V> extends LinkedHashMap<K, V> {
        //最大缓存数
        private static final int MAX_ENTRIES = 3;

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > MAX_ENTRIES;
        }

        public LinkedCache() {
            super(MAX_ENTRIES, 0.75F, true);
        }
    }


    @Test
    public void testCompare() {
//        ArrayList<Integer> integers = new ArrayList<>();
//        integers.add(-1);
//        integers.add(2);
//        integers.add(1);
//        integers.add(0);
//        integers.add(9);
//        integers.add(13);
//        integers.add(-5);
//
//        System.out.println("原始排列:");
//        System.out.println(integers);
//
//        Collections.reverse(integers);
//        System.out.println("反序排列:");
//        System.out.println(integers);
//
//        Collections.rotate(integers,-3);//相当于循环右移，distance为负值时表示循环左移
//        System.out.println("旋转排列:");
//        System.out.println(integers);
//
//        Collections.sort(integers);
//        System.out.println("自然排列:");//对于数字的话就是由小及大的排列，排序的数组必须都实现Comparable接口
//        System.out.println(integers);
//
//        Collections.shuffle(integers);
//        System.out.println("随机排列:");
//        System.out.println(integers);
//
//        //自定义Comparator
//        Collections.sort(integers, new Comparator<Integer>() {
//            @Override
//            //返回负值、0、正值，分别表示o1<、=、>o2
//            public int compare(Integer o1, Integer o2) {
//                //偶数在后，数值小的在后（数组中小的值放在头，大的值放在尾）
//                if(o1%2==0 & o2%2!=0){
//                    return 1;
//                }else if(o1%2!=0 & o2%2==0){
//                    return -1;
//                }else
//                return Integer.compare(o1,o2);
//            }
//        });
//        System.out.println("自定义排列:");
//        System.out.println(integers);

//        ArrayList<Iphone> iphones = new ArrayList<Iphone>();
//        iphones.add(new Iphone("XR",2018,6999,1));
//        iphones.add(new Iphone("8 64G",2017,3499,0));
//        iphones.add(new Iphone("7 Plus 128G",2016,3999));
//
//        Collections.sort(iphones,iphones.get(0));
//        System.out.println("按年份排列:");
//        System.out.println(iphones);
//
//        Collections.sort(iphones,iphones.get(1));
//        System.out.println("按价格排列:");
//        System.out.println(iphones);


        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

//        ArrayList<Integer> list2 = new ArrayList<Integer>();
//        list2.add(2);
//        list2.add(3);
//        list2.add(4);
//        list2.add(5);
        // 并集
//         list1.addAll(list2);
        // 交集
//        list1.retainAll(list2);
//        // 差集
//        // list1.removeAll(list2);
//        // 无重复并集
////        list2.removeAll(list1);
////        list1.addAll(list2);
//        for (Integer i : list1) {
//            System.out.println(i);
//        }


        List<Integer> list = Collections.unmodifiableList(list1);
        list1.add(5);//list中保存了指向list1的引用，因此list1修改，list的内容也会发生变化。
        for (Integer i : list) {
            System.out.println(i);
        }
        list.add(6);//list不可修改，抛出UnsupportedOperationException


    }

    static class Iphone implements Comparator<Iphone> {
        String name;
        int year;
        int price;
        int ORDER_BY_YEAR = 1;

        public Iphone(String name, int year, int price) {
            this.name = name;
            this.year = year;
            this.price = price;
        }

        public Iphone(String name, int year, int price, int ORDER_BY_YEAR) {
            this.name = name;
            this.year = year;
            this.price = price;
            this.ORDER_BY_YEAR = ORDER_BY_YEAR;
        }

        @Override
        public String toString() {
            return "Iphone{" +
                    "name='" + name + '\'' +
                    ", year=" + year +
                    ", price=" + price +
                    '}';
        }

        @Override
        public int compare(Iphone o1, Iphone o2) {
            //按年份排序
            if (ORDER_BY_YEAR == 1) {
                if (o1.year == o2.year) return 0;
                return o1.year > o2.year ? 1 : -1;
            } else {
                //按价格排序
                if (o1.price == o2.price) return 0;
                return o1.price > o2.price ? 1 : -1;
            }
        }
    }

    @Test
    public void testHashMap() throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>();
        System.out.println("begin");
        int num = 15;
        final CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            final Integer integer = i;
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        map.put("a" + integer + j, "b" + integer + j);
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        countDownLatch.await();
        System.out.println(map.size());

        for (int m = 0; m < 200; m++) {
            System.out.println("c" + m + ":  " + map.get("c" + m));
        }
    }


    public int[] testNonConcurrent(int count) throws InterruptedException {
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
        System.out.println("第"+count+"次插入耗时：" + (end - begin));
        //返回的int数组中，分别为该次测试执行耗时，测试执行的put操作次数。
        int[] ints={(int)(end - begin),15000000};
        return ints;
    }

    public int[] testHashtable(int cLevel,int count) throws InterruptedException {
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
            });
        }
        //等待所有线程完成写入操作
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("应插入条数据，" + num * num2 + "条，实际插入" + map.size() + "条数据");
        System.out.println("第"+count+"次"+num + "个线程并发执行，耗时：" + (end - begin));
        //返回的int数组中，分别为该次测试执行耗时，测试执行的put操作次数。
        int[] ints={(int)(end - begin),num * num2 };
        return ints;
    }

    public int[] testConcurrentHashMap18(int cLevel,int count) throws InterruptedException {
        //此处修改并发度
        int concurrentLevel=16;
        //concurrentLevel等于segment[]的长度，initialCapacity>=concurrentLevel
        int initialCapacity=concurrentLevel;
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(initialCapacity,0.75f,concurrentLevel);
        //此处修改并发线程的数量
        int num = concurrentLevel;
        int num2= (1000000*15/concurrentLevel);//每个线程执行put操作的次数
        //开始并发put操作
        System.out.println("begin");
        long begin = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            final Integer integer = i;
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < num2; j++) {
                        map.put("a" + integer + j, "b" + integer + j);
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待所有线程完成写入操作
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("应插入条数据，"+num * num2+"条，实际插入" + map.size() + "条数据");
        System.out.println("第"+count+"次"+num+"个线程并发执行，耗时：" + (end - begin));
        //返回的int数组中，分别为该次测试执行耗时，测试执行的put操作次数。
        int[] ints={(int)(end - begin),num * num2};
        return ints;
    }
}
