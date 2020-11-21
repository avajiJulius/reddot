package com.reddot.services;

import com.reddot.model.entities.Article;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IArticleService {
    List<Article> search(Specification<Article> specification);

    List<Article> readAllNotHiddenArticles();

    ResponseEntity<List<Article>> readAllArticles();

    ResponseEntity<Article> readArticle(Long id);

    ResponseEntity<Article> deleteArticle(Long id);

    ResponseEntity<Article> creatArticle(Article article);

    ResponseEntity<Article> updateArticle(long id,Article update);
}
