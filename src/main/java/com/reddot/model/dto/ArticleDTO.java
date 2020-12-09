package com.reddot.model.dto;

import java.io.Serializable;

public class ArticleDTO implements Serializable {
    private Long articleId;
    private String title;
    private String content;
    private int rate;
    private AuthorDTO author;
    private boolean hidden;

    public ArticleDTO() {
    }

    public ArticleDTO(Long articleId, String title, String content, int rate, AuthorDTO author, boolean hidden) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.author = author;
        this.hidden = hidden;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
