package com.asviridov.academit.tree;

import java.util.*;

public class Tree<T> {
    public TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public Tree() {
        this.root = null;
    }

    public int getSize() {
        return size;
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int getComparisonResult(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }


        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    public void add(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);

        if (size == 0) {
            root = newNode;
            size++;

            return;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            int comparingResult = getComparisonResult(data, currentNode.getData());

            if (comparingResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    break;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    break;
                }
            }
        }

        size++;
    }

    public boolean contains(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> node = root;

        while (node != null) {
            int comparisonResult = getComparisonResult(node.getData(), data);

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

    public boolean remove(T data) {
        if (getComparisonResult(root.getData(), data) == 0) {
            removeRoot();
            return true;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;

        while (currentNode != null) {
            int compareResult = getComparisonResult(currentNode.getData(), data);

            if (compareResult == 0) {
                removeCurrentNode(parentNode, currentNode);
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

    private void removeCurrentNode(TreeNode<T> parentNode, TreeNode<T> currentNode) {
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (parentNode.getRight() == currentNode) {
                parentNode.setRight(null);
            } else {
                parentNode.setLeft(null);
            }
        }
        if (currentNode.getLeft() != null && currentNode.getRight() == null) {
            if (parentNode.getLeft() == currentNode) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight((currentNode.getLeft()));
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() != null) {
            if (parentNode.getRight() == currentNode) {
                parentNode.setRight(currentNode.getRight());
            } else {
                parentNode.setLeft(currentNode.getRight());
            }
        }

        if (currentNode.getLeft() != null && currentNode.getRight() != null) {
            TreeNode<T> leftList = currentNode.getRight();
            TreeNode<T> leftListParent = currentNode;

            if (leftList.getLeft() == null) {
                leftList.setLeft(currentNode.getLeft());
                parentNode.setRight(leftList);
            } else {
                while (leftList.getLeft() != null) {
                    leftListParent = leftList;
                    leftList = leftList.getLeft();
                }

                leftListParent.setLeft(leftList.getRight());
                currentNode.setData(leftList.getData());

            }
        }

        size--;
    }

    private void removeRoot() {
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            size--;
            return;
        }

        if (root.getLeft() == null) {
            root = root.getRight();
        }

        if (root.getRight() == null) {
            root = root.getLeft();
        }

        TreeNode<T> leftList = root.getRight();
        TreeNode<T> leftListParent = root;

        if (leftList.getLeft() == null) {
            leftList.setLeft(root.getLeft());
        } else {
            while (leftList.getLeft() != null) {
                leftListParent = leftList;
                leftList = leftList.getLeft();
            }

            leftListParent.setLeft(leftList.getRight());
            leftList.setLeft(root.getLeft());
            leftList.setRight(root.getRight());
        }

        root = leftList;
        size--;
    }

    public void breadthFirstSearch() {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            System.out.println(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthFirstSearch() {
        if (size == 0) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.poll();
            System.out.println(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    private static <T> void recursiveDepthFirstSearch(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getData());

        recursiveDepthFirstSearch(node.getLeft());
        recursiveDepthFirstSearch(node.getRight());
    }

    public void depthFirstSearchByRecursion() {
        if (size == 0) {
            return;
        }

        recursiveDepthFirstSearch(root);
    }
}