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


    /**
     * Read all activated users.
     *
     * @return list of activated users and HttpStatus 200
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<List<User>> readAllActivatedUsers();

     /**
     * Read all users.
     *
     * @return list of  users and HttpStatus 200
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<List<User>> readAllUsers();

    /**
     * Read user by id
     *
     * @param id must not be {@literal null}.
     * @return the user and HttpStatus 200; If don't find user by this id, HttpStatus 404
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<User> readById(Long id);

    /**
     * Delete user by id
     *
     * @param id must not be {@literal null}.
     * @return the deleted user and HttpStatus 204; If don't find user by this id, HttpStatus 404
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<User> deleteUser(Long id);


    /**
     * Create a given user.
     *
     * @param user
     * @return created User and HttpStatus 200. If user data not valid, return HttpStatus 400
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<User> createUser(User user);


    /**
     *  Updates a given user.
     *
     * @param update new user data.
     * @param id must not be {@literal null}.
     * @return the updated user and HttpStatus 200; If user data not valid, return HttpStatus 400;
     * If id is null return HttpStatus 404.
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<User> updateUser(long id, User update);


    /**
     * Find user by username.
     *
     * @return user enity
     * This method using in UserDetailsServiceImpl
     * @see com.reddot.security.UserDetailsServiceImpl
     */
    User findByUsername(String username);

}
