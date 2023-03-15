package com.backend.LibraryManagementSystem.Controller;

import com.backend.LibraryManagementSystem.Entity.Author;
import com.backend.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return "Author is added";
    }
    @GetMapping("/get_authors")
    public List<Author> getAuthors(){

        return authorService.getAuthors();
    }
}

