package com.project.SchoolBook.services;

import com.project.SchoolBook.data.entity.Parent;
import com.project.SchoolBook.dto.ParentDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface ParentService {
    List <ParentDTO> getParents();

    ParentDTO getParent(@Min(1) long id);

    Parent create(@Valid ParentDTO parentDTO);

    Parent updateParent(long id, ParentDTO parentDTO);

    void deleteParent(long id);
}
