/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Elements.Book;

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

    public void add(Book book) {
        if (isEmpty()) {
            this.root = new Page(null);
            this.root.addAndSort(book);
            numElements++;
        } else {
            addRecursively(this.root, book);
        }
    }

    private void addRecursively(Page actualNode, Book newBook) {
        if (actualNode.isLeave()) {
            actualNode.addAndSort(newBook);
            numElements++;
            if (actualNode.values.Size == ORDER) {
                //It's necessary to break nodes.
                partition(actualNode);
            }
        } else {
            addRecursively(actualNode.getChildPage(newBook.getIsbn()), newBook);
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
