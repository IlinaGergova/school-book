package com.project.SchoolBook.views.models;

import com.project.SchoolBook.data.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserModel {
    private String username;
    private String password;
    private Role role;
}
