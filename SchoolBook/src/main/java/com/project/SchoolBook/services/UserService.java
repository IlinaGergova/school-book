package com.project.SchoolBook.services;

import com.project.SchoolBook.data.entity.User;
import com.project.SchoolBook.dto.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface UserService {
    List <UserDTO> getUsers();

    UserDTO getUser(@Min(1) long id);

    User create(@Valid UserDTO userDTO);

    User updateUser(long id, UserDTO updateUserDTO);

    void deleteUser(long id);
}
