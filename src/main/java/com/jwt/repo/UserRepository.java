package com.jwt.repo;

import com.jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    //method to get user by email from database
    public User findByUserEmail(String email);

}
