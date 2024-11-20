
public class Edge {
    Vertex from, to;
    Edge next;
    int w;


    public Edge(Vertex f, Vertex t, int weight) {
        from = f;
        to = t;
        w = weight;
    }


}
