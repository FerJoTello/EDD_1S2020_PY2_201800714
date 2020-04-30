/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

/**
 *
 * @author Fernando
 */
public class User {

    private int Id;
    private String Name, LastName, Career, Password;
    private BooksList booksList;

    public User(int Id, String Name, String LastName, String Career, String Password) {
        this.Id = Id;
        this.Name = Name;
        this.LastName = LastName;
        this.Career = Career;
        this.Password = Password;
        this.booksList = null;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getCareer() {
        return Career;
    }

    public String getPassword() {
        return Password;
    }

    public BooksList getBooksList() {
        return this.booksList;
    }

}

class BooksList {

    public Node first, last;
    private int size;

    public BooksList() {
        first = null;
        last = null;
        size = 0;
    }

    public void AddFirst(Book book) {
        Node newNode = new Node(book);
        if (IsEmpty()) {
            //Assigns newNode as First and Last.
            first = newNode;
            last = newNode;
            size++;
        } else {
            newNode.setNext(first);
            first = newNode;
            size++;
        }
    }

    public void AddLast(Book book) {
        if (IsEmpty()) {
            //Since is the same algorithm...
            AddFirst(book);
        } else {
            Node newNode = new Node(book);
            last.setNext(newNode);
            last = newNode;
            size++;
        }
    }

    public int getSize() {
        return size;
    }

    private boolean IsEmpty() {
        return size == 0;
    }

}

class Node {

    private Book book;
    private Node next;

    public Node() {
        this.book = null;
        this.next = null;
    }

    public Node(Book book) {
        this.book = book;
        this.next = null;
    }

    public Book getBook() {
        return book;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
