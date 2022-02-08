package com.library.library.application.repository;

import com.library.library.application.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findByCategoryname (String categoryname);



}
