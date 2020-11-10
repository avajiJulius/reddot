package com.reddot.services;

import com.reddot.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> search(Specification<User> specification);

    @Deprecated
    Page<User> findUsersWithPagingAndFiltering(int pageNumber, int pageSize, Specification<User> specification);

    List<User> findAllActivatedUsers();

    List<User> findAllUsers();

    User findById(Long id);

    void deleteUser(Long id);

    void saveUser(User user);

    void updateUser(Long id, User user);

}
