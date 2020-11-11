package com.reddot.entities;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "articles")
public class Article implements SearchWrapper{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long articleId;

    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @Column(name = "image")
    private String image;
    @Column(name = "upload_date")
    private LocalDate uploadDate;
    @Column(name = "update_date")
    private LocalDate updateDate;
    @Column(name = "hidden")
    private boolean hidden;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    public Article() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public User getAuthor() {
        return author;
    }
    public String getAuthorName() {
        return author.getUsername();
    }


    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String getSearchParam() {
        return title;
    }
}
