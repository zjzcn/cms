package com.zjzcn.controller.admin;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjzcn.Constants;
import com.zjzcn.controller.BaseAction;
import com.zjzcn.entity.Log;
import com.zjzcn.helper.query.Page;
import com.zjzcn.helper.query.QueryFilter;
import com.zjzcn.service.LogService;
import com.zjzcn.util.StringUtils;

/**
 * @classDescription:日志管理Action
 * @author:zhangjz
 * @createTime:2013-7-8
 */
@Controller
@RequestMapping("admin")
public class LogController extends BaseAction {
	@Autowired
	private LogService logService;

	/**
	 * 查找所有的日志
	 */
	@RequestMapping("log_list")
	public String list(Log log, Page<Log> pageBean, HttpServletRequest request, ModelMap model) {
		QueryFilter filter = QueryFilter.newFilter();
		filter.likeAnywhere("username", log.getUsername());
		filter.likeAnywhere("ip", log.getIp());
		filter.likeAnywhere("operation", log.getName());
		filter.eq("logType", log.getLogType());
		filter.gt("createTime", log.getStartTime());
		if (StringUtils.isNotBlank(log.getEndTime())) {
			filter.lt("createTime", log.getEndTime() + " 23:59:59");
		}

		filter.page(pageBean);
		filter.orderByDesc("createTime");

		// 查询所有权限，并放入会话
		pageBean = logService.findPageByFilter(filter);

		model.addAttribute("log", log);
		model.addAttribute("pageBean", pageBean);

		return "admin/log/log_list";
	}

	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	@RequestMapping("log_delete")
	public void delete(Long id, Writer writer) throws IOException {
		logService.deleteById(id);
		writer.write(Constants.OK_FLAG);
	}
	// @RequestMapping("log_delete")
	// @ResponseBody
	// public JsonVo<Log> delete(Long id) throws IOException {
	// logService.deleteById(id);
	// JsonVo<Log> vo = new JsonVo<Log>();
	// vo.setSuccess(true);
	// vo.setMessage("修改成功!");
	// return vo;
	// }
}
