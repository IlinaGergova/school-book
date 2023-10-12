package com.project.SchoolBook.dto;

import com.project.SchoolBook.data.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDTO {
    private long id;
    private School school;
    private Grade grade;
    private Subject subject;
    private Teacher teacher;
    private Date date;
}
