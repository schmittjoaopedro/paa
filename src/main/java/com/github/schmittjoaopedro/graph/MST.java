package com.github.schmittjoaopedro.graph;

import java.util.List;

public class MST {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4.0);
        graph.addEdge(0, 7, 8.0);
        graph.addEdge(1, 2, 8.0);
        graph.addEdge(1, 7, 11.0);
        graph.addEdge(2, 3, 7.0);
        graph.addEdge(2, 5, 4.0);
        graph.addEdge(2, 8, 2.0);
        graph.addEdge(3, 4, 9.0);
        graph.addEdge(3, 5, 14.0);
        graph.addEdge(4, 5, 10.0);
        graph.addEdge(5, 6, 2.0);
        graph.addEdge(6, 7, 1.0);
        graph.addEdge(6, 8, 6.0);
        graph.addEdge(7, 8, 7.0);
        Node root = graph.getNode(0);
        MST_PRIM(graph, root);
        for(Node n : graph.getNodes()) {
            if(n.parent != null)
                System.out.println("(" + n.parent.value + "," + n.value + ") = " + n.key);
        }
        dijkstra(graph, root);
        for(Node n : graph.getNodes()) {
            System.out.println(n.value + " = " + n.key);
        }
    }

    public static void MST_PRIM(Graph g, Node root) {
        for(Node node : g.getNodes()) {
            node.key = Integer.MAX_VALUE;
            node.parent = null;
        }
        root.key = 0;
        List<Node> Q = g.getNodes();
        buildHeap(Q);
        while(!Q.isEmpty()) {
            Node u = extractMin(Q);
            for(Node v : g.getAdjacent(u.value)) {
                if(Q.contains(v) && u.dist.get(v.value) < v.key) {
                    v.parent = u;
                    v.key = u.dist.get(v.value);
                    decreaseKey(Q, v);
                }
            }
        }
    }

    public static void dijkstra(Graph g, Node root) {
        for(Node node : g.getNodes()) {
            node.key = Integer.MAX_VALUE;
            node.parent = null;
        }
        root.key = 0;
        List<Node> Q = g.getNodes();
        buildHeap(Q);
        while(!Q.isEmpty()) {
            Node u = extractMin(Q);
            for(Node v : g.getAdjacent(u.value)) {
                if(Q.contains(v) && u.key + u.dist.get(v.value) < v.key) {
                    v.parent = u;
                    v.key = u.key + u.dist.get(v.value);
                    decreaseKey(Q, v);
                }
            }
        }
    }

    public static void decreaseKey(List<Node> q, Node key) {
        int i = q.indexOf(key);
        while (i > 0 && q.get(parent(i)).key > key.value) {
            q.set(i, q.get(parent(i)));
            i = parent(i);
        }
        q.set(i, key);
    }

    public static int parent(int i) {
        return i / 2;
    }

    public static Node extractMin(List<Node> q) {
        int last = q.size() - 1;
        Node aux = q.get(0);
        q.set(0, q.get(last));
        q.remove(last);
        heapify(q, 0);
        return aux;
    }

    public static void buildHeap(List<Node> a) {
        for (int i = a.size() / 2; i >= 0; i--) {
            heapify(a, i);
        }
    }

    public static void heapify(List<Node> a, int i) {
        int smallest = i;
        int e = (2 * i) + 1;
        int d = (2 * i) + 2;
        if (e < a.size() && a.get(e).key < a.get(i).key) {
            smallest = e;
        }
        if(d < a.size() && a.get(d).key < smallest) {
            smallest = d;
        }
        if(smallest != i) {
            Node aux = a.get(smallest);
            a.set(smallest, a.get(i));
            a.set(i, aux);
            heapify(a, smallest);
        }
    }

}
