package com.github.schmittjoaopedro.subsetsum;

import java.util.Stack;

public class SubSetSum {

    public static void main(String[] args) {

        int S[] = {
                1001001,
                1000110,
                100001,
                101110,
                10011,
                11100,
                1000,
                2000,
                100,
                200,
                10,
                20,
                1,
                2
        };
        int t = 1114444;

        Stack<Integer> visited = new Stack<Integer>();
        for (int i = 0; i < S.length; i++) {
            subSetSum(S, 0, visited, i, t);
            if (!visited.isEmpty()) break;
        }
        printStack(visited, S);
    }

    private static void printStack(Stack<Integer> visited, int S[]) {
        for (int i : visited) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static boolean subSetSum(int S[], int cur, Stack<Integer> stack, int idx, int t) {
        stack.push(idx);
        printStack(stack, S);
        cur += S[idx];
        if (cur == t) {
            return true;
        }
        if (stack.size() < S.length) {
            for (int i = 0; i < S.length; i++) {
                if (!stack.contains(i)) {
                    if (subSetSum(S, cur, stack, i, t)) {
                        return true;
                    }
                }
            }
        }
        stack.pop();
        return false;
    }

}
