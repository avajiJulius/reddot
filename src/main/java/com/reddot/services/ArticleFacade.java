package com.reddot.services;

import com.reddot.entities.Article;
import com.reddot.dto.ArticleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleFacade {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleFacade(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }


    public ArticleDTO getArticleById(Long id){
        return convertToArticleDto(articleService.findArticleById(id));
    }

    private ArticleDTO convertToArticleDto(Article article) {
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return articleDTO;
    }
}
