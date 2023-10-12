package com.project.SchoolBook.services.implementations;

import com.project.SchoolBook.data.entity.Parent;
import com.project.SchoolBook.data.entity.User;
import com.project.SchoolBook.data.repository.ParentRepository;
import com.project.SchoolBook.data.repository.UserRepository;
import com.project.SchoolBook.dto.ParentDTO;
import com.project.SchoolBook.dto.UserDTO;
import com.project.SchoolBook.exceptions.ParentNotFoundException;
import com.project.SchoolBook.exceptions.UserNotFoundException;
import com.project.SchoolBook.services.ParentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;

    private ParentDTO convertToParentDTO(Parent parent) {
        return modelMapper.map(parent, ParentDTO.class);
    }

    @Override
    public List <ParentDTO> getParents() {
        return parentRepository.findAll().stream().map(this::convertToParentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParentDTO getParent(@Min(1) long id) {
        return modelMapper.map(parentRepository.findById(id)
                .orElseThrow(() -> new ParentNotFoundException("Invalid parent Id: " + id)), ParentDTO.class);
    }

    @Override
    public Parent create(@Valid ParentDTO parentDTO) {
        return parentRepository.save(modelMapper.map(parentDTO, Parent.class));
    }

    @Override
    public Parent updateParent(long id, ParentDTO parentDTO) {
        Parent parent = modelMapper.map(parentDTO, Parent.class);
        parent.setId(id);
        return parentRepository.save(parent);
    }

    @Override
    public void deleteParent(long id) {
        parentRepository.deleteById(id);
    }
}
