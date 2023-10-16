package com.project.SchoolBook.views.models;

import com.project.SchoolBook.data.entity.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrincipleModel {
    private String name;
    private String address;
    private String contact;
    private School school;
}