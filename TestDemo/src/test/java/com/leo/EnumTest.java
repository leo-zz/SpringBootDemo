package com.leo;

public class EnumTest {

    public static void main(String[] args) {

        System.out.println("电脑配置为：CPU：" + Computer.CPU + "，内存：" + Computer.Memory + "，显卡：" + Computer.VideoCard + "，硬盘：" + Computer.HardDisk + "，主板：" + Computer.MotherBoard + "。");

        System.out.println("电脑配置为：CPU：" + Computer.CPU.getMiddle() + "，内存：" + Computer.Memory.getMiddle() + "，显卡：" + Computer.VideoCard.getMiddle() + "，硬盘：" + Computer.HardDisk.getMiddle() + "，主板：" + Computer.MotherBoard.getMiddle() + "。");

        System.out.println("成色"+Computer.CPU.getPercentNew()+"，电脑配置为：CPU：" + Computer.CPU.getMiddle() + "，内存：" + Computer.Memory.getMiddle() + "，显卡：" + Computer.VideoCard.getMiddle() + "，硬盘：" + Computer.HardDisk.getMiddle() + "，主板：" + Computer.MotherBoard.getMiddle() + "。");

        System.out.println("价格："+Computer.CPU.price+"，成色"+Computer.CPU.getPercentNew()+"，电脑配置为：CPU：" + Computer.CPU.getMiddle() + "，内存：" + Computer.Memory.getMiddle() + "，显卡：" + Computer.VideoCard.getMiddle() + "，硬盘：" + Computer.HardDisk.getMiddle() + "，主板：" + Computer.MotherBoard.getMiddle() + "。");

        System.out.println("产地："+Computer.getOrigin()+"，价格："+Computer.CPU.price+"，成色"+Computer.CPU.getPercentNew()+"，电脑配置为：CPU：" + Computer.CPU.getMiddle() + "，内存：" + Computer.Memory.getMiddle() + "，显卡：" + Computer.VideoCard.getMiddle() + "，硬盘：" + Computer.HardDisk.getMiddle() + "，主板：" + Computer.MotherBoard.getMiddle() + "。");

    }
}
