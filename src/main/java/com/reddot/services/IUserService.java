package com.reddot.services;

import com.reddot.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    List<User> search(Specification<User> specification);

    @Deprecated
    Page<User> findUsersWithPagingAndFiltering(int pageNumber, int pageSize, Specification<User> specification);

    ResponseEntity<List<User>> readAllActivatedUsers();

    ResponseEntity<List<User>> readAllUsers();

    ResponseEntity<User> readById(Long id);

    ResponseEntity<User> deleteUser(Long id);

    ResponseEntity<User> createUser(User user);

    ResponseEntity<User> updateUser(long id, User update);

    User findByUsername(String username);

}
