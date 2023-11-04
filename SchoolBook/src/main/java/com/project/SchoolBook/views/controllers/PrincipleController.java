package com.project.SchoolBook.views.controllers;

import com.project.SchoolBook.dto.PrincipleDTO;
import com.project.SchoolBook.services.PrincipleService;
import com.project.SchoolBook.views.models.PrincipleModel;
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
@RequestMapping(value = "/principle", method = RequestMethod.POST)
public class PrincipleController {
    private final PrincipleService principleService;
    private final ModelMapper modelMapper;

    private PrincipleModel convertToPrincipleModel(PrincipleDTO principleDTO) {
        return modelMapper.map(principleDTO, PrincipleModel.class);
    }

    @GetMapping("/principles")
    ResponseEntity<Object> getPrinciples(Model model) {
        final List<PrincipleModel> principles = principleService.getPrinciples()
                .stream()
                .map(this::convertToPrincipleModel)
                .collect(Collectors.toList());
        model.addAttribute("principles", principles);
        return new ResponseEntity<Object>(principles, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPrinciple(@Valid @ModelAttribute("principle") PrincipleModel principleModel,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        principleService.create(modelMapper.map(principleModel, PrincipleDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updatePrinciple(
            @PathVariable long id,
            @Valid @ModelAttribute("principle") PrincipleModel principleModel,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        principleService.updatePrinciple(id, modelMapper.map(principleModel, PrincipleDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deletePrinciple(@PathVariable int id) {
        principleService.deletePrinciple(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
