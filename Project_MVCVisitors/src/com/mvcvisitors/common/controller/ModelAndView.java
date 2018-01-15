package com.mvcvisitors.common.controller;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView{
	private String view;
	private Map<String, Object> model;
	
	public ModelAndView() {
		model = new HashMap<String, Object>();
	}
	
	public ModelAndView(String view, Map<String, Object> model) {
		this.view = view;
		this.model = model;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	public void addObject(String key, Object value){
		model.put(key, value);
	}
	
	@Override
	public String toString() {
		return "ModelAndView [view=" + view + ", model=" + model + "]";
	}
}