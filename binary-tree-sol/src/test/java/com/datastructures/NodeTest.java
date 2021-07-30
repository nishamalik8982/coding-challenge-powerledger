package com.datastructures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() {
    }


    @Test
    public void testNodeWithLessThanZero() {

        int nodeValue = -1;

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Node(nodeValue),
            "Invalid value ("+nodeValue+") to Node()"
        );

        assertTrue(thrown.getMessage().contains("Value (-1) must be between 0 and 9"));
    }

    public void testNodeWithGreaterThanNine() {
        int nodeValue = 10;

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Node(nodeValue),
                "Invalid value ("+nodeValue+") to Node()"
        );

        assertTrue(thrown.getMessage().contains("Value (10) must be between 0 and 9"));
    }

    public void testGetData() {
        IntStream
            .rangeClosed(0, 9)
            .forEach(
                (int value) -> {
                    Node node = new Node(value);
                    assertNotNull(node);
                    assertNull(node.getLeft());
                    assertNull(node.getRight());
                    assertEquals(value, node.getData());
                }
            );
    }

    public void testSetLeft() {

        int rootValue = 5;
        Node root = new Node(rootValue);

        int leftValue = 2;
        Node left = new Node(leftValue);
        root.setLeft(left);

        assertNotNull(root.getLeft());
    }

    public void testGetLeft() {

        int rootValue = 5;
        Node root = new Node(rootValue);

        int leftValue = 3;
        Node left = new Node(leftValue);
        root.setLeft(left);

        assertNotNull(root.getLeft());
        assertEquals(leftValue, root.getLeft().getData());
    }

    public void testSetRight() {

        int rootValue = 5;
        Node root = new Node(rootValue);

        int rightValue = 9;
        Node right = new Node(rightValue);
        root.setRight(right);

        assertNotNull(root.getRight());
    }

    public void testGetRight() {

        int rootValue = 5;
        Node root = new Node(rootValue);

        int rightValue = 7;
        Node right = new Node(rightValue);
        root.setRight(right);

        assertNotNull(root.getRight());
        assertEquals(rightValue, root.getRight().getData());
    }
}
