package com.zjzcn.action.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @classDescription:登陆Action
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Controller
public class IndexAction
{
    /**
     * 主页
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request,HttpServletResponse response, ModelMap model)
    {
    	return "portal/index";
    }
    
    @RequestMapping("about")
    public String about(HttpServletRequest request,HttpServletResponse response, ModelMap model)
    {
    	return "portal/about";
    }   
}
