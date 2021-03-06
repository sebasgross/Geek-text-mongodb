package group9.geektextadminfeatures.Controllers;

import group9.geektextadminfeatures.Entities.Wishlist;
import group9.geektextadminfeatures.ServiceLayers.AdminServices;
import group9.geektextadminfeatures.Repositories.BookRepo;
import group9.geektextadminfeatures.Entities.Author;
import group9.geektextadminfeatures.Entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Geek_Text")
public class AdminController {

    private final AdminServices adminS;

    @Autowired
    private BookRepo bookRepo;

    public AdminController(AdminServices adminS) {
        this.adminS = adminS;
    }

    //Book admin http request

    @PostMapping("/bookcatalog/add_books")
    public void abbBookToDB(@RequestBody final List<Book> bookList) {


        List<Book> idmaker;

      for (int i = 0; bookList.size() > i; i++){

          idmaker = showAllBooks();
          int size;
          int id;

          if(showAllBooks().isEmpty()){
              id = 1;
          }
          else {
               size = idmaker.size()-1;
               id = idmaker.get(size).getBookID()+1;
          }


          bookList.get(i).setBookID(id);

          adminS.addBooks(bookList.get(i));
      }

    }

    @GetMapping("/bookcatalog/show_all")
    public List<Book> showAllBooks(){
        return adminS.showAllBooks();
    }

    @GetMapping("/bookcatalog/isbn_search/{isbn}")
    public Book lookUpByIsbn(@PathVariable String isbn){
        return adminS.searchBookByIsbn(isbn);
    }

    @GetMapping("/bookcatalog/author_search/{authorName}")
    public List<Book> showAllAuthorBooks(@PathVariable String authorName){
        return adminS.searchBookByAuthor(authorName);
    }

    //Author http request

    @PostMapping("/authorcatalog/add_author")
    public void addAuthorToDB(@RequestBody List<Author> authorList){

        List<Author> idmaker;

        for (int i = 0; authorList.size() > i; i++){

            idmaker = showAllAuthors();
            int size;
            int id;

            if(showAllAuthors().isEmpty()){
                id = 1;
            }
            else {
                size = idmaker.size()-1;
                id = idmaker.get(size).getAutorID()+1;
            }


            authorList.get(i).setAutorID(id);

            adminS.addAuthor(authorList.get(i));
        }
    }

    @GetMapping("/authorcatalog/show_all")
    public List<Author> showAllAuthors(){
        return adminS.showAllAuthors();
    }

    //Book Browsing and Sorting http endpoints


    @GetMapping("/bookcatalog/BestSellers")
    public List<Book> showTopTenBooks(){
        return adminS.topTenBestSellers();
    }

    @GetMapping("/bookcatalog/genre_search/{genre}")
    public List<Book> showBooksByGenre(@PathVariable String genre){
        return adminS.booksByGenre(genre);
    }

    @GetMapping("/bookcatalog/show_x_ammount/{xAmmount}")
    public List<Book> showBooksByXAmmount(@PathVariable Integer xAmmount){
        return adminS.searchBooksByXAmount(xAmmount);
    }

    /*
    public List<Book> showBooksByRating(String stars){

    }
     */

    //Wish List Management

    @PostMapping("user//create_wishlist/{wishlistname}")
    public void addNewWishList(@RequestBody Wishlist wishlist, @PathVariable String wishlistname){
        wishlist.setName(wishlistname);

        adminS.addWishlist(wishlist);
    }

    @GetMapping("/user/{username}/show_all_wishlist}")
    public List<Wishlist> showUserWishList(@PathVariable String username){

        return adminS.showAllUserWishList(username);
    }


    //updates the given user wishlist with their favorite books
    @PostMapping("/user/{username}/wishlist/{wishlistname}/addBookToWishlist/{title}")
    public void addBookToWishList(@PathVariable String username , @PathVariable String wishlistname,
                                  @PathVariable String title){

        List<Wishlist> userWishList = adminS.showAllUserWishList(username);

        adminS.getWishlist(wishlistname, userWishList, title);
    }


}
