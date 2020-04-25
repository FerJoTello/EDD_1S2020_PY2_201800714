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
public class Book {
    private int Isbn, Year, Id_User;
    private String Title, Autor, Editorial, Edition, Category, Language;

    public Book(int Isbn, int Year, int Id_User, String Title, String Autor, String Editorial, String Edition, String Category, String Language) {
        this.Isbn = Isbn;
        this.Year = Year;
        this.Id_User = Id_User;
        this.Title = Title;
        this.Autor = Autor;
        this.Editorial = Editorial;
        this.Edition = Edition;
        this.Category = Category;
        this.Language = Language;
    }

    public int getIsbn() {
        return Isbn;
    }

    public int getYear() {
        return Year;
    }

    public int getId_User() {
        return Id_User;
    }

    public String getTitle() {
        return Title;
    }

    public String getAutor() {
        return Autor;
    }

    public String getEditorial() {
        return Editorial;
    }

    public String getEdition() {
        return Edition;
    }

    public String getCategory() {
        return Category;
    }

    public String getLanguage() {
        return Language;
    }
    
}
