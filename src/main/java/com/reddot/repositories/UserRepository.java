package com.reddot.repositories;

import com.reddot.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findByIsActivatedTrue();
    @Deprecated
    @Query("SELECT u FROM User u WHERE CONCAT(u.username, ' ' , u.firstName, ' ' , u.lastName) LIKE %?1%")
    List<User> search(String keyword);
}
