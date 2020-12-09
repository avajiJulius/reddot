package com.reddot.controllers;

import com.reddot.model.dto.ArticleDTO;
import com.reddot.model.entities.Article;
import com.reddot.services.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleRestController {

    private final ArticleService ArticleService;

    @Autowired
    public ArticleRestController(ArticleService IArticleService) {
        this.ArticleService = IArticleService;
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleDTO>> getArticles() {

        return ArticleService.readAllArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") Long id) {
        return ArticleService.readArticle(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<Article> saveArticle(@RequestBody @Valid Article article) {
        return ArticleService.creatArticle(article);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id ,@RequestBody @Valid Article update)  {
        return ArticleService.updateArticle(id ,update);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:admin')")
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") Long id) {
        return ArticleService.deleteArticle(id);
    }
}
