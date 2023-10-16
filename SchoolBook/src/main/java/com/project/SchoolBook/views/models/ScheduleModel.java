package com.project.SchoolBook.views.models;

import com.project.SchoolBook.data.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleModel {
    private Grade grade;
    private Subject subject;
    private Teacher teacher;
    private Date date;
}
