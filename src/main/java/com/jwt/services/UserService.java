package com.jwt.services;

import com.jwt.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    List<User> list = new ArrayList<>();

    public List<User> getUser() {

        list.add(new User(UUID.randomUUID().toString(), "Tuhin Kumar", "tuhin@gmail.com"));
        list.add(new User(UUID.randomUUID().toString(), "Prince", "prince@gmail.com"));
        list.add(new User(UUID.randomUUID().toString(), "Avijit", "avijit@gmail.com"));

        return list;
    }
}
