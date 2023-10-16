package com.project.SchoolBook.views.models;

import com.project.SchoolBook.data.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeacherModel {
    private String name;
    private String address;
    private String contact;
    private List<Subject> subjects;
}
