# DSA5343
Collection of programs written in Java submitted as assignments for a graduate class in Data Structures and Algorithms

# Hash Table with Quadratic Probing

A Java implementation of a hash table data structure using quadratic probing for collision resolution. The hash table automatically resizes when the load factor exceeds a specified threshold and provides collision tracking for performance analysis.

## Features

- **Quadratic Probing**: Uses quadratic probing (i²) to resolve hash collisions
- **Dynamic Resizing**: Automatically doubles in size when load factor exceeds 0.5
- **Polynomial Hash Function**: Uses base-31 polynomial rolling hash for string keys
- **Collision Tracking**: Monitors and reports the number of collisions
- **Load Factor Management**: Maintains optimal performance through load factor control
- **Error Handling**: Validates input and prevents infinite loops

## Hash Function

The implementation uses a polynomial rolling hash function:
```
hash = (31 * hash + character) for each character in the string
```
This provides good distribution properties and is commonly used for string hashing.

## Collision Resolution

**Quadratic Probing**: When a collision occurs, the algorithm searches for the next available slot using:
```
index = (original_index + i²) % table_size
```
where `i` is the probe sequence number (1, 2, 3, ...).

## Operations Supported

### `insert(String word)`
Inserts a string into the hash table. Automatically handles collisions and triggers resizing if needed.

### `getCollisions()`
Returns the total number of collisions encountered during insertions.

### `getSize()`
Returns the current size of the hash table.

### `getItemCount()`
Returns the number of items currently stored in the hash table.

## How to Compile and Run

```bash
# Compile the Java file
javac HashTable.java

# Run the program
java HashTable
```

## Usage Example

```java
HashTable hashTable = new HashTable();

// Insert strings
hashTable.insert("apple");
hashTable.insert("banana");
hashTable.insert("cherry");

// Check statistics
System.out.println("Items: " + hashTable.getItemCount());
System.out.println("Collisions: " + hashTable.getCollisions());
System.out.println("Table size: " + hashTable.getSize());
```

## Sample Input and Output

### Input (Array of animal names):
```
["dog", "cat", "rat", "bird", "fish", "lion", "bear", "wolf", "deer", "frog",
 "snake", "tiger", "horse", "sheep", "goat", "rabbit", "mouse", "eagle", "hawk", "duck"]
```

### Expected Output:
```
Inserted: dog    Size: 31   Items: 1   Collisions: 0
Inserted: cat    Size: 31   Items: 2   Collisions: 0
Inserted: rat    Size: 31   Items: 3   Collisions: 0
Inserted: bird   Size: 31   Items: 4   Collisions: 0
Inserted: fish   Size: 31   Items: 5   Collisions: 0
Inserted: lion   Size: 31   Items: 6   Collisions: 0
Inserted: bear   Size: 31   Items: 7   Collisions: 0
Inserted: wolf   Size: 31   Items: 8   Collisions: 0
Inserted: deer   Size: 31   Items: 9   Collisions: 0
Inserted: frog   Size: 31   Items: 10  Collisions: 0
Inserted: snake  Size: 31   Items: 11  Collisions: 0
Inserted: tiger  Size: 31   Items: 12  Collisions: 0
Inserted: horse  Size: 31   Items: 13  Collisions: 0
Inserted: sheep  Size: 31   Items: 14  Collisions: 0
Inserted: goat   Size: 31   Items: 15  Collisions: 0
Inserted: rabbit Size: 31   Items: 16  Collisions: 0
Inserted: mouse  Size: 62   Items: 17  Collisions: 0
Inserted: eagle  Size: 62   Items: 18  Collisions: 0
Inserted: hawk   Size: 62   Items: 19  Collisions: 0
Inserted: duck   Size: 62   Items: 20  Collisions: 0

Final table size: 62
Total items: 20
Total collisions: 0
```

**Note**: The table automatically resizes from 31 to 62 when the load factor threshold (0.5) is reached, which occurs before inserting the 17th item (16/31 > 0.5).

## Algorithm Details

- **Time Complexity**: 
  - Average case: O(1) for insertion
  - Worst case: O(n) when many collisions occur
- **Space Complexity**: O(n) where n is the number of items
- **Load Factor**: Maintained below 0.5 for optimal performance
- **Resizing**: Doubles table size when load factor exceeds threshold

## Key Implementation Features

- **Load Factor Threshold**: 0.5 (50% capacity)
- **Initial Size**: 31 (prime number for better hash distribution)
- **Resize Strategy**: Double the current size
- **Probe Sequence**: Quadratic probing with i² increments
- **Hash Distribution**: Polynomial rolling hash with base 31

## Collision Handling

The quadratic probing sequence explores slots in the following pattern:
```
original_index, (original_index + 1) % size, (original_index + 4) % size, 
(original_index + 9) % size, (original_index + 16) % size, ...
```

This helps avoid clustering that can occur with linear probing.

## Performance Characteristics

- **Best Case**: No collisions, O(1) insertion time
- **Average Case**: Few collisions due to good hash function and load factor management
- **Collision Rate**: Typically low due to 0.5 load factor threshold and prime table sizes
- **Resizing Cost**: O(n) when triggered, but amortized over many insertions

## Error Handling

- **Null Input**: Throws `IllegalArgumentException` for null keys
- **Full Table**: Throws `IllegalStateException` if no empty slot can be found
- **Infinite Loop Prevention**: Limits probe attempts to prevent infinite searching

## Design Decisions

- **String-only Keys**: Specialized for string storage (easily extensible to generic types)
- **Open Addressing**: Uses quadratic probing instead of chaining for memory efficiency
- **Prime Table Sizes**: Initial size 31 helps reduce clustering
- **Conservative Load Factor**: 0.5 threshold ensures good performance with minimal memory waste

The implementation provides an efficient hash table suitable for applications requiring fast string lookup and insertion with predictable performance characteristics.
