package com.datastructures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testTreeWithNullRoot() {

        Node root = null;

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Tree.findSum(root),
            "Invalid value (null) to find a sum"
        );

        assertTrue(thrown.getMessage().contains("Tree is null"));
    }

    @Test
    public void testSumWithOnlyRoot() {

        Node tree = new Node(5);

        int sum = Tree.findSum(tree);
        assertEquals(5, sum);
    }

    @Test
    public void testSumWithATree1() {

        Node tree = new Node(1);

        tree.setLeft(new Node(0));
        tree.setRight(new Node(4));

        tree.getLeft().setLeft(new Node(3));
        tree.getLeft().setRight(new Node(1));

        int sum = Tree.findSum(tree);
        assertEquals(218, sum);
    }

    @Test
    public void testSumWithATree2() {

        Node tree = new Node(0);

        tree.setLeft(new Node(9));
        tree.setRight(new Node(9));

        tree.getLeft().setLeft(new Node(1));
        tree.getLeft().setRight(new Node(3));

        int sum = Tree.findSum(tree);
        assertEquals(193, sum);
    }
}
