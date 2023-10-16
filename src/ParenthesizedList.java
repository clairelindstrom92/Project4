public class ParenthesizedList<T> implements DFSActions<T> {

    private StringBuilder representation = new StringBuilder();

    @Override
    public void processVertex(T vertex) {
        representation.append(vertex).append(" ");
    }

    @Override
    public void descend(T vertex) {
        representation.append("( ");
    }

    @Override
    public void ascend(T vertex) {
        representation.append(") ");
    }

    @Override
    public void cycleDetected() {
        representation.append("* ");
    }

    @Override
    public String toString() {
        return representation.toString().trim();
    }
}
