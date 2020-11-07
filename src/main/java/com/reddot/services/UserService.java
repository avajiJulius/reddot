package com.reddot.services;

import com.reddot.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
