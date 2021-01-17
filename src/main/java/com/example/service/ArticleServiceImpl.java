package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Article;
import com.example.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public Article updateArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public List<Article> getAllArticles() {
		return (List<Article>) articleRepository.findAll();
	}

	@Override
	public Article getArticle(Long articleId) {
		return articleRepository.findById(articleId).get();
	}

	@Override
	public void deleteArticle(Long articleId) {
		articleRepository.deleteById(articleId);

	}

}
