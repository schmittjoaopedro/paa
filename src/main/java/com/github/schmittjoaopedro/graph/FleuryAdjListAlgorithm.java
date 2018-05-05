package com.github.schmittjoaopedro.graph;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

/**
 * http://people.ku.edu/~jlmartin/courses/math105-F11/Lectures/chapter5-part2.pdf
 * https://www.geeksforgeeks.org/fleurys-algorithm-for-printing-eulerian-path/
 */
public class FleuryAdjListAlgorithm {


    private static String problems[] = {
            "graphs/euler-cycle-5_1.graph",
            "graphs/euler-cycle-16_1.graph",
            "graphs/euler-cycle-6_1.graph",
            "graphs/euler-path-5_1.graph",
            "graphs/euler-path-6_1.graph"
    };

    public static void main(String[] args) throws Exception {

        for(String problem : problems) {
            String graphLines[] = FileUtils.readFileToString(new File(problem), "UTF-8").split("\n");
            processGraph(graphLines, problem);
        }
    }

    public static void processGraph(String[] graphLines, String problem) {
        int V = Integer.valueOf(graphLines[0].replaceAll("\\|V\\|=", ""));
        Graph graph = new Graph();
        for(int l = 1; l < graphLines.length; l++) {
            int i = Integer.valueOf(graphLines[l].replaceAll("\\(", "").replaceAll(",.*?\\)", ""));
            int j = Integer.valueOf(graphLines[l].replaceAll("\\)", "").replaceAll("\\(.*?,", ""));
            graph.addEdge(i, j);
        }
        int oddCount = getOddVerticesCount(graph);

        if(oddCount == 0) {
            System.out.println(problem + " has 0 odd vertices = Euler cycle.");
            printRoute(getEulerPath(graph, false));
        } else if(oddCount == 2) {
            System.out.println(problem + " has 2 odd vertices = Euler path.");
            printRoute(getEulerPath(graph, true));
        } else {
            System.out.println(problem + " not have 0 or 2 odd vertices = not Euler path neither Euler cycle.");
        }
    }

    public static void printRoute(List<Node> path) {
        StringBuilder route = new StringBuilder();
        for(Node vertex : path) {
            route.append(vertex.value).append("-");
        }
        route.deleteCharAt(route.length() - 1);
        System.out.println(route);
    }

    public static int getOddVerticesCount(Graph graph) {
        int oddCount = 0;
        for(Node node : graph.getNodes()) {
            if(node.degree() % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount;
    }

    public static Node getEulerPathOrigin(Graph graph) {
        for(Node node : graph.getNodes()) {
            if(node.degree() % 2 == 1) {
                return node;
            }
        }
        throw new RuntimeException("Error on finding Euler path origin");
    }

    public static List<Node> getEulerPath(Graph graph, boolean isPath) {
        List<Node> path = new ArrayList<Node>();
        if(isPath) {
            path.add(getEulerPathOrigin(graph));
            getEulerPath(graph, path, path.get(0));
        } else {
            path.add(graph.getNodes().get(0));
            getEulerPath(graph, path, path.get(0));
        }
        return path;
    }

    public static void getEulerPath(Graph graph, List<Node> path, Node from) {
        Node[] nodes = from.adjacency.values().toArray(new Node[] {});
        for (Node to : nodes) {
            if(!isBridge(graph, from, to)) {
                path.add(to);
                graph.delEdge(from, to);
                getEulerPath(graph, path, to);
                break;
            }
        }
    }

    public static boolean isBridge(Graph graph, Node from, Node to) {
        if(from.adjacency.size() == 1) {
            return false;
        }
        int bridgeCount = getReachableNodesCount(new HashSet<Node>(), to);

        graph.delEdge(from, to);
        int nonBridgeCount = getReachableNodesCount(new HashSet<Node>(), to);

        graph.addEdge(from.value, to.value);
        return nonBridgeCount < bridgeCount;
    }

    public static int getReachableNodesCount(Set<Node> visited, Node from) {
        int count = 1;
        visited.add(from);
        Node[] nodes = from.adjacency.values().toArray(new Node[] {});
        for (Node to : nodes) {
            if(!visited.contains(to)) {
                count = count + getReachableNodesCount(visited, to);
            }
        }
        return count;
    }

}
