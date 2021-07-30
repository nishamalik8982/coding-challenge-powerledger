package com.datastructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree {
    

    public static int findSum(Node tree) {

        if (tree == null) {
            throw new IllegalArgumentException("Tree is null");
        }
        List<Integer> values = traverseTree(tree);

        return calculateSum(values);
    }

    private static int calculateSum(List<Integer> values) {

        int sum = 0;
        for (Integer value : values) {
            sum = sum + value.intValue();
        }
        //System.out.println("Sum : "+sum);
        return sum;
    }
    private static List<Integer> traverseTree(Node root) {
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> values = new ArrayList<>();
        traverseToLeaf(root, deque, values);
        return values;
    }

    private static void traverseToLeaf(Node node, Deque<Integer> deque, List<Integer> values) {

        // base case
        if (node == null) {
            return;
        }

        // include the current node to the path
        deque.addLast(node.data);

        // if a leaf node is found, print the path
        if (isLeaf(node)) {
            //System.out.println(deque);
            int count = 0;
            int sum = 0;
            for (Integer value : deque) {
                if (count > 0) {
                    sum = sum * 10;
                }
                sum = sum + value;
                count++;
            }
            //System.out.println("sum : "+sum);
            values.add(sum);
        }

        // recur for the left and right subtree
        traverseToLeaf(node.left, deque, values);
        traverseToLeaf(node.right, deque, values);

        // backtrack: remove the current node after the left, and right subtree are done
        deque.removeLast();
    }

    // Function to check if a given node is a leaf node or not
    public static boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);
    }

}
