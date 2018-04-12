package com.github.schmittjoaopedro.integer.gauss;

import java.math.BigInteger;

public class GaussMultiplication {

    public static void main(String[] args) {
        //String num1 = "234567891012938120938123908124210942189362142197411";
        //String num2 = "439058203791837598713984795832758275023875092384092";
        String num1 = "10040";
        String num2 = "1201232342342";

        BigInteger bigNum1 = new BigInteger(num1);
        BigInteger bigNum2 = new BigInteger(num2);
        System.out.println(bigNum1.multiply(bigNum2));

        String x = bigNum1.toString(2);
        String y = bigNum2.toString(2);
        System.out.println(new BigInteger(multiply(x, y), 2).toString(10));
        System.out.println(new BigInteger(new Multiplication(x, y).karatsuba(x, y), 2).toString(10));
    }

    private static String multiply(String x, String y) {
        int n = Math.max(x.length(), y.length());
        //if (n == 1) return multiplyBinary(x, y);
        if(x.length() <= n || y.length() <= n) {
            return multiplyBinary(x, y);
        }

        int middle = n / 2;
        //String xl = getSubBinary(x, 0, middle);
        //String xr = getSubBinary(x, middle, n);
        //String yl = getSubBinary(y, 0, middle);
        //String yr = getSubBinary(y, middle, n);
        String xl = x.substring(0, middle);
        String xr = x.substring(middle, x.length());
        String yl = y.substring(0, middle);
        String yr = y.substring(middle, y.length());

        String P1 = multiply(xl, yl);
        String P2 = multiply(xr, yr);
        //String P3 = multiply(addBinary(xl, xr), addBinary(yl, yr));
        String P3 = multiply(addBinary(xl, yl), addBinary(xr, yr));

        String T1 = powBase2Binary(P1, n);
        String P3P1 = subBinary(P3, P1);
        String P3P2 = subBinary(P3P1, P2);
        String T2 = powBase2Binary(P3P2, (int) Math.ceil(n / 2.0));
        return addBinary(T1, addBinary(T2, P2));
    }

    private static String multiplyBinary(String x, String y) {
        if (x.length() == 0) return y;
        if (y.length() == 0) return x;
        return new BigInteger(x, 2).multiply(new BigInteger(y, 2)).toString(2);
    }

    private static String addBinary(String x, String y) {
        if (x.length() == 0) return y;
        if (y.length() == 0) return x;
        return new BigInteger(x, 2).add(new BigInteger(y, 2)).toString(2);
    }

    private static String subBinary(String x, String y) {
        if (x.length() == 0) return y;
        if (y.length() == 0) return x;
        return new BigInteger(x, 2).subtract(new BigInteger(y, 2)).toString(2);
    }

    private static String powBase2Binary(String p, int n) {
        return new BigInteger(p, 2).multiply(new BigInteger("10", 2).pow(n)).toString(2);
    }

}
