/*
This is a class called DirectedGraph that represents a directed graph data structure.
It uses a Map to store vertices and their corresponding labels.
The class has methods to add edges between vertices, perform Depth First Search (DFS), and get a list of unreachable classes.
It also includes a private static inner class called Vertex to represent each vertex in the graph.
The Vertex class has fields for a label, a list of adjacent vertices, and a state.
The State enum is used to track the state of each vertex during DFS.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraph<T> {

    private Map<T, Vertex<T>> vertices;

    public DirectedGraph() {
        // Create an empty HashMap to store the vertices
        this.vertices = new HashMap<>();
    }
    List<String> unreachableClasses = graph.getUnreachableClasses();
    if (!unreachableClasses.isEmpty()) {
        System.out.println("Unreachable Classes:");
        for (String className : unreachableClasses) {
            System.out.println(className + " is unreachable");
        }
    } else {
        System.out.println("All classes are reachable.");
    }
    
    public void addEdge(T source, T target) {
        // Get or create the source vertex with the given label
        Vertex<T> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        // Get or create the target vertex with the given label
        Vertex<T> targetVertex = vertices.computeIfAbsent(target, Vertex::new);
        // Add the target vertex as an adjacent vertex to the source vertex
        sourceVertex.addAdjacentVertex(targetVertex);
    }

    public void DFS(T start, DFSActions<T> actions) {
        // TODO: Implement Depth First Search
    }

    public List<T> getUnreachableClasses() {
        List<T> unreachable = new ArrayList<>();
        for (Vertex<T> vertex : vertices.values()) {
            if (vertex.state == State.UNDISCOVERED) {
                unreachable.add(vertex.label);
            }
        }
        return unreachable;
    }
    

    private static class Vertex<U> {
        private U label;
        private List<Vertex<U>> adjacentVertices;
        private State state;

        public Vertex(U label) {
            // Initialize the vertex with the given label
            this.label = label;
            // Create an empty list to store the adjacent vertices
            this.adjacentVertices = new ArrayList<>();
            // Set the state of the vertex to UNDISCOVERED
            this.state = State.UNDISCOVERED;
        }

        public void addAdjacentVertex(Vertex<U> vertex) {
            // Add the given vertex to the list of adjacent vertices
            adjacentVertices.add(vertex);
        }

        // Other getters and setters
    }

    private enum State {
        UNDISCOVERED,
        DISCOVERED,
        FINISHED
    }
}
