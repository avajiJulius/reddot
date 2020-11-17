package com.reddot.controllers;

import com.reddot.entities.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface HomeController {
    @GetMapping
    public List<Article> homePage();

    @GetMapping("/search")
    public Map<Class, List> globalSearch(@RequestParam(name = "keyword", required = false) String keyword);
}
