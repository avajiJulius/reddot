package com.reddot.services;

import com.reddot.entities.Article;
import com.reddot.entities.SearchWrapper;
import com.reddot.entities.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    List<SearchWrapper> globalSearch(Specification<Article> articleSpec, Specification<User> userSpec);
}
