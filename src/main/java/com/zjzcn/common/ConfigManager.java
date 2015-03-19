/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.zjzcn.common;

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

import com.zjzcn.bean.LogConfig;
import com.zjzcn.bean.Menu;
import com.zjzcn.util.UuidUtils;

/**
 * 配置管理
 * 
 * @author zjzcn Team
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ConfigManager
{
    private static final String CONFIG_XML_PATH = "/config.xml";
    
    private static Document document;
    
	public static Map<String, LogConfig> getLogConfigs() 
	{
		List<Element> elements = getDocument().selectNodes("/config//log | /config//perm[@log='true']");
	    Map<String, LogConfig> logConfigs = new HashMap<String, LogConfig>();
	    for (Element element : elements)
	    {
	        String name = element.attributeValue("name");
	        String url = element.attributeValue("url");
	        LogConfig logConfig = new LogConfig();
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
	
	public static Menu getMenuTree() 
	{
	    Node node = getDocument().selectSingleNode("/config/menu");
	    if(node instanceof Element)
	    {
	    	return createMenuTree((Element)node, null);
	    }
	    
	    return null;
	}
	
	public static List<Menu> getMenuList() 
	{
	    Menu menu = getMenuTree();
	    
	    return createMenuList(new ArrayList<Menu>(), menu);
	}
	
	private static List<Menu> createMenuList(List<Menu> menus, Menu menu) 
	{
		menus.add(menu);
		
		for(Menu child : menu.getChildren())
		{
			createMenuList(menus, child);
		}
		
		return menus;
	}
	
	private static Menu createMenuTree(Element element, Menu parent) 
	{
		Menu menu = new Menu();
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
				Menu child = createMenuTree(childElement, menu);
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