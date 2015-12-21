package com.wechat.model;

public class Article {

	protected long article_id;
	protected String article_title;
	protected String article_content;
	protected long create_time;
	protected String cteate_name;
	
	protected String article_title_min;
	protected String create_time_str;
	public long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(long article_id) {
		this.article_id = article_id;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public String getCteate_name() {
		return cteate_name;
	}
	public void setCteate_name(String cteate_name) {
		this.cteate_name = cteate_name;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public String getArticle_title_min() {
		return article_title_min;
	}
	public void setArticle_title_min(String article_title_min) {
		this.article_title_min = article_title_min;
	}
	
}
