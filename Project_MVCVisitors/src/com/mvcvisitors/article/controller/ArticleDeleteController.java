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

public class ArticleDeleteController implements Controller{
	
	private ArticleService articleService = new ArticleServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, UnsupportedEncodingException {
		
		String id = request.getParameter("id");
		String result = "failed";
		
		articleService.delete(id);
		result = "success";
		int count = articleService.countAll();
		
		JSONObject jsonObject = new JSONObject();
		
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out;
		
		try {
			if (result.equals("success")) {
				jsonObject.put("id", id);
				jsonObject.put("count", count);
				out = response.getWriter();
				out.print(jsonObject);
			} else {
				out = response.getWriter();
				out.print(result);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
