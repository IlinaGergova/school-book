package com.project.SchoolBook.services;

import com.project.SchoolBook.data.entity.Teacher;
import com.project.SchoolBook.dto.TeacherDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface TeacherService {
    List <TeacherDTO> getTeachers();

    TeacherDTO getTeacher(@Min(1) long id);

    Teacher create(@Valid TeacherDTO teacherDTO);

    Teacher updateTeacher(long id, TeacherDTO teacherDTO);

    void deleteTeacher(long id);
}
