package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.Article;


@Repository
public interface ArticleRepository {
	Article findById(long id);
}
