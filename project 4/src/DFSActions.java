/*
This is an interface called DFSActions that defines a set of actions for Depth First Search (DFS) traversal in a graph.
The interface includes several methods that can be implemented by classes that implement this interface.
*/

public interface DFSActions<T> {
    // Method called when a cycle is detected during DFS traversal
    void cycleDetected();

    // Method called when processing a vertex during DFS traversal
    void processVertex(T vertex);

    // Method called when descending into a vertex during DFS traversal
    void descend(T vertex);

    // Method called when ascending from a vertex during DFS traversal
    void ascend(T vertex);
}
