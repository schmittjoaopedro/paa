package com.github.schmittjoaopedro.graph;

import java.util.HashMap;
import java.util.Map;

public class Node {

    public int value;

    public double key;

    public Node parent;

    public Map<Integer, Node> adjacency = new HashMap<Integer, Node>();

    public Map<Integer, Double> dist = new HashMap<Integer, Double>();

    public int degree() {
        return adjacency.size();
    }

}
