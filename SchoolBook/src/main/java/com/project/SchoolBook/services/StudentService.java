package com.project.SchoolBook.services;

import com.project.SchoolBook.data.entity.Student;
import com.project.SchoolBook.dto.StudentDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface StudentService {
    List <StudentDTO> getStudents();

    StudentDTO getStudent(@Min(1) long id);

    Student create(@Valid StudentDTO studentDTO);

    Student updateStudent(long id, StudentDTO studentDTO);

    void deleteStudent(long id);
}
