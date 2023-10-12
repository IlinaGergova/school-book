package com.project.SchoolBook.data.repository;

import com.project.SchoolBook.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
