package com.project.SchoolBook.views.controllers;

import com.project.SchoolBook.dto.ParentDTO;
import com.project.SchoolBook.services.ParentService;
import com.project.SchoolBook.views.models.ParentModel;
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

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/parent", method = RequestMethod.POST)
public class ParentController {
    private final ParentService parentService;
    private final ModelMapper modelMapper;

    private ParentModel convertToParentModel(ParentDTO parentDTO) {
        return modelMapper.map(parentDTO, ParentModel.class);
    }

    @GetMapping("/students")
    ResponseEntity<Object> getStudents(Model model) {
        final List<ParentModel> parents = parentService.getParents()
                .stream()
                .map(this::convertToParentModel)
                .collect(Collectors.toList());
        model.addAttribute("parents", parents);
        return new ResponseEntity<Object>(parents, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createParent(@Valid @ModelAttribute("parent") ParentModel parentModel,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        parentService.create(modelMapper.map(parentModel, ParentDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateParent(
            @PathVariable long id,
            @Valid @ModelAttribute("parent") ParentModel parentModel,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        parentService.updateParent(id, modelMapper.map(parentModel, ParentDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteParent(@PathVariable int id) {
        parentService.deleteParent(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
