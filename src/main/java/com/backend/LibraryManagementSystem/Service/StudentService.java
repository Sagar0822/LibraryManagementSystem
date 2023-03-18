package com.backend.LibraryManagementSystem.Service;

import com.backend.LibraryManagementSystem.DTO.StudentRequestDto;
import com.backend.LibraryManagementSystem.DTO.StudentResponseDto;
import com.backend.LibraryManagementSystem.DTO.StudentUpdateEmailRequestDto;
import com.backend.LibraryManagementSystem.Entity.LibraryCard;
import com.backend.LibraryManagementSystem.Entity.Student;
import com.backend.LibraryManagementSystem.Enum.CardStatus;
import com.backend.LibraryManagementSystem.Repository.StudentRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentRequestDto studentRequestDto){

        //create a student object
        Student student = new Student();
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setDepartment(studentRequestDto.getDepartment());

        //create a card object
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setStudent(student);


        //set card in student
        student.setCard(card);

        //will save both student and card
        studentRepository.save(student);
    }
    public String findByEmail(String email){
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }


    //DTO
    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){
        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        //update step
        Student updatedStudent = studentRepository.save(student);

        //convert updated student to response Dto

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());

        return studentResponseDto;
    }
}




