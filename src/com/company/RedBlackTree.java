package com.company;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Szymon on 28.05.2017.
 */
public class RedBlackTree<T> {
    Node root;
    Comparator<T> comparator;
    enum Color {RED, BLACK}
    private ArrayList<String> helper = new ArrayList<>();

    public RedBlackTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void treeInsert(Node n, Node x){
        if(x == null) root = n;
        else if(comparator.compare(n.value, x.value) < 0){
            if(x.left == null){
                x.left = n;
                n.parent = x;
            }
            else treeInsert(n, x.left);
        }
        else if(comparator.compare(n.value, x.value) > 0){
            if(x.right == null){
                x.right = n;
                n.parent = x;
            }
            else treeInsert(n, x.right);
        }
    }

    public void insert(T value){
        Node n = new Node(value);
        treeInsert(n, root);
        n.setColor(Color.RED);
        while(n != root && getColor(n.parent) == Color.RED){
            if(n.parent == n.parent.parent.left){
                Node y = n.parent.parent.right;
                if(getColor(y) == Color.RED){               //CASE 1
                    n.parent.setColor(Color.BLACK);         //CASE 1
                    y.setColor(Color.BLACK);                //CASE 1
                    n.parent.parent.setColor(Color.RED);    //CASE 1
                    n = n.parent.parent;                    //CASE 1
                }
                else{
                    if(n == n.parent.right){
                        n = n.parent;
                        leftRotate(n);                      //CASE 2
                    }
                    n.parent.setColor(Color.BLACK);         //CASE 3
                    n.parent.parent.setColor(Color.RED);    //CASE 3
                    rightRotate(n.parent.parent);           //CASE 3
                }
            }

            else {
                Node y = n.parent.parent.left;
                if(getColor(y) == Color.RED){               //CASE 1
                    n.parent.setColor(Color.BLACK);         //CASE 1
                    y.setColor(Color.BLACK);                //CASE 1
                    n.parent.parent.setColor(Color.RED);    //CASE 1
                    n = n.parent.parent;                    //CASE 1
                }
                else{
                    if(n == n.parent.left){
                        n = n.parent;
                        rightRotate(n);                     //CASE 2
                    }
                    n.parent.setColor(Color.BLACK);         //CASE 3
                    n.parent.parent.setColor(Color.RED);    //CASE 3
                    leftRotate(n.parent.parent);            //CASE 3
                }
            }
        }
        root.setColor(Color.BLACK);
    }

    private void leftRotate(Node x){
        Node y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        }
        else if(x == x.parent.left){
            x.parent.left = y;
        }
        else{
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;

    }

