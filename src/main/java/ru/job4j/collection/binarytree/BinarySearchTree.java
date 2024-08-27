package ru.job4j.collection.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        Node currentNode = node;
        boolean result = false;
        if (key.compareTo(currentNode.key) > 0) {
            if (Objects.nonNull(currentNode.right)) {
                put(currentNode.right, key);
            } else {
                currentNode.right = new Node(key);
                result = true;
            }
        }
        if (key.compareTo(currentNode.key) < 0) {
            if (Objects.nonNull(currentNode.left)) {
                put(currentNode.left, key);
            } else {
                currentNode.left = new Node(key);
                result = true;
            }
        }
        return result;
    }

    public boolean contains(T key) {
        return Objects.nonNull(find(root, key));
    }

    private Node find(Node node, T key) {
        Node currentNode = node;
        Node result = null;
        if (Objects.nonNull(currentNode) && key.compareTo(currentNode.key) == 0) {
            result = currentNode;
        } else {
            if (Objects.nonNull(currentNode.left) && key.compareTo(currentNode.key) < 0) {
                result = find(currentNode.left, key);
            }
            if (Objects.nonNull(currentNode.right) && key.compareTo(currentNode.key) > 0) {
                result = find(currentNode.right, key);
            }
        }
        return result;
    }

    public boolean remove(T key) {
        /* Метод будет реализован в следующих уроках */
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPreOrder(node, result);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPostOrder(node, result);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        Node currentNode = node;
        Node result = null;
        if (Objects.nonNull(currentNode.left)) {
            result = minimum(currentNode.left);
        } else {
            result = currentNode;
        }
        return result;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        Node currentNode = node;
        Node result = null;
        if (Objects.nonNull(currentNode.right)) {
            result = maximum(currentNode.right);
        } else {
            result = currentNode;
        }
        return result;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        System.out.println(tree.toString());
    }
}
