package com.library.library.application.services;

import com.library.library.application.dto.BookRequest;
import com.library.library.application.entity.Book;
import com.library.library.application.entity.Category;
import com.library.library.application.repository.BookRepo;
import com.library.library.application.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepo bookRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    // Check if the Isbn already exist.
    // Check if the category has been created, since the category created are to be added to the book.
    // Get the id of category list in other to map it to the book to be able to add category to book.
    public ResponseEntity addBook(BookRequest payload) {
        List<Book> booklist = bookRepo.findByIsbn(payload.getIsbn());
        Optional<Category> categoryList = categoryRepo.findById(payload.getCategoryid().getId());
        if(categoryList.isEmpty()){
            return ResponseEntity.ok("Category with specified ID not found");
        }
        if (!booklist.isEmpty()) {
            return ResponseEntity.ok("ISBN already exist, kindly add new book with different isbn");
        } else {
            Category category =categoryList.get();
            Book book = new Book();
            book.setIsbn(payload.getIsbn());
            book.setLanguage(payload.getLanguage());
            book.setPublisher(payload.getPublisher());
            book.setTitle(payload.getTitle());
            book.setFavourites(payload.isFavourites());
            book.setCategoryId(category);
            book.setDatecreated(new Date());
            bookRepo.save(book);

            return ResponseEntity.ok("Book successfully saved");
        }
    }

    @Override
    // Get the Id of the existing book before updating it.
    public BookRequest updateBook(Long id, BookRequest payload) {

        Book book = bookRepo.findById(id).get();
        book.setTitle(payload.getTitle());
        book.setPublisher(payload.getPublisher());
        book.setLanguage(payload.getLanguage());
        book.setIsbn(payload.getIsbn());
        book.setDatecreated(new Date());
        bookRepo.save(book);
        return payload;
    }

    @Override
    //Get the list of book by using find all method among the JPA repository method
    public ResponseEntity getAllBooks() {
        List<Book> bookList = bookRepo.findAll();
        return ResponseEntity.ok(bookList);
    }

    @Override
    //Get the  specified Id of the book before deleting it.
    public boolean  deleteBook(Long id) {
        Book book = bookRepo.findById(id).get();
        bookRepo.delete(book);
        return true;
    }

    @Override
    //Get the list of favourite books
    public ResponseEntity getFavouriteBooks() {
        List<Book> bookList = bookRepo.findByFavouritesNotNull();
        return ResponseEntity.ok(bookList);
    }


}
