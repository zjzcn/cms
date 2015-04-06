package com.zjzcn.helper.config;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @classDescription:菜单类
 * @author zhangjz
 * @createTime:2010-7-5
 */
public class MenuNode implements Serializable
{
	private static final long serialVersionUID = 5811074799930799430L;

	private String id;
    
    private String name;//动作名称（例如增加，删除）
    
    private String url;//相对于主目录的路径

    private MenuNode parent;
    
    private boolean perm;

    private List<MenuNode> children = new LinkedList<MenuNode>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuNode getParent() {
		return parent;
	}

	public void setParent(MenuNode parent) {
		this.parent = parent;
	}

	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public boolean isPerm() {
		return perm;
	}

	public void setPerm(boolean perm) {
		this.perm = perm;
	}

	public static void main(String[] args)
    {
        Integer a=1,b=1;
        System.out.println(a==b);//true
        
        MenuNode menu = new MenuNode();
        menu.getChildren().add(new MenuNode());
        for(MenuNode m : menu.getChildren())
        {
        	menu.getChildren().remove(m);
        }
    }
}