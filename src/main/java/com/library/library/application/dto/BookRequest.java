package com.library.library.application.dto;

import com.library.library.application.entity.Category;
import lombok.Data;

@Data
public class BookRequest {

    private String isbn;
    private String title;
    private String publisher;
    private String language;
    private Category categoryid;
    private boolean favourites;

}
