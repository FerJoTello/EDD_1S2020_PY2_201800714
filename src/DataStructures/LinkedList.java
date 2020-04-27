/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Elements.User;

/**
 *
 * @author Fernando
 */
class Node {

    private User User;
    private String Representation;
    private Node Next;

    public Node() {
        this.User = null;
        this.Representation = "";
        this.Next = null;
    }

    public Node(User User, String Representation) {
        this.User = User;
        this.Representation = Representation;
        this.Next = null;
    }

    public User getObject() {
        return User;
    }

    public String getRepresentation() {
        return Representation;
    }

    public Node getNext() {
        return Next;
    }

    public void setNext(Node Next) {
        this.Next = Next;
    }

}

/**
 * LinkedList Dynamic structure used on HashTable. Holds in every node an User
 * type object.
 */
public class LinkedList {

    public Node First, Last;
    private int Size;

    public LinkedList() {
        First = null;
        Last = null;
        Size = 0;
    }

    public void AddFirst(User user, String representation) {
        Node newNode = new Node(user, representation);
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

    public void AddLast(User user, String representation) {
        if (IsEmpty()) {
            //Since is the same algorithm...
            AddFirst(user, representation);
        } else {
            Node newNode = new Node(user, representation);
            Last.setNext(newNode);
            Last = newNode;
            Size++;
        }
    }

    public int getSize() {
        return Size;
    }

    private boolean IsEmpty() {
        return Size == 0;
    }

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

    /**
     * Deletes an User type object of the linked list. Returns a boolean
     * indicating if the user with the provided id could be deleted from the
     * list.
     *
     * @param idRequested id from the user to delete.
     * @return boolean indicating if the user was deleted.
     */
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
}
