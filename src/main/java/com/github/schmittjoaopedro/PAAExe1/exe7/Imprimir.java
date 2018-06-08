package com.github.schmittjoaopedro.PAAExe1.exe7;

public class Imprimir {

    public static void main(String[] args) {
        Arvore[] a = new Arvore[15];
        for(int i = 0; i < 15; i++) {
            a[i] = new Arvore(i + 1);
        }
        a[7].esquerda = a[3];
        a[3].esquerda = a[1];
        a[1].esquerda = a[0];
        a[1].direita = a[2];
        a[3].direita = a[5];
        a[5].esquerda = a[4];
        a[5].direita = a[6];
        a[7].direita = a[11];
        a[11].esquerda = a[9];
        a[9].esquerda = a[8];
        a[9].direita = a[10];
        a[11].direita = a[13];
        a[13].esquerda = a[12];
        a[13].direita = a[14];
        imprimir(a[7]);
    }

    public static void imprimir(Arvore r) {
        if(r != null) {
            imprimir(r.esquerda);
            System.out.println(r.elem);
            imprimir(r.direita);
        }
    }

}
