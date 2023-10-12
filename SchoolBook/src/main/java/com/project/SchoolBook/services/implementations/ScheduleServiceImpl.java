package com.project.SchoolBook.services.implementations;

import com.project.SchoolBook.data.entity.Schedule;
import com.project.SchoolBook.data.repository.ScheduleRepository;
import com.project.SchoolBook.dto.ScheduleDTO;
import com.project.SchoolBook.exceptions.ScheduleNotFoundException;
import com.project.SchoolBook.services.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    private ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDTO.class);
    }

    @Override
    public List <ScheduleDTO> getSchedules() {
        return scheduleRepository.findAll().stream().map(this::convertToScheduleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDTO getSchedule(@Min(1) long id) {
        return modelMapper.map(scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Invalid schedule Id: " + id)), ScheduleDTO.class);
    }

    @Override
    public Schedule create(@Valid ScheduleDTO scheduleDTO) {
        return scheduleRepository.save(modelMapper.map(scheduleDTO, Schedule.class));
    }

    @Override
    public Schedule updateSchedule(long id, ScheduleDTO scheduleDTO) {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
        schedule.setId(id);
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(long id) {
        scheduleRepository.deleteById(id);
    }
}
