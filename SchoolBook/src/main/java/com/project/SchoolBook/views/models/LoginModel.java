package com.project.SchoolBook.views.models;
import com.project.SchoolBook.data.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoginModel {
    private String username;
    private String password;
}
