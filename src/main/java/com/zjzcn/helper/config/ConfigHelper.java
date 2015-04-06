/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.zjzcn.helper.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import com.zjzcn.util.UuidUtils;

/**
 * 配置管理
 * 
 * @author zjzcn Team
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ConfigHelper
{
    private static final String CONFIG_XML_PATH = "/config.xml";
    
    private static Document document;
    
	public static Map<String, LogNode> getLogConfigs() 
	{
		List<Element> elements = getDocument().selectNodes("/config//log | /config//perm[@log='true']");
	    Map<String, LogNode> logConfigs = new HashMap<String, LogNode>();
	    for (Element element : elements)
	    {
	        String name = element.attributeValue("name");
	        String url = element.attributeValue("url");
	        LogNode logConfig = new LogNode();
	        logConfig.setUrl(url);
	        logConfig.setName(name);
	        logConfigs.put(url, logConfig);
	    }
	    return logConfigs;
	}
	
	public static Set<String> getAllPerms() 
	{
	    List<Element> elements = getDocument().selectNodes("/config//perm");
	    Set<String> set = new HashSet<String>();
	    for (Element element : elements)
	    {
	        String url = element.attributeValue("url");
	        set.add(url);
	    }
	    return set;
	}
	
	public static MenuNode getMenuTree() 
	{
	    Node node = getDocument().selectSingleNode("/config/menu");
	    if(node instanceof Element)
	    {
	    	return createMenuTree((Element)node, null);
	    }
	    
	    return null;
	}
	
	public static List<MenuNode> getMenuList() 
	{
	    MenuNode menu = getMenuTree();
	    
	    return createMenuList(new ArrayList<MenuNode>(), menu);
	}
	
	private static List<MenuNode> createMenuList(List<MenuNode> menus, MenuNode menu) 
	{
		menus.add(menu);
		
		for(MenuNode child : menu.getChildren())
		{
			createMenuList(menus, child);
		}
		
		return menus;
	}
	
	private static MenuNode createMenuTree(Element element, MenuNode parent) 
	{
		MenuNode menu = new MenuNode();
		menu.setId(UuidUtils.generateUuidHexString());
		menu.setName(element.attributeValue("name"));
		menu.setUrl(element.attributeValue("url"));
		menu.setParent(parent);
		
		if("perm".equals(element.getName()))
		{
			menu.setPerm(true);
		}

		List<Element> elements = element.elements();
		for(Element childElement : elements)
		{
			if("perm".equals(childElement.getName())||"menu".equals(childElement.getName()))
			{
				MenuNode child = createMenuTree(childElement, menu);
				menu.getChildren().add(child);
			}
		}
		
		return menu;
	}
	
	
	private static Document getDocument() 
	{
	    try 
	    {
	    	if(document==null)
	    	{
	    		File configFile = new ClassPathResource(CONFIG_XML_PATH).getFile();
	    		document = new SAXReader().read(configFile);
	    	}
	    	
	    	return document;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
}