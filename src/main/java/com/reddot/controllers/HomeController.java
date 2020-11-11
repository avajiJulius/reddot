package com.reddot.controllers;

import com.reddot.entities.Article;
import com.reddot.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ArticleService articleService;

    @Autowired
    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String homePage(Model model) {
        List<Article> articleList = articleService.findAllNotHiddenArticles();
        model.addAttribute("articleList", articleList);
        return "home";
    }
}
