package com.mvcvisitors.article.service;

import java.util.List;

import com.mvcvisitors.article.domain.Article;

public interface ArticleService {
	
//	글 등록
	public void write(Article article);
	
//	글 읽어오기
	public Article read();
	
//	글 삭제
	public void delete(String id);
	
//	전체 리스트 출력
	public List<Article> listAll(String lastId);
	
//	최신 id 출력
	public int getLastId();
	
//	전체 글 개수 출력
	public int countAll();
}
