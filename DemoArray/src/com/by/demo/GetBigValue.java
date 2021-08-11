package com.by.demo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GetBigValue {
    public static void main(String[] args) {
        int[] arr = {5, 15, 30, 20, 100};
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println(max);
        System.out.println(Arrays.toString(arr));
        System.out.println("=======================");
        arr=flashBack();
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println();
    }
    public static int[] flashBack(){
        int[] arr = {5, 15, 30, 20, 100};
        for (int min = 0,max=arr.length-1; min < max; min++,max--) {
            int temp=0;
            temp=arr[min];
            arr[min]=arr[max];
            arr[max]=temp;
        }
        return arr;
    }
}
