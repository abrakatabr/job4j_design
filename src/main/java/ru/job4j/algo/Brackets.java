package ru.job4j.algo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean result = true;
        for (char ch : chars) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
                continue;
            } else if (ch == ')' && !stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else if (ch == ']' && !stack.empty() && stack.peek() == '[') {
                stack.pop();
            } else if (ch == '}' && !stack.empty() && stack.peek() == '{') {
                stack.pop();
            } else {
                result = false;
            }
        }
        if (!stack.empty()) {
            result = false;
        }
        return  result;
    }
}
