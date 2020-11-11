package com.reddot.services;

import com.reddot.entities.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAllNotHiddenArticles();

    List<Article> findAllArticles();

    Article findArticle(Long id);

    Article findArticleById(Long id);

    void deleteArticle(Long id);

    void saveArticle(Article article);

    void updateArticle(Long id, Article article);
}
