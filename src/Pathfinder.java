import java.util.*;

public class Pathfinder {

    // This class holds the result of the shortest path search:
    // the distance and the path as a list of vertices
    public static class Result {
        public int distance;
        public List<String> path;

        // constructor to set distance and path
        public Result(int distance, List<String> path) {
            this.distance = distance;
            this.path = path;
        }
    }

    // This method runs Dijkstra's algorithm on a graph from start to end
    public static Result dijkstra(WeightedGraph graph, String start, String end) {
        // distances map holds the shortest known distance to each vertex
        Map<String, Integer> distances = new HashMap<>();
        // previous map to remember the best path (which vertex we came from)
        Map<String, String> previous = new HashMap<>();
        // priority queue to pick the next vertex with the smallest distance
        PriorityQueue<VertexDistance> pq = new PriorityQueue<>(Comparator.comparingInt(vd -> vd.distance));

        // set all distances to a very big number at start (unknown)
        for (String vertex : graph.getVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        // distance to start vertex is 0 (because we start there)
        distances.put(start, 0);

        // add the start vertex to the queue with distance 0
        pq.add(new VertexDistance(start, 0));

        // while there are still vertices to process in the queue
        while (!pq.isEmpty()) {
            VertexDistance current = pq.poll();  // get vertex with smallest distance
            String currentVertex = current.vertex;
            int currentDist = current.distance;

            // if we reached the end vertex, stop searching
            if (currentVertex.equals(end)) {
                break;
            }

            // if this distance is worse than a known one, skip this vertex
            if (currentDist > distances.get(currentVertex)) {
                continue; // we already have a better way
            }

            // look at all neighbors of current vertex
            for (WeightedGraph.Edge edge : graph.getNeighbors(currentVertex)) {
                String neighbor = edge.to;
                int newDist = currentDist + edge.weight;  // new distance via current vertex

                // if new distance is better, update distance and path
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, currentVertex);  // remember path
                    pq.add(new VertexDistance(neighbor, newDist));  // add neighbor to queue
                }
            }
        }

        // now build the path by going backwards from end to start
        List<String> path = new ArrayList<>();
        // if no path found, return -1 and empty path
        if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
            return new Result(-1, path); // no path found
        }
        // follow previous vertices back to start
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        // reverse the path because we added it backwards
        Collections.reverse(path);

        // return the shortest distance and the path found
        return new Result(distances.get(end), path);
    }

    // this helper class holds a vertex and its distance for the priority queue
    private static class VertexDistance {
        String vertex;
        int distance;

        public VertexDistance(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // this method prints the path nicely
    public static void printPath(List<String> path) {
        if (path.isEmpty()) {
            System.out.println("No path found.");
            return;
        }
        System.out.println("Path:");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}
