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

    private Color getColor(Node n){
        if(n == null) return Color.BLACK;
        else return n.getColor();
    }

    public Node find(T o){
        Node n = search(o, root);
        if(n != null) System.out.println(n.value);
        else System.out.println("Not found.");
        return n;
    }

    private Node search(T o, Node n){
        if(n == null || o.equals(n.value)) return n;
        else if(comparator.compare(o, n.value) < 0) return search(o, n.left);
        else return search(o, n.right);
    }

    private class Node{
        Node parent;
        Node left;
        Node right;
        Color color;
        T value;

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
            return this.value + " " + this.color;
        }
    }
}
