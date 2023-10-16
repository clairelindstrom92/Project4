/*
This is a class called Hierarchy that implements the DFSActions interface.
It represents a hierarchy and provides methods to handle various actions during DFS traversal in a graph.
*/

public class Hierarchy<T> implements DFSActions<T> {
    // A StringBuilder object used to build the representation of the hierarchy
    private StringBuilder representation = new StringBuilder();

    // Method called when a cycle is detected, appends "*" to the representation
    @Override
    public void cycleDetected() {
        representation.append("* ");
    }

    // Method called when processing a vertex, appends the vertex to the representation
    @Override
    public void processVertex(T vertex) {
        representation.append(vertex).append(" ");
    }

    // Method called when descending into a vertex, no specific action for Hierarchy representation
    @Override
    public void descend(T vertex) {
        // No specific action for descend in Hierarchy representation
    }

    // Method called when ascendfving from a vertex, no specific action for Hierarchy representation
    @Override
    public void ascend(T vertex) {
        // No specific action for ascend in Hierarchy representation
    }

    // Overrides the toString method to return the representation string, trimming any trailing white spaces
    @Override
    public String toString() {
        return representation.toString().trim();
    }
}
