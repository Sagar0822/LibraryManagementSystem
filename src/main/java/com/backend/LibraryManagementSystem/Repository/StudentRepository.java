package com.backend.LibraryManagementSystem.Repository;

import com.backend.LibraryManagementSystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
