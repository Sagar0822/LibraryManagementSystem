package com.backend.LibraryManagementSystem.DTO;


import com.backend.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookResponseDto {

    private String TransactionId;

    private String bookName;

    private TransactionStatus transactionStatus;
}
