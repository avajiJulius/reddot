package com.reddot.controllers;

import com.reddot.entities.Article;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ArticleController {

    @GetMapping("/")
    List<Article> showArticles();

    @GetMapping("/{id}")
    Article showArticle(@PathVariable(name = "id") Long id);

    @PostMapping()
    @PreAuthorize("hasAuthority('user:user')")
    String createArticle(@ModelAttribute("article") Article article);

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('user:user')")
    String updateArticle(@PathVariable(name = "id") Long id,
                                @ModelAttribute Article editArticle) ;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:admin')")
    String deleteArticle(@PathVariable(name = "id") Long id);
}
