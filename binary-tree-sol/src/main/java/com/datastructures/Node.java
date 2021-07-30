package com.datastructures;

public class Node {

    int data;
    Node left = null;
    Node right = null;

    Node(int data) {

        if (data > 9 || data < 0) {
            throw new IllegalArgumentException("Value ("+data+") must be between 0 and 9");
        }
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}