package com.reddot.rest;

import com.reddot.entities.Article;
import com.reddot.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/articles")
public class ArticleRestController {


    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:read')")
    public String showArticles(Model model) {
        model.addAttribute("articleList", articleService.findAllNotHiddenArticles());
        return "articles/home";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String showArticle(Model model,
                              @PathVariable(name = "id") Long id) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "articles/article";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('user:read')")
    public String writeArticle(@ModelAttribute("article") Article article) {
        return "articles/create";
    }

    @PostMapping()
    public String createArticle(@ModelAttribute("article") Article article) {
        articleService.saveArticle(article);
        return "redirect:/";
    }


    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('user:read')")
    public String editArticle(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("article", articleService.findArticleById(id));
        return "articles/edit";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String updateArticle(@PathVariable(name = "id") Long id,
                                @ModelAttribute Article editArticle) {

        articleService.updateArticle(id, editArticle);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        articleService.deleteArticle(id);
        return "redirect:/";
    }
}
