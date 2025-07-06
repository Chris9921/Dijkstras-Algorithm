import java.io.*;
import java.util.*;

public class GraphLoader {

    // This method loads a graph from a file with given filename
    public static WeightedGraph loadGraphFromFile(String filename) {
        WeightedGraph graph = new WeightedGraph();  // create a new empty graph

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;  // variable to hold each line we read

            // read file line by line until no more lines
            while ((line = br.readLine()) != null) {
                line = line.trim();  // remove spaces at start and end of line
                if (line.isEmpty()) continue;  // skip empty lines

                // split the line by spaces to get from vertex and edges
                String[] parts = line.split("\\s+"); 
                String fromVertex = parts[0];  // first part is starting vertex

                // loop through remaining parts which represent edges
                for (int i = 1; i < parts.length; i++) {
                    String[] edgeParts = parts[i].split(",");  // split edge into toVertex and weight
                    if (edgeParts.length != 2) {  // if not properly formatted, skip this edge
                        System.out.println("Skipping malformed edge: " + parts[i]);
                        continue;
                    }
                    String toVertex = edgeParts[0];  // vertex where edge goes
                    int weight = Integer.parseInt(edgeParts[1]);  // weight of edge

                    // add the edge to the graph
                    graph.addEdge(fromVertex, toVertex, weight);
                }
            }

        } catch (IOException e) {
            // if error happens while reading file, print error message and stack trace
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        return graph;  // return the graph we loaded
    }
}
