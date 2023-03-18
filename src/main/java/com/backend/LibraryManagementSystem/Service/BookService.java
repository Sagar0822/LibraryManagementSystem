package com.backend.LibraryManagementSystem.Service;

import com.backend.LibraryManagementSystem.DTO.BookRequestDto;
import com.backend.LibraryManagementSystem.DTO.BookResponseDto;
import com.backend.LibraryManagementSystem.Entity.Author;
import com.backend.LibraryManagementSystem.Entity.Book;
import com.backend.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public BookResponseDto addBook(BookRequestDto bookRequestDto) {

        //get the author object
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

         Book book = new Book();
         book.setTitle(bookRequestDto.getTitle());
         book.setGenre(bookRequestDto.getGenre());
         book.setPrice(bookRequestDto.getPrice());
         book.setIssued(false);
         book.setAuthor(author);

         author.getBooks().add(book);
         authorRepository.save(author);  //will save both author and book

        //create a response else
        BookResponseDto bookResponseDto = new BookResponseDto();

        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;
    }
}
