package com.project.SchoolBook.services;

import com.project.SchoolBook.data.entity.School;
import com.project.SchoolBook.dto.SchoolDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface SchoolService {
    List <SchoolDTO> getSchools();

    SchoolDTO getSchool(@Min(1) long id);

    School create(@Valid SchoolDTO schoolDTO);

    School updateSchool(long id, SchoolDTO schoolDTO);

    void deleteSchool(long id);
}
