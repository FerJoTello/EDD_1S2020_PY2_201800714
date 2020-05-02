/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class AVLTree {

    public static AVLTree Categories = new AVLTree();
    public AVLNode root;
    public int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public AVLNode checkRecursively(AVLNode actualNode, String categoryName) {
        if (actualNode == null) {
            //Doesn't exists
            return null;
        }
        if (categoryName.compareToIgnoreCase(actualNode.categoryName) < 0) {
            //It's previous
            return checkRecursively(actualNode.left, categoryName);
        } else if (categoryName.compareToIgnoreCase(actualNode.categoryName) > 0) {
            //It's next
            return checkRecursively(actualNode.right, categoryName);
        } else if (categoryName.compareToIgnoreCase(actualNode.categoryName) == 0) {
            //JOptionPane.showMessageDialog(null, "Ya existe una categoria con el nombre: " + newNode.categoryName);
            return actualNode;
        }
        return null;
    }

    public void add(BTree bTree, String categoryName) {
        AVLNode newNode = new AVLNode(categoryName, bTree);
        if (isEmpty()) {
            root = newNode;
            size++;
        } else {
            this.root = addRecursively(this.root, newNode);
        }
    }

    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int max(int val1, int val2) {
        return (val1 > val2) ? val1 : val2;
    }

    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode rightRotate(AVLNode t2) {
        AVLNode t1 = t2.left;
        AVLNode temp = t1.right;
        t1.right = t2;
        t2.left = temp;
        t1.height = max(getHeight(t1.left), getHeight(t1.right)) + 1;
        t2.height = max(getHeight(t2.left), getHeight(t2.right)) + 1;
        return t1;
    }

    private AVLNode leftRotate(AVLNode t1) {
        AVLNode t2 = t1.right;
        AVLNode temp = t2.left;
        t2.left = t1;
        t1.right = temp;
        t1.height = max(getHeight(t1.left), getHeight(t1.right)) + 1;
        t2.height = max(getHeight(t2.left), getHeight(t2.right)) + 1;
        return t2;
    }

    public LinkedList<BTree> getPreOrder() {
        LinkedList<BTree> preOrderList = new LinkedList();
        if (!isEmpty()) {
            preOrderList = addPreOrder(this.root, preOrderList);
        }
        return preOrderList;
    }

    //  Preorder: Root, Left, Right.
    private LinkedList<BTree> addPreOrder(AVLNode actualNode, LinkedList<BTree> preOrderList) {
        preOrderList.AddLast(actualNode.bTreeCategory, actualNode.categoryName + "\\nLibros: " + actualNode.bTreeCategory.numElements);
        if (actualNode.left != null) {
            preOrderList = addPreOrder(actualNode.left, preOrderList);
        }
        if (actualNode.right != null) {
            preOrderList = addPreOrder(actualNode.right, preOrderList);
        }
        return preOrderList;
    }

    public LinkedList<BTree> getInOrder() {
        LinkedList<BTree> inOrderList = new LinkedList();
        if (!isEmpty()) {
            inOrderList = addInOrder(this.root, inOrderList);
        }
        return inOrderList;
    }

    //  Inorder: Left, Root, Right.
    private LinkedList<BTree> addInOrder(AVLNode actualNode, LinkedList<BTree> inOrderList) {
        if (actualNode.left != null) {
            inOrderList = addInOrder(actualNode.left, inOrderList);
        }
        inOrderList.AddLast(actualNode.bTreeCategory, actualNode.categoryName + "\\nLibros: " + actualNode.bTreeCategory.numElements);
        if (actualNode.right != null) {
            inOrderList = addInOrder(actualNode.right, inOrderList);
        }
        return inOrderList;
    }

    public LinkedList<BTree> getPostOrder() {
        LinkedList<BTree> postOrderList = new LinkedList();
        if (!isEmpty()) {
            postOrderList = addPostOrder(this.root, postOrderList);
        }
        return postOrderList;
    }

    //  Postorder: Left, Right, Root.
    private LinkedList<BTree> addPostOrder(AVLNode actualNode, LinkedList<BTree> postOrderList) {
        if (actualNode.left != null) {
            postOrderList = addPostOrder(actualNode.left, postOrderList);
        }
        if (actualNode.right != null) {
            postOrderList = addPostOrder(actualNode.right, postOrderList);
        }
        postOrderList.AddLast(actualNode.bTreeCategory, actualNode.categoryName + "\\nLibros: " + actualNode.bTreeCategory.numElements);
        return postOrderList;
    }

    public String generateGraph(String name) {
        StringBuilder dot = new StringBuilder("digraph Report{\n"
                + "\tGraph[label = \"Arbol AVL - " + name + "\" fontname=Arial];\n"
                + "\tedge [colorscheme = rdbu11  color=9];\n"
                + "\tnode [colorscheme=rdbu11  color=9 style=filled fillcolor=8 fontname=Arial fontcolor=6 shape=record];\n");
        if (!isEmpty()) {
            this.root.itsDot(0, dot);
        } else {
            dot.append("\tn0[shape=none; label=\"null\"];\n");
        }
        dot.append("}");
        Grapher grapher = new Grapher(name);
        return grapher.generateGraph(dot.toString());
    }

    /**
     * Adds in their respective nodes by comparing the strings.
     */
    private AVLNode addRecursively(AVLNode actualNode, AVLNode newNode) {
        if (actualNode == null) {
            //If it doesn't have any other children.
            size++;
            return newNode;
        }
        if (newNode.categoryName.compareToIgnoreCase(actualNode.categoryName) < 0) {
            //It's previous
            actualNode.left = addRecursively(actualNode.left, newNode);
        } else if (newNode.categoryName.compareToIgnoreCase(actualNode.categoryName) > 0) {
            //It's next
            actualNode.right = addRecursively(actualNode.right, newNode);
        } else if (newNode.categoryName.compareToIgnoreCase(actualNode.categoryName) == 0) {
            //It's the same
            JOptionPane.showMessageDialog(null, "Ya existe una categoria con el nombre: " + newNode.categoryName);
            return actualNode;
        }
        //Updating the height
        actualNode.height = max(getHeight(actualNode.left), getHeight(actualNode.right)) + 1;

        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(actualNode);
        // Depending on the balance value does the 
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case

        if (balance > 1 && newNode.categoryName.compareToIgnoreCase(actualNode.left.categoryName) < 0) {
            return rightRotate(actualNode);
        }

        // Right Right Case 
        if (balance < -1 && newNode.categoryName.compareToIgnoreCase(actualNode.right.categoryName) > 0) {
            return leftRotate(actualNode);
        }

        // Left Right Case 
        if (balance > 1 && newNode.categoryName.compareToIgnoreCase(actualNode.left.categoryName) > 0) {
            actualNode.left = leftRotate(actualNode.left);
            return rightRotate(actualNode);
        }

        // Right Left Case 
        if (balance < -1 && newNode.categoryName.compareToIgnoreCase(actualNode.right.categoryName) < 0) {
            actualNode.right = rightRotate(actualNode.right);
            return leftRotate(actualNode);
        }

        /* return the (unchanged) node pointer */
        return actualNode;
    }
}
