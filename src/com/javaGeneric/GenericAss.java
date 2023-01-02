package com.javaGeneric;

import java.util.*;

public class GenericAss {
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);l1.add(3);

        List<Integer> l2 = new ArrayList<>();
        l2.add(2);

        List<Integer> l3 = new ArrayList<>();
        for (Integer i: l1) {
            l3.add(i);
        }
        for (Integer j: l2) {
            l3.add(j);
        }

        System.out.println(l3);

    }
}
/*
*
* (1, "josh")
* (2. "gaza")
* [1, 2] set
* ["josh", "gaza"] list
* */
class ListExample{
    public static void main(String[] args) {
        LinkedList<String> staff = new LinkedList<String>();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        Collections.shuffle(arr);
        Collections.sort(arr);

        System.out.println(arr);
        ListIterator<String> iterator = staff.listIterator();

        iterator.add("Addis");
        iterator.add("Adama");
        iterator.add("Awasa");
        iterator = staff.listIterator();

        iterator.next();
//        iterator.next();

        iterator.add("Bishoftu");
//        iterator.next();

        iterator.add("Diredawa");
        iterator = staff.listIterator();
        System.out.println(staff);


//        iterator.next();
//        iterator.remove();
        while (iterator.hasNext()) { System.out.println(iterator.next());
        }

    }
}
