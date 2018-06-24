package com.github.schmittjoaopedro.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public static void main(String[] args) {
        for (int k = 1; k < 40; k++) {
            Map<Integer, Integer> table = new HashMap<Integer, Integer>();
            System.out.println(k + " = " + getFibonacciTopDown(k, table));
        }
        for (int k = 1; k < 40; k++) {
            System.out.println(k + " = " + getFibonacciBottomUp(k));
        }
    }

    public static int getFibonacciTopDown(int k, Map<Integer, Integer> table) {
        if (k == 0 || k == 1) {
            return k;
        } else {
            if (!table.containsKey(k)) {
                table.put(k, getFibonacciTopDown(k - 1, table) + getFibonacciTopDown(k - 2, table));
            }
            return table.get(k);
        }
    }

    public static int getFibonacciBottomUp(int k) {
        if (k == 0 || k == 1) {
            return k;
        } else {
            Map<Integer, Integer> table = new HashMap<Integer, Integer>();
            table.put(0, 0);
            table.put(1, 1);
            for (int i = 2; i <= k; i++) {
                table.put(i, table.get(i - 1) + table.get(i - 2));
            }
            return table.get(k);
        }
    }

}
