package com.zjzcn.controller.admin;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjzcn.auth.UserManager;
import com.zjzcn.controller.BaseController;
import com.zjzcn.helper.config.MenuNode;
import com.zjzcn.util.Captcha;

/**
 * @classDescription:登陆Action
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Controller
@RequestMapping("admin")
public class LoginController extends BaseController {
	@Autowired
	private UserManager userManager;

	/**
	 * 登录
	 * 
	 * @throws IOException
	 */
	@RequestMapping("doLogin")
	public void doLogin(String username, String password, String captcha, boolean rememberMe,
			HttpServletRequest request, Writer writer) throws IOException {
		String captchaSession = (String) request.getSession().getAttribute(Captcha.SESSION_CAPTCHA);
		if (!captcha.equalsIgnoreCase(captchaSession)) {
			writer.write("1");
			return;
		}
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
			currentUser.login(token);
			writer.write("0");// 验证通过
		} catch (UnknownAccountException e) {// 用户名错误
			writer.write("2");
		} catch (DisabledAccountException e) {// 用户被禁用
			writer.write("3");
		} catch (IncorrectCredentialsException e) {// 密码错误
			writer.write("4");
		} catch (AuthenticationException e) {// 未知错误
			e.printStackTrace();
			writer.write("5");
		}
	}

	/**
	 * 登陆页
	 */
	@RequestMapping("login")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("main")
	public String main(ModelMap model) {
		MenuNode menu = userManager.getCurrentMenuTree();
		model.addAttribute("menus", menu.getChildren());
		return "admin/main";
	}

	@RequestMapping("unauthz")
	public String unauthz() {
		return "admin/common/unauthz";
	}

	@RequestMapping("overview")
	public String index() {
		return "admin/overview";
	}

	/**
	 * 退出系统
	 */
	@RequestMapping("logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:login.do";
	}

	/**
	 * 产生图片验证码
	 */
	@RequestMapping("captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		Captcha captcha = new Captcha();

		request.getSession().setAttribute(Captcha.SESSION_CAPTCHA, captcha.getCaptchaString());

		captcha.outputCaptcha(response.getOutputStream());
	}

}
