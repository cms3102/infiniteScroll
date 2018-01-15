package com.mvcvisitors.article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.mvcvisitors.article.domain.Article;
import com.mvcvisitors.article.service.ArticleService;
import com.mvcvisitors.article.service.ArticleServiceImpl;
import com.mvcvisitors.common.controller.Controller;
import com.mvcvisitors.common.controller.ModelAndView;

public class ArticleWriteController implements Controller{
	
	private ArticleService articleService = new ArticleServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, UnsupportedEncodingException {
		
		String name = request.getParameter("name");
		String content = request.getParameter("content").replace("\n", "<br>");
		
		Article article = new Article(name, content);
		
		articleService.write(article);
		Article article2 = null;
		article2 = articleService.read();
		int count = articleService.countAll();
		
		JSONObject jsonObject = new JSONObject();
		
		if (article2 != null) {
			jsonObject.put("message", "success");
			jsonObject.put("count", count);
			jsonObject.put("id", article2.getId());
			jsonObject.put("name", article2.getName());
			jsonObject.put("content", article2.getContent());
			jsonObject.put("regdate", article2.getRegdate());
		} else {
			jsonObject.put("message", "failed");
		}
		
		
		
		String articleInfo = jsonObject.toJSONString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.print(articleInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
