package com.github.schmittjoaopedro.PAAExe1;

public class MultMatrix {

    public static void main(String[] args) {
        double a[][] = {{1,1,1}};
        double b[][] = {{1,1,1},{1,1,1},{1,1,1}};
        double r[][] = multMatrix(a, b);
        for (int i = 0; i < r.length; i++) {
            for(int j = 0; j < r[i].length; j++) {
                System.out.print(r[i][j] + " , ");
            }
            System.out.println();
        }
    }

    public static double[][] multMatrix(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        int rS = result.length;
        int cS = result[0].length;
        for(int r = 0; r < rS; r++) {
            for(int c = 0; c < cS; c++) {
                 for(int i = 0; i < a[0].length; i++) {
                     result[r][c] += a[r][i] * b[i][c];
                 }
            }
        }
        return result;
    }

}
