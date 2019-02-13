package com.leo;

public enum Computer {

    //枚举类中的枚举元素直接声明，枚举元素编译后等同于枚举类的一个静态对象。枚举元素可以使用枚举类中定义的成员变量和方法。
    CPU{
        @Override
        public String getMiddle() {
            return "intel i5-7200U";
        }
        //枚举元素中不能定义额外的成员变量，无效
        public double price=50d;
    },
    Memory{
        @Override
        public String getMiddle() {
            return "8G DDR4 2400";
        }
    },
    VideoCard{
        @Override
        public String getMiddle() {
            return "GT1050";
        }
    },
    HardDisk{
        @Override
        public String getMiddle() {
            return "1TB";
        }
    },
    MotherBoard{
        @Override
        public String getMiddle() {
            return "华硕";
        }
    };

    //成员变量，必须通过枚举元素调用
    public double price=100d;

    //抽象方法，枚举元素必须自己实现该方法
    public abstract String getMiddle();

    //常规方法，必须通过枚举元素调用
    public  String getPercentNew(){
        return "全新";
    };

    //静态方法，属于枚举类，枚举元素不能访问。
    public static String getOrigin(){
        return "中国";
    }
}
