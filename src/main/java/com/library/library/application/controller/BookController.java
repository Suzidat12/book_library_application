package com.library.library.application.controller;

import com.library.library.application.dto.BookRequest;
import com.library.library.application.entity.Book;
import com.library.library.application.repository.BookRepo;
import com.library.library.application.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/library/")
public class BookController {
    @Autowired
    BookService bookService;


    @PostMapping("book/add")
    public ResponseEntity addBook(@RequestBody BookRequest payload) {
        return  bookService.addBook(payload);

    }

    @PutMapping("book/update/{id}")
    public ResponseEntity <BookRequest> updateBook(@PathVariable Long id, @RequestBody BookRequest payload) {
       payload = bookService.updateBook(id, payload);
       return ResponseEntity.ok(payload);

    }

    @GetMapping("book/fetch")
    public ResponseEntity getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("book/fetchFavourite")
    public ResponseEntity getFavouriteBooks( ){

        return bookService.getFavouriteBooks();
    }

    @DeleteMapping("book/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteBook(@PathVariable Long id){
        Boolean deleted = false;
        deleted = bookService.deleteBook(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Book deleted successfully",deleted);
        return ResponseEntity.ok(response);

    }




}
