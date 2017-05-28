package com.company;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>(Integer::compare);
        rbt.insert(30);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(43);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(82);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(35);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(20);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(70);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(50);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(8);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(15);
        System.out.println();
        rbt.printInOrder();
        rbt.insert(60);
        System.out.println();
        rbt.printInOrder();
//        System.out.println(rbt.find(30));
//        rbt.find(123);
    }
}
