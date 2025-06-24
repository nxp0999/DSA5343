# DSA5343
Collection of programs written in Java submitted as assignments for a graduate class in Data Structures and Algorithms

# Topological Sort Implementation

A comprehensive Java implementation of topological sorting for Directed Acyclic Graphs (DAGs) featuring both Depth-First Search (DFS) and Breadth-First Search (BFS/Kahn's Algorithm) approaches with cycle detection capabilities.

## Features

- **Dual Algorithm Support**: Both DFS and BFS (Kahn's Algorithm) implementations
- **Cycle Detection**: Automatically detects and reports cycles in directed graphs
- **Graph Visualization**: Methods to display graph structure and statistics
- **Comprehensive Analysis**: In-degree calculation, DAG validation, and performance metrics
- **Character Vertex Support**: Uses character-based vertices for clear demonstration
- **Educational Value**: Clear implementation showing algorithmic differences

## What is Topological Sorting?

Topological sorting is a linear ordering of vertices in a Directed Acyclic Graph (DAG) such that for every directed edge (u, v), vertex u appears before vertex v in the ordering. It's only possible for DAGs - graphs with cycles cannot have a valid topological ordering.

## Use Cases

- **Course Prerequisites**: Ordering courses based on prerequisite requirements
- **Build Systems**: Determining compilation order for dependencies
- **Task Scheduling**: Arranging tasks with dependency constraints
- **Package Management**: Resolving software package dependencies
- **Project Planning**: Ordering project tasks with dependencies

## Algorithm Overview

### DFS-Based Topological Sort
1. Perform DFS traversal with cycle detection using 3-state tracking
2. Add vertices to result list after processing all dependencies
3. Reverse the final list to get correct topological order
4. **Time Complexity**: O(V + E), **Space Complexity**: O(V)

### BFS-Based Topological Sort (Kahn's Algorithm)
1. Calculate in-degrees for all vertices
2. Add vertices with zero in-degree to a queue
3. Process queue: remove vertex, reduce neighbors' in-degrees
4. Add neighbors to queue when their in-degree becomes zero
5. **Time Complexity**: O(V + E), **Space Complexity**: O(V)

## Graph Representation

The implementation uses an adjacency list representation:
```java
Map<Character, List<Character>> graph  // vertex -> list of neighbors
Set<Character> vertices                // set of all vertices
```

## Operations Supported

### Core Sorting Methods
- `dfsTopologicalSort()`: Returns topological order using DFS
- `bfsTopologicalSort()`: Returns topological order using Kahn's algorithm

### Graph Building
- `addEdge(char u, char v)`: Adds directed edge from u to v

### Analysis Methods
- `printGraph()`: Displays the graph structure
- `getInDegrees()`: Returns in-degree map for all vertices
- `isDAG()`: Checks if graph is a valid DAG
- `getVertexCount()`: Returns number of vertices
- `getEdgeCount()`: Returns number of edges

## How to Compile and Run

```bash
# Compile the Java file
javac TopologicalSort.java

# Run the program
java TopologicalSort
```

## Usage Example

```java
TopologicalSort graph = new TopologicalSort();

// Build a simple dependency graph
graph.addEdge('A', 'B');  // A depends on B
graph.addEdge('A', 'C');  // A depends on C
graph.addEdge('B', 'D');  // B depends on D
graph.addEdge('C', 'D');  // C depends on D

// Get topological ordering
List<Character> dfsOrder = graph.dfsTopologicalSort();
List<Character> bfsOrder = graph.bfsTopologicalSort();

System.out.println("DFS Order: " + dfsOrder); // [D, B, C, A] or [D, C, B, A]
System.out.println("BFS Order: " + bfsOrder); // [D, B, C, A] or [D, C, B, A]
```

## Sample Input and Output

### Input Graphs:

**Graph 1** (Complex DAG):
```
Edges: m→q, m→r, m→x, n→o, n→q, n→u, o→r, o→s, p→o, p→s, p→z, 
       q→t, r→u, r→y, s→r, u→x, v→w, v→y, w→z
```

**Graph 2** (Simple DAG):
```
Edges: 1→2, 1→3, 2→3, 2→4, 3→4, 3→7, 4→7, 5→3, 5→8, 8→7
```

### Expected Output:

```
=== Topological Sort Demonstration ===

Graph 1 Statistics:
Vertices: 10
Edges: 19
Is DAG: true

Graph structure:
m -> [q, r, x]
n -> [o, q, u]
o -> [r, s]
p -> [o, s, z]
q -> [t]
r -> [u, y]
s -> [r]
t -> []
u -> [x]
v -> [w, y]
w -> [z]
x -> []
y -> []
z -> []

Graph 1 In-degrees: {m=0, n=0, o=2, p=0, q=2, r=2, s=2, t=1, u=2, v=0, w=1, x=2, y=2, z=2}
Graph 1 DFS Topological Sort: [v, p, n, m, w, o, s, q, r, t, y, u, x, z]
Graph 1 BFS Topological Sort: [m, n, p, v, q, o, w, s, r, t, u, y, x, z]

==================================================

Graph 2 Statistics:
Vertices: 6
Edges: 10
Is DAG: true

Graph structure:
1 -> [2, 3]
2 -> [3, 4]
3 -> [4, 7]
4 -> [7]
5 -> [3, 8]
7 -> []
8 -> [7]

Graph 2 In-degrees: {1=0, 2=1, 3=3, 4=2, 5=0, 7=4, 8=1}
Graph 2 DFS Topological Sort: [5, 8, 1, 2, 3, 4, 7]
Graph 2 BFS Topological Sort: [1, 5, 2, 8, 3, 4, 7]

==================================================

Graph 3 (Contains Cycle):
Vertices: 3
Edges: 3
Is DAG: false
A -> [B]
B -> [C]
C -> [A]

Cycle detected in DFS!
Graph 3 DFS Topological Sort: []
Cycle detected in BFS!
Graph 3 BFS Topological Sort: []
```

## Algorithm Analysis

### Time Complexity
- **DFS Approach**: O(V + E)
  - Each vertex visited exactly once: O(V)
  - Each edge examined exactly once: O(E)
- **BFS Approach**: O(V + E)
  - In-degree calculation: O(V + E)
  - Queue processing: O(V + E)

### Space Complexity
- **Both Approaches**: O(V)
  - Visited/in-degree tracking: O(V)
  - Result list: O(V)
  - Queue/recursion stack: O(V)

### Algorithm Comparison

| Aspect | DFS Approach | BFS Approach (Kahn's) |
|--------|-------------|------------------------|
| **Implementation** | Recursive, uses stack | Iterative, uses queue |
| **Cycle Detection** | During traversal | After processing |
| **Memory Usage** | Recursion stack | Explicit queue |
| **Output Order** | Post-order based | Level-based processing |
| **Intuition** | "Finish dependencies first" | "Process ready tasks first" |

## Key Implementation Details

### Cycle Detection

**DFS Method**: Uses 3-state vertex tracking
- **State 0**: Unvisited
- **State 1**: Currently being processed (on recursion stack)
- **State 2**: Fully processed

Cycle detected when visiting a vertex in state 1.

**BFS Method**: Uses in-degree counting
- If fewer vertices are processed than total vertices, a cycle exists
- Vertices involved in cycles never reach zero in-degree

### Multiple Valid Orders

Topological sorting is not unique - multiple valid orderings may exist:
- DFS order depends on vertex processing sequence
- BFS order depends on tie-breaking when multiple vertices have zero in-degree
- Both are correct as long as dependency constraints are satisfied

## Real-World Applications

### Course Scheduling System
```java
// Prerequisites: Calculus before Physics, Programming before Data Structures
graph.addEdge("Physics", "Calculus");
graph.addEdge("DataStructures", "Programming");
```

### Build System Dependencies
```java
// Compile order: source files before executables
graph.addEdge("main.exe", "main.cpp");
graph.addEdge("main.exe", "utils.lib");
graph.addEdge("utils.lib", "utils.cpp");
```

### Project Task Dependencies
```java
// Project phases: Design before Implementation before Testing
graph.addEdge("Implementation", "Design");
graph.addEdge("Testing", "Implementation");
```

## Error Handling and Edge Cases

- **Empty Graph**: Returns empty list (valid topological order)
- **Single Vertex**: Returns list with single vertex
- **Disconnected Components**: Handles multiple components correctly
- **Self-Loops**: Detected as cycles
- **Duplicate Edges**: Handled gracefully (no duplicates in adjacency lists)

## Performance Characteristics

- **Best Case**: O(V + E) for both algorithms
- **Average Case**: O(V + E) for both algorithms  
- **Worst Case**: O(V + E) for both algorithms
- **Memory Efficient**: Linear space complexity
- **Scalable**: Suitable for large graphs with thousands of vertices

## Educational Value

This implementation demonstrates:
- **Graph Theory Concepts**: DAGs, topological ordering, cycle detection
- **Algorithm Design**: Recursive vs. iterative approaches
- **Data Structure Usage**: Maps, sets, queues, lists
- **Real-World Problem Solving**: Dependency resolution patterns

The dual implementation allows comparison of different algorithmic approaches to the same problem, showcasing how different strategies (DFS vs. BFS) can achieve the same goal with different characteristics and trade-offs.
