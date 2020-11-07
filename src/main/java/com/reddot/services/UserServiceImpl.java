package com.reddot.services;

import com.reddot.entities.User;
import com.reddot.repositories.UserDAO;
import com.reddot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO repository;

    @Autowired
    public UserServiceImpl(UserDAO repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
