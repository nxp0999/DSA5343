public class Dijkstra {
    private int[][] adjacencyMatrix;
    private int V; // number of vertices
    private static final int NO_EDGE = 0; // represents no edge between vertices
    
    public Dijkstra(int vertices) {
        this.V = vertices;
        adjacencyMatrix = new int[V][V];
    }
    
    public void addEdge(int source, int destination, int weight) {
        adjacencyMatrix[source][destination] = weight;
        adjacencyMatrix[destination][source] = weight; // For undirected graph
    }
    
    private int minDistance(int[] distances, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        
        for (int v = 0; v < V; v++) {
            if (!visited[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    
    public int[] dijkstra(int source) {
        int[] distances = new int[V];
        boolean[] visited = new boolean[V];
        
        // Initialize distances
        for (int i = 0; i < V; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0;
        
        // Find shortest paths
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(distances, visited);
            visited[u] = true;
            
            for (int v = 0; v < V; v++) {
                if (!visited[v] && adjacencyMatrix[u][v] != NO_EDGE && 
                    distances[u] != Integer.MAX_VALUE && 
                    distances[u] + adjacencyMatrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + adjacencyMatrix[u][v];
                }
            }
        }
        return distances;
    }
    
    public static void main(String[] args) {
        int V = 10; // vertices A through J
        Dijkstra graph = new Dijkstra(V);
        
        // Add edges (20 undirected edges)
        graph.addEdge(0, 1, 4);  // A-B
        graph.addEdge(0, 2, 2);  // A-C
        graph.addEdge(1, 2, 1);  // B-C
        graph.addEdge(1, 3, 5);  // B-D
        graph.addEdge(2, 3, 3);  // C-D
        graph.addEdge(2, 4, 4);  // C-E
        graph.addEdge(3, 4, 2);  // D-E
        graph.addEdge(3, 5, 4);  // D-F
        graph.addEdge(4, 5, 2);  // E-F
        graph.addEdge(4, 6, 4);  // E-G
        graph.addEdge(5, 6, 2);  // F-G
        graph.addEdge(5, 7, 4);  // F-H
        graph.addEdge(6, 7, 2);  // G-H
        graph.addEdge(6, 8, 4);  // G-I
        graph.addEdge(7, 8, 2);  // H-I
        graph.addEdge(7, 9, 4);  // H-J
        graph.addEdge(8, 9, 2);  // I-J
        graph.addEdge(1, 4, 3);  // B-E
        graph.addEdge(3, 6, 3);  // D-G
        graph.addEdge(5, 8, 4);  // F-I
        
        // Print adjacency matrix
        System.out.println("Adjacency Matrix:");
        System.out.print("   ");
        for (int i = 0; i < V; i++) {
            System.out.printf("%3s", (char)('A' + i));
        }
        System.out.println();
        
        for (int i = 0; i < V; i++) {
            System.out.printf("%3s", (char)('A' + i));
            for (int j = 0; j < V; j++) {
                System.out.printf("%3d", graph.adjacencyMatrix[i][j]);
            }
            System.out.println();
        }
        
        // Print all edges
        System.out.println("\nEdges:");
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (graph.adjacencyMatrix[i][j] != NO_EDGE) {
                    System.out.println((char)('A' + i) + " -- " + 
                        (char)('A' + j) + " (weight: " + 
                        graph.adjacencyMatrix[i][j] + ")");
                }
            }
        }
        
        // Run Dijkstra's algorithm from vertex A (0)
        int source = 0;
        int[] distances = graph.dijkstra(source);
        
        // Print shortest distances
        System.out.println("\nShortest distances from vertex A:");
        for (int i = 0; i < V; i++) {
            System.out.println((char)('A' + i) + ": " + 
                (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
        }
    }
}
