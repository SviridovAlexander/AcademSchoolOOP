package com.asviridov.academit.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<E> comparator;

    public BinaryTree() {
        comparator = null;
    }

    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<E>) data1).compareTo(data2);
    }

    public void add(E data) {
        TreeNode<E> newNode = new TreeNode<>(data);

        if (size == 0) {
            root = newNode;
            size++;

            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int comparingResult = compare(currentNode.getData(), data);

            if (comparingResult > 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    break;
                } else {
                    currentNode = currentNode.getLeft();
                }
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    break;
                } else {
                    currentNode = currentNode.getRight();
                }
            }
        }

        size++;
    }

    public boolean contains(E data) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> node = root;

        while (node != null) {
            int comparisonResult = compare(node.getData(), data);

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
                node = node.getLeft();
                continue;
            }

            node = node.getRight();
        }

        return false;
    }

    public boolean remove(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> parentNode = null;

        while (currentNode != null) {
            int compareResult = compare(currentNode.getData(), data);

            if (compareResult == 0) {
                removeNode(parentNode, currentNode);
                return true;
            }

            parentNode = currentNode;

            if (compareResult > 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    private void removeNode(TreeNode<E> parentNode, TreeNode<E> currentNode) {
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (parentNode == null) {
                root = null;
            } else if (parentNode.getLeft() == currentNode) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (currentNode.getLeft() != null && currentNode.getRight() != null) {
            TreeNode<E> rightSubtreeMinNode = currentNode.getRight();
            TreeNode<E> rightSubtreeMinNodeParent = currentNode;

            while (rightSubtreeMinNode.getLeft() != null) {
                rightSubtreeMinNodeParent = rightSubtreeMinNode;
                rightSubtreeMinNode = rightSubtreeMinNode.getLeft();
            }

            if (rightSubtreeMinNodeParent != currentNode) {
                rightSubtreeMinNodeParent.setLeft(rightSubtreeMinNode.getRight());
                rightSubtreeMinNode.setRight(currentNode.getRight());
            }

            rightSubtreeMinNode.setLeft(currentNode.getLeft());

            if (parentNode == null) {
                root = rightSubtreeMinNode;
            } else if (parentNode.getLeft() == currentNode) {
                parentNode.setLeft(rightSubtreeMinNode);
            } else {
                parentNode.setRight(rightSubtreeMinNode);
            }
        } else {
            TreeNode<E> child = (currentNode.getLeft() != null) ? currentNode.getLeft() : currentNode.getRight();

            if (parentNode == null) {
                root = child;
            } else if (parentNode.getLeft() == currentNode) {
                parentNode.setLeft(child);
            } else {
                parentNode.setRight(child);
            }
        }

        size--;
    }

    public void visitByWidth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void visitByDepth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.poll();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    private static <E> void visitByDepthWithRecursion(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        visitByDepthWithRecursion(node.getLeft(), consumer);
        visitByDepthWithRecursion(node.getRight(), consumer);
    }

    public void visitByDepthWithRecursion(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        visitByDepthWithRecursion(root, consumer);
    }
}