package com.reddot.services;

import com.reddot.entities.User;
import com.reddot.exceprions.UserNotFoundException;
import com.reddot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAllActivatedUsers() {
        return repository.findByIsActivatedTrue();
    }

    @Override
    public List<User> findAllUsers() {
        List<User> result = new ArrayList<>();
        repository.findAll().forEach(result::add);

        return result;
    }

    @Override
    public List<User> search(Specification<User> specification) {
        return repository.findAll(specification);
    }

    @Override
    public Page<User> findUsersWithPagingAndFiltering(int pageNumber, int pageSize, Specification<User> specification) {
        return repository.findAll(specification, PageRequest.of(pageNumber, pageSize));
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
//        User result = repository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("User not found by ID " + id));
//        result.setUsername(user.getUsername());
//        result.setFirstName(user.getFirstName());
//        result.setLastName(user.getLastName());
//        result.setDateOfBirth(user.getDateOfBirth());
//        repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User by this username not found"));
    }


}
