package com.reddot.controllers;

import com.reddot.entities.Article;
import com.reddot.entities.User;
import com.reddot.services.ArticleService;
import com.reddot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.reddot.repositories.specifications.ArticleSpecification.*;
import static com.reddot.repositories.specifications.UserSpecification.*;

@RestController
public class HomeControllerImpl implements HomeController{

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public HomeControllerImpl(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @Override
    public List<Article> homePage() {
        List<Article> articleList = articleService.findAllNotHiddenArticles();
        return articleList;
    }

    @Override
    public Map<Class, List> globalSearch(@RequestParam(name = "keyword", required = false) String keyword) {
        Map<Class, List> result = new HashMap<>();

        Specification<User> userSpec = Specification.where(usernameContains(keyword)).and(activatedContains(true));
        Specification<Article> articleSpec = Specification.where(titleContains(keyword)).and(hiddenContains(false));
        List<User> userList = userService.search(userSpec);
        List<Article> articleList = articleService.search(articleSpec);

        result.put(User.class, userList);
        result.put(Article.class, articleList);

        return result;
    }
}
