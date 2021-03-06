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
public class Page {

    public int height;
    public LinkedList<Book> values;
    public LinkedList<Page> children;

    public boolean isLeave() {
        return this.children.Size == 0;
    }
    public Page father;

    public Page(Page father) {
        this.values = new LinkedList<>();
        this.children = new LinkedList<>();
        this.father = father;
        if (father != null) {
            height = father.height + 1;
        } else {
            height = 0;
        }
    }

    public void addAndSort(Book newBook) {
        values.AddLast(newBook, String.valueOf(newBook.getIsbn()) + "\\n" + newBook.getTitle());
        this.sort();
    }

    private Page getChildPageByPosition(int position) {
        int jCount = 0;
        for (Node<Page> j = children.First; j != null; j = j.getNext()) {
            if (jCount == position) {
                return j.getObject();
            }
            jCount++;
        }
        return null;
    }

    public Page getChildPage(int isbn) {
        int iCount = 0;
        for (Node<Book> i = values.First; i != null; i = i.getNext()) {
            if (isbn <= i.getObject().getIsbn()) {
                return getChildPageByPosition(iCount);
            } else if (i.getNext() == null) {
                //It's bigger than the last value
                return getChildPageByPosition(iCount + 1);
            }
            iCount++;
        }
        return null;
    }

    /**
     * Splits values LinkedList creating two new Pages linking them to the
     * father.
     *
     * @return the medium value of the values LinkedList.
     */
    public Book split(Page newFather) {
        Page minorPage = new Page(newFather);
        Page mayorPage = new Page(newFather);
        int count = 0;
        //Adding value to the first Page
        Node<Book> auxNode = this.values.First;
        while (count != 2) {
            minorPage.values.AddLast(auxNode.getObject(), auxNode.getRepresentation());
            auxNode = auxNode.getNext();
            count++;
        }
        //Getting medium value
        Book mediumValue = auxNode.getObject();
        auxNode = auxNode.getNext();
        count++;
        //Adding value to the second page
        while (count != 5) {
            mayorPage.values.AddLast(auxNode.getObject(), auxNode.getRepresentation());
            auxNode = auxNode.getNext();
            count++;
        }
        //Checking number of children
        if (this.children.Size > BTree.ORDER) {
            //It's greater than the ORDER
            count = 0;
            Node<Page> auxPageNode = this.children.First;
            while (count != 3) {
                auxPageNode.getObject().height++;
                auxPageNode.getObject().father = minorPage;
                minorPage.children.AddLast(auxPageNode.getObject(), auxPageNode.getRepresentation());
                auxPageNode = auxPageNode.getNext();
                count++;
            }
            while (count != 6) {
                auxPageNode.getObject().height++;
                auxPageNode.getObject().father = mayorPage;
                mayorPage.children.AddLast(auxPageNode.getObject(), auxPageNode.getRepresentation());
                auxPageNode = auxPageNode.getNext();
                count++;
            }
            this.children = new LinkedList<>();
        }
        //Removing the 'extra child' (the one who originally contained the added values)
        count = 0;
        Node<Page> i = newFather.children.First;
        while (i != null) {
            if (i.getObject() == this) {
                newFather.children.deappendObjectAt(count);
                break;
            } else {
                i = i.getNext();
                count++;
            }
        }
        //Adding the splitted pages
        newFather.children.AddLast(minorPage, "");
        newFather.children.AddLast(mayorPage, "");
        newFather.sortPages();
        return mediumValue;
    }

    private void sortPages() {
        Node<Page> i = children.First;
        while (i.getNext() != null) {
            Node<Page> j = i.getNext();
            while (j != null) {
                if (i.getObject().values.Last.getObject().getIsbn() > j.getObject().values.First.getObject().getIsbn()) {
                    Page temp = i.getObject();
                    i.setObject(j.getObject());
                    j.setObject(temp);
                }
                j = j.getNext();
            }
            i = i.getNext();
        }
    }

    private void sort() {
        //values bubble sort
        Node<Book> i = values.First;
        while (i.getNext() != null) {
            Node<Book> j = i.getNext();
            while (j != null) {
                if (i.getObject().getIsbn() > j.getObject().getIsbn()) {
                    Book temp = i.getObject();
                    String tempRepresentation = i.getRepresentation();
                    i.setObject(j.getObject());
                    i.setRepresentation(j.getRepresentation());
                    j.setObject(temp);
                    j.setRepresentation(tempRepresentation);
                }
                j = j.getNext();
            }
            i = i.getNext();
        }
    }

    public int itsDot(int nodeCount, StringBuilder dot) {
        int nodeValue = nodeCount;
        dot.append("\tn").append(nodeValue).append(" [label=\"");
        Node<Book> i = this.values.First;
        //appending values
        while (i != null) {
            dot.append(i.getRepresentation());
            i = i.getNext();
            if (i != null) {
                dot.append("|");
            }
        }
        //end of appending values
        dot.append("\"];\n");
        Node<Page> child = this.children.First;
        //father to its children
        while (child != null) {
            dot.append("\tn").append(nodeValue).append("->n").append(++nodeCount).append(";\n");
            nodeCount = child.getObject().itsDot(nodeCount, dot);
            child = child.getNext();
        }
        return nodeCount;
    }
}