    private void rightRotate(Node x){
        Node y = x.left;
        x.left = y.right;
        if(y.right != null){
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        }
        else if(x == x.parent.left){
            x.parent.left = y;
        }
        else{
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;

    }

    public void printInOrder(){
        inOrderWalk(root);
    }

    public void inOrderWalk(Node n){
        if(n == null) return;
        else{
            inOrderWalk(n.left);
            System.out.print(n + " ");
            inOrderWalk(n.right);
        }
    }


    public void printByLevels(){
        this.helper = new ArrayList<>();
        printByLevels(root, 0);
        System.out.println("Keys by levels: "/* + helper*/);
        helper.forEach(System.out::println);
    }

    private void printByLevels(Node n, int x){
        if(n/*.value*/ != null && n.value != null){
            if(helper.size() == x) helper.add(x, n + " ");
            else helper.set(x, helper.get(x) + n + " ");
        }
        else if(n == null){
            if(helper.size() == x) helper.add(x, "null ");
            else helper.set(x, helper.get(x) + "null ");
        }
        if(n != null){
            printByLevels(n.left, x + 1);
            printByLevels(n.right, x + 1);
        }
    }

    public void printHeight(){
        System.out.println("Height: " + height(root));
    }

    public void printHeight(T o){
        Node n = search(o, root);
        if(n != null){
            System.out.println("Height: " + height(n));
        }
        else System.out.println("Key " + o + " not found.");
    }

    public int height(Node n){
        if(n == null) return -1;
        else if(n.left != null && n.right != null){
            if(height(n.left) > height(n.right)) return height(n.left) + 1;
            else return height(n.right) + 1;
        }
        else if (n.left == null) return height(n.right) + 1;
        else return height(n.left) + 1;
    }

    public void printLeftHeight(){
        if(root != null){
            System.out.println("Height: " + height(root.left));
        }
        else System.out.println("Height: -1");
    }

    public void printLeftHeight(T o){
        Node n = search(o, root);
        if(n != null){
            System.out.println("Height: " + height(n.left));
        }
        else System.out.println("Height: -1");
    }

    public void printRightHeight(){
        if(root != null){
            System.out.println("Height: " + height(root.right));
        }
        else System.out.println("Height: -1");
    }

    public void printRightHeight(T o){
        Node n = search(o, root);
        if(n != null){
            System.out.println("Height: " + height(n.right));
        }
        else System.out.println("Height: -1");
    }

    private Color getColor(Node n){
        if(n == null) return Color.BLACK;
        else return n.getColor();
    }

    public Node find(T o){
        Node n = search(o, root);
        if(n != null) System.out.println("Key found: " + n);
        else System.out.println("Key " + o + " not found.");
        return n;
    }

    private Node search(T o, Node n){
        if(n == null || o.equals(n.value)) return n;
        else if(comparator.compare(o, n.value) < 0) return search(o, n.left);
        else return search(o, n.right);
    }

    public void printNumberOfNodes(){
        System.out.println("Number of nodes: " + numberOfNodes(root));
    }

    private int numberOfNodes(Node n){
        int x = 0;
        if(n == null) return x;
        else if(n.left != null && n.right != null){
            return x + 1 + numberOfNodes(n.left) + numberOfNodes(n.right);
        }
        else if(n.left == null) return  x + 1 + numberOfNodes(n.right);
        else return  x + 1 + numberOfNodes(n.left);
    }

    public void printNumberOfNodes(T o){
        Node n = search(o, root);
        if(n != null){
            System.out.println("Number of nodes: " + numberOfNodes(root));
        }
        else System.out.println("Key: " + o + " not found.");
    }

    public void printLeftNumberOfNodes(){
        if(root != null){
            System.out.println("Number of nodes: " + numberOfNodes(root.left));
        }
    }
    public void printRightNumberOfNodes(){
        if(root != null){
            System.out.println("Number of nodes: " + numberOfNodes(root.right));
        }
    }

    public void printLeftNumberOfNodes(T o){
        Node n = search(o, root);
        if(n != null){
            System.out.println("Number of nodes: " + numberOfNodes(n.left));
        }
        else System.out.println("Key: " + o + " not found.");
    }

    public void printRightNumberOfNodes(T o){
        Node n = search(o, root);
        if(n != null){
            System.out.println("Number of nodes: " + numberOfNodes(n.right));
        }
        else System.out.println("Key: " + o + " not found.");
    }

    public void printHeightsByLevels(){
        this.helper = new ArrayList<>();
        heightsByLevels(root, 0);
        System.out.println("Heights by levels: "/* + helper*/);
        helper.forEach(System.out::println);
    }

    private void heightsByLevels(Node n, int x){
        if(n/*.value*/ != null && n.value != null){
            if(helper.size() == x) helper.add(x, n.printHeights() + " ");
            else helper.set(x, helper.get(x) + n.printHeights() + " ");
        }
        else if(n == null){
            if(helper.size() == x) helper.add(x, "null ");
            else helper.set(x, helper.get(x) + "null ");
        }
        if(n != null){
            heightsByLevels(n.left, x + 1);
            heightsByLevels(n.right, x + 1);
        }
    }

    public void fillHeights(){
        fillHeights(root);
    }

    private void fillHeights(Node n){
        if(n == null) return;
        else{
            fillHeights(n.left);
            n.height = height(n);
            n.leftHeight = height(n.left);
            n.rightHeight = height(n.right);
            fillHeights(n.right);
        }
    }

    public void printSuccessor(T o){
        System.out.println(findSuccessor(o, root, null));
    }


    private Node findSuccessor(T o, Node n, Node successor) {
        if (null == n) {
            return null;
        }

        if (comparator.compare(o, n.value) < 0) {
            return findSuccessor(o, n.left, n);
        } else if (comparator.compare(o, n.value) > 0) {
            return findSuccessor(o, n.right, successor);
        }
        if (null != n.right) {
            return (findMin(n.right));
        }
        return successor;
    }


    public void min(){
        Node n = findMin(root);
        if(n == null) System.out.println("The tree is empty.");
        else System.out.println("Min: " + n.value);
    }

    private Node findMin(Node n){
        if(n.left == null) return n;
        else return findMin(n.left);
    }

    public void delete(T o){
        delete(find(o));
    }

    public Node delete(Node z){
        Node y;
        if(z.left == null || z.right == null){
            y = z;
        }
        else{
            y = findSuccessor(z.value, root, null);
        }
        Node x;
        if(y.left != null){
            x = y.left;
        }
        else x = y.right;
        if(x == null){
            x = new Node();
        }
        x.parent = y.parent;
        if(y.parent == null){
            root = x;
        }
        else if(y == y.parent.left){
            y.parent.left = x;
        }
        else y.parent.right = x;
        if(y != z){
            z.value = y.value;
        }
        if (y.getColor() == Color.BLACK){
            deleteFixup(x);
        }
        return y;
    }

    private void deleteFixup(Node x){
        while (x != root && x.getColor() == Color.BLACK){
            if(x == x.parent.left){
                Node w = x.parent.right;
                if(w.getColor() == Color.RED){
                    w.setColor(Color.BLACK);
                    x.parent.setColor(Color.RED);
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if(w.left.getColor() == Color.BLACK && w.right.getColor() == Color.BLACK){
                    w.setColor(Color.RED);
                    x = x.parent;
                }
                else if(w.right.getColor() == Color.BLACK){
                    w.left.setColor(Color.BLACK);
                    w.setColor(Color.RED);
                    rightRotate(w);
                    w = x.parent.right;
                }
                w.setColor(x.parent.getColor());
                x.parent.setColor(Color.BLACK);
                w.right.setColor(Color.BLACK);
                leftRotate(x.parent);
                x = root;
            }
            else {
                Node w = x.parent.left;
                if(w.getColor() == Color.RED){
                    w.setColor(Color.BLACK);
                    x.parent.setColor(Color.RED);
                    leftRotate(x.parent);
                    w = x.parent.left;
                }
                if(w.right.getColor() == Color.BLACK && w.left.getColor() == Color.BLACK){
                    w.setColor(Color.RED);
                    x = x.parent;
                }
                else if(w.left.getColor() == Color.BLACK){
                    w.right.setColor(Color.BLACK);
                    w.setColor(Color.RED);
                    rightRotate(w);
                    w = x.parent.left;
                }
                w.setColor(x.parent.getColor());
                x.parent.setColor(Color.BLACK);
                w.left.setColor(Color.BLACK);
                leftRotate(x.parent);
                x = root;
            }
            x.setColor(Color.BLACK);
        }
    }


    private class Node{
        Node parent;
        Node left;
        Node right;
        Color color;
        T value;
        int height = -1;
        int leftHeight = -1;
        int rightHeight = -1;

        public Node() {
        }

        Node(T value) {
            this.value = value;
        }

        Color getColor() {
            return color;
        }

        void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return this.value + " " + this.color /*Integer.toString(height)*/;
        }

        public String printHeights(){
            return "[" + Integer.toString(leftHeight) + ", " + Integer.toString(height) + ", " + Integer.toString(rightHeight) + "]";
        }
    }
}
