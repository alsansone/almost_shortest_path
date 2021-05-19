public class Edge {
    private final int distance;
    private final Vertex destination;

    Edge(int distance, Vertex destination) {
        this.distance = distance;
        this.destination = destination;
    }

    int getDistance() {
        return distance;
    }

    Vertex getDestination() {
        return destination;
    }
}
