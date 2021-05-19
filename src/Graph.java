import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graph {

    private Vertex source, target;
    private List<Vertex> graph = new ArrayList<>();
    private int shortestDistance;

    // Parses input file
    void getAlmostShortestPath() {
        try (FileInputStream stream = new FileInputStream("input.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String[] line1 = reader.readLine().split(" ");
            String vertices = line1[0];
            String edges = line1[1];
            while (!vertices.equals("0") && !edges.equals("0")) {
                line1 = reader.readLine().split(" ");
                source = new Vertex(line1[0]);
                target = new Vertex(line1[1]);
                // Initialize graph with # of vertices
                initializeVertices(vertices);
                // Initialize edge lists for each vertex
                for (int i = 0; i < Integer.parseInt(edges); i++) {
                    String[] line = reader.readLine().split(" ");
                    Edge e = new Edge(Integer.parseInt(line[2]), graph.get(Integer.parseInt(line[1])));
                    graph.get(Integer.parseInt(line[0])).addNeighbor(e);
                }
                computeAlmostShortestDistance();
                System.out.println("Almost Shortest Distance: " + shortestDistance);
                // reset graph
                graph = new ArrayList<>();
                line1 = reader.readLine().split(" ");
                vertices = line1[0];
                edges = line1[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeVertices(String vertices) {
        for (int i = 0; i < Integer.parseInt(vertices); i++) {
            graph.add(new Vertex(i + ""));
        }
    }

    // computes the shortest distance until we find the almost shortest distance
    // or no path exists
    private void computeAlmostShortestDistance() {
        computeShortestPath(graph.get(Integer.parseInt(source.getName())));
        getAllShortestPaths(new ArrayList<>(), graph.get(Integer.parseInt(target.getName())));
        resetGraph();
        computeShortestPath(graph.get(Integer.parseInt(source.getName())));
        // if no path exists return -1 else return shortest distance
        shortestDistance = shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance;
    }

    // dijkstra's algorithm
    private void computeShortestPath(Vertex source) {
        source.setDistance(0);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(source);
        List<Vertex> prev;

        while (!pq.isEmpty()) {
            Vertex min = pq.poll();
            for (Edge e : min.getAdjacencyList()) {
                Vertex v = e.getDestination();
                prev = v.getPrev();
                int newDistance = min.getDistance() + e.getDistance();
                if (newDistance < v.getDistance()) {
                    pq.remove(v);
                    v.setDistance(newDistance);
                    pq.add(v);
                    prev = new ArrayList<>();
                    prev.add(min);
                    v.setPrev(prev);
                } else if (newDistance == v.getDistance()) {
                    if (prev != null)
                        prev.add(min);
                    else {
                        prev = new ArrayList<>();
                        prev.add(min);
                        v.setPrev(prev);
                    }
                }
            }
        }
        // The shortest distance will be stores in the target vertex once the algorithm finishes
        shortestDistance = graph.get(Integer.parseInt(target.getName())).getDistance();
    }

    // resets the distance and list of parent vertices for each vertex in the graph
    // this allows dijkstra's to be rerun un the updated graph
    private void resetGraph() {
        for (Vertex v : graph)
            v.reset();
    }

    // finds all minimum paths and removes the edges from the graph
    private void getAllShortestPaths(List<Vertex> shortestPath, Vertex target) {
        List<Vertex> prev = target.getPrev();
        if (prev == null) {
            shortestPath.add(target);
        } else {
            List<Vertex> updatedPath = new ArrayList<>(shortestPath);
            updatedPath.add(target);
            for (Vertex vertex : prev) {
                Vertex parent = graph.get(Integer.parseInt(vertex.getName()));
                if (parent != null) {
                    parent.removeNeighbor(target);
                    graph.set(Integer.parseInt(parent.getName()), parent);
                }
                getAllShortestPaths(updatedPath, vertex);
            }
        }
    }
}
