import java.util.*;

/**
 * Implementation of Topological Sorting using both DFS and BFS (Kahn's Algorithm)
 * Works on Directed Acyclic Graphs (DAGs) and detects cycles
 */
public class TopologicalSort {
    private Map<Character, List<Character>> graph;
    private Set<Character> vertices;

    public TopologicalSort() {
        graph = new HashMap<>();
        vertices = new HashSet<>();
    }

    /**
     * Adds a directed edge from vertex u to vertex v
     * @param u Source vertex
     * @param v Destination vertex
     */
    public void addEdge(char u, char v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        vertices.add(u);
        vertices.add(v);
    }

    /**
     * Performs topological sorting using Depth-First Search (DFS)
     * @return List of vertices in topological order, empty list if cycle detected
     */
    public List<Character> dfsTopologicalSort() {
        Map<Character, Integer> visited = new HashMap<>();
        List<Character> result = new ArrayList<>();

        // Initialize all vertices as unvisited (state 0)
        for (char vertex : vertices) {
            visited.put(vertex, 0);
        }

        // Perform DFS from each unvisited vertex
        for (char vertex : vertices) {
            if (visited.get(vertex) == 0) {
                if (!dfsVisit(vertex, visited, result)) {
                    System.out.println("Cycle detected in DFS!");
                    return new ArrayList<>();
                }
            }
        }

        // Reverse to get correct topological order
        Collections.reverse(result);
        return result;
    }

    /**
     * DFS helper method with cycle detection
     * @param vertex Current vertex being visited
     * @param visited Map tracking visit states (0=unvisited, 1=processing, 2=processed)
     * @param result List to store the topological order
     * @return false if cycle detected, true otherwise
     */
    private boolean dfsVisit(char vertex, Map<Character, Integer> visited, List<Character> result) {
        // Mark vertex as currently being processed (state 1)
        visited.put(vertex, 1);

        // Visit all neighbors
        for (char neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
            int neighborState = visited.get(neighbor);
            
            // If neighbor is currently being processed, we found a cycle
            if (neighborState == 1) {
                return false;
            }
            
            // If neighbor is unvisited, recursively visit it
            if (neighborState == 0 && !dfsVisit(neighbor, visited, result)) {
                return false;
            }
        }

        // Mark vertex as fully processed (state 2)
        visited.put(vertex, 2);
        result.add(vertex);
        return true;
    }

