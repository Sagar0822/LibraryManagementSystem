package com.backend.LibraryManagementSystem.Service;

import com.backend.LibraryManagementSystem.Entity.LibraryCard;
import com.backend.LibraryManagementSystem.Entity.Student;
import com.backend.LibraryManagementSystem.Enum.CardStatus;
import com.backend.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student){

        // set the value of card
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setValidTill("03/2025");
        card.setStudent(student);

        // set the card attroubte in student
        student.setCard(card);

        studentRepository.save(student);
    }

}




