package com.project.SchoolBook.services.implementations;

import com.project.SchoolBook.data.entity.User;
import com.project.SchoolBook.data.repository.UserRepository;
import com.project.SchoolBook.dto.UserDTO;
import com.project.SchoolBook.exceptions.UserNotFoundException;
import com.project.SchoolBook.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List <UserDTO> getUsers() {
        return userRepository.findAll().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(@Min(1) long id) {
        return modelMapper.map(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Invalid user Id: " + id)), UserDTO.class);
    }

    @Override
    public User create(@Valid UserDTO userDTO) {
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public User updateUser(long id, UserDTO updateUserDTO) {
        User user = modelMapper.map(updateUserDTO, User.class);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}