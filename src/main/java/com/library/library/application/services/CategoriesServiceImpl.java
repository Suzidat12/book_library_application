package com.library.library.application.services;

import com.library.library.application.dto.CategoryRequest;
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
public class CategoriesServiceImpl implements CategoriesServices {
    @Autowired
    CategoryRepo categoryRepoistory;
    @Autowired
    BookRepo bookRepo;

    @Override
    // Check if the category name already exist.
    // Check if the book has been created, since the book created are to be added to the category.
    // Get the id of book list in other to map it to the category to be able to add books to category
    public ResponseEntity addCategory(CategoryRequest payload) {
        List<Category> categoryList = categoryRepoistory.findByCategoryname(payload.getCategoryname());
       Optional<Book> bookList = bookRepo.findById(payload.getBookid().getId());
       if(bookList.isEmpty()){
           return ResponseEntity.ok("Book with specified ID not found");
       }
        if (!categoryList.isEmpty()) {
            return ResponseEntity.ok("Category name already exist");
        } else {
            Book book = bookList.get();
            Category category = new Category();
            category.setCategoryname(payload.getCategoryname());
            category.setBookid(book);
            category.setDatecreated(new Date());
            categoryRepoistory.save(category);
            return ResponseEntity.ok("Category successfully created");
        }
    }

    @Override
    // Get the Id of the existing category before updating it.
    public CategoryRequest updateCategory(Long id, CategoryRequest payload) {
        Category category = categoryRepoistory.findById(id).get();
        category.setCategoryname(payload.getCategoryname());
        category.setDatecreated(new Date());
        categoryRepoistory.save(category);
        return payload;
    }
    @Override
    //Get the list of category by using find all method among the JPA repository method
    public ResponseEntity listCategory() {
        List<Category> categoryList = categoryRepoistory.findAll();

        return ResponseEntity.ok(categoryList);
    }

    @Override
    //Get the  specified Id of the category before deleting it.
    public boolean  deleteCategory(Long id) {
        Category category = categoryRepoistory.findById(id).get();
        categoryRepoistory.delete(category);
        return true;
    }




}
