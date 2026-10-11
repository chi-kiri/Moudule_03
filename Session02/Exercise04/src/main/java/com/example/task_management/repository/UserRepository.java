package com.example.task_management.repository;

import com.example.task_management.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users = List.of(
            new User(1, "user1", "user1@gmail.com", "admin"),
            new User(2, "user2", "user2@gmail.com", "member"),
            new User(3, "user3", "user3@gmail.com", "member")
    );

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}