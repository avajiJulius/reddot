package com.reddot.services;

import com.reddot.model.entities.Article;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IArticleService {
    List<Article> search(Specification<Article> specification);

    List<Article> readAllNotHiddenArticles();

    /**
     * Read all articles.
     *
     * @return list of  articles and HttpStatus 200
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<List<Article>> readAllArticles();

    /**
     * Read artilce by id
     *
     * @param id must not be {@literal null}.
     * @return the article and HttpStatus 200; If don't find article by this id, HttpStatus 404
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<Article> readArticle(Long id);

    /**
     * Delete article by id
     *
     * @param id must not be {@literal null}.
     * @return the deleted article and HttpStatus 204; If don't find article by this id, HttpStatus 404
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<Article> deleteArticle(Long id);

    /**
     * Create a given article.
     *
     * @param article
     * @return created Article and HttpStatus 200. If article data not valid, return HttpStatus 400
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<Article> creatArticle(Article article);

    /**
     *  Updates a given article.
     *
     * @param update new article data.
     * @param id must not be {@literal null}.
     * @return the updated Article and HttpStatus 200; If article data not valid, return HttpStatus 400;
     * If id is null return HttpStatus 404.
     * @see org.springframework.http.HttpStatus
     */
    ResponseEntity<Article> updateArticle(long id,Article update);
}
