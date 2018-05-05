package com.github.schmittjoaopedro.graph;

import java.util.HashMap;
import java.util.Map;

public class Node {

    public int value;

    public Map<Integer, Node> adjacency = new HashMap<Integer, Node>();

    public int degree() {
        return adjacency.size();
    }
}
