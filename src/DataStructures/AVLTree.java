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

    public static AVLTree VirtualLibrary = new AVLTree();
    public AVLNode root;
    public int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public BTree getCategory(String categoryName) {
        try {
            return checkRecursively(this.root, categoryName).getBTree();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    private AVLNode checkRecursively(AVLNode actualNode, String categoryName) {
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

        //Get the balance factor of the actual node to check whether this node became unbalanced 
        int balance = getBalance(actualNode);
        // Depending on the balance value there are 4 cases.
        //Left Left Case    
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

        return actualNode;
    }

    public void delete(String category) {
        Logical log = new Logical(false);
        this.root = deleteRecursively(this.root, category, log);
    }

    AVLNode deleteRecursively(AVLNode actualNode, String category, Logical log) {
        if (actualNode == null) {
            System.out.println("No sale");
        } else if (category.compareTo(actualNode.categoryName) < 0) {
            AVLNode left = deleteRecursively(actualNode.left, category, log);
            actualNode.left = left;
            if (log.wellBalanced) {
                actualNode = setBalanceL(actualNode, log);
            }
        } else if (category.compareTo(actualNode.categoryName) > 0) {
            AVLNode right = deleteRecursively(actualNode.right, category, log);
            actualNode.right = right;
            if (log.wellBalanced) {
                actualNode = setBalanceR(actualNode, log);
            }
        } else {
            AVLNode tmp = actualNode;
            if (tmp.left == null) {
                actualNode = tmp.right;
                log.wellBalanced = true;
            } else if (tmp.right == null) {
                actualNode = tmp.left;
                log.wellBalanced = true;
            } else {
                AVLNode node = replace(actualNode, actualNode.left, log);
                actualNode.left = node;
                if (log.wellBalanced) {
                    actualNode = setBalanceL(actualNode, log);
                }
            }
            System.out.println("Se elimino");
            tmp = null;
        }
        return actualNode;
    }

    AVLNode replace(AVLNode tmp, AVLNode aux, Logical log) {
        if (aux.right != null) {
            AVLNode right = replace(tmp, aux.right, log);
            aux.right=right;
            if (log.wellBalanced) {
                aux = setBalanceR(aux, log);
            }
        } else {
            //tmp.setData(aux.getData());
            tmp = aux;
            //aux = (AVL_Node) aux.getLeft();
            tmp = null;
            //log.setLog(true);
        }
        return aux;
    }

    AVLNode setBalanceL(AVLNode actualNode, Logical log) {
        AVLNode tmp;
        switch (getBalance(actualNode)) {
            case -1:
                System.out.println("No se que pedo jaja");
                break;
            case 0:
                //actualNode
                break;
            case 1:
                tmp = actualNode.right;
                if (getBalance(tmp) >= 0) {
                    if (getBalance(tmp) == 0) {
                        log.wellBalanced = false;
                    }
                    //actualNode = 
                }
        }
        return null;
    }

    AVLNode setBalanceR(AVLNode actualNode, Logical log) {
        AVLNode tmp;
        switch (getBalance(actualNode)) {
            case -1:
                System.out.println("No se que pedo jaja");
                break;
            case 0:
                //actu
                break;
        }
        return null;
    }
}

class Logical {

    public boolean wellBalanced;

    Logical(boolean balanced) {
        wellBalanced = balanced;
    }
}
