public class Main {
    public static void main(String[] args) {
        String filepath = "/Users/chris/Desktop/bvg.txt";
        WeightedGraph graph = GraphLoader.loadGraphFromFile(filepath);
        System.out.println("Number of vertexes loaded: " + graph.getVertices().size());

        String start = "060192001006";  // S Schöneweide Bhf
        String[] targets = {"060068201511","060066102852","060053301433","060120003653"};

        for (int i=0; i <= targets.length; i++) {  // off-by-one error here (should be <)
            String end = targets[i];
            Pathfinder.Result result = Pathfinder.dijkstra(graph, start, end);
            System.out.println("From " + start + " to " + end + ":");
           
         // Only print path if distance is greater than zero
            if (result.distance > 0) {  
            	
                System.out.println("  Shortest travel time: " + result.distance + " seconds");
                Pathfinder.printPath(result.path);
            } else {
                System.out.println("  No path found");
            }
            System.out.println();
        }
    }
}
