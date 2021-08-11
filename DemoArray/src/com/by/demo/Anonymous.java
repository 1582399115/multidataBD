package com.by.demo;

import java.util.Scanner;

/*
*匿名对象就是只有右边的对象,没有左边的名字和赋值运算符
* 匿名对象只能使用一次 ,一次再使用不得不再创建一个新对象
* */
public class Anonymous {
    public static void main(String[] args) {
        Person person=new Person();
        person.name="LBY";
        person.getName();

        //使用匿名对象 作为参数
        System.out.println("请输入你的名字!");
//        methodScanner(new Scanner(System.in));
        String next = returnScanner().next();
        System.out.println("你的名字:"+next);
    }

    public static void methodScanner(Scanner sc){
        String next = sc.next();

        System.out.println("输出的名字是:"+next);
    }
    public static Scanner returnScanner(){
         return new Scanner(System.in);
    }
}
