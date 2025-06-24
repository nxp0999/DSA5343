# DSA5343
Collection of programs written in Java submitted as assignments for a graduate class in Data Structures and Algorithms

# Linked List Selection Sort

A Java implementation of selection sort for linked lists that sorts by rearranging node positions rather than swapping values. This approach preserves the original memory addresses of the nodes while achieving sorted order, demonstrating an important distinction between value-based and pointer-based sorting techniques.

## Features

- **Node-Based Sorting**: Moves actual nodes instead of swapping values
- **Address Preservation**: Original node memory addresses remain unchanged during sorting
- **Selection Sort Algorithm**: Finds minimum element in each pass and moves it to correct position
- **Comprehensive Validation**: Includes methods to verify sorting correctness and address preservation
- **Random Data Generation**: Can generate test data with random values
- **Memory Address Tracking**: Displays node addresses before and after sorting for verification
- **Utility Methods**: Size calculation, search functionality, and sorted verification

## Algorithm Overview

The implementation uses selection sort with a unique twist - instead of swapping values between nodes, it physically rearranges the nodes themselves in memory. This demonstrates the difference between:

- **Traditional approach**: Swap `node1.data` with `node2.data` (changes values, preserves addresses)
- **This approach**: Rearrange node linkages (preserves values in original memory locations, changes list structure)

## Core Algorithm Steps

1. **Create dummy head**: Simplifies edge case handling during node manipulation
2. **For each position in sorted portion**:
   - Find the minimum value node in remaining unsorted portion
   - Track the previous node for proper unlinking
   - Move minimum node to current sorted position
   - Update linkages to maintain list integrity
3. **Update head pointer**: Point to first actual node (excluding dummy)

## Node Structure

```java
class Node {
    int data;        // The value stored in the node
    Node next;       // Reference to the next node
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

## Operations Supported

### Core Methods
- `addNode(int val)`: Adds a new node to the end of the list
- `selectionSort()`: Sorts the list by moving nodes (preserves addresses)
- `traverse()`: Prints all values in the list
- `printAddresses()`: Shows memory addresses of all nodes

### Utility Methods
- `isSorted()`: Verifies if the list is sorted in ascending order
- `getSize()`: Returns the number of nodes in the list
- `search(int value)`: Searches for a specific value
- `generateRandomList(int count, int maxValue)`: Creates random test data
- `clear()`: Removes all nodes from the list
- `isEmpty()`: Checks if the list is empty

## How to Compile and Run

```bash
# Compile the Java file
javac SortedLinkedList.java

# Run the program
java SortedLinkedList
```

## Usage Example

```java
SortedLinkedList list = new SortedLinkedList();

// Add values to the list
list.addNode(64);
list.addNode(34);
list.addNode(25);
list.addNode(12);

// Print original list
list.traverse(); // Output: 64 -> 34 -> 25 -> 12 -> null

// Sort the list (moves nodes, preserves addresses)
list.selectionSort();

// Print sorted list  
list.traverse(); // Output: 12 -> 25 -> 34 -> 64 -> null

// Verify sorting
System.out.println("Is sorted: " + list.isSorted()); // true
```

## Sample Input and Output

### Input Values:
```
[37, 6, 29, 4, 48, 19, 98, 35, 2, 18, 76, 46, 3, 54, 29]
```

### Expected Output:
```
=== Linked List Selection Sort Demo ===
Adding 15 nodes to the linked list...

Unsorted linked list:
37 -> 6 -> 29 -> 4 -> 48 -> 19 -> 98 -> 35 -> 2 -> 18 -> 76 -> 46 -> 3 -> 54 -> 29 -> null

Node addresses before sorting:
Node[0]: Value=37, Address=Node@1a2b3c4d
Node[1]: Value=6, Address=Node@2b3c4d5e
Node[2]: Value=29, Address=Node@3c4d5e6f
Node[3]: Value=4, Address=Node@4d5e6f7g
Node[4]: Value=48, Address=Node@5e6f7g8h
...

Sorting using selection sort (moving nodes, not values)...

