/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import DataStructures.LinkedList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Fernando
 */
public class User {

    private int Id;
    private String Name, LastName, Career, Password;
    private LinkedList<Book> booksList;

    public User(int Id, String Name, String LastName, String Career, String password) {
        this.Id = Id;
        this.Name = Name;
        this.LastName = LastName;
        this.Career = Career;
        this.booksList = new LinkedList();
        this.Password = getHashMD5(password);
        System.out.println("Password de " + this.Name + ": " + this.Password);
    }

    public static String getHashMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digestedBytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digestedBytes) {
                sb.append(Integer.toHexString(b & 0xff).toString());
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
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

    public LinkedList<Book> getBooksList() {
        return this.booksList;
    }

}
