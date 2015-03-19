/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.zjzcn.bean;

import java.io.Serializable;

/**
 * 日志配置
 * 
 * @author zjzcn Team
 * @version 3.0
 */
public class LogConfig implements Serializable {

	private static final long serialVersionUID = -1108848647938408402L;

	/** 操作名称 */
	private String name;

	/** 请求URL */
	private String url;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}