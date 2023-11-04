package com.project.SchoolBook.views.controllers;

import com.project.SchoolBook.dto.ScheduleDTO;
import com.project.SchoolBook.services.ScheduleService;
import com.project.SchoolBook.views.models.ScheduleModel;
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
@RequestMapping(value = "/schedule", method = RequestMethod.POST)
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

    private ScheduleModel convertToScheduleModel(ScheduleDTO scheduleDTO) {
        return modelMapper.map(scheduleDTO, ScheduleModel.class);
    }

    @GetMapping("/schedules")
    ResponseEntity<Object> getSchedules(Model model) {
        final List<ScheduleModel> schedules = scheduleService.getSchedules()
                .stream()
                .map(this::convertToScheduleModel)
                .collect(Collectors.toList());
        model.addAttribute("schedule", schedules);
        return new ResponseEntity<Object>(schedules, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createSchedule(@Valid @ModelAttribute("schedule") ScheduleModel scheduleModel,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        scheduleService.create(modelMapper.map(scheduleModel, ScheduleDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateSchedule(
            @PathVariable long id,
            @Valid @ModelAttribute("schedule") ScheduleModel scheduleModel,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        scheduleService.updateSchedule(id, modelMapper.map(scheduleModel, ScheduleDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
