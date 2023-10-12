package com.project.SchoolBook.services;

import com.project.SchoolBook.data.entity.Principle;
import com.project.SchoolBook.dto.PrincipleDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface PrincipleService {
    List <PrincipleDTO> getPrinciples();

    PrincipleDTO getPrinciple(@Min(1) long id);

    Principle create(@Valid PrincipleDTO principleDTO);

    Principle updatePrinciple(long id, PrincipleDTO principleDTO);

    void deletePrinciple(long id);
}
