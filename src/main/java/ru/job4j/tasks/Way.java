package ru.job4j.tasks;

import java.util.*;

public class Way {
    Map<Integer, List<Integer>> map = new HashMap<>();
    Node start;
    Node finish;

    public Way(int start, int finish, int[][] allWays) {
        this.start = new Node(start);
        this.finish = new Node(finish);
        for (int i = 0; i < allWays.length; i++) {
            if (map.containsKey(allWays[i][0])) {
                List<Integer> list = map.get(allWays[i][0]);
                list.add(allWays[i][1]);
                map.put(allWays[i][0], list);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(allWays[i][1]);
                map.put(allWays[i][0], list);
            }
        }
    }

    public void setNode() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (queue.size() > 0) {
            Node tempNode = queue.poll();
            if (map.get(tempNode.city) != null) {
                for (Integer way : map.get(tempNode.city)) {
                    if (way == finish.city) {
                        tempNode.ways.add(finish);
                        queue.add(finish);
                        continue;
                    }
                    if (way != null) {
                        Node newNode = new Node(way);
                        tempNode.ways.add(newNode);
                        queue.add(newNode);
                    }
                }
            }
        }
    }

    public void printTree() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (queue.size() > 0) {
            Node tempNode = queue.poll();
            System.out.print(tempNode.city);
            if (tempNode.ways.size() > 0) {
                for (Node node : tempNode.ways) {
                    queue.add(node);
                }
            }
        }
        System.out.println();
    }

    public int[] findWay() {
        int[] result = null;
        Deque<Node> stack = new LinkedList<>();
        List<Integer> visit = new ArrayList<>();
        stack.add(start);
        while (stack.size() > 0) {
            Node tempNode = stack.peekLast();
            if (tempNode.ways.size() > 0 && !visit.contains(tempNode.city)) {
                if (tempNode.ways.contains(finish)) {
                    visit.add(tempNode.city);
                    stack.addLast(finish);
                    visit.add(finish.city);
                    Deque<Node> resultStack = new LinkedList<>();
                    for (Node node : stack) {
                        if (visit.contains(node.city)) {
                            resultStack.addLast(node);
                        }
                    }
                    result = new int[resultStack.size()];
                    int i = 0;
                    for (Node node : resultStack) {
                        result[i] = node.city;
                        i++;
                    }
                    break;
                }
                for (Node node : tempNode.ways) {
                    stack.addLast(node);
                }
            }
            if (tempNode.ways.size() == 0 || visit.contains(tempNode.city)) {
                stack.pollLast();
            }
            visit.add(tempNode.city);
            if (stack.size() == 0) {
                System.out.println("Way not found");
            }
        }
        return result;
    }

    private class Node {
        private int city;
        private List<Node> ways = new ArrayList();

        public Node(int city) {
            this.city = city;
        }
    }

    public static void main(String[] args) {
        int start = 1;
        int finish = 4;
        int[][] connections = {{1, 2}, {3, 4}, {2, 3}};
        Way way = new Way(start, finish, connections);
        for (Map.Entry<Integer, List<Integer>> entry : way.map.entrySet()) {
            System.out.print(entry.getKey());
            System.out.println(entry.getValue());
        }
        way.setNode();
        way.printTree();
        for (int city : way.findWay()) {
            System.out.print(city);
        }
    }
}
