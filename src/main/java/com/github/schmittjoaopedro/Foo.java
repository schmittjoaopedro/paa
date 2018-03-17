package com.github.schmittjoaopedro;


/**
 * Imprime sequência binárias, o tamanho do array indica a quantidade de bits.
 * Ex: para um vetor com qualquer preenchimento de char, como char v[] = { 'a', 'b', 'c' };
 * será imprimido:
 *
 * 000
 * 001
 * 010
 * 011
 * 100
 * 101
 * 110
 * 111
 *
 * ==================================================================================
 * Com base na função foo, podemos fazer a seguinte análise de complexidade de tempo.
 *
 * Dado n = t temos a seguinte função de recorrência:
 * T(n) = 2*T(n-1) + 1
 * T(0) = t
 *
 * T(n-1) = 2*T(n-2)
 * T(n-2) = 2*T(n-3)
 * T(n-3) = 2*T(n-4)
 * ...
 * T(n-n) = T(0) = t
 *
 * Realizando as substituições temos:
 *
 * T(n) = 2*T(n-1)
 *
 * T(n) = 2*(2*T(n-2))
 *      = 2^2*T(n-2)
 *
 * T(n) = 2^2*(2*T(n-3))
 *      = 2^3*T(n-3)
 *
 * T(n) = 2^3*(2*T(n-4))
 *      = 2^4*T(n-4)
 * ...
 * T(n) = 2^n*T(n-n)
 * T(n) = 2^n*t
 *
 * como t = n temos
 *
 * T(n) = n * 2^n
 *
 * Assim a complexidade está na ordem de O(2^n)
 */
public class Foo {

    public static void main(String[] args) {
        char v[] = { 'a', 'b', 'c' };
        foo(v, v.length, v.length);
    }

    public static void foo(char[] v, int n, int t) {
        int i;                                  // O(1)
        if(n > 0) {                             // O(1)
            v[t-n] = '0';                       // O(1)
            foo(v, n-1, t);                     // T(n-1)
            v[t-n] = '1';                       // O(1)
            foo(v, n-1, t);                     // T(n-1)
        } else {
            for(i = 0; i < t; i++) {            // O(n)
                System.out.printf("%c", v[i]);  // O(1)
            }
            System.out.printf("\n");            // O(1)
        }
    }

}
