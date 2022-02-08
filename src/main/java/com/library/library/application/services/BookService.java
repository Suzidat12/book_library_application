package com.library.library.application.services;

import com.library.library.application.dto.BookRequest;
import com.library.library.application.entity.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity addBook(BookRequest payload);
    BookRequest updateBook(Long id, BookRequest payload);
    ResponseEntity getAllBooks();
    boolean deleteBook(Long id);

    ResponseEntity getFavouriteBooks();
}
