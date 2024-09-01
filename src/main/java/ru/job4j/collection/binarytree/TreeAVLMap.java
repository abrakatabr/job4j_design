package ru.job4j.collection.binarytree;

import java.util.*;
import java.util.stream.Collectors;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean insert(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key)) {
            root = insert(root, key, value);
            result = true;
        }
        return result;
    }

    private Node insert(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = insert(node.left, key, value);
            } else if (comparisonResult > 0) {
                node.right = insert(node.right, key, value);
            } else {
                node.value = value;
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        if (Objects.nonNull(node.right)) {
            Node tempNodeParent = node;
            node = node.right;
            Node tempNodeChild = node.left;
            node.left = tempNodeParent;
            tempNodeParent.right = tempNodeChild;
            updateHeight(tempNodeParent);
        }
        return node;
    }

    private Node rightRotation(Node node) {
        if (Objects.nonNull(node.left)) {
            Node tempNodeParent = node;
            node = node.left;
            Node tempNodeChild = node.right;
            node.right = tempNodeParent;
            tempNodeParent.left = tempNodeChild;
            updateHeight(tempNodeParent);
        }
        return node;
    }

    public boolean contains(T key) {
        return Objects.nonNull(find(root, key));
    }

    private Node find(Node node, T key) {
        Node currentNode = node;
        Node result = null;
        if (Objects.nonNull(currentNode) && key.compareTo(currentNode.key) == 0) {
            result = currentNode;
        } else if (Objects.nonNull(currentNode)) {
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
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, key);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
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

    private List<Node> inSymmetricalOrder(Node localRoot, List<Node> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public V get(T key) {
        Node node = find(root, key);
        return node == null ? null : node.value;
    }

    public Set<T> keySet() {
        List<Node> result = new ArrayList<>();
        return inSymmetricalOrder(root, result).stream()
                .map(n -> n.key).collect(Collectors.toSet());
    }

    public Collection<V> values() {
        List<Node> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result).stream()
                .map(n -> n.value).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
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
            return String.valueOf(key) + "-" + String.valueOf(value);
        }
    }
}
