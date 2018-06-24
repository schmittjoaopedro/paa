package com.github.schmittjoaopedro.dynamic_programming;

public class SubSetSum {

    public static void main(String[] args) {

        int x[] = {1, 3, 5, 7};
        int n = x.length;
        int t = 13;

        boolean S[][] = new boolean[n + 1][t + 1];

        // Algorithm
        S[n][0] = true;
        for (int j = 1; j <= t; j++) {
            S[n][j] = false;
        }
        for (int i = n - 1; i >= 0; i--) {
            S[i][0] = true;
            for (int j = 1; j <= x[i] - 1; j++) {
                S[i][j] = S[i + 1][j];
            }
            for (int j = x[i]; j <= t; j++) {
                S[i][j] = S[i + 1][j] || S[i + 1][j - x[i]];
            }
        }
        System.out.println(S[0][t]);
    }

}
