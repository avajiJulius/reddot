package com.reddot.repositories;

import com.reddot.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface UserRepository {
    @Query(name = "User.findById",
            value = "select u from User u where id = :id")
    Optional<User> findById(@Param("id") Long id);
    @Query(name = "User.findByUsername",
            value = "select u from User u where upper(u.username) = upper(:username)")
    List<User> findByUsername(@Param("username") String username);
    @Query(name = "User.findAll",
    value = "select u from User u")
    List<User> findAll();
}
