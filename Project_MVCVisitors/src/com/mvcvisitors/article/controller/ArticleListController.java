package com.mvcvisitors.article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mvcvisitors.article.domain.Article;
import com.mvcvisitors.article.service.ArticleService;
import com.mvcvisitors.article.service.ArticleServiceImpl;
import com.mvcvisitors.common.controller.Controller;
import com.mvcvisitors.common.controller.ModelAndView;

public class ArticleListController implements Controller{
	
	private ArticleService articleService = new ArticleServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, UnsupportedEncodingException {
		
		response.setContentType("text/plain;charset=utf-8");
		
		String lastId = request.getParameter("lastId");
		String lastId2 = Integer.toString(articleService.getLastId() + 1); 
		if (lastId == null) {
			lastId = lastId2;
		}
		
		List<Article> list = articleService.listAll(lastId);
		int count = articleService.countAll();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (Article article : list) {
			JSONObject obj = new JSONObject();
			
			obj.put("id", article.getId());
			obj.put("name", article.getName());
			obj.put("content", article.getContent());
			obj.put("regdate", article.getRegdate());
			
			jsonArray.add(obj);
		}
		
		if (list.size() != 0) {
			jsonObject.put("message", "success");
			jsonObject.put("count", count);
			jsonObject.put("list", jsonArray);
		} else {
			jsonObject.put("message", "failed");
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.println(jsonObject);
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
