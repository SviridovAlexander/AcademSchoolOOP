package com.asviridov.academit.tree_main;

import com.asviridov.academit.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(8);
        tree.add(3);
        tree.add(10);
        tree.add(1);
        tree.add(6);
        tree.add(14);
        tree.add(4);
        tree.add(7);
        tree.add(13);

        System.out.println("Nodes in breadth-first traversal order: ");
        tree.breadthFirstSearch();
        System.out.println();

        System.out.println("Nodes in depth-first traversal order: ");
        tree.depthFirstSearch();
        System.out.println();

        System.out.println("Nodes in order of depth-first traversal of the tree with recursion: ");
        tree.depthFirstSearchByRecursion();
        System.out.println();

        System.out.println("Tree size: " + tree.getSize());
        System.out.println();

        int data1 = 13;
        if (tree.contains(data1)) {
            System.out.println("Node with value " + data1 + " found.");
            System.out.println();
        } else {
            System.out.println("Node with value " + data1 + " not found.");
            System.out.println();
        }

        int data2 = 8;
        if (tree.remove(data2)) {
            System.out.println("Tree after removing node with value " + data2 + ": ");
            tree.breadthFirstSearch();
            System.out.println();
        } else {
            System.out.println("Node with value " + data2 + " was not found. Deletion is not possible.");
            System.out.println();
        }

        System.out.println("Tree size: " + tree.getSize());
    }
}