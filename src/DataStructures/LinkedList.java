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
/**
 * Dynamic structure that can hold any object type.
 */
public class LinkedList<T> {

    public Node<T> First, Last;
    public int Size;

    public LinkedList() {
        First = null;
        Last = null;
        Size = 0;
    }

    public void AddFirst(T object, String representation) {
        Node<T> newNode = new Node<T>(object, representation);
        if (IsEmpty()) {
            //Assigns newNode as First and Last.
            First = newNode;
            Last = newNode;
            Size++;
        } else {
            newNode.setNext(First);
            First = newNode;
            Size++;
        }
    }

    public void AddLast(T object, String representation) {
        if (IsEmpty()) {
            //Since is the same algorithm...
            AddFirst(object, representation);
        } else {
            Node<T> newNode = new Node<T>(object, representation);
            Last.setNext(newNode);
            Last = newNode;
            Size++;
        }
    }

    public void AddAt(T object, String representation, int index) {
        if (index > 0 && index < Size) {
            //auxNode should be the previous node to the one that is being pushed one position forward
            Node<T> auxNode = GetNodeAt(index - 1);
            Node<T> newNode = new Node<T>(object, representation);
            newNode.setNext(auxNode.getNext());
            auxNode.setNext(newNode);
            Size++;

        } else if (index == 0) {
            AddFirst(object, representation);
        } else if (index == Size) {
            AddLast(object, representation);
        } else {
            //Out of bounds!
            System.out.println("Index \"" + index + "\" out of bounds!");
        }
    }

    public T deappendObjectAt(int index) {
        if (index > 0 && index < Size) {
            int count = 0;
            Node<T> auxNode = First;
            //auxNode must be the previous node to the one which is being deleted.
            while (count != index - 1) {
                auxNode = auxNode.getNext();
                count++;
            }
            T auxObject = auxNode.getNext().getObject();
            auxNode.setNext(auxNode.getNext().getNext());
            if (auxNode.getNext() == null) {
                //last node was deleted
                Last = auxNode;
            }
            Size--;
            return auxObject;
        } else if (index == 0) {
            //First node to delete
            T auxObject = First.getObject();
            First = First.getNext();
            Size--;
            if (Size == 0) {
                First = null;
                Last = null;
            }
            return auxObject;
        } else {
            System.out.println("Index out of bounds! " + index);
            return null;
        }
    }

    public T GetObjectAt(int index) {
        return GetNodeAt(index).getObject();
    }

    private Node<T> GetNodeAt(int index) {
        if (index > 0 && index < Size - 1) {
            int count = 0;
            Node<T> auxNode = First;
            //Advancing in the list to obtain the required auxNode
            while (count != index) {
                auxNode = auxNode.getNext();
                count++;
            }
            return auxNode;
        } else if (index == 0) {
            return First;
        } else if (index == Size - 1) {
            return Last;
        } else {
            //Posible exception. The index is out of bounds.
            System.out.println("Index \"" + index + "\" out of bounds!");
        }
        return null;
    }

    public String generateGraph(String name) {
        String dot = "digraph Report{\n"
                + "\trankdir = LR;\n"
                + "\tGraph[label = \"ListaSimple:"
                + name + "\" fontname=Arial];\n"
                + "\tedge [colorscheme = rdbu11  color=11];\n"
                + "\tnode [colorscheme=rdbu11  color=11 style=filled fillcolor=10 fontname=Arial fontcolor=6 shape=box]\n";
        if (!IsEmpty()) {
            int count = 0;
            Node auxNode = First;
            while (count != Size - 1) {
                dot += "\tn" + count + "[label = \"" + auxNode.getRepresentation() + "\"];\n";
                dot += "\tn" + count + " -> n" + ++count + ";\n";
                auxNode = auxNode.getNext();
            }
            dot += "\tn" + count + "[label=\"" + auxNode.getRepresentation() + "\"];\n";
        } else {
            dot += "\tn0[label=\"null\" shape=none]\n";
        }
        dot += "}";
        Grapher grapher = new Grapher(name);
        return grapher.generateGraph(dot);
    }

    private boolean IsEmpty() {
        return Size == 0;
    }

    /*
    public User getUser(int idRequested) {
        int count = 0;
        Node auxNode = First;
        while (count != Size) {
            if (auxNode.getObject().getId() == idRequested) {
                return auxNode.getObject();
            } else {
                auxNode = auxNode.getNext();
                count++;
            }
        }
        return null;
    }
     */
 /*
    private int findUserPosition(int idRequested) {
        int actualPosition = 0;
        Node auxNode = First;
        while (actualPosition != Size) {
            if (auxNode.getObject().getId() == idRequested) {
                return actualPosition;
            } else {
                auxNode = auxNode.getNext();
                actualPosition++;
            }
        }
        return -1;
    }
     */

 /*
    public boolean deleteUser(int idRequested) {
        int position = findUserPosition(idRequested);
        if (position == 0) {
            //It's the first node
            First = First.getNext();
            Size--;
            return true;
        } else if (position > 0 && position < Size) {
            //It's any other node
            int actualPosition = 0;
            //auxNode must be the previous node to the one which is being deleted
            Node auxNode = First;
            while (actualPosition != position - 1) {
                //Advances on the list until it gets the required node
                auxNode = auxNode.getNext();
                actualPosition++;
            }
            Node deletedNode = auxNode.getNext();
            auxNode.setNext(deletedNode.getNext());
            if (position == Size - 1) {
                Last = auxNode;
            }
            Size--;
            return true;
        }
        return false;
    }
     */
}
