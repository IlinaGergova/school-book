package com.project.SchoolBook.views.controllers;

import com.project.SchoolBook.dto.StudentDTO;
import com.project.SchoolBook.services.StudentService;
import com.project.SchoolBook.views.models.StudentModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/student", method = RequestMethod.POST)
public class StudentController {
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    private StudentModel convertToStudentModel(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, StudentModel.class);
    }

    @GetMapping("/students")
    ResponseEntity<Object> getStudents(Model model) {
        final List<StudentModel> students = studentService.getStudents()
                .stream()
                .map(this::convertToStudentModel)
                .collect(Collectors.toList());
        model.addAttribute("students", students);
        return new ResponseEntity<Object>(students, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createStudent(@Valid @ModelAttribute("student") StudentModel studentModel,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        studentService.create(modelMapper.map(studentModel, StudentDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateStudent(
            @PathVariable long id,
            @Valid @ModelAttribute("student") StudentModel studentModel,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        studentService.updateStudent(id, modelMapper.map(studentModel, StudentDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
