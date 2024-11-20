import java.util.ArrayList;

public class Graph {
    Vertex[] vertices;
    int actualNumVertices = 0;

    public Graph(int expectedNumVertices) {
        vertices = new Vertex[expectedNumVertices];
    }

    public void addEdge(String fromStr, String toStr, int weight) {
        Vertex from = findV(fromStr);

        if (from == null) {
            from = new Vertex(fromStr);
            vertices[actualNumVertices++] = from;
        }

        Vertex to = findV(toStr);
        if (to == null) {
            to = new Vertex(toStr);
            vertices[actualNumVertices++] = to;
        }

        Edge newEdge = new Edge(from, to, weight);
        Edge firstEdgeInLL = from.firstEdge;
        from.firstEdge = newEdge;
        newEdge.next = firstEdgeInLL;
    }

    public Vertex findV(String label) {
        for (Vertex v : vertices)
            if (v != null && v.label.equals(label))
                return v;
        return null;
    }

    public void print() {
        for (Vertex v : vertices) {
            System.out.print(v.label);
            Edge currEdge = v.firstEdge;
            while (currEdge != null) {
                System.out.print(" -> " + currEdge.to.label + "(" + currEdge.w + ")");
                currEdge = currEdge.next;
            }
            System.out.println();
        }
    }

    public void dijkstra(String startLabel) {
        Vertex source = findV(startLabel);
        if (source == null) return;

        MinHeap minHeap = new MinHeap();

        // Initialize all vertices
        for (Vertex v : vertices) {
            v.dist = Integer.MAX_VALUE;
            v.known = false;
            v.path = null;
        }

        // Set up the source
        source.dist = 0;
        minHeap.insert(source);

        while (!minHeap.isEmpty()) {
            Vertex v = minHeap.extractMin();
            if (v.known) continue;

            v.known = true;

            for (Vertex neighbor : getAdjacentVs(v)) {
                int edgeWeight = findWeight(v, neighbor);

                if (!neighbor.known && v.dist + edgeWeight < neighbor.dist) {
                    neighbor.dist = v.dist + edgeWeight;
                    neighbor.path = v;

                    minHeap.insert(neighbor); // Add or re-add to heap
                }
            }
        }
    }

    public ArrayList<Vertex> getAdjacentVs(Vertex v) {
        ArrayList<Vertex> result = new ArrayList<>();

        Edge currEdge = v.firstEdge;
        while (currEdge != null) {
            result.add(currEdge.to);
            currEdge = currEdge.next;
        }

        return result;
    }

    public int findWeight(Vertex from, Vertex to) {
        for (Vertex v : vertices) {
            if (v.label.equals(from.label)) {
                Edge currEdge = v.firstEdge;
                while (currEdge != null) {
                    if (currEdge.to.label.equals(to.label))
                        return currEdge.w;
                    currEdge = currEdge.next;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public void printPath(Vertex v) {
        if (v.path != null) {
            printPath(v.path);
            System.out.print("-> ");
        }
        System.out.print(v.label);
    }

    public void printAllPaths() {
        System.out.println();
        for (Vertex v : vertices) {
            System.out.print(v.label + "(dist:" + v.dist + "), " + " path: ");
            printPath(v);
            System.out.println();
        }
    }

    // MinHeap Implementation
    class MinHeap {
        private ArrayList<Vertex> heap;

        public MinHeap() {
            heap = new ArrayList<>();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public void insert(Vertex v) {
            heap.add(v);
            bubbleUp(heap.size() - 1);
        }

        public Vertex extractMin() {
            if (heap.isEmpty()) return null;

            Vertex min = heap.get(0);
            Vertex last = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, last);
                bubbleDown(0);
            }

            return min;
        }

        private void bubbleUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (heap.get(index).dist < heap.get(parentIndex).dist) {
                    swap(index, parentIndex);
                    index = parentIndex;
                } else {
                    break;
                }
            }
        }

        private void bubbleDown(int index) {
            int size = heap.size();
            int smallest = index;

            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            if (leftChild < size && heap.get(leftChild).dist < heap.get(smallest).dist) {
                smallest = leftChild;
            }

            if (rightChild < size && heap.get(rightChild).dist < heap.get(smallest).dist) {
                smallest = rightChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                bubbleDown(smallest);
            }
        }

        private void swap(int i, int j) {
            Vertex temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}