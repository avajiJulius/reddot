package com.reddot.controllers;

import com.reddot.entities.Article;
import com.reddot.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/articles")
public class ArticleControllerImpl implements ArticleController{

    private final ArticleService articleService;

    @Autowired
    public ArticleControllerImpl(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public List<Article> showArticles() {

        return articleService.findAllArticles();
    }

    @Override
    public Article showArticle(Long id) {
        return articleService.findArticleById(id);
    }

    @Override
    public String createArticle(Article article) {
        articleService.saveArticle(article);
        return "Article successful create";
    }

    @Override
    public String updateArticle(Long id, Article editArticle) {
        articleService.updateArticle(id, editArticle);
        return "Article successful update";
    }

    @Override
    public String deleteArticle(Long id) {
        articleService.deleteArticle(id);
        return "Article successful delete";
    }
}
