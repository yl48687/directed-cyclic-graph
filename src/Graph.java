import java.util.*;

/**
 * Creates a directed cyclic {@code Graph}.
 */
public class Graph {
    private Map<String, Node> vertices;
    private Map<String, Map<String, String>> edgeLabels;

    /**
     * Initializes the graph.
     */
    public Graph() {
        vertices = new HashMap<>();
        edgeLabels = new HashMap<>();
    } // Graph

    /**
     * Adds an edge between two nodes.
     *
     * @param u The first node.
     * @param v The second node.
     * @param label The edge label.
     */
    public void addEdge(String u, String v, String label) {
        Node uNode = vertices.getOrDefault(u, new Node(u));
        Node vNode = vertices.getOrDefault(v, new Node(v));
        vertices.put(u, uNode);
        vertices.put(v, vNode);
        uNode.neighbors.add(vNode);
        Map<String, String> uEdgeLabels = edgeLabels.getOrDefault(u, new HashMap<>());
        uEdgeLabels.put(v, label);
        edgeLabels.put(u, uEdgeLabels);
    } // addEdge

    /**
     * Gets the edge label between two nodes.
     *
     * @param node1 The first node.
     * @param node2 The second node.
     * @return The edge label between the two nodes.
     */
    public String getEdgeLabel(String node1, String node2) {
        Map<String, String> edgeLabelsMap = edgeLabels.get(node1);
        if (edgeLabelsMap != null) {
            return edgeLabelsMap.get(node2);
        } // if
        return null;
    } // getEdgeLabel

    /**
     * Finds all directed paths between two nodes.
     *
     * @param start The starting node of the path.
     * @param end The ending node of the path.
     * @return the list of all directed paths between two nodes.
     */
    public List<List<String>> findPaths(String start, String end) {
        List<List<String>> paths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(start, end, currentPath, paths, visited);
        return paths;
    } // findPaths

    /**
     * Operates the depth-first search (DFS) to find all directed paths between two nodes.
     *
     * @param current The current node.
     * @param end The ending node.
     * @param currentPath The list of current path.
     * @param paths The list of all directed paths.
     * @param visited The set of nodes that have been visited.
     */
    private void dfs(String current, String end, List<String> currentPath, List<List<String>> paths,
                     Set<String> visited) {
        visited.add(current);
        currentPath.add(current);
        if (current.equals(end) && currentPath.size() > 1) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            Node currentVertex = vertices.get(current);
            if (currentVertex != null) {
                for (Node neighbor : currentVertex.neighbors) {
                    if (!visited.contains(neighbor.data)) {
                        dfs(neighbor.data, end, currentPath, paths, visited);
                    } // if
                } // for
            } // if
        } // if
        visited.remove(current);
        currentPath.remove(currentPath.size() - 1);
    } // dfs

    /**
     * Finds all directed paths of a given length (edge count) between two nodes.
     *
     * @param start The starting node of the path.
     * @param end The ending node of the path.
     * @param length The length of the path.
     * @return The list of all directed paths.
     */
    public List<List<String>> findPathsLength(String start, String end, int length) {
        List<List<String>> paths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfsLength(start, end, length, currentPath, paths, visited);
        return paths;
    } // findPathsLength

    /**
     * Operates the depth-first search (DFS) to find all directed paths of a given length (edge
     * count) between two nodes.
     *
     * @param current The current node.
     * @param end The ending node.
     * @param length The length of the path.
     * @param currentPath The list of current path.
     * @param paths The list of all directed paths.
     * @param visited The set of nodes that have been visited.
     */
    private void dfsLength(String current, String end, int length, List<String> currentPath,
                           List<List<String>> paths, Set<String> visited) {
        visited.add(current);
        currentPath.add(current);
        if (current.equals(end) && currentPath.size() == length + 1) {
            paths.add(new ArrayList<>(currentPath));
        } else if (currentPath.size() <= length) {
            Node currentVertex = vertices.get(current);
            if (currentVertex != null) {
                for (Node neighbor : currentVertex.neighbors) {
                    if (!visited.contains(neighbor.data)) {
                        dfsLength(neighbor.data, end, length, currentPath, paths, visited);
                    } // if
                } // for
            } // if
        } // if
        visited.remove(current);
        currentPath.remove(currentPath.size() - 1);
    } // dfsLength

    /**
     * Finds the shortest directed path(s) with the minimum number of edges.
     *
     * @param start The starting node of the path.
     * @param end The ending node of the path.
     * @return The list of all directed paths.
     */
    public List<List<String>> findShortestPaths(String start, String end) {
        List<List<String>> paths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        int[] shortestPathLength = {Integer.MAX_VALUE};
        dfsShortestPath(start, end, currentPath, paths, visited, 0, shortestPathLength);
        return paths;
    } // findShortestPaths

    /**
     * Operates the depth-first search (DFS) to find the shortest directed path(s) with the minimum
     * number of edges.
     *
     * @param current The current node.
     * @param end The ending node.
     * @param currentPath The list of current path.
     * @param paths The list of all directed paths.
     * @param visited The set of nodes that have been visited.
     * @param currentLength The length of the current path.
     * @param shortestPathLength The length of the shortest path.
     */
    private void dfsShortestPath(String current, String end, List<String> currentPath,
                                 List<List<String>> paths, Set<String> visited, int currentLength,
                                 int[] shortestPathLength) {
        visited.add(current);
        currentPath.add(current);
        if (current.equals(end) && currentLength <= shortestPathLength[0]) {
            if (currentLength < shortestPathLength[0]) {
                paths.clear();
                shortestPathLength[0] = currentLength;
            } // if
            paths.add(new ArrayList<>(currentPath));
        } else {
            Node currentVertex = vertices.get(current);
            if (currentVertex != null) {
                for (Node neighbor : currentVertex.neighbors) {
                    if (!visited.contains(neighbor.data)) {
                        dfsShortestPath(neighbor.data, end, currentPath, paths, visited,
                                        currentLength + 1, shortestPathLength);
                    } // if
                } // for
            } // if
        } // if
        visited.remove(current);
        currentPath.remove(currentPath.size() - 1);
    } // dfsShortestPath

    /**
     * Checks whether the {@code vertices} contains the {@code label}.
     *
     * @param label The label being searched for.
     * @return true if {@code vertices} contains the {@code label}; otherwise, false.
     */
    public boolean contains(String label) {
        return vertices.containsKey(label);
    } // contains

    /**
     * Gets a node with {@code label}.
     *
     * @param label The label being searched for.
     * @return the {@code vertices} containing {@code label}.
     */
    public Node getNode(String label) {
        return vertices.get(label);
    } // getNode
} // graph