    /**
     * Performs topological sorting using Breadth-First Search (Kahn's Algorithm)
     * @return List of vertices in topological order, empty list if cycle detected
     */
    public List<Character> bfsTopologicalSort() {
        Map<Character, Integer> inDegree = new HashMap<>();
        List<Character> result = new ArrayList<>();
        Queue<Character> queue = new LinkedList<>();

        // Step 1: Initialize in-degrees to 0
        for (char vertex : vertices) {
            inDegree.put(vertex, 0);
        }

        // Step 2: Calculate in-degrees for all vertices
        for (char u : graph.keySet()) {
            for (char v : graph.get(u)) {
                inDegree.put(v, inDegree.get(v) + 1);
            }
        }

        // Step 3: Add all vertices with in-degree 0 to queue
        for (char vertex : vertices) {
            if (inDegree.get(vertex) == 0) {
                queue.offer(vertex);
            }
        }

        // Step 4: Process vertices with in-degree 0
        while (!queue.isEmpty()) {
            char vertex = queue.poll();
            result.add(vertex);

            // Reduce in-degree of all neighbors
            for (char neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                
                // If neighbor's in-degree becomes 0, add to queue
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 5: Check if all vertices were processed (no cycle)
        if (result.size() != vertices.size()) {
            System.out.println("Cycle detected in BFS!");
            return new ArrayList<>();
        }

        return result;
    }

    /**
     * Displays the graph structure
     */
    public void printGraph() {
        System.out.println("Graph structure:");
        for (char vertex : vertices.stream().sorted().toArray(Character[]::new)) {
            List<Character> neighbors = graph.getOrDefault(vertex, Collections.emptyList());
            System.out.printf("%c -> %s%n", vertex, neighbors);
        }
    }

    /**
     * Calculates in-degrees for all vertices
     * @return Map of vertex to its in-degree
     */
    public Map<Character, Integer> getInDegrees() {
        Map<Character, Integer> inDegree = new HashMap<>();
        
        // Initialize all vertices with in-degree 0
        for (char vertex : vertices) {
            inDegree.put(vertex, 0);
        }
        
        // Calculate in-degrees
        for (char u : graph.keySet()) {
            for (char v : graph.get(u)) {
                inDegree.put(v, inDegree.get(v) + 1);
            }
        }
        
        return inDegree;
    }

    /**
     * Checks if the graph is a valid DAG (Directed Acyclic Graph)
     * @return true if DAG, false if contains cycles
     */
    public boolean isDAG() {
        return !dfsTopologicalSort().isEmpty();
    }

    /**
     * Gets the number of vertices in the graph
     * @return vertex count
     */
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * Gets the number of edges in the graph
     * @return edge count
     */
    public int getEdgeCount() {
        return graph.values().stream().mapToInt(List::size).sum();
    }

    public static void main(String[] args) {
        System.out.println("=== Topological Sort Demonstration ===\n");

        // Graph 1: Complex DAG with multiple valid topological orders
        TopologicalSort g1 = new TopologicalSort();
        g1.addEdge('m', 'q');
        g1.addEdge('m', 'r');
        g1.addEdge('m', 'x');
        g1.addEdge('n', 'o');
        g1.addEdge('n', 'q');
        g1.addEdge('n', 'u');
        g1.addEdge('o', 'r');
        g1.addEdge('o', 's');
        g1.addEdge('p', 'o');
        g1.addEdge('p', 's');
        g1.addEdge('p', 'z');
        g1.addEdge('q', 't');
        g1.addEdge('r', 'u');
        g1.addEdge('r', 'y');
        g1.addEdge('s', 'r');
        g1.addEdge('u', 'x');
        g1.addEdge('v', 'w');
        g1.addEdge('v', 'y');
        g1.addEdge('w', 'z');

        System.out.println("Graph 1 Statistics:");
        System.out.println("Vertices: " + g1.getVertexCount());
        System.out.println("Edges: " + g1.getEdgeCount());
        System.out.println("Is DAG: " + g1.isDAG());
        System.out.println();

        g1.printGraph();
        System.out.println();

        System.out.println("Graph 1 In-degrees: " + g1.getInDegrees());
        System.out.println("Graph 1 DFS Topological Sort: " + g1.dfsTopologicalSort());
        System.out.println("Graph 1 BFS Topological Sort: " + g1.bfsTopologicalSort());

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Graph 2: Simpler DAG for comparison
        TopologicalSort g2 = new TopologicalSort();
        g2.addEdge('1', '2');
        g2.addEdge('1', '3');
        g2.addEdge('2', '3');
        g2.addEdge('2', '4');
        g2.addEdge('3', '4');
        g2.addEdge('3', '7');
        g2.addEdge('4', '7');
        g2.addEdge('5', '3');
        g2.addEdge('5', '8');
        g2.addEdge('8', '7');

        System.out.println("Graph 2 Statistics:");
        System.out.println("Vertices: " + g2.getVertexCount());
        System.out.println("Edges: " + g2.getEdgeCount());
        System.out.println("Is DAG: " + g2.isDAG());
        System.out.println();

        g2.printGraph();
        System.out.println();

        System.out.println("Graph 2 In-degrees: " + g2.getInDegrees());
        System.out.println("Graph 2 DFS Topological Sort: " + g2.dfsTopologicalSort());
        System.out.println("Graph 2 BFS Topological Sort: " + g2.bfsTopologicalSort());

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Graph 3: Demonstration of cycle detection
        TopologicalSort g3 = new TopologicalSort();
        g3.addEdge('A', 'B');
        g3.addEdge('B', 'C');
        g3.addEdge('C', 'A'); // Creates a cycle

        System.out.println("Graph 3 (Contains Cycle):");
        System.out.println("Vertices: " + g3.getVertexCount());
        System.out.println("Edges: " + g3.getEdgeCount());
        System.out.println("Is DAG: " + g3.isDAG());
        g3.printGraph();
        System.out.println();
        System.out.println("Graph 3 DFS Topological Sort: " + g3.dfsTopologicalSort());
        System.out.println("Graph 3 BFS Topological Sort: " + g3.bfsTopologicalSort());
    }
}
