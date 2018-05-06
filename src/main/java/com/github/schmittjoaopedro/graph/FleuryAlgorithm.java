package com.github.schmittjoaopedro.graph;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * http://people.ku.edu/~jlmartin/courses/math105-F11/Lectures/chapter5-part2.pdf
 * https://www.geeksforgeeks.org/fleurys-algorithm-for-printing-eulerian-path/
 */
public class FleuryAlgorithm {

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
        int graph[][] = new int[V][V];
        for(int l = 1; l < graphLines.length; l++) {
            int i = Integer.valueOf(graphLines[l].replaceAll("\\(", "").replaceAll(",.*?\\)", ""));
            int j = Integer.valueOf(graphLines[l].replaceAll("\\)", "").replaceAll("\\(.*?,", ""));
            graph[i][j] = 1;
            graph[j][i] = 1;
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

    public static void printRoute(List<Integer> path) {
        StringBuilder route = new StringBuilder();
        for(Integer vertex : path) {
            route.append(vertex).append("-");
        }
        route.deleteCharAt(route.length() - 1);
        System.out.println(route);
    }

    public static int getOddVerticesCount(int[][] graph) {
        int oddCount = 0;
        for(int i = 0; i < graph.length; i++) {
            int degree = 0;
            for(int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] == 1) {
                    degree++;
                }
            }
            if(degree % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount;
    }

    public static int getEulerPathOrigin(int[][] graph) {
        for(int i = 0; i < graph.length; i++) {
            int degree = 0;
            for(int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] == 1) {
                    degree++;
                }
            }
            if(degree % 2 == 1) {
                return i;
            }
        }
        throw new RuntimeException("Error on finding Euler path origin");
    }

    public static List<Integer> getEulerPath(int graph[][], boolean isPath) {
        List<Integer> path = new ArrayList<Integer>();
        if(isPath) {
            path.add(getEulerPathOrigin(graph));
            getEulerPath(graph, path, path.get(0));
        } else {
            getEulerPath(graph, path, 0);
            path.add(0, 0);
        }
        return path;
    }

    public static void getEulerPath(int graph[][], List<Integer> path, int i) {
        for(int j = 0; j < graph[i].length; j++) {
            if(graph[i][j] == 1 && !isBridge(graph, path, i, j)) {
                path.add(j);
                graph[j][i] = graph[i][j] = 0;
                getEulerPath(graph, path, j);
            }
        }
    }

    public static boolean isBridge(int graph[][], List<Integer> path, int i, int j) {
        int pathCount = 0;
        for(int p = 0; p < graph[i].length; p++) {
            if(graph[i][p] == 1) pathCount++;
        }
        if(pathCount == 1) {
            return false;
        }
        int visited[] = new int[graph.length];
        int bridgeCount = DFS(graph, visited, j);

        graph[i][j] = graph[j][i] = 0;
        visited= new int[graph.length];
        int nonBridgeCount = DFS(graph, visited, j);

        graph[i][j] = graph[j][i] = 1;
        return nonBridgeCount < bridgeCount;
    }

    public static int DFS(int graph[][], int visited[], int i) {
        int count = 1;
        visited[i] = 1;
        for(int j = 0; j < graph[i].length; j++) {
            if(graph[i][j] == 1 && visited[j] == 0) {
                count = count + DFS(graph, visited, j);
            }
        }
        return count;
    }
}