Sorted linked list:
2 -> 3 -> 4 -> 6 -> 18 -> 19 -> 29 -> 29 -> 35 -> 37 -> 46 -> 48 -> 54 -> 76 -> 98 -> null

Node addresses after sorting:
Node[0]: Value=2, Address=Node@8h9i0j1k
Node[1]: Value=3, Address=Node@9i0j1k2l
Node[2]: Value=4, Address=Node@4d5e6f7g
Node[3]: Value=6, Address=Node@2b3c4d5e
Node[4]: Value=18, Address=Node@0j1k2l3m
...

Is list sorted correctly? true
List size: 15

=== Random Values Demo ===
Random unsorted list:
45 -> 12 -> 78 -> 23 -> 56 -> 34 -> 89 -> 67 -> 91 -> 5 -> null
After selection sort:
5 -> 12 -> 23 -> 34 -> 45 -> 56 -> 67 -> 78 -> 89 -> 91 -> null
Is sorted: true
```

## Algorithm Analysis

- **Time Complexity**: O(n²) where n is the number of nodes
  - Outer loop: n iterations
  - Inner loop: Finding minimum in remaining unsorted portion
  - Each pass examines fewer elements: n + (n-1) + (n-2) + ... + 1 = n²/2
- **Space Complexity**: O(1) - only uses a constant amount of extra space
- **Stability**: Not stable (relative order of equal elements may change)
- **In-place**: Yes (if we consider rearranging pointers as in-place)

## Key Implementation Details

### Dummy Head Technique
```java
Node dummy = new Node(0);
dummy.next = head;
```
Using a dummy head simplifies the algorithm by:
- Avoiding special cases for the first node
- Providing a consistent way to handle node insertion at the beginning
- Eliminating null pointer checks when moving nodes

### Node Movement Process
For each minimum node found:
1. **Unlink from current position**: `prevMinNode.next = minNode.next`
2. **Link to sorted position**: `minNode.next = sortedTail.next`
3. **Update sorted tail**: `sortedTail.next = minNode`

### Address Preservation Verification
The program demonstrates that node addresses remain constant:
- Same `Node@hashcode` values before and after sorting
- Only the linkage between nodes changes
- Original memory locations of data remain intact

## Comparison with Traditional Sorting

| Aspect | Value Swapping | Node Movement (This Implementation) |
|--------|----------------|-------------------------------------|
| **Memory Addresses** | Nodes stay in same positions | Nodes change positions |
| **Data Values** | Values move between nodes | Values stay with original nodes |
| **Implementation** | Simple data assignment | Complex pointer manipulation |
| **Use Case** | Most common approach | Educational/specific requirements |

## Performance Characteristics

- **Best Case**: O(n²) - even if list is already sorted
- **Average Case**: O(n²) - quadratic performance
- **Worst Case**: O(n²) - reverse sorted list
- **Memory Usage**: Minimal extra space (just a few pointer variables)

## Educational Value

This implementation demonstrates several important computer science concepts:

1. **Pointer Manipulation**: Understanding how to rearrange linked structures
2. **Memory Management**: Distinction between data movement and structural rearrangement
3. **Algorithm Adaptation**: Modifying algorithms for different data structures
4. **Complexity Analysis**: Time/space tradeoffs in different approaches

## Real-World Applications

While selection sort is not optimal for large datasets, this pointer-based approach is useful for:

- **Educational purposes**: Teaching pointer manipulation and linked list operations
- **Small datasets**: When simplicity is more important than efficiency
- **Memory-constrained environments**: When data copying is expensive
- **Specialized requirements**: When preserving original memory locations is important

## Error Handling

The implementation includes robust error handling for:
- **Empty lists**: Gracefully handles null head
- **Single-element lists**: No sorting needed, returns immediately
- **Null inputs**: Safe handling of edge cases
- **Memory verification**: Address tracking confirms correct implementation

This implementation provides a comprehensive demonstration of selection sort adapted for linked lists with emphasis on understanding the underlying mechanics of node manipulation and memory management.
