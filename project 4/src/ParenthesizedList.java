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
    public void ascend(T vertex, T parent) {
        representation.append(") ");
    }

    @Override
    public void cycleDetected(T source, T target) {
        representation.append("* ");
    }


    @Override
    public String toString() {
        return representation.toString().trim();
    }

    @Override
    public void cycleDetected() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cycleDetected'");
    }

    @Override
    public void descend(T vertex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'descend'");
    }

    @Override
    public void ascend(T vertex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ascend'");
    }
}