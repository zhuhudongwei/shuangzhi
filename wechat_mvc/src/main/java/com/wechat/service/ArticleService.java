package com.wechat.service;

import java.util.List;

import com.wechat.model.Article;

public interface ArticleService {

	long addArticle(Article article)throws Exception;
	
	void updateArticle(Article article)throws Exception;
	
	void delArticle(long article_id)throws Exception;
	
	Article getArticle(long article_id)throws Exception;
	
	List<Article> articles()throws Exception;
}
