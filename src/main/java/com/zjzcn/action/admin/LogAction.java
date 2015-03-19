package com.zjzcn.action.admin;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjzcn.Constants;
import com.zjzcn.entity.Log;
import com.zjzcn.service.LogService;
import com.zjzcn.util.StringUtils;
import com.zjzcn.util.query.Condition;
import com.zjzcn.util.query.PageBean;

/**
 * @classDescription:日志管理Action
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Controller
@RequestMapping("admin")
public class LogAction
{
    @Autowired
    private LogService logService;
    
    /**
     * 查找所有的日志
     */
    @RequestMapping("log_list")
    public String list(Log log, PageBean<Log> pageBean, HttpServletRequest request, ModelMap model)
    {
        Condition cond = new Condition();
        cond.likeAnywhere("username", log.getUsername());
        cond.likeAnywhere("ip", log.getIp());
        cond.likeAnywhere("operation", log.getName());
        cond.eq("logType", log.getLogType());
        cond.gt("createTime", log.getStartTime());
        if(StringUtils.isNotBlank(log.getEndTime())){
            cond.lt("createTime", log.getEndTime()+" 23:59:59");
        }
               
        cond.page(pageBean);
        cond.orderByDesc("createTime");
        
        //查询所有权限，并放入会话
        pageBean = logService.findPageByCond(cond);
        
        model.addAttribute("log", log);
        model.addAttribute("pageBean", pageBean);

        return "admin/log/log_list";
    }
    
    /**
     * 删除
     * @throws IOException 
     */
    @RequestMapping("log_delete")
    public void delete(Long id, Writer writer) throws IOException
    {
    	logService.deleteById(id);
    	writer.write(Constants.OK_FLAG);
    }
}
