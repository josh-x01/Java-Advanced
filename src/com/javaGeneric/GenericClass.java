package com.javaGeneric;


import java.awt.print.PrinterGraphics;
import java.util.ArrayList;

public class GenericClass {
    public static void testGeneric() {
        System.out.println("Generic class is working!");
    }

    // method for integers
    public static void printGeneric(Integer numbers[]) {
        for (Integer number : numbers)
            System.out.printf("%s ", number);
        System.out.println();
    }

    // method for characters
    public static void printGeneric(Character _chars[]) {
        for (Character _char : _chars)
            System.out.printf("%s ", _char);
        System.out.println();
    }

    // first generic function
    public static <T> void firstGeneric(T[] t) {
        for (T i : t) {
            System.out.printf("%s ", i);
        }
        System.out.println();
    }
}

class FindTarget {
    static Integer[] findTarget(Integer[] arr, int target) {
        Integer[] targets = new Integer[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if ((arr[i] + arr[j]) == target) {
                    targets[0] = i;
                    targets[1] = j;
                    return  targets;
                }
            }
        }
        return  null;
    }

    public static void main(String[] args) {
        Integer[] arr = {2, 6, 7, 8};
        int target = 15;
        Integer[] resultArr = findTarget(arr, target);
        System.out.println("["+ resultArr[0] + ", " + resultArr[1] +"]");
    }
}

class GenericMethod {

    static <T>  void checkGeneric(T t) {
        if (t instanceof Integer) {
            System.out.println("is id");
        } else if (t instanceof String) {
            try {
                Integer.parseInt((String) t);
                System.out.println("is phone");
            } catch (NumberFormatException e) {
                System.out.println("is String");
            }
        }
    }

    public static void main(String[] args) {
        Integer num = 5;
        String name = "josh";
        String phone = "0960077672";
        checkGeneric(num);
    }
}
