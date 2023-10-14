package com.project.SchoolBook.views.controllers;

import com.project.SchoolBook.dto.UserDTO;
import com.project.SchoolBook.services.UserService;
import com.project.SchoolBook.views.models.UserModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    private UserModel convertToUserModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserModel.class);
    }

//    @GetMapping("/list")
//    String getUserList(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        final UserDTO user = clientService.getClientByEmail(email);
//        model.addAttribute("user", client);
//        return "/client/client-home";
//    }
    @GetMapping("/users")
    ResponseEntity<Object> getUsers(Model model) {
        final List<UserModel> users = userService.getUsers()
                .stream()
                .map(this::convertToUserModel)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@Valid @ModelAttribute("user") UserModel userModel,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        UserModel user = new UserModel();
        user.setPassword(encodedPassword);
        user.setRole(userModel.getRole());
        user.setUsername(userModel.getUsername());

        userService.create(modelMapper.map(user, UserDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(
            @PathVariable long id,
            @Valid @ModelAttribute("user") UserModel userModel,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(id, modelMapper.map(userModel, UserDTO.class));
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<Object> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
