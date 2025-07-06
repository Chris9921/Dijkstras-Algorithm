import java.util.*;

public class WeightedGraph {
    // This map keeps vertex name -> list of edges
    private Map<String, List<Edge>> adjList;

    // Edge class to store destination and weight of edge
    public static class Edge {
        public String to;      // where the edge goes
        public int weight;     // how heavy the edge is

        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // constructor, make empty graph (no vertices yet)
    public WeightedGraph() {
        adjList = new HashMap<>();
    }

    // Add edge from one vertex to another with some weight
    // also add reverse edge cause graph is undirected
    public void addEdge(String from, String to, int weight) {
        // if from vertex not in map, add it with empty list then add edge
        if (!adjList.containsKey(from)) {
            adjList.put(from, new ArrayList<>());
        }
        adjList.get(from).add(new Edge(to, weight));

        // same for to vertex (reverse edge)
        if (!adjList.containsKey(to)) {
            adjList.put(to, new ArrayList<>());
        }
        adjList.get(to).add(new Edge(from, weight));
    }

    // Get list of edges from a vertex (neighbors)
    public List<Edge> getNeighbors(String vertex) {
        if (adjList.containsKey(vertex)) {
            return adjList.get(vertex);
        }
        // if no vertex found, return empty list (no neighbors)
        return new ArrayList<>();
    }

    // Get all vertices in the graph (keys of map)
    public Set<String> getVertices() {
        return adjList.keySet();
    }
}
