/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author Fernando
 */
public class AVLNode {

    public String categoryName; //categoryName same as representation
    int height;
    BTree bTreeCategory; //bTreeCategory same as Object
    AVLNode left, right;

    public AVLNode(String categoryName, BTree bTree) {
        this.categoryName = categoryName;
        this.bTreeCategory = bTree;
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    int itsDot(int nodeCount, StringBuilder dot) {
        int nodeValue = nodeCount;
        dot.append("\tn" + nodeValue).
                append(" [label=\"<left>|").
                append(categoryName + "\\nLibros: " + bTreeCategory.numElements).
                append("|<right>\"];\n");
        if (left != null) {
            dot.append("\tn" + nodeValue + ":left->n" + ++nodeCount + ";\n");
            nodeCount = left.itsDot(nodeCount, dot);
        }
        if (right != null) {
            dot.append("\tn" + nodeValue + ":right->n" + ++nodeCount + ";\n");
            nodeCount = right.itsDot(nodeCount, dot);
        }
        return nodeCount;
    }

    public BTree getBTree() {
        return bTreeCategory;
    }
}
