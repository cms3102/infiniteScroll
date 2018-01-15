package com.mvcvisitors.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mvcvisitors.article.domain.Article;
import com.mvcvisitors.common.exception.GBException;

public class jdbcArticleDao implements ArticleDao{
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

//	글 쓰기
	@Override
	public void write(Article article) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("INSERT INTO mvcguestbook ");
		stringBuilder.append("(id, ");
		stringBuilder.append("NAME, ");
		stringBuilder.append("content) ");
		stringBuilder.append("VALUES     (mvcguestbook_seq.nextval, ");
		stringBuilder.append("?, ");
		stringBuilder.append("?) ");
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(stringBuilder.toString());
			pstmt.setString(1, article.getName());
			pstmt.setString(2, article.getContent());
			pstmt.executeQuery();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new GBException("JdbcArticleDao.write(Article article) 에러 발생", e);
			}
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	작성한 글 정보 읽어오기
	@Override
	public Article read() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Article article = null;
		String sql = "SELECT * " + 
					 "FROM   mvcguestbook " + 
					 "WHERE  id = (SELECT Max(id) " + 
					 "             FROM   mvcguestbook) ";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				article = createArticle(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
				throw new GBException("JdbcArticleDao.read(String id) 에러 발생", e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return article;
	}
	
//	Article 객체 생성
	private Article createArticle(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String content = rs.getString("content");
		String regdate = rs.getString("regdate");
		
		Article article = new Article();
		article.setId(Integer.toString(id));
		article.setName(name);
		article.setContent(content);
		article.setRegdate(regdate);
		
		return article;
	}
	
//	글 삭제
	@Override
	public void delete(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM mvcguestbook " + 
					 "WHERE  id = ? ";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GBException("JdbcArticleDao.delete(Article article) 에러 발생", e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
			}
		}
	}
	
//	글 리스트 10개씩 불러오기
	@Override
	public List<Article> listAll(String lastId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> list = new ArrayList<>();
		Article article = null;
		String sql = "SELECT * " + 
					 "FROM   (SELECT Ceil(rownum / 10) page, " + 
					 "               id, " + 
					 "               NAME, " + 
					 "               content, " + 
					 "               regdate " + 
					 "        FROM   mvcguestbook " + 
					 "        WHERE  id < ? " + 
					 "        ORDER  BY id DESC) " + 
					 "WHERE  page = 1 ";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lastId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				article = createArticle(rs);
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
				throw new GBException("JdbcArticleDao.List<Article> listAll() 에러 발생", e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
//	전체 글 개수 계산
	@Override
	public int countAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT Count(id) count " + 
					 "FROM   mvcguestbook ";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
				throw new GBException("JdbcArticleDao.countAll() 에러 발생", e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
//	최신 글 아이디 조회
	@Override
	public int getLastId() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int lastId = 0;
		String sql = "SELECT Max(id) lastId " + 
					 "FROM   mvcguestbook ";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				lastId = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
				throw new GBException("JdbcArticleDao.getLastId( 에러 발생", e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lastId;
	}
	
}
