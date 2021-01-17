package com.example.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.model.Article;


public interface ArticleRepository extends CrudRepository <Article,Long>{
	
}
