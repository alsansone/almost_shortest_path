import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    private final String name;
    private final List<Edge> adjacencyList;
    private List<Vertex> prev;
    private int distance = Integer.MAX_VALUE;

    Vertex(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    List<Edge> getAdjacencyList() {
        return adjacencyList;
    }

    List<Vertex> getPrev() {
        return prev;
    }

    int getDistance() {
        return distance;
    }

    void setPrev(List<Vertex> prev) {
        this.prev = prev;
    }

    void setDistance(int distance) {
        this.distance = distance;
    }

    void addNeighbor(Edge edge) {
        this.adjacencyList.add(edge);
    }

    void removeNeighbor(Vertex destination) {
        this.adjacencyList.removeIf(e -> e.getDestination().getName().equals(destination.getName()));
    }

    void reset() {
        this.distance = Integer.MAX_VALUE;
        this.prev = null;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Integer.compare(this.distance, otherVertex.getDistance());
    }
}
