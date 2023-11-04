package com.project.SchoolBook.dto;
import com.project.SchoolBook.data.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
