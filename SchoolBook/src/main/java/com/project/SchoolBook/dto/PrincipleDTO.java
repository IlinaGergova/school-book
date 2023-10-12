package com.project.SchoolBook.dto;

import com.project.SchoolBook.data.entity.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrincipleDTO {
    private long id;
    private String name;
    private String address;
    private String contact;
    private School school;
}
