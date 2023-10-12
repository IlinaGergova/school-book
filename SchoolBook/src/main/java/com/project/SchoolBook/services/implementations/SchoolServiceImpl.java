package com.project.SchoolBook.services.implementations;

import com.project.SchoolBook.data.entity.School;
import com.project.SchoolBook.data.repository.SchoolRepository;
import com.project.SchoolBook.dto.SchoolDTO;
import com.project.SchoolBook.exceptions.UserNotFoundException;
import com.project.SchoolBook.services.SchoolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final ModelMapper modelMapper;

    private SchoolDTO convertToSchoolDTO(School school) {
        return modelMapper.map(school, SchoolDTO.class);
    }

    @Override
    public List <SchoolDTO> getSchools() {
        return schoolRepository.findAll().stream().map(this::convertToSchoolDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDTO getSchool(@Min(1) long id) {
        return modelMapper.map(schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Invalid school Id: " + id)), SchoolDTO.class);
    }

    @Override
    public School create(@Valid SchoolDTO schoolDTO) {
        return schoolRepository.save(modelMapper.map(schoolDTO, School.class));
    }

    @Override
    public School updateSchool(long id, SchoolDTO schoolDTO) {
        School school = modelMapper.map(schoolDTO, School.class);
        school.setId(id);
        return schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(long id) {
        schoolRepository.deleteById(id);
    }
}
