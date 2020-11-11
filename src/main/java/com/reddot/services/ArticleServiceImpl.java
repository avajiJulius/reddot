package com.reddot.services;

import com.reddot.entities.Article;
import com.reddot.exceprions.ArticleNotFoundException;
import com.reddot.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Article> findAllNotHiddenArticles() {
        return repository.findArticleByHiddenFalse();
    }

    @Override
    public List<Article> findAllArticles() {
        return repository.findAll();
    }

    @Override
    public Article findArticle(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Article findArticleById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Not found article by ID " + id));
    }

    @Override
    public void deleteArticle(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void saveArticle(Article article) {
        repository.save(article);
    }

    @Override
    public void updateArticle(Long id, Article article) {
        Article a = repository.getOne(id);
        a.setText(article.getText());
        a.setTitle(article.getTitle());
        repository.save(a);
    }
}
