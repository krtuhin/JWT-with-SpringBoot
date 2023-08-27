package com.jwt.services;

import com.jwt.models.User;
import com.jwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    //List<User> list = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUser() {

        /*
        list.add(new User(UUID.randomUUID().toString(), "Tuhin Kumar", "tuhin@gmail.com"));
        list.add(new User(UUID.randomUUID().toString(), "Prince", "prince@gmail.com"));
        list.add(new User(UUID.randomUUID().toString(), "Avijit", "avijit@gmail.com"));

        return list;
        */

        return this.userRepository.findAll();
    }

    public User createUser(User user) {

        //create random user id and encode password
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }
}
