package com.reddot.controllers;

import com.reddot.model.entities.Article;
import com.reddot.services.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleRestController {

    private final IArticleService IArticleService;

    @Autowired
    public ArticleRestController(IArticleService IArticleService) {
        this.IArticleService = IArticleService;
    }

    @GetMapping("")
    public ResponseEntity<List<Article>> getArticles() {
        return IArticleService.readAllArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") Long id) {
        return IArticleService.readArticle(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<Article> saveArticle(@RequestBody @Valid Article article) {
        return IArticleService.creatArticle(article);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id ,@RequestBody @Valid Article update)  {
        return IArticleService.updateArticle(id ,update);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:admin')")
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") Long id) {
        return IArticleService.deleteArticle(id);
    }
}
