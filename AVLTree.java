public class AVLTree {
    private Node root;

    private static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        int height;

        Node(int val) {
            this.val = val;
            this.height = 1;
        }
    }

    public void insert(int val) {
        Node newNode = new Node(val);
        root = insertAVL(root, newNode);
    }

    private Node insertAVL(Node currentRoot, Node n) {
        n = insertBST(currentRoot, n);
        boolean flag = false;
        Node par1 = n.parent;

        while (par1 != null && !flag) {
            int leftHeight = getHeight(par1.left);
            int rightHeight = getHeight(par1.right);

            if (Math.abs(leftHeight - rightHeight) == 2) {
                avlBalance(par1, leftHeight, rightHeight);
                flag = true;
            }

            updateHeight(par1);
            par1 = par1.parent;
        }

        // Fixed: Return the actual tree root, not the parameter
        return this.root;
    }

    private Node insertBST(Node currentRoot, Node n) {
        if (currentRoot == null) {
            this.root = n;  // Update class root when tree is empty
            return n;
        }

        if (n.val < currentRoot.val) {
            currentRoot.left = insertBST(currentRoot.left, n);
            if (currentRoot.left != null) {
                currentRoot.left.parent = currentRoot;
            }
        } else if (n.val > currentRoot.val) {
            currentRoot.right = insertBST(currentRoot.right, n);
            if (currentRoot.right != null) {
                currentRoot.right.parent = currentRoot;
            }
        }
        // Ignore duplicates

        return currentRoot;
    }

    private void avlBalance(Node n1, int leftHeight, int rightHeight) {
        Node n2 = (leftHeight > rightHeight) ? n1.left : n1.right;
        int leftHeight3 = getHeight(n2.left);
        int rightHeight3 = getHeight(n2.right);
        
        // Fixed: Better handling for equal heights - prefer left child
        Node n3;
        if (leftHeight3 > rightHeight3) {
            n3 = n2.left;
        } else if (rightHeight3 > leftHeight3) {
            n3 = n2.right;
        } else {
            // When heights are equal, prefer left child for consistency
            n3 = n2.left != null ? n2.left : n2.right;
        }

        if (n3 == n2.left && n2 == n1.left) {
            balanceLeftStraightLine(n1, n2, n3);
        } else if (n3 == n2.right && n2 == n1.right) {
            balanceRightStraightLine(n1, n2, n3);
        } else if (n3 == n2.left && n2 == n1.right) {
            balanceZigzagRight(n1, n2, n3);
        } else if (n3 == n2.right && n2 == n1.left) {
            balanceZigzagLeft(n1, n2, n3);
        }
    }

    private void balanceLeftStraightLine(Node n1, Node n2, Node n3) {
        Node tmp2 = n1.parent;
        n1.left = n2.right;
        n1.parent = n2;
        n2.parent = tmp2;
        n2.right = n1;
        
        if (tmp2 != null) {
            if (tmp2.left == n1) {
                tmp2.left = n2;
            } else {
                tmp2.right = n2;
            }
        } else {
            root = n2;
        }
        
        if (n1.left != null) {
            n1.left.parent = n1;
        }
        updateHeight(n1);
        updateHeight(n2);
    }

    private void balanceRightStraightLine(Node n1, Node n2, Node n3) {
        Node tmp2 = n1.parent;
        n1.right = n2.left;
        n1.parent = n2;
        n2.parent = tmp2;
        n2.left = n1;
        
        if (tmp2 != null) {
            if (tmp2.left == n1) {
                tmp2.left = n2;
            } else {
                tmp2.right = n2;
            }
        } else {
            root = n2;
        }
        
        if (n1.right != null) {
            n1.right.parent = n1;
        }
        updateHeight(n1);
        updateHeight(n2);
    }

    private void balanceZigzagRight(Node n1, Node n2, Node n3) {
        Node tmp1 = n3.left;
        Node tmp2 = n3.right;
        Node tmp3 = n1.parent;
        
        n1.right = n3.left;
        n2.left = n3.right;
        n3.left = n1;
        n3.right = n2;
        n3.parent = tmp3;
        n1.parent = n3;
        n2.parent = n3;
        
        if (tmp3 != null) {
            if (tmp3.left == n1) {
                tmp3.left = n3;
            } else {
                tmp3.right = n3;
            }
        } else {
            root = n3;
        }
        
        if (tmp1 != null) {
            tmp1.parent = n1;
        }
        if (tmp2 != null) {
            tmp2.parent = n2;
        }
        updateHeight(n1);
        updateHeight(n2);
        updateHeight(n3);
    }

    private void balanceZigzagLeft(Node n1, Node n2, Node n3) {
        Node tmp1 = n3.left;
        Node tmp2 = n3.right;
        Node tmp3 = n1.parent;
        
        n1.left = n3.right;
        n2.right = n3.left;
        n3.right = n1;
        n3.left = n2;
        n3.parent = tmp3;
        n1.parent = n3;
        n2.parent = n3;
        
        if (tmp3 != null) {
            if (tmp3.left == n1) {
                tmp3.left = n3;
            } else {
                tmp3.right = n3;
            }
        } else {
            root = n3;
        }
        
        if (tmp1 != null) {
            tmp1.parent = n2;
        }
        if (tmp2 != null) {
            tmp2.parent = n1;
        }
        updateHeight(n1);
        updateHeight(n2);
        updateHeight(n3);
    }

    private int getHeight(Node n) {
        return (n == null) ? 0 : n.height;
    }

    private void updateHeight(Node n) {
        if (n != null) {
            n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
        }
    }

    // Added utility methods for better demonstration
    public void inorderTraversal() {
        System.out.print("Inorder traversal: ");
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.val + " ");
            inorderRecursive(node.right);
        }
    }

    public void printTreeStructure() {
        System.out.println("Tree structure:");
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val + " (h:" + node.height + ")");
            if (node.left != null || node.right != null) {
                if (node.right != null) {
                    printTree(node.right, prefix + (isLast ? "    " : "│   "), node.left == null);
                }
                if (node.left != null) {
                    printTree(node.left, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }

    // Enhanced main method with detailed output
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {10, 20, 30, 40, 50, 25};
        
        System.out.println("=== AVL Tree Insertion Demo ===");
        System.out.println("Inserting values: " + java.util.Arrays.toString(values));
        System.out.println();
        
        for (int val : values) {
            System.out.println("Inserting: " + val);
            tree.insert(val);
            tree.inorderTraversal();
            tree.printTreeStructure();
            System.out.println();
        }
        
        System.out.println("=== Final Tree ===");
        tree.inorderTraversal();
        tree.printTreeStructure();
    }
}
