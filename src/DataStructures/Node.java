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
public class Node<T> {

    private T object;
    private String representation;
    private Node<T> next;

    public Node() {
        this.object = null;
        this.representation = "";
        this.next = null;
    }

    public Node(T object, String representation) {
        this.object = object;
        this.representation = representation;
        this.next = null;
    }

    public T getObject() {
        return object;
    }

    public String getRepresentation() {
        return representation;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> Next) {
        this.next = Next;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

}
