package com.project.SchoolBook.services.implementations;

import com.project.SchoolBook.data.entity.Student;
import com.project.SchoolBook.data.repository.StudentRepository;
import com.project.SchoolBook.dto.StudentDTO;
import com.project.SchoolBook.exceptions.StudentNotFoundException;
import com.project.SchoolBook.services.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    private StudentDTO convertToStudentDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }


    @Override
    public List <StudentDTO> getStudents() {
        return studentRepository.findAll().stream().map(this::convertToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudent(@Min(1) long id) {
        return modelMapper.map(studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Invalid student Id: " + id)), StudentDTO.class);
    }


    @Override
    public Student create(@Valid StudentDTO studentDTO) {
        return studentRepository.save(modelMapper.map(studentDTO, Student.class));
    }

    @Override
    public Student updateStudent(long id, StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
}
