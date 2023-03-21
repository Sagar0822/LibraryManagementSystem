package com.backend.LibraryManagementSystem.Service;

import com.backend.LibraryManagementSystem.DTO.IssueBookRequestDto;
import com.backend.LibraryManagementSystem.DTO.IssueBookResponseDto;
import com.backend.LibraryManagementSystem.Entity.Book;
import com.backend.LibraryManagementSystem.Entity.LibraryCard;
import com.backend.LibraryManagementSystem.Entity.Transaction;
import com.backend.LibraryManagementSystem.Enum.CardStatus;
import com.backend.LibraryManagementSystem.Enum.TransactionStatus;
import com.backend.LibraryManagementSystem.Repository.BookRepository;
import com.backend.LibraryManagementSystem.Repository.CardRepository;
import com.backend.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        //Create Transaction object
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));  //Generate auto generated no
        transaction.setIssueOperation(true);  //by default false but book issue or not any reason set true b"coz transaction is done


        //step 1
        LibraryCard card;
        try {
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card Id");  //Db throw this message u can see
            transactionRepository.save(transaction);
            throw new Exception("Invalid card Id");   //User see this message
        }

        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Book Id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book Id");
        }

        //both card and book are valid
        transaction.setBook(book);
        transaction.setCard(card);

        if(card.getStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Your card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated");
    }
        if(book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Sorry! Book is already issued");
            transactionRepository.save(transaction);
            throw new Exception("Sorry! Book is already issued");
        }

        //I can issue the book
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Transaction was succesfull");

        book.setIssued(true);
        book.setCard(card);   //this book is issued in current card
        book.getTransaction().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);

        cardRepository.save(card); //Book & transaction also save book parrent

        //prepare Response Dto
         IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();

         issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());
         issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
         issueBookResponseDto.setBookName(book.getTitle());

         return issueBookResponseDto;
    }
}
