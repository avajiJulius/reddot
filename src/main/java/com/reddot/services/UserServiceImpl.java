package com.reddot.services;

import com.reddot.model.entities.User;
import com.reddot.exceprions.UserNotFoundException;
import com.reddot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<User>> readAllActivatedUsers() {
        List<User> users = repository.findByIsActivatedTrue();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> readAllUsers() {
        List<User> result = new ArrayList<>();
        repository.findAll().forEach(result::add);

        return null;
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
    public ResponseEntity<User> readById(Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> deleteUser(Long id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        HttpHeaders headers = new HttpHeaders();

        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repository.save(user);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(long id ,User update) {
        HttpHeaders headers = new HttpHeaders();

        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(update == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        update.setId(user.get().getId());
        repository.save(update);
        return new ResponseEntity<>(update, headers, HttpStatus.OK);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User by this username not found"));
    }


}
