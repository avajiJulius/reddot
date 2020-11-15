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

import java.util.List;

import static com.reddot.repositories.specifications.ArticleSpecification.*;
import static com.reddot.repositories.specifications.UserSpecification.*;

@Controller
public class HomeController {

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public HomeController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping
    public String homePage(Model model) {
        List<Article> articleList = articleService.findAllNotHiddenArticles();
        model.addAttribute("articleList", articleList);
        return "home";
    }

    @GetMapping("/search")
    public String globalSearch(Model model,
                         @RequestParam(name = "keyword", required = false) String keyword) {
        Specification<User> userSpec = Specification.where(usernameContains(keyword)).and(activatedContains(true));
        Specification<Article> articleSpec = Specification.where(titleContains(keyword)).and(hiddenContains(false));
        List<User> userList = userService.search(userSpec);
        List<Article> articleList = articleService.search(articleSpec);

        model.addAttribute("userList", userList);
        model.addAttribute("articleList", articleList);

        return "search";
    }
}
