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

    public User(int Id, String Name, String LastName, String Career, String Password) {
        this.Id = Id;
        this.Name = Name;
        this.LastName = LastName;
        this.Career = Career;
        this.Password = Password;
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
    
}
