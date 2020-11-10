package com.reddot.repositories.specifications;

import com.reddot.entities.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Deprecated
public class UserSpecificationFull implements Specification<User> {

    public UserSpecificationFull(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if(criteria.getOperation().equalsIgnoreCase(":")) {
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue()  + "%");
        }
        return null;
    }
}
