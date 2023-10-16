package com.project.SchoolBook.config;

import com.project.SchoolBook.data.entity.Role;
import com.project.SchoolBook.data.entity.User;
import com.project.SchoolBook.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Admin implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String...args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        User admin = new User();
        admin.setUsername("admin@email.com");
        admin.setPassword(encodedPassword);
        admin.setRole(Role.Admin);
        userRepository.save(admin);
    }
}