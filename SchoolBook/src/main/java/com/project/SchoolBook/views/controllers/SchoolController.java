package com.project.SchoolBook.views.controllers;

import com.project.SchoolBook.dto.SchoolDTO;
import com.project.SchoolBook.services.SchoolService;
import com.project.SchoolBook.views.models.SchoolModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/school", method = RequestMethod.POST)
public class SchoolController {
    private final SchoolService schoolService;
    private final ModelMapper modelMapper;

    private SchoolModel convertToSchoolModel(SchoolDTO schoolDTO) {
        return modelMapper.map(schoolDTO, SchoolModel.class);
    }

    @GetMapping("/schools")
    ResponseEntity<Object> getSchools(Model model) {
        final List<SchoolModel> schools = schoolService.getSchools()
                .stream()
                .map(this::convertToSchoolModel)
                .collect(Collectors.toList());
        model.addAttribute("schools", schools);
        return new ResponseEntity<Object>(schools, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createSchool(@Valid @ModelAttribute("school") SchoolModel schoolModel,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        schoolService.create(modelMapper.map(schoolModel, SchoolDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateSchool(
            @PathVariable long id,
            @Valid @ModelAttribute("school") SchoolModel schoolModel,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        schoolService.updateSchool(id, modelMapper.map(schoolModel, SchoolDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSchool(@PathVariable int id) {
        schoolService.deleteSchool(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
