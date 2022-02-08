package com.library.library.application.controller;

import com.library.library.application.dto.BookRequest;
import com.library.library.application.dto.CategoryRequest;
import com.library.library.application.entity.Book;
import com.library.library.application.entity.Category;
import com.library.library.application.repository.BookRepo;
import com.library.library.application.repository.CategoryRepo;
import com.library.library.application.services.CategoriesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/library/")
public class CategoryController {
    @Autowired
    CategoriesServices categoriesServices;


    @PostMapping("category/add")
    public ResponseEntity addCategory(@RequestBody CategoryRequest payload){
       return categoriesServices.addCategory(payload);
    }

    @PutMapping("category/update/{id}")
    public ResponseEntity <CategoryRequest> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest payload) {
        payload = categoriesServices.updateCategory(id, payload);
        return ResponseEntity.ok(payload);

    }
    @GetMapping("category/fetch")
    public ResponseEntity listCategory(){

        return categoriesServices.listCategory();
    }


    @DeleteMapping("category/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteCategory(@PathVariable Long id){
        Boolean deleted = false;
        deleted = categoriesServices.deleteCategory(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Category deleted successfully",deleted);
        return ResponseEntity.ok(response);

    }

}
