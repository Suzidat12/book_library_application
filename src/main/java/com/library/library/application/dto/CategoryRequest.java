package com.library.library.application.dto;

import com.library.library.application.entity.Book;
import lombok.Data;

@Data
public class CategoryRequest {
    private String categoryname;
    private Book bookid;
}
