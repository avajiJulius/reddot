package com.reddot.controllers;

import com.reddot.entities.Article;
import com.reddot.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String showArticles(Model model) {
        model.addAttribute("articleList", articleService.findAllNotHiddenArticles());
        return "articles/home";
    }

    @GetMapping("/{id}")
    public String showArticle(Model model,
                              @PathVariable(name = "id") Long id) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "articles/article";
    }
}
