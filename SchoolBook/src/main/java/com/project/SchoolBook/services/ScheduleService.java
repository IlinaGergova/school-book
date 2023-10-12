package com.project.SchoolBook.services;

import com.project.SchoolBook.data.entity.Schedule;
import com.project.SchoolBook.dto.ScheduleDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface ScheduleService {
    List <ScheduleDTO> getSchedules();

    ScheduleDTO getSchedule(@Min(1) long id); // ??

    Schedule create(@Valid ScheduleDTO scheduleDTO);

    Schedule updateSchedule(long id, ScheduleDTO scheduleDTO);

    void deleteSchedule(long id);
}
