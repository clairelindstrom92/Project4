import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A directed graph data structure.
 * @param <T> the type of the vertices in the graph
 */
public class DirectedGraph<T> {

    private Map<T, Vertex<T>> vertices;

    /**
     * Constructs an empty directed graph.
     */
    public DirectedGraph() {
        this.vertices = new HashMap<>();
    }

    /**
     * Adds a directed edge from the source vertex to the target vertex.
     * If the vertices do not exist in the graph, they are added.
     * @param source the source vertex
     * @param target the target vertex
     */
    public void addEdge(T source, T target) {
        Vertex<T> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<T> targetVertex = vertices.computeIfAbsent(target, Vertex::new);
        sourceVertex.addAdjacentVertex(targetVertex);
    }

    /**
     * Performs a depth-first search starting from the specified vertex.
     * Calls the specified actions during the search.
     * @param start the starting vertex
     * @param actions the actions to perform during the search
     */
    public void DFS(T start, DFSActions<T> actions) {
        if (!vertices.containsKey(start)) {
            return;
        }
        for (Vertex<T> vertex : vertices.values()) {
            vertex.state = State.UNDISCOVERED;
        }
        DFSRecursive(vertices.get(start), actions);
    }

    /**
     * Performs a depth-first search starting from the specified vertex.
     * Calls the specified actions during the search.
     * @param current the current vertex being visited
     * @param actions the actions to perform during the search
     */
    private void DFSRecursive(Vertex<T> current, DFSActions<T> actions) {
        current.state = State.DISCOVERED;
        actions.processVertex(current.label);
        for (Vertex<T> adjacent : current.adjacentVertices) {
            if (adjacent.state == State.UNDISCOVERED) {
                actions.descend(current.label);
                DFSRecursive(adjacent, actions);
                actions.ascend(current.label);
            } else if (adjacent.state == State.DISCOVERED) {
                actions.cycleDetected(current.label, adjacent.label);
            }
        }
        current.state = State.FINISHED;
    }

    /**
     * Returns a list of vertices that are unreachable from any starting vertex.
     * @return a list of unreachable vertices
     */
    public List<T> getUnreachableClasses() {
        List<T> unreachable = new ArrayList<>();
        for (Vertex<T> vertex : vertices.values()) {
            if (vertex.state == State.UNDISCOVERED) {
                unreachable.add(vertex.label);
            }
        }
        return unreachable;
    }

    /**
     * Represents a vertex in the graph.
     * @param <U> the type of the vertex label
     */
    private static class Vertex<U> {
        private U label;
        private List<Vertex<U>> adjacentVertices;
        private State state;

        /**
         * Constructs a vertex with the specified label.
         * The vertex is initially undiscovered and has no adjacent vertices.
         * @param label the label of the vertex
         */
        public Vertex(U label) {
            this.label = label;
            this.adjacentVertices = new ArrayList<>();
            this.state = State.UNDISCOVERED;
        }

        /**
         * Adds an adjacent vertex to this vertex.
         * @param vertex the adjacent vertex to add
         */
        public void addAdjacentVertex(Vertex<U> vertex) {
            adjacentVertices.add(vertex);
        }
    }

    /**
     * Represents the state of a vertex during a depth-first search.
     */
    private enum State {
        UNDISCOVERED,
        DISCOVERED,
        FINISHED
    }
}

interface DFSActions<T> {
    void processVertex(T vertex);
    void descend(T vertex);
    void ascend(T vertex);
    void cycleDetected(T source, T target);
}
