package com.project.SchoolBook.services.implementations;

import com.project.SchoolBook.data.entity.Teacher;
import com.project.SchoolBook.data.repository.TeacherRepository;
import com.project.SchoolBook.dto.TeacherDTO;
import com.project.SchoolBook.exceptions.TeacherNotFoundException;
import com.project.SchoolBook.services.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    private TeacherDTO convertToTeacherDTO(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    public List <TeacherDTO> getTeachers() {
        return teacherRepository.findAll().stream().map(this::convertToTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO getTeacher(@Min(1) long id) {
        return modelMapper.map(teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Invalid teacher Id: " + id)), TeacherDTO.class);
    }

    @Override
    public Teacher create(@Valid TeacherDTO teacherDTO) {
        return teacherRepository.save(modelMapper.map(teacherDTO, Teacher.class));
    }

    @Override
    public Teacher updateTeacher(long id, TeacherDTO teacherDTO) {
        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
        teacher.setId(id);
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(long id) {
        teacherRepository.deleteById(id);
    }
}
