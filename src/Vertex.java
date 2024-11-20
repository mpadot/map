
public class Vertex {
    String label;
    Edge firstEdge;
    boolean known = false;
    int dist = Integer.MAX_VALUE;

    public Vertex(String l) {
        label = l;
    }
}
