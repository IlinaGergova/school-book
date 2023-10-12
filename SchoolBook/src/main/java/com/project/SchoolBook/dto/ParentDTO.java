package com.project.SchoolBook.dto;

import com.project.SchoolBook.data.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParentDTO {
    private long id;
    private String name;
    private String address;
    private String contact;
    private List <Student> children;
}
