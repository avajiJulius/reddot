package com.reddot.repositories.specifications;

import com.reddot.model.entities.Article;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecification {
    public static Specification<Article> titleContains(String word) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Article> hiddenContains(boolean flag) {
        return (Specification<Article>)(root, query, builder) ->
                builder.equal(root.get("isHidden"), flag);
    }

}
