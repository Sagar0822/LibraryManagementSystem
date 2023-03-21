package com.backend.LibraryManagementSystem.Controller;


import com.backend.LibraryManagementSystem.DTO.IssueBookRequestDto;
import com.backend.LibraryManagementSystem.DTO.IssueBookResponseDto;
import com.backend.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
//Return type is issueBookResponseDTO so write responseEntity
        IssueBookResponseDto issueBookResponseDto;
        try{
            issueBookResponseDto = transactionService.issueBook(issueBookRequestDto);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(issueBookResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllTrnx")
    public String getAllTrnx(@RequestParam("cardId") int cardId){
        return transactionService.getAllTrnx(cardId);
    }
}
