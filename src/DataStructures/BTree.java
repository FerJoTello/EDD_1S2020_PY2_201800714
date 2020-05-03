/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Elements.Book;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class BTree {

    public static final int ORDER = 5;
    public int numElements;
    public Page root;

    public BTree() {
        this.root = null;
        this.numElements = 0;
    }

    public boolean add(Book book) {
        if (isEmpty()) {
            this.root = new Page(null);
            this.root.addAndSort(book);
            numElements++;
            return true;
        } else {
            return addRecursively(this.root, book);
        }
    }

    private boolean addRecursively(Page actualNode, Book newBook) {
        Node<Book> i = actualNode.values.First;
        boolean exists = false;
        while (i != null) {
            if (i.getObject().getIsbn() == newBook.getIsbn()) {
                exists = true;
                break;
            }
            i = i.getNext();
        }
        if (!exists) {
            if (actualNode.isLeave()) {
                actualNode.addAndSort(newBook);
                numElements++;
                if (actualNode.values.Size == ORDER) {
                    //It's necessary to break nodes.
                    partition(actualNode);
                }
                return true;
            } else {
                return addRecursively(actualNode.getChildPage(newBook.getIsbn()), newBook);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede volver a ingresar el libro con el siguiente ISBN: " + newBook.getIsbn());
            return false;
        }
    }

    private void partition(Page splittedPage) {
        Book mediumBook;
        if (splittedPage.father == null) {
            //It's the root
            mediumBook = splittedPage.split(splittedPage);
            splittedPage.values = new LinkedList<>();
            splittedPage.addAndSort(mediumBook);
        } else {
            //Any other node
            mediumBook = splittedPage.split(splittedPage.father);
            splittedPage.father.addAndSort(mediumBook);
            if (splittedPage.father.values.Size == ORDER) {
                partition(splittedPage.father);
            }
        }
    }

    public String generateGraph(String name) {
        StringBuilder dot = new StringBuilder("digraph Report{\n"
                + "\tGraph[label = \"Arbol B - Categoria:" + name + "\" fontname=Arial];\n"
                + "\tedge [colorscheme = rdbu11  color=9];\n"
                + "\tnode [colorscheme=rdbu11  color=9 style=filled fillcolor=8 fontname=Arial fontcolor=6 shape=record];\n");
        this.root.itsDot(0, dot);
        dot.append("}");
        Grapher grapher = new Grapher(name);
        return grapher.generateGraph(dot.toString());
    }

    private boolean isEmpty() {
        return numElements == 0;
    }
}
