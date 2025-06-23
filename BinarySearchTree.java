public class BinarySearchTree {
    private Node tmp;

    private class Node {
        int val;
        Node lch;
        Node rch;

        Node(int val) {
            this.val = val;
            lch = null;
            rch = null;
        }
    }

    public BinarySearchTree() {
        tmp = null;
    }

    public void insert(int val) {
        tmp = ins(tmp, val);
    }

     
    private Node ins(Node tmp, int val) {
        
        //checking if the current node is null
        if (tmp == null) {
            return new Node(val);
        }

        //checking if the value is less than the value of tmp ie., parent, insertion is on the left
        if (val < tmp.val) {
            tmp.lch = ins(tmp.lch, val);
        }
        
        //checking if the value is less than the value of tmp ie., parent, insertion is on the right
        else if (val > tmp.val) {
            tmp.rch = ins(tmp.rch, val);
        }

        //node value is added to the tree
        return tmp;
        
    }

    public void inorderTraversal() {
        inorderRecursive(tmp);
        System.out.println(); // New line after traversal
    }

    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.lch);
            System.out.print(node.val + " ");
            inorderRecursive(node.rch);
        }
    }

    
    public void delete(int val) {
        tmp = deleteRecursive(tmp, val);
    }

    private Node deleteRecursive(Node tmp, int val) {
        if (tmp == null) {
            return null;
        }

        if (val == tmp.val) {
            //checking if there are no children
            if (tmp.lch == null && tmp.rch == null) {
                return null;
            }

            //checking if theres only one child
            if (tmp.rch == null) {
                return tmp.lch;
            }
            if (tmp.lch == null) {
                return tmp.rch;
            }

            //checking if there are both children
            //finding the smaller value using successor rule
            int smallestval = findSmallestval(tmp.rch);
            tmp.val = smallestval;
            tmp.rch = deleteRecursive(tmp.rch, smallestval);
            return tmp;
        }

        if (val < tmp.val) {
            tmp.lch = deleteRecursive(tmp.lch, val);
            return tmp;
        }

        tmp.rch = deleteRecursive(tmp.rch, val);
        return tmp;
    }

    private int findSmallestval(Node tmp) {
        
        //using conditional operator to call the smaller value function
        return tmp.lch == null ? tmp.val : findSmallestval(tmp.lch);
    }
    

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] nodes = { 40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46 };

        for (int node : nodes) {
            bst.insert(node);
        }

        //System.out.println("Inorder traversal after insertion:");
        //bst.inorderTraversal();

        //delete 40
        bst.delete(40);
        System.out.println("\nInorder traversal after deleting 40:");
        bst.inorderTraversal();

        //delete 20
        bst.delete(20);
        System.out.println("\nInorder traversal after deleting 20:");
        bst.inorderTraversal();

    }
}
