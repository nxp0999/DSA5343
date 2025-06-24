//program to print the values of a linked list in ascending order using selection sort without changing the addresses of the values

//package to generate values at random
//import java.util.Random;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SortedLinkedList {

    Node head;

    // defining main for the program
    public static void main(String[] args) {
        SortedLinkedList list = new SortedLinkedList();

        /*
        //code segment which can generate random values to add to the linked list as testing data
        Random rand = new Random();
        //insert at least 15 random nodes
        for (int i = 0; i < 15; i++) {
            list.insert(rand.nextInt(100));
        }*/
        
        // creating an unsorted linked list with atleast 15 nodes
        list.addNode(37);
        list.addNode(6);
        list.addNode(29);
        list.addNode(4);
        list.addNode(48);
        list.addNode(19);
        list.addNode(98);
        list.addNode(35);
        list.addNode(2);
        list.addNode(18);
        list.addNode(76);
        list.addNode(46);
        list.addNode(3);
        list.addNode(54);
        list.addNode(29);

        // to traverse and print the unsorted list
        System.out.println("Unsorted list:");
        list.traverse();

        // to sort the linked list using selection sort
        list.selectionSort();
        
        //to print the sorted list
        System.out.println("Sorted list:");
        list.traverse();
    }

    //adding nodes to the linked list
    public void addNode(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;

        } else {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }

    }
    
    /*
    //function for adding the node values at random
    public void insert(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
    }*/
    
    
    //traversing the linked list to print its nodes
    public void traverse() {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data + " => ");
            //System.out.printf("(Value: %d, Address: %s) ", tmp.data, tmp.toString()); // printing value and address of the values in the linked list
            tmp = tmp.next;
        }
        System.out.println("null");
    }

    //function to sort the list using selection sort
    public void selectionSort() {
        if (head == null || head.next == null){
            return;
        }

        Node tmp = new Node(0);
        tmp.next = head;
        Node tmpsort = tmp;

        while (tmpsort.next != null) {
            Node min = tmpsort.next;
            Node prevmin = tmpsort;
            Node current = tmpsort.next.next;
            Node prev = tmpsort.next;

            while (current != null) {
                if (current.data < min.data) {
                    min = current;
                    prevmin = prev;
                }
                prev = current;
                current = current.next;
            }

            if (min != tmpsort.next) {
                prevmin.next = min.next;
                min.next = tmpsort.next;
                tmpsort.next = min;
            }

            tmpsort = tmpsort.next;
        }

        head = tmp.next;
    }

}
