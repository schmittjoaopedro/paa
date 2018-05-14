package com.github.schmittjoaopedro.PAAExe1;

/**
 * Ambas as funões power podem ser calculadas da seguinte forma
 *
 * Dado uma base b e um expoente e, temos a seguinte relação de recorrência no
 * caso da função recursiva.
 *
 * T(e)     = T(e/2)   + 1
 * T(e/2)   = T(e/2^2) + 1
 * T(e/2^2) = T(e/2^3) + 1
 * ...
 * T(e/e)   = 2       -> Neste caso é dois porque quando invocamos T(1) também invocamos T(0) e retornamos b*1*1
 *
 * Substituindo temos:
 *
 * T(e)     = T(e/2)    + 1
 * T(e)     = (T(e/2^2) + 1) + 1
 * T(e)     = (T(e/2^3) + 1) + 2
 * ...
 * T(e)     = T(e/2^l) + l
 * T(e)     = l + 2
 *
 * Para termos T(1) precisamos invocar T(e/e) assim e/2^l = 1 o que implica que:
 * e = 2^l -> l = Math.log2(e)
 *
 * Assim a equação fechada é dada por:
 * T(n) = Math.log2(e) + 2
 *
 * E a complexidade de tempos está na ordem de: O(log e)
 *
 * Para resolver esse problema é interessante usarmos alguns conceitos da teoria dos números,
 * sabemos que todos números pares são divísiveis pelo número prímo dois, dessa forma o que
 * é feito é separar essa parte da parte ímpar para resolver potencia.
 *
 * No caso da função recursiva a complexidade de espaço e O(log e) devida a pilha de chamadas
 * e por não ser recursão de cauda.
 */
public class Power {

    public static void main(String[] args) {
        comp(2,0);
        comp(2,1);
        comp(2,2);
        comp(2,3);
        comp(2,4);
        comp(2,5);
        comp(2,6);
        comp(2,7);
        comp(2,8);

        comp(3,0);
        comp(3,1);
        comp(3,2);
        comp(3,3);
        comp(3,4);

        comp(6,5);
        comp(9,10);
    }

    public static void comp(long b, long e) {
        System.out.println(power_rec(b,e) + " = " + power_ite(b,e));
    }

    public static long power_rec(long b, long e) {
        long r;
        if(e == 0) {
            return 1;
        }
        r = power_rec(b, e/2);
        if(e % 2 == 0) {
            return r*r;
        } else {
            return r*r*b;
        }
    }

    public static long power_ite(long b, long e) {
        if(e == 0) return 1;
        if(e == 1) return b;
        long k = (long) (Math.log10(e) / Math.log10(2));
        long r = 1;
        for(long i = k; i >= 0; i--) {
            long rl = e / (long) Math.pow(2, i);
            if(rl % 2 == 0) r = r * r;
            else r = r * r * b;
        }
        return r;
    }

}
