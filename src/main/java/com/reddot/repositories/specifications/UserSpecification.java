package com.reddot.repositories.specifications;

import com.reddot.entities.User;
import org.springframework.data.jpa.domain.Specification;


public class UserSpecification {
    public static Specification<User> usernameContains(String word) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + word + "%");
    }

//    public static Specification<User> firstNameContains(String word) {
//            return (Specification<User>) (root, criteriaQuery, criteriaBuilder) ->
//                    criteriaBuilder.like(root.get("firstName"), "%" + word + "%");
//    }
//
//    public static Specification<User> lastNameContains(String word) {
//            return (Specification<User>) (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.like(root.get("lastName"), "%" + word + "%");
//    }

    public static Specification<User> statusContains(String status) {
            return (Specification<User>)(root, query, builder) ->
                builder.like(root.get("status"), status);
    }

    public static Specification<User> activatedContains(boolean flag) {
        return (Specification<User>)(root, query, builder) ->
                builder.equal(root.get("isActivated"), flag);
    }


}