# DSA5343
Collection of programs written in Java submitted as assignments for a graduate class in Data Structures and Algorithms

# HeapSort Algorithm Implementation

A comprehensive Java implementation of the HeapSort algorithm featuring both ascending and descending order sorting capabilities. The implementation uses a 1-indexed array structure with heap size stored at index 0, and provides both min-heap (for descending order) and max-heap (for ascending order) sorting methods.

## Features

- **Dual Sorting Modes**: Min-heap for descending order, max-heap for ascending order
- **1-Indexed Array Structure**: Heap size stored at arr[0], elements from arr[1] onwards
- **Heap Validation**: Built-in method to verify heap property
- **Input Validation**: Robust error handling for edge cases
- **Performance Monitoring**: Clear demonstration of heap building and sorting phases
- **Utility Methods**: Array creation, printing, and heap property checking

## Algorithm Overview

HeapSort is a comparison-based sorting algorithm that uses a binary heap data structure:

1. **Build Heap Phase**: Convert array into a heap (O(n) time)
2. **Sorting Phase**: Repeatedly extract the root and place it in correct position (O(n log n) time)

### Unique Implementation Feature

This implementation demonstrates an interesting approach:
- **Min-Heap → Descending Order**: Extract smallest elements first, place at end
- **Max-Heap → Ascending Order**: Extract largest elements first, place at end

## Array Structure

The implementation uses a 1-indexed heap array:
```
arr[0] = heap size (number of elements)
arr[1] = first element (root of heap)
arr[2] = second element (left child of root)
arr[3] = third element (right child of root)
...
```

For a node at index `i`:
- Left child: `2*i`
- Right child: `2*i + 1`
- Parent: `i/2`

## Operations Supported

### Core Sorting Methods
- `heapSort(int[] arr)`: Sorts in descending order using min-heap
- `heapSortAscending(int[] arr)`: Sorts in ascending order using max-heap

### Heap Operations
- `heapify(int[] arr, int i)`: Maintains min-heap property
- `buildHeap(int[] arr)`: Converts array to min-heap
- `maxHeapify(int[] arr, int i)`: Maintains max-heap property
- `buildMaxHeap(int[] arr)`: Converts array to max-heap

### Utility Methods
- `printArray(int[] arr)`: Displays array contents
- `isMinHeap(int[] arr)`: Validates min-heap property
- `createHeapArray(int[] values)`: Converts regular array to heap format

## How to Compile and Run

```bash
# Compile the Java file
javac HeapSort.java

# Run the program
java HeapSort
```

## Usage Example

```java
HeapSort hs = new HeapSort();

// Create heap array from regular array
int[] values = {64, 34, 25, 12, 22, 11, 90};
int[] heapArray = HeapSort.createHeapArray(values);

// Sort in descending order (min-heap approach)
hs.heapSort(heapArray);
hs.printArray(heapArray); // Output: 90 64 34 25 22 12 11

// Sort in ascending order (max-heap approach)  
int[] heapArray2 = HeapSort.createHeapArray(values);
hs.heapSortAscending(heapArray2);
hs.printArray(heapArray2); // Output: 11 12 22 25 34 64 90
```

## Sample Input and Output

### Input Array:
```
[23, 17, 14, 6, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0, 11]
```

### Expected Output:
```
=== HeapSort Demo (Descending Order) ===
Original array:
23 17 14 6 13 10 1 5 7 12 4 8 9 0 11 

Array after converting to min-heap:
0 4 1 5 12 8 9 23 7 17 13 10 14 6 11 
Is valid min-heap: true

Array after heap sort (descending order):
23 17 14 13 12 11 10 9 8 7 6 5 4 1 0 

=== Alternative: Ascending Order Sort ===
Original array:
23 17 14 6 13 10 1 5 7 12 4 8 9 0 11 

Array after heap sort (ascending order):
0 1 4 5 6 7 8 9 10 11 12 13 14 17 23 

=== Algorithm Explanation ===
Min-heap + extract minimum → Descending order
Max-heap + extract maximum → Ascending order
```

## Algorithm Analysis

- **Time Complexity**: 
  - Build Heap: O(n)
  - Sorting Phase: O(n log n)
  - Overall: O(n log n)
- **Space Complexity**: O(1) - in-place sorting
- **Stability**: Not stable (equal elements may change relative order)
- **Best/Average/Worst Case**: O(n log n) for all cases

## Heap Properties

### Min-Heap Property
For every node `i`, the value at `i` is smaller than or equal to values at its children:
```
arr[i] ≤ arr[2*i] and arr[i] ≤ arr[2*i + 1]
```

### Max-Heap Property  
For every node `i`, the value at `i` is greater than or equal to values at its children:
```
arr[i] ≥ arr[2*i] and arr[i] ≥ arr[2*i + 1]
```

## Step-by-Step Algorithm

### Descending Order (Min-Heap Method):
1. **Build min-heap**: Smallest element becomes root
2. **Extract minimum**: Swap root with last element
3. **Reduce heap size**: Exclude sorted element
4. **Re-heapify**: Restore min-heap property
5. **Repeat**: Until only one element remains

### Ascending Order (Max-Heap Method):
1. **Build max-heap**: Largest element becomes root
2. **Extract maximum**: Swap root with last element  
3. **Reduce heap size**: Exclude sorted element
4. **Re-heapify**: Restore max-heap property
5. **Repeat**: Until only one element remains

## Key Implementation Details

- **Heapify Direction**: Starts from last non-leaf node (`n/2`) down to root
- **Index Calculation**: Uses 1-based indexing for clean parent-child relationships
- **Size Management**: Dynamic heap size reduction during sorting phase
- **Validation**: Input checking prevents array bounds errors
- **Memory Efficiency**: In-place sorting with O(1) extra space

## Performance Characteristics

- **Consistent Performance**: O(n log n) regardless of input distribution
- **Memory Efficient**: No additional arrays needed
- **Cache Friendly**: Good locality of reference during heap operations
- **Predictable**: No worst-case quadratic behavior unlike QuickSort

## Error Handling

- **Null Array**: Gracefully handles null input
- **Empty Array**: Manages arrays with zero elements
- **Single Element**: Correctly processes single-element arrays
- **Bounds Checking**: Prevents index out of bounds errors

## Applications

- **Priority Queues**: Foundation for heap-based priority queue implementations
- **External Sorting**: Useful when memory is limited
- **Real-time Systems**: Predictable O(n log n) performance
- **Embedded Systems**: Low memory footprint sorting

This HeapSort implementation provides a robust, educational, and practical sorting solution with comprehensive heap management capabilities and dual sorting order support.
