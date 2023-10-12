package com.project.SchoolBook.dto;

import com.project.SchoolBook.data.entity.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private long id;
    private String name;
    private String address;
    private String contact;
    private Grade grade;
}
