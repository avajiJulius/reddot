package com.reddot.services;

import com.reddot.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    User findById(Long id);
    void deleteUser(Long id);
    void saveUser(User user);
    void updateUser(Long id, User user);

}
