package com.github.schmittjoaopedro.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Graph {

    private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

    public void addEdge(int i, int j) {
        Node from = getNode(i);
        Node to = getNode(j);
        from.adjacency.put(j, to);
        to.adjacency.put(i, from);
    }

    public void addEdge(int i, int j, double cost) {
        Node from = getNode(i);
        Node to = getNode(j);
        from.adjacency.put(j, to);
        from.dist.put(j, cost);
        to.adjacency.put(i, from);
        to.dist.put(i, cost);
    }

    public Node getNode(int i) {
        if(nodes.containsKey(i)) {
            return nodes.get(i);
        } else {
            Node node = new Node();
            node.value = i;
            nodes.put(i, node);
            return node;
        }
    }

    public double getDist(int i, int j) {
        if(nodes.containsKey(i) && getNode(i).dist.containsKey(j)) {
            return getNode(i).dist.get(j);
        } else {
            return 0;
        }
    }

    public List<Node> getAdjacent(int i) {
        if(nodes.containsKey(i)) {
            return new ArrayList<Node>(Arrays.asList(nodes.get(i).adjacency.values().toArray(new Node[] {})));
        } else {
            return new ArrayList<Node>();
        }
    }

    public void delEdge(Node i, Node j) {
        if(nodes.containsKey(i.value)) {
            nodes.get(i.value).adjacency.remove(j.value);
            nodes.get(i.value).dist.remove(j.value);
            nodes.get(j.value).adjacency.remove(i.value);
            nodes.get(j.value).dist.remove(i.value);
        }
    }

    public List<Node> getNodes() {
        return new ArrayList<Node>(Arrays.asList(nodes.values().toArray(new Node[] {})));
    }

    public int getSize() {
        return nodes.size();
    }
}
