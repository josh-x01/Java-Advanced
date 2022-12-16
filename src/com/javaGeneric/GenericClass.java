package com.javaGeneric;


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
