package com.project.SchoolBook.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "principle")
public class Principle extends PersonalInformation {

    @NotNull
    @OneToOne
    @JoinColumn(name = "school_id")
    private School school;
}
