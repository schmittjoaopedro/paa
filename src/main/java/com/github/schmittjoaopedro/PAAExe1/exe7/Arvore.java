package com.github.schmittjoaopedro.PAAExe1.exe7;

public class Arvore {

    public int elem;

    public Arvore esquerda;

    public Arvore direita;

    public Arvore(int elem) {
        this.elem = elem;
    }

    public Arvore(int elem, Arvore e, Arvore d) {
        this.elem = elem;
        this.esquerda = e;
        this.direita = d;
    }
}
