package com.project.SchoolBook.views.controllers;

import com.project.SchoolBook.dto.TeacherDTO;
import com.project.SchoolBook.services.TeacherService;
import com.project.SchoolBook.views.models.TeacherModel;
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
@RequestMapping(value = "/teacher", method = RequestMethod.POST)
public class TeacherController {
    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    private TeacherModel convertToTeacherModel(TeacherDTO teacherDTO) {
        return modelMapper.map(teacherDTO, TeacherModel.class);
    }

    @GetMapping("/teachers")
    ResponseEntity<Object> getTeachers(Model model) {
        final List<TeacherModel> teachers = teacherService.getTeachers()
                .stream()
                .map(this::convertToTeacherModel)
                .collect(Collectors.toList());
        model.addAttribute("teachers", teachers);
        return new ResponseEntity<Object>(teachers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTeacher(@Valid @ModelAttribute("teacher") TeacherModel teacherModel,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        teacherService.create(modelMapper.map(teacherModel, TeacherDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateTeacher(
            @PathVariable long id,
            @Valid @ModelAttribute("teacher") TeacherModel teacherModel,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        teacherService.updateTeacher(id, modelMapper.map(teacherModel, TeacherDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
