package com.company;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
//        RedBlackTree<String> asd = new RedBlackTree<>(String::compareTo);
//        asd.insert("Kacper");
//        asd.insert("Alicja");
//        asd.insert("Comparator");
//        asd.insert("Unimportant");
//        asd.insert("Volvo");
//        asd.insert("Honda");
//        asd.insert("Integer");
//        asd.printInOrder();
        RedBlackTree<Integer> rbt = new RedBlackTree<>(Integer::compare);
        rbt.insert(30);
        rbt.insert(43);
        rbt.insert(82);
        rbt.insert(35);
        rbt.insert(20);
        rbt.insert(70);
        rbt.insert(50);
        rbt.insert(8);
        rbt.insert(15);
        rbt.insert(60);
//        rbt.find(30);
//        rbt.find(123);
//        rbt.printByLevels();
//        rbt.printHeight();
//        rbt.printHeight(15);
//        rbt.printHeight(8);
//        rbt.printHeight(230);
//        rbt.printLeftHeight();
//        rbt.printRightHeight();
//        rbt.printLeftHeight(70);
//        rbt.printRightHeight(70);
//        rbt.printNumberOfNodes();
        rbt.fillHeights();
        rbt.printByLevels();
        rbt.printHeightsByLevels();
        rbt.min();
        rbt.printSuccessor(35);
        rbt.delete(35);
        rbt.printByLevels();
    }
}
