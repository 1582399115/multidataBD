package com.by.demo;

import java.util.Random;

public class DemoRandom {
    public static void main(String[] args) {
        Random r=new Random();
        System.out.println(r.nextBoolean());
        System.out.println(r.nextDouble());
        System.out.println(r.nextGaussian());
        System.out.println(r.nextInt());
        System.out.println(r.nextLong());
    }
}
