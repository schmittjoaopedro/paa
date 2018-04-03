package com.github.schmittjoaopedro.heap;

public class Heap {

    public static void main(String[] args) {
        int a[] = {1, 6, 5, 3, 7, 8, 4, 2};
        buildHeap(a, a.length);
//        int a[] = {12,6,3,2,7,1,4,9,10,12,5,8,11};
//        heapSort(a, a.length);
        System.out.print("| ");
        for(int i : a) {
            System.out.print(i + " | ");
        }
        System.out.println();
    }

    public static void heapify_ite(int a[], int n, int i) {
        int e, d, maior = -1;
        while(maior != i) {
            e = esquerda(i);
            d = direita(i);
            if(e < n && a[e] > a[i]) {
                maior = e;
            } else {
                maior = i;
            }
            if(d < n && a[d] > a[maior]) {
                maior = d;
            }
            if(maior != i) {
                swap(a, i, maior);
                i = maior;
                maior = -1;
            }
        }
    }

    public static void heapify(int a[], int n, int i) {
        int e, d, maior, aux;
        e = esquerda(i);
        d = direita(i);
        if(e < n && a[e] > a[i]) {
            maior = e;
        } else {
            maior = i;
        }
        if(d < n && a[d] > a[maior]) {
            maior = d;
        }
        if(maior != i) {
            aux = a[i];
            a[i] = a[maior];
            a[maior] = aux;
            heapify(a, n, maior);
        }
    }

    public static int heapExtract(int a[], int n) {
        int maior;
        maior = a[0];
        a[0] = a[n-1];
        heapify(a, n-1, 0);
        return maior;
    }

    public static void buildHeap(int a[], int n) {
        int i;
        for(i = n/2; i>=0; i--) {
            heapify(a, n, i);
        }
    }

    public static int esquerda(int i) {
        return (2 * i + 1);
    }

    public static int direita(int i) {
        return (2 * i + 2);
    }

    public static void swap(int a[], int i, int j) {
        int aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }

    public static void heapSort(int a[], int n) {
        int i;
        buildHeap(a, n);
        for(i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            heapify(a, i, 0);
        }
    }

}
