package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //Long : the type of id
    //doesn't need to add the functions of create, read, update and delete
    //since it extends CrudRepository
}
