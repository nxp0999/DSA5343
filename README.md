# DSA5343
Collection of programs written in Java submitted as assignments for a graduate class in Data Structures and Algorithms

# Binary Search Tree Implementation

A Java implementation of a Binary Search Tree (BST) data structure with basic operations including insertion, deletion, and traversal.

## Features

- **Insert**: Add new values to the tree while maintaining BST properties
- **Delete**: Remove values from the tree with proper handling of all cases:
  - Node with no children (leaf node)
  - Node with one child
  - Node with two children (uses inorder successor)
- **Inorder Traversal**: Visit nodes in ascending order (left → root → right)

## Operations Supported

### `insert(int val)`
Inserts a new value into the BST. Duplicates are ignored.

### `delete(int val)`
Removes a value from the BST. Uses the inorder successor method for nodes with two children.

### `inorderTraversal()`
Prints all values in the tree in ascending order.

## How to Compile and Run

```bash
# Compile the Java file
javac BinarySearchTree.java

# Run the program
java BinarySearchTree
```

## Usage Example

```java
BinarySearchTree bst = new BinarySearchTree();

// Insert values
bst.insert(40);
bst.insert(60);
bst.insert(20);
// ... more insertions

// Delete a value
bst.delete(40);

// Print tree in sorted order
bst.inorderTraversal();
```

## Sample Input and Output

### Input (Array of values to insert):
```
[40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46]
```

### Tree Structure After Insertion:
```
                    40
                   /  \
                  20   60
                 / \   / \
                10 30 50 80
               /  / \  /\  \
              5  25 35 45 55 90
                /  /\   \   /
               15 32 33  48 70
                     \   /
                      33 46
```

### Output After Operations:
```
Inorder traversal after deleting 40:
5 10 15 20 25 30 32 33 35 45 46 48 50 55 60 70 80 90 

Inorder traversal after deleting 20:
5 10 15 25 30 32 33 35 45 46 48 50 55 60 70 80 90 
```

## Algorithm Details

- **Insertion**: O(log n) average case, O(n) worst case
- **Deletion**: O(log n) average case, O(n) worst case  
- **Traversal**: O(n) time complexity
- **Space Complexity**: O(n) for storing n nodes

## Key Implementation Notes

- The tree uses recursive algorithms for all operations
- Deletion of nodes with two children uses the inorder successor (leftmost node in right subtree)
- The `tmp` variable serves as the root of the tree
- Inorder traversal produces a sorted sequence of values
