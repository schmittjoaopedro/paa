package com.github.schmittjoaopedro.PAAExe1;

/**
 *
 */
public class Fibonacci {

    public static void main(String[] args) {
        int n = 45;
        System.out.print("x <- c(");
        for(long i = 0; i < n; i++) {
            if(i != 0) System.out.print(",");
            System.out.print(i);
        }
        System.out.print(")\ny <- c(");
        for(long i = 0; i < n; i++) {
            long time = System.nanoTime();
            fibonacci_rec(i);
            if(i != 0) System.out.print(",");
            System.out.print(System.nanoTime() - time);
        }
        System.out.print(")\nz <- c(");
        for(long i = 0; i < n; i++) {
            long time = System.nanoTime();
            fibonacci_ite(i);
            if(i != 0) System.out.print(",");
            System.out.print(System.nanoTime() - time);
        }
        System.out.print(")\nplot(x,y, type = \"l\")\nlines(z)");
    }

    public static long fibonacci_rec(long n) {
        if(n == 0 || n == 1) return n;
        return fibonacci_rec(n-1) + fibonacci_rec(n-2);
    }

    public static long fibonacci_ite(long n) {
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        long n1 = 1, n2 = 1;
        long tot = 0;
        for(long i = 2; i < n; i++) {
            tot = n1 + n2;
            n1 = n2;
            n2 = tot;
        }
        return tot;
    }

}
