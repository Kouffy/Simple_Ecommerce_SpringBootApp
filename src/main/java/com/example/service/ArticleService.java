package com.example.service;

import java.util.List;

import com.example.model.Article;


public interface ArticleService {
    Article saveArticle(Article article);
    Article updateArticle(Article article);
    List<Article> getAllArticles();
    Article getArticle(Long articleId);
    void deleteArticle(Long articleId);
}
