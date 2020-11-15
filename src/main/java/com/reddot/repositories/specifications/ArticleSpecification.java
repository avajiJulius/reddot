package com.reddot.repositories.specifications;

import com.reddot.entities.Article;
import com.reddot.entities.User;
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
