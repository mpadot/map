import java.util.ArrayList;

public class Graph {
    Vertex[] vertices;
    int actualNumVertices = 0;

    public Graph(int expectedNumVertices) {
        vertices = new Vertex[expectedNumVertices];
    }

    public void addEdge(String fromStr, String toStr, int weight) {
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

        Edge newEdge = new Edge(from, to, weight);
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

    public void print(){
        for(Vertex v : vertices){
            System.out.print(v.label);
            Edge currentEdge = v.firstEdge;
            while(currentEdge != null){
                System.out.print(" --> " + currentEdge.to.label + "(" + currentEdge.w + ")");
                currentEdge = currentEdge.next;
            }
            System.out.println();
        }
    }
    public void dijkstra(Vertex s){
        Vertex v, w;
        s.dist = 0;

        for(;;){
            v = smallestUnknownVertex();
         if(v == null);
         break;
         v.known = true;

         for(Vertex w : getVertices(V)){
             if(!w.known){
                 if(v.)
             }
         }
        }
    }
    public Vertex smallestUnknownVertex(){
        int smallestDist = Integer.MAX_VALUE;
        Vertex result = null;
        for(Vertex v : vertices){
            if(!v.known && v.dist < smallestDist){
                smallestDist = v.dist;
                result = v;
            }
        }
        return result;
    }
    public ArrayList<Vertex> getVertices(Vertex v){
        ArrayList <Vertex> result = new ArrayList<>();
        Edge currEdge = v.firstEdge;
        while(currEdge != null){
            result.add(currEdge.to);
            currEdge = currEdge.next;
        }

    }
}

