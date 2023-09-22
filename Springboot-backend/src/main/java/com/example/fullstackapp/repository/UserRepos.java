package com.example.fullstackapp.repository;

import com.example.fullstackapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User,Long> {
}
