package com.backend.LibraryManagementSystem.Service;

import com.backend.LibraryManagementSystem.Entity.Author;
import com.backend.LibraryManagementSystem.Entity.Book;
import com.backend.LibraryManagementSystem.Repository.AuthorRepository;
import com.backend.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(Book book) throws Exception{

        //insert book detail & author id
        //book object -> author -> id
        //.get() if Id not find handle by try catch
        Author author;
        try{
            author = authorRepository.findById(book.getAuthor().getId()).get();
        } catch (Exception e) {
            return "Book not added";
        }

        List<Book> bookWritten = author.getBooks();  //already book present
        bookWritten.add(book); //new book added

        authorRepository.save(author);  //author save b'coz book added in list
        return "book added";
    }
}
