package com.backend.LibraryManagementSystem.Service;

import com.backend.LibraryManagementSystem.DTO.AuthorRequestDto;
import com.backend.LibraryManagementSystem.DTO.AuthorResponseDto;
import com.backend.LibraryManagementSystem.Entity.Author;
import com.backend.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

//    public void addAuthor(Author author){
//
//        authorRepository.save(author);
//    }

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto){

        Author author = new Author();
        author.setAge(authorRequestDto.getAge());
        author.setName(authorRequestDto.getName());
        author.setEmail(authorRequestDto.getEmail());
        author.setMobNo(authorRequestDto.getMobNo());

        authorRepository.save(author);

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());

        return authorResponseDto;
    }

    public List<Author> getAuthors(){
       return authorRepository.findAll();
    }
}

