package com.zjzcn.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @classDescription:登陆Action
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Controller
@RequestMapping("portal")
public class IndexController {
	/**
	 * 主页
	 */
	@RequestMapping("index")
	public String index() {
		return "portal/index";
	}

	@RequestMapping("about")
	public String about() {
		return "portal/about";
	}
}
