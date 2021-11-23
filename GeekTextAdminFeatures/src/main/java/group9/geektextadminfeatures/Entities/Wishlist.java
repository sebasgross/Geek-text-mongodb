package group9.geektextadminfeatures.Entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishlistcatalog")
public class Wishlist {

    @Id
    private Integer id;
    private User user;
    private String name;
    private Integer number;
    private List<Book> bookList;

    public Wishlist(Integer id, User user, String name, Integer number, List<Book> bookList) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.number = number;
        this.bookList = bookList;
    }



    public Integer getId() {
        return id;
    }

    public Wishlist setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public List<Book> getBookList() {
        return bookList;
    }
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}