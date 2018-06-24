package com.github.schmittjoaopedro.dynamic_programming;

public class ChangeMakingProblem {

    public static void main(String[] args) {
        int[] x = {1, 2, 5, 10, 25, 50, 100};
        int t = 137;

        int c[] = new int[t + 1];
        int s[] = new int[t + 1];

        c[0] = 0;
        for (int p = 0; p <= t; p++) {
            int min = Integer.MAX_VALUE;
            int coin = 0;
            for (int i = 0; i < x.length; i++) {
                if (x[i] <= p && c[p - x[i]] + 1 <= min) { // Já existe algum troco que pode ser reutilizado
                    min = c[p - x[i]] + 1;
                    coin = i;
                }
            }
            c[p] = min;
            s[p] = coin;
        }

        while (t > 0) {
            System.out.println(x[s[t]]);
            t = t - x[s[t]];
        }
    }

}
