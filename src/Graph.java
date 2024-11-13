
public class Graph {
    Vertex[] vertices;
    int actualNumVertices = 0;

    public Graph(int expectedNumVertices) {
        vertices = new Vertex[expectedNumVertices];
    }

    public void addEdge(String fromStr, String toStr) {
        Vertex from = findV(fromStr);

        if(from == null) {
            from = new Vertex(fromStr);
            vertices[actualNumVertices++] = from;
        }

        Vertex to = findV(toStr);
        if(to == null) {
            to = new Vertex(toStr);
            vertices[actualNumVertices++] = to;
        }

        Edge newEdge = new Edge(from, to);
        Edge firstEdgeInLL = from.firstEdge;
        from.firstEdge = newEdge;
        newEdge.next = firstEdgeInLL;

    }

    public Vertex findV(String label) {
        for(Vertex v : vertices)
            if(v != null && v.label.equals(label))
                return v;
        return null;
    }
}

