package com.project.SchoolBook.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.SchoolBook.data.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
