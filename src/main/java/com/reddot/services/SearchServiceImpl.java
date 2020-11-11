package com.reddot.services;

import com.reddot.entities.Article;
import com.reddot.entities.SearchWrapper;
import com.reddot.entities.User;
import com.reddot.repositories.ArticleRepository;
import com.reddot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public SearchServiceImpl(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<SearchWrapper> globalSearch(Specification<Article> articleSpec, Specification<User> userSpec) {
        List<SearchWrapper> searchList = new ArrayList<>();
        searchList.addAll(userRepository.findAll(userSpec));
        searchList.addAll(articleRepository.findAll(articleSpec));
        return searchList;
    }
}
