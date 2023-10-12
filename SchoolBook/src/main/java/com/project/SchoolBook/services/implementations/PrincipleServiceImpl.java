package com.project.SchoolBook.services.implementations;

import com.project.SchoolBook.data.entity.Principle;
import com.project.SchoolBook.data.repository.PrincipleRepository;
import com.project.SchoolBook.dto.PrincipleDTO;
import com.project.SchoolBook.exceptions.PrincipleNotFoundException;
import com.project.SchoolBook.services.PrincipleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrincipleServiceImpl implements PrincipleService {
    private final PrincipleRepository principleRepository;
    private final ModelMapper modelMapper;

    private PrincipleDTO convertToPrincipleDTO(Principle principle) {
        return modelMapper.map(principle, PrincipleDTO.class);
    }

    @Override
    public List <PrincipleDTO> getPrinciples() {
        return principleRepository.findAll().stream().map(this::convertToPrincipleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PrincipleDTO getPrinciple(@Min(1) long id) {
        return modelMapper.map(principleRepository.findById(id)
                .orElseThrow(() -> new PrincipleNotFoundException("Invalid principle Id: " + id)), PrincipleDTO.class);
    }

    @Override
    public Principle create(@Valid PrincipleDTO principleDTO) {
        return principleRepository.save(modelMapper.map(principleDTO, Principle.class));
    }

    @Override
    public Principle updatePrinciple(long id, PrincipleDTO principleDTO) {
        Principle principle = modelMapper.map(principleDTO, Principle.class);
        principle.setId(id);
        return principleRepository.save(principle);
    }

    @Override
    public void deletePrinciple(long id) {
        principleRepository.deleteById(id);
    }
}
