
public class Driver {

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge("A", "B", 3);
        g.addEdge("A", "C", 1);
        g.addEdge("A", "D", 2);

        g.addEdge("B", "E", 2);
        g.addEdge("C", "D", 5);

        g.dijkstra("A");
        g.printAllPaths();



    }

}
