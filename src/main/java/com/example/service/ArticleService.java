package com.example.service;

import com.example.model.Article;
import com.example.web.dto.AjoutArticleDto;

public interface ArticleService {
	Article save(AjoutArticleDto ajoutArticleDto);
}
