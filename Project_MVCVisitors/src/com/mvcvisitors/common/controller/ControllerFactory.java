package com.mvcvisitors.common.controller;

import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ControllerFactory {

	private HashMap<String, Controller> controllerMap;
	DocumentBuilderFactory parserFactory;
	DocumentBuilder parser;

	public ControllerFactory(String controllerMapperLocation){
		
		
		controllerMap = new HashMap<String, Controller>();

		
		try {
			parserFactory = DocumentBuilderFactory.newInstance();
			parserFactory.setIgnoringElementContentWhitespace(true);
			parser = parserFactory.newDocumentBuilder();
			
			Document document = parser.parse(controllerMapperLocation);
			Element beansElem = document.getDocumentElement();
			NodeList beans=beansElem.getChildNodes();
			
			for(int i=0; i<beans.getLength(); i++) {
				Node node=beans.item(i);
				if(node.getNodeType()!=Node.ELEMENT_NODE)continue;
				Element beanElem=(Element)node;
//				System.out.println(beanElem.getAttribute("name"));
//				System.out.println(beanElem.getAttribute("class"));
				String uri = beanElem.getAttribute("name");
				String controllerClass = beanElem.getAttribute("class");
				
				Controller controllerObject = (Controller) Class.forName(controllerClass).newInstance();
				controllerMap.put(uri, controllerObject);
				System.out.println(uri + "=" + controllerObject);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Controller getController(String uri) {
		return controllerMap.get(uri);
	}
}
