package com.by.demo;

import java.util.ArrayList;

/*
对于ArrayList来说,有一个尖括号<E> 代表泛型
泛型: 也就是装在集合当中的所有元素,全都是统一的什么类型
注意: 泛型只能是引用类型,不能是基本类型
*/
public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        System.out.println(arrayList);
        String s= arrayList.toString().replace("[","{").replace("]", "}").replace(",", "@");
        System.out.println(s);
    }
}
