package com.javaGeneric.zak;

import java.util.*;

public class Employee {
    private static Map<String, Integer> emps = new TreeMap<>();
    private static Map.Entry<String, Integer> maxSal = null;
    private static Map.Entry<String, Integer> minSal = null;
    static Integer randomSalary() {
        int min  = 10000;
        int max = 70000;
        double randNum = Math.random();
        int salary = (int) Math.floor(min + (randNum * (max - min)));
        return salary;
    }

    static void addAll(int size) {
        String name = "Employee", empName;
        Integer salary;
        for (int i = 0; i < size; i++) {
            salary = randomSalary();
            empName = name + i;
            if (emps.containsValue(salary) == false) {
                emps.put(empName, salary);
            }
        }
    }

    static Integer totalSalary() {
        // total salary
        int t_sal = 0;
        for (Map.Entry<String, Integer> emp : emps.entrySet()) {
            t_sal += emp.getValue();
        }
        return  t_sal;
    }

    static String averageSalary() {
        Integer avSal = totalSalary() / emps.size();
        String avSalName = "";
        Integer dif = null;
        Integer preDiff = null;
        for (Map.Entry<String , Integer> emp : emps.entrySet()) {
            dif = avSal - emp.getValue();
            if (dif < 0) {
                dif *= -1;
            }
            if (preDiff == null) {
                preDiff = dif;
                avSalName = emp.getKey();
            } else {
                if (preDiff > dif) {
                    avSalName = emp.getKey();
                    preDiff = dif;
                }
            }
        }
        return avSalName;
    }

    static void sortedByName() {
        for (Map.Entry<String , Integer> emp : emps.entrySet()) {
            System.out.print(emp.getKey());
            System.out.println("\t" + emp.getValue());
        }
    }

    static void sortedBySalary() {
        TreeMap<Integer, String> sortedEmp = new TreeMap();
        emps.forEach((k,v) -> sortedEmp.put(v, k));

        for (Map.Entry<Integer , String> emp : sortedEmp.entrySet()) {
            System.out.print(emp.getValue());
            System.out.println("\t" + emp.getKey());
        }
    }

    static void displayInfo() {
        String info = String.format("" +
                "Salary Information\n" +
                "Maximum Salary: %d\n" +
                "Minimum Salary: %d\n" +
                "Total Salary: %d\n\n" +
                "Employees Information\n" +
                "%s is paying highest.\n" +
                "%s is paying lowest.\n" +
                "%s is paying average.\n",
                maxSal.getValue(), minSal.getValue(), totalSalary(), maxSal.getKey(), minSal.getKey(), averageSalary());

        System.out.println(info);
    }

    public static void main(String[] args) {
        // create and add the desire number of employees
        addAll(30);

        maxSal = Collections.max(emps.entrySet(), Map.Entry.comparingByValue());
        minSal = Collections.min(emps.entrySet(), Map.Entry.comparingByValue());

        displayInfo();

        System.out.println("Name\t\tSalary");
//        sortedByName();
        sortedBySalary();
    }
}
