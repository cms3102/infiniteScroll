package com.mvcvisitors.article.domain;

public class Article {
	
	private String id;
	private String name;
	private String content;
	private String regdate;
	
	public Article() {}
	
	public Article(String name, String content) {
		this(null, name, content, null);
	}
	
	public Article(String id, String name, String content) {
		this(id, name, content, null);
	}
	
	public Article(String id, String name, String content, String regdate) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String articleId) {
		this.id = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + id + ", name=" + name + ", content=" + content + ", regdate=" + regdate
				+ "]";
	}
}
