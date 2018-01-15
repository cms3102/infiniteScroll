package com.mvcvisitors.article.service;

import java.util.List;

import com.mvcvisitors.article.dao.ArticleDao;
import com.mvcvisitors.article.dao.jdbcArticleDao;
import com.mvcvisitors.article.domain.Article;
import com.mvcvisitors.common.db.DaoFactory;

public class ArticleServiceImpl implements ArticleService {
	
	ArticleDao articleDao = (ArticleDao) DaoFactory.getInstance().getDao(jdbcArticleDao.class);
	
//	글 작성
	@Override
	public void write(Article article) {
		articleDao.write(article);
	}
	
//	글 삭제
	@Override
	public void delete(String id) {
		articleDao.delete(id);
	}
	
//	글 정보 읽어오기
	@Override
	public Article read() {
		return articleDao.read();
	}
	
//	리스트 출력
	@Override
	public List<Article> listAll(String lastId) {
		return articleDao.listAll(lastId);
	}
	
//	전체 글 개수 출력
	@Override
	public int countAll() {
		return articleDao.countAll();
	}
	
//	최신 글 아이디 출력
	@Override
	public int getLastId() {
		return articleDao.getLastId();
	}
	
}
