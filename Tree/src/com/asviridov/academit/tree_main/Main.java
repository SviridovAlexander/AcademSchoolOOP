package com.asviridov.academit.tree_main;

import com.asviridov.academit.tree.BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(8);
        binaryTree.add(3);
        binaryTree.add(10);
        binaryTree.add(1);
        binaryTree.add(6);
        binaryTree.add(14);
        binaryTree.add(4);
        binaryTree.add(7);
        binaryTree.add(13);

        System.out.println("Nodes in breadth-first traversal order: ");
        binaryTree.visitByWidth(result -> System.out.print(result + " "));
        System.out.println();

        System.out.println("Nodes in depth-first traversal order: ");
        binaryTree.visitByDepth(result -> System.out.print(result + " "));
        System.out.println();

        System.out.println("Nodes in order of depth-first traversal of the tree with recursion: ");
        binaryTree.visitByDepthWithRecursion(result -> System.out.print(result + " "));
        System.out.println();

        System.out.println("Tree size: " + binaryTree.getSize());
        System.out.println();

        int data1 = 13;
        if (binaryTree.contains(data1)) {
            System.out.println("Node with value " + data1 + " found.");
            System.out.println();
        } else {
            System.out.println("Node with value " + data1 + " not found.");
            System.out.println();
        }

        int data2 = 8;
        if (binaryTree.remove(data2)) {
            System.out.println("Tree after removing node with value " + data2 + ": ");
            binaryTree.visitByWidth(result -> System.out.print(result + " "));
            System.out.println();
        } else {
            System.out.println("Node with value " + data2 + " was not found. Deletion is not possible.");
            System.out.println();
        }

        System.out.println("Tree size: " + binaryTree.getSize());
    }
}