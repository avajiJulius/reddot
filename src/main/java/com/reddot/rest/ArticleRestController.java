package com.reddot.rest;

import com.reddot.entities.Article;
import com.reddot.services.ArticleService;
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
@RequestMapping("/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Article>> getArticles() {
        List<Article> articles = articleService.findAllArticles();

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> getArticle(@PathVariable("id") Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Article article = articleService.findArticleById(id);

        if(article == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<Article> saveArticle(@RequestBody @Valid Article article) {
        HttpHeaders headers = new HttpHeaders();

        if(article == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        articleService.saveArticle(article);

        return new ResponseEntity<>(article, headers, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:user')")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id,
                                                 @RequestBody @Valid Article editArticle)  {
        HttpHeaders headers = new HttpHeaders();

        if(editArticle == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        articleService.updateArticle(id, editArticle);
        return new ResponseEntity<>(editArticle, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:admin')")
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") Long id) {
        Article article = articleService.findArticleById(id);

        if(article == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
