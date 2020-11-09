package com.reddot.services;

import com.reddot.entities.User;
import com.reddot.exceprions.UserNotFoundException;
import com.reddot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
       User result = repository.findById(id)
               .orElseThrow(() -> new UserNotFoundException("User not found by ID " + id));
        return result;
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public void updateUser(Long id,User user) {
        User result = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by ID " + id));
        result.setUsername(user.getUsername());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setDateOfBirth(user.getDateOfBirth());
        repository.save(user);
    }

}
