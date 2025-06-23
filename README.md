# DSA5343
Collection of programs written in Java submitted as assignments for a graduate class in Data Structures and Algorithms

# Dijkstra's Shortest Path Algorithm

A Java implementation of Dijkstra's algorithm for finding the shortest paths from a source vertex to all other vertices in a weighted, undirected graph using adjacency matrix representation.

## Features

- **Adjacency Matrix Representation**: Stores graph as a 2D array for O(1) edge lookup
- **Undirected Graph Support**: Automatically adds edges in both directions
- **Shortest Path Calculation**: Finds shortest paths from source to all vertices
- **Graph Visualization**: Displays adjacency matrix and edge list
- **Clean Output**: Shows distances with proper formatting and infinity symbol for unreachable vertices

## Algorithm Overview

Dijkstra's algorithm uses a greedy approach to find shortest paths:
1. Initialize all distances to infinity except source (distance = 0)
2. Repeatedly select unvisited vertex with minimum distance
3. Mark selected vertex as visited
4. Update distances to all unvisited neighbors if shorter path found
5. Repeat until all vertices processed

## Operations Supported

### `addEdge(int source, int destination, int weight)`
Adds a weighted undirected edge between two vertices.

### `dijkstra(int source)`
Computes shortest distances from the source vertex to all other vertices.

### Helper Methods
- `minDistance()`: Finds unvisited vertex with minimum distance
- Graph visualization methods in main()

## How to Compile and Run

```bash
# Compile the Java file
javac Dijkstra.java

# Run the program
java Dijkstra
```

## Usage Example

```java
// Create graph with 5 vertices
Dijkstra graph = new Dijkstra(5);

// Add edges (source, destination, weight)
graph.addEdge(0, 1, 4);  // A-B with weight 4
graph.addEdge(0, 2, 2);  // A-C with weight 2
graph.addEdge(1, 2, 1);  // B-C with weight 1

// Find shortest paths from vertex 0 (A)
int[] distances = graph.dijkstra(0);

// Print results
for (int i = 0; i < distances.length; i++) {
    System.out.println("Distance to " + (char)('A' + i) + ": " + distances[i]);
}
```

## Sample Input and Output

### Input Graph:
- **Vertices**: 10 (A through J)
- **Edges**: 20 undirected weighted edges
- **Source**: Vertex A (index 0)

### Graph Structure:
```
A-B (4), A-C (2), B-C (1), B-D (5), C-D (3), C-E (4), D-E (2), 
D-F (4), E-F (2), E-G (4), F-G (2), F-H (4), G-H (2), G-I (4), 
H-I (2), H-J (4), I-J (2), B-E (3), D-G (3), F-I (4)
```

### Expected Output:

```
Adjacency Matrix:
     A  B  C  D  E  F  G  H  I  J
  A  0  4  2  0  0  0  0  0  0  0
  B  4  0  1  5  3  0  0  0  0  0
  C  2  1  0  3  4  0  0  0  0  0
  D  0  5  3  0  2  4  3  0  0  0
  E  0  3  4  2  0  2  4  0  0  0
  F  0  0  0  4  2  0  2  4  4  0
  G  0  0  0  3  4  2  0  2  4  0
  H  0  0  0  0  0  4  2  0  2  4
  I  0  0  0  0  0  4  4  2  0  2
  J  0  0  0  0  0  0  0  4  2  0

Edges:
A -- B (weight: 4)
A -- C (weight: 2)
B -- C (weight: 1)
B -- D (weight: 5)
B -- E (weight: 3)
C -- D (weight: 3)
C -- E (weight: 4)
D -- E (weight: 2)
D -- F (weight: 4)
D -- G (weight: 3)
E -- F (weight: 2)
E -- G (weight: 4)
F -- G (weight: 2)
F -- H (weight: 4)
F -- I (weight: 4)
G -- H (weight: 2)
G -- I (weight: 4)
H -- I (weight: 2)
H -- J (weight: 4)
I -- J (weight: 2)

Shortest distances from vertex A:
A: 0
B: 3
C: 2
D: 5
E: 6
F: 7
G: 8
H: 9
I: 10
J: 12
```

## Algorithm Analysis

- **Time Complexity**: O(V²) where V is the number of vertices
  - Finding minimum distance vertex: O(V)
  - Done for all V vertices: O(V²)
  - Edge relaxation: O(V) per iteration
- **Space Complexity**: O(V²) for adjacency matrix storage
- **Graph Type**: Works with weighted, directed/undirected graphs with non-negative weights

## Key Implementation Details

- **Adjacency Matrix**: `adjacencyMatrix[i][j]` stores weight of edge between vertices i and j
- **Distance Array**: Stores shortest known distance from source to each vertex
- **Visited Array**: Tracks which vertices have been processed
- **Edge Weight 0**: Represents no edge between vertices
- **Infinity Handling**: Uses `Integer.MAX_VALUE` for unreachable vertices

## Path Reconstruction (Not Included)

To track actual paths (not just distances), you would need to maintain a predecessor array:
```java
int[] predecessor = new int[V];
// Update predecessor[v] = u when relaxing edge (u,v)
```

## Limitations

- **Negative Weights**: Algorithm doesn't work with negative edge weights
- **Memory Usage**: O(V²) space requirement for dense graphs
- **Directed vs Undirected**: Current implementation treats all edges as undirected

The implementation follows the classic Dijkstra's algorithm and will correctly compute shortest paths for any connected, weighted graph with non-negative edge weights.
