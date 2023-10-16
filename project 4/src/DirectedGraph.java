import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraph<T> {

    private Map<T, Vertex<T>> vertices; // Declare a map to store the vertices of the graph

    public DirectedGraph() {
        this.vertices = new HashMap<>(); // Initialize the vertices map as an empty HashMap
    }

    public void addEdge(T source, T target) {
        Vertex<T> sourceVertex = vertices.computeIfAbsent(source, Vertex::new); // Create a new vertex if it doesn't
                                                                                // exist in the map
        Vertex<T> targetVertex = vertices.computeIfAbsent(target, Vertex::new); // Create a new vertex if it doesn't
                                                                                // exist in the map
        sourceVertex.addAdjacentVertex(targetVertex); // Add the target vertex as an adjacent vertex to the source
                                                      // vertex
    }

    public void DFS(T start, ParenthesizedList<String> parenthesizedList) {
        if (!vertices.containsKey(start)) {
            return; // If the start vertex is not present in the graph, return and do nothing
        }
        for (Vertex<T> vertex : vertices.values()) {
            vertex.state = State.UNDISCOVERED; // Set the state of all vertices as undiscovered
        }
        DFSRecursive(vertices.get(start), parenthesizedList); // Call the recursive Depth First Search method starting
                                                              // from the given start vertex
    }

    private void DFSRecursive(Vertex<T> current, DFSActions<T> actions) {
        current.state = State.DISCOVERED; // Set the state of the current vertex as discovered
        actions.processVertex(current.label); // Process the current vertex using the given actions object
        for (Vertex<T> adjacent : current.adjacentVertices) {
            if (adjacent.state == State.UNDISCOVERED) { // If the adjacent vertex is undiscovered
                actions.descend(current.label, adjacent.label); // Perform the descend action using the given actions
                                                                // object
                DFSRecursive(adjacent, actions); // Call the recursive Depth First Search method on the adjacent vertex
                actions.ascend(current.label, adjacent.label); // Perform the ascend action using the given actions
                                                               // object
            } else if (adjacent.state == State.DISCOVERED) { // If the adjacent vertex is discovered
                actions.cycleDetected(current.label, adjacent.label); // Perform the cycle detected action using the
                                                                      // given actions object
            }
        }
        current.state = State.FINISHED; // Set the state of the current vertex as finished
    }

    public List<T> getUnreachableClasses() {
        List<T> unreachable = new ArrayList<>(); // Create a new list to store the unreachable vertices
        for (Vertex<T> vertex : vertices.values()) {
            if (vertex.state == State.UNDISCOVERED) {
                unreachable.add(vertex.label); // Add the label of each undiscovered vertex to the unreachable list
            }
        }
        return unreachable; // Return the list of unreachable vertices
    }

    private static class Vertex<U> {
        private U label;
        private List<Vertex<U>> adjacentVertices;
        private State state;

        public Vertex(U label) {
            this.label = label; // Set the label of the vertex as the given label
            this.adjacentVertices = new ArrayList<>(); // Initialize the list of adjacent vertices as an empty ArrayList
            this.state = State.UNDISCOVERED; // Set the state of the vertex as undiscovered
        }

        public void addAdjacentVertex(Vertex<U> vertex) {
            adjacentVertices.add(vertex); // Add the given vertex as an adjacent vertex to the current vertex
        }
    }

    private enum State {
        UNDISCOVERED,
        DISCOVERED,
        FINISHED
    }

    public interface DFSActions<T> {
        void processVertex(T vertex); // Actions to be performed on a vertex during DFS traversal

        void descend(T source, T target); // Action to be performed when descending from a source vertex to a target
                                          // vertex

        void ascend(T source, T target); // Action to be performed when ascending from a source vertex to a target
                                         // vertex

        void cycleDetected(T source, T target); // Action to be performed when a cycle is detected between a source
                                                // vertex and a target vertex
    }
}
