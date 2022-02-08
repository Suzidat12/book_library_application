package com.library.library.application.repository;

import com.library.library.application.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Long> {
    List<Book> findByIsbn (String isbn);
    List<Book> findByFavouritesNotNull();

}
