import java.util.Random;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
    
    @Override
    public String toString() {
        return "Node@" + Integer.toHexString(hashCode());
    }
}

public class SortedLinkedList {
    private Node head;

    public static void main(String[] args) {
        SortedLinkedList list = new SortedLinkedList();

        // Creating an unsorted linked list with 15 nodes
        int[] values = {37, 6, 29, 4, 48, 19, 98, 35, 2, 18, 76, 46, 3, 54, 29};
        
        System.out.println("=== Linked List Selection Sort Demo ===");
        System.out.println("Adding " + values.length + " nodes to the linked list...\n");
        
        for (int value : values) {
            list.addNode(value);
        }

        // Print unsorted list
        System.out.println("Unsorted linked list:");
        list.traverse();
        
        // Show node addresses before sorting
        System.out.println("\nNode addresses before sorting:");
        list.printAddresses();

        // Sort the linked list using selection sort
        System.out.println("\nSorting using selection sort (moving nodes, not values)...");
        list.selectionSort();
        
        // Print sorted list
        System.out.println("\nSorted linked list:");
        list.traverse();
        
        // Show node addresses after sorting to verify they haven't changed
        System.out.println("\nNode addresses after sorting:");
        list.printAddresses();
        
        // Verify sorting correctness
        System.out.println("\nIs list sorted correctly? " + list.isSorted());
        System.out.println("List size: " + list.getSize());
        
        // Demonstrate with random values
        System.out.println("\n=== Random Values Demo ===");
        SortedLinkedList randomList = new SortedLinkedList();
        randomList.generateRandomList(10, 100);
        
        System.out.println("Random unsorted list:");
        randomList.traverse();
        
        randomList.selectionSort();
        System.out.println("After selection sort:");
        randomList.traverse();
        System.out.println("Is sorted: " + randomList.isSorted());
    }

    /**
     * Adds a new node to the end of the linked list
     * @param val The value to add
     */
    public void addNode(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    /**
     * Generates a linked list with random values
     * @param count Number of nodes to generate
     * @param maxValue Maximum value for random numbers
     */
    public void generateRandomList(int count, int maxValue) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            addNode(rand.nextInt(maxValue));
        }
    }
    
    /**
     * Traverses and prints the linked list values
     */
    public void traverse() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }
    
    /**
     * Prints the memory addresses of all nodes to verify they don't change during sorting
     */
    public void printAddresses() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = head;
        int index = 0;
        while (current != null) {
            System.out.printf("Node[%d]: Value=%d, Address=%s%n", 
                            index, current.data, current.toString());
            current = current.next;
            index++;
        }
    }

    /**
     * Sorts the linked list using selection sort by moving nodes (not swapping values)
     * This preserves the original node addresses while sorting the list
     */
    public void selectionSort() {
        if (head == null || head.next == null) {
            return; // List is empty or has only one element
        }

        // Create a dummy head node to simplify node manipulation
        Node dummy = new Node(0);
        dummy.next = head;
        Node sortedTail = dummy; // Points to the last node of sorted portion

        while (sortedTail.next != null) {
            // Find the minimum node in the remaining unsorted portion
            Node minNode = sortedTail.next;
            Node prevMinNode = sortedTail;
            Node current = sortedTail.next.next;
            Node prevCurrent = sortedTail.next;

            // Search for the minimum value in unsorted portion
            while (current != null) {
                if (current.data < minNode.data) {
                    minNode = current;
                    prevMinNode = prevCurrent;
                }
                prevCurrent = current;
                current = current.next;
            }

            // Move minimum node to the sorted position (if it's not already there)
            if (minNode != sortedTail.next) {
                // Remove minimum node from its current position
                prevMinNode.next = minNode.next;
                
                // Insert minimum node at the beginning of unsorted portion
                minNode.next = sortedTail.next;
                sortedTail.next = minNode;
            }

            // Move sorted boundary forward
            sortedTail = sortedTail.next;
        }

        // Update head to point to the first actual node (skip dummy)
        head = dummy.next;
    }
    
    /**
     * Checks if the linked list is sorted in ascending order
     * @return true if sorted, false otherwise
     */
    public boolean isSorted() {
        if (head == null || head.next == null) {
            return true;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.data > current.next.data) {
                return false;
            }
            current = current.next;
        }
        return true;
    }
    
    /**
     * Returns the size of the linked list
     * @return number of nodes in the list
     */
    public int getSize() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    /**
     * Searches for a value in the linked list
     * @param value The value to search for
     * @return true if found, false otherwise
     */
    public boolean search(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Clears the linked list
     */
    public void clear() {
        head = null;
    }
    
    /**
     * Checks if the linked list is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }
}
