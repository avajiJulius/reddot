package com.reddot.services;

import com.reddot.entities.Article;
import com.reddot.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ArticleService {
    List<Article> findAllNotHiddenArticles();

    List<Article> findAllArticles();

    Article findArticleById(Long id);

    void deleteArticle(Long id);

    void saveArticle(Article article);

    void updateArticle(Long id, Article article);
}
