package com.backend.LibraryManagementSystem.Controller;

import com.backend.LibraryManagementSystem.DTO.StudentResponseDto;
import com.backend.LibraryManagementSystem.DTO.StudentUpdateEmailRequestDto;
import com.backend.LibraryManagementSystem.Entity.Student;
import com.backend.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return "Student has been added";
    }
    @GetMapping("/find_by_email")
    public String findByEmail(@RequestParam("email") String email) {
        return studentService.findByEmail(email);
    }

//    @GetMapping("/get_student")
//    public Student findStudent(@RequestParam("id") int id) {
//        return studentService.findStudent(id);
//    }

    @PutMapping("/update_email")
    public StudentResponseDto updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }

}




