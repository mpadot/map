
public class Driver2 {

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge("S", "A", 4);
        g.addEdge("S", "D", 8);
        g.addEdge("S", "B", 1);
        g.addEdge("A", "C", 2);
        g.addEdge("A", "E", 3);
        g.addEdge("B", "D", 5);
        g.addEdge("B", "A", 2);
        g.addEdge("B", "C", 8);
        g.addEdge("C", "E", 3);
        g.addEdge("C", "G", 4);
        g.addEdge("E", "F", 10);
        g.addEdge("D", "H", 3);
        g.addEdge("H", "G", 1);
        g.addEdge("H", "F", 5);
        g.addEdge("G", "F", 2);

        g.dijkstra("S");
        g.printAllPaths();

    }

}
