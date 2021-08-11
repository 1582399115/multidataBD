package com.by.demo;

public class StringDemo {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        char[] chars = {'a', 'b', 'c'};
        byte[] bytes = {97, 98, 99};
        String str3 = new String(chars);
        String str4 = new String(bytes);
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str3);
        System.out.println(str1 == str4);
        System.out.println(str3 == str4);

    }
}
