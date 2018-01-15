package com.mvcvisitors.test;

import org.junit.After;
import org.junit.Test;

import com.mvcvisitors.article.dao.ArticleDao;
import com.mvcvisitors.article.dao.jdbcArticleDao;
import com.mvcvisitors.article.domain.Article;
import com.mvcvisitors.common.db.DaoFactory;

public class test {
	
	ArticleDao articleDao = (ArticleDao) DaoFactory.getInstance().getDao(jdbcArticleDao.class);
	
	/*@After
	public void tearDown() {
		System.out.println("작업 완료됨");
	}*/
	
	/*@Test
	public void write() {
		articleDao.write(new Article("강백호", "안냥안냥안냥"));
	}*/
	
	/*@Test
	public void delete() {
		articleDao.delete("4");
	}*/
	
	@Test
	public void read() {
		Article article = articleDao.read();
	}
}
