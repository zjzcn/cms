package com.zjzcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zjzcn.auth.UserManager;
import com.zjzcn.entity.Log;
import com.zjzcn.service.LogService;
import com.zjzcn.service.UserService;
import com.zjzcn.util.DateUtils;

public class WebExceptionResolver implements HandlerExceptionResolver
{
    private Logger logger;
    
    @Autowired
	private LogService logService;
	@Autowired
	private UserManager userService;
    
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,  Exception e)
    {
        logger = LoggerFactory.getLogger(Object.class);
        
        String errorCode = DateUtils.getCurrentTime("yyyyMMddHHmmssSSS");

        logger.error("errorCode:"+errorCode, e);
        
		Log log = new Log();
		log.setLogType(1);
		log.setName("系统异常");
		log.setContent("异常码:"+errorCode);
		log.setUsername(userService.getUsername());
		log.setCreateTime(DateUtils.getCurrentTime(null));
		log.setIp(request.getRemoteAddr());
		logService.save(log);
        
        ModelAndView mv=new ModelAndView("admin/common/error");
        mv.addObject("errorCode", errorCode);
        return mv;
    }
}
