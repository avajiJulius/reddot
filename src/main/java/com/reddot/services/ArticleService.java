package com.reddot.services;

import com.reddot.model.entities.Article;
import com.reddot.exceprions.ArticleNotFoundException;
import com.reddot.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {

    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Article> search(Specification<Article> specification) {
        return repository.findAll(specification);
    }

    @Override
    public List<Article> readAllNotHiddenArticles() {
        return repository.findArticleByIsHiddenFalse();
    }

    @Override
    public ResponseEntity<List<Article>> readAllArticles() {
        List<Article> articles = repository.findAll();

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Article> readArticle(Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Article article = repository.getOne(id);

        if(article == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(article, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Article> deleteArticle(Long id) {
        Article article = repository.getOne(id);

        if(article == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Article> creatArticle(Article article) {
        HttpHeaders headers = new HttpHeaders();

        if(article == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repository.save(article);

        return new ResponseEntity<>(article, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Article> updateArticle(long id ,Article update) {
        HttpHeaders headers = new HttpHeaders();
        Optional<Article> article = repository.findById(id);
        if(update == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(article.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        update.setArticleId(article.get().getArticleId());
        repository.save(update);
        return new ResponseEntity<>(update, headers, HttpStatus.OK);
    }
}
