package com.library.library.application.services;

import com.library.library.application.dto.BookRequest;
import com.library.library.application.dto.CategoryRequest;
import com.library.library.application.entity.Book;
import com.library.library.application.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoriesServices {
    ResponseEntity addCategory(CategoryRequest payload);
    CategoryRequest updateCategory(Long id, CategoryRequest payload);
    ResponseEntity listCategory();
    boolean deleteCategory(Long id);

}
