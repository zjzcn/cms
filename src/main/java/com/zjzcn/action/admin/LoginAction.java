package com.zjzcn.action.admin;

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

import com.zjzcn.bean.Menu;
import com.zjzcn.service.UserService;
import com.zjzcn.util.Captcha;

/**
 * @classDescription:登陆Action
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Controller
@RequestMapping("admin")
public class LoginAction
{
    @Autowired
    private UserService userService;
    
    /**
     * 登录
     * @throws IOException 
     */
    @RequestMapping("doLogin")
    public void doLogin(String username, String password, String captcha, boolean rememberMe, HttpServletRequest request, Writer writer) throws IOException{
        String captchaSession = (String)request.getSession().getAttribute(Captcha.CAPTCHA_SESSION);
        if(!captcha.equalsIgnoreCase(captchaSession)){
            writer.write("1");
            return;
        }
        try {
            Subject currentUser = SecurityUtils.getSubject(); 
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            currentUser.login(token); 
            writer.write("0");//验证通过
        } catch ( UnknownAccountException e ) {//用户名错误
        	writer.write("2");
        } catch ( DisabledAccountException e ) {//用户被禁用
        	writer.write("3");
        } catch ( IncorrectCredentialsException e ) {//密码错误
        	writer.write("4");
        } catch ( AuthenticationException e ) {//未知错误
        	writer.write("5");
        	e.printStackTrace();
        } 
    }
    
    /**
     * 登陆页
     */
    @RequestMapping("login")
    public String login( ModelMap model){
    	//System.out.println(SpringUtils.getBean("servletContext", ServletContext.class).getContextPath());
        return "admin/login";
    }
 
    @RequestMapping("main")
    public String main( ModelMap model){
    	Menu menu = userService.getCurrentMenuTree();
    	model.addAttribute("menus", menu.getChildren());
        return "admin/main";
    }  
    
    @RequestMapping("unauthz")
    public String unauthz( ModelMap model){
        return "admin/common/unauthz";
    }  
    
    @RequestMapping("index")
    public String index( ModelMap model){
        return "admin/index";
    }    
    /**
     * 退出系统
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request, ModelMap model){
        Subject currentUser = SecurityUtils.getSubject(); 
        currentUser.logout();
        return "redirect:login.do";
    }

    /**
     * 产生图片验证码
     */
    @RequestMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException{
        Captcha captcha=new Captcha();
        
        request.getSession().setAttribute(Captcha.CAPTCHA_SESSION, captcha.getCaptchaString());
        
        captcha.outputCaptcha(response.getOutputStream());
    }
    
}
