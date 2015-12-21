package com.wechat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.Article;
import com.wechat.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	protected SqlSession sqlSession;
	
	public long addArticle(Article article) throws Exception {
		// TODO Auto-generated method stub
		Long key = sqlSession.selectOne(Article.class.getName()+".selectMaxId");
		if(key == null){
			key = 0l;
		}
		key +=1;
		article.setArticle_id(key);
		sqlSession.insert(Article.class.getName()+".insert", article);
		return 0;
	}

	public void updateArticle(Article article) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Article.class.getName()+".updateArticle", article);
	}

	public void delArticle(long article_id) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(Article.class.getName()+".delete", article_id);
	}

	public Article getArticle(long article_id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("article_id", article_id);
		Article article = sqlSession.selectOne(Article.class.getName()+".selectArticle", params);
		return article;
	}

	public List<Article> articles() throws Exception {
		// TODO Auto-generated method stub
		List<Article> articles = sqlSession.selectList(Article.class.getName()+".selectArticles");
		return articles;
	}

}
