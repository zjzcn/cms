package com.zjzcn.controller.admin;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjzcn.Constants;
import com.zjzcn.auth.UserManager;
import com.zjzcn.controller.BaseAction;
import com.zjzcn.entity.Role;
import com.zjzcn.entity.User;
import com.zjzcn.helper.query.Page;
import com.zjzcn.helper.query.QueryFilter;
import com.zjzcn.service.RoleService;
import com.zjzcn.service.UserService;
import com.zjzcn.util.DateUtils;
import com.zjzcn.util.DigestUtils;
import com.zjzcn.util.StringUtils;

/**
 * @className:UserManageAction.java
 * @classDescription:用户管理Action
 * @author:zhangjz
 * @createTime:2010-7-8
 */
@Controller
@RequestMapping("admin")
public class UserController extends BaseAction {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserManager userManager;

	/**
	 * 查找所有的人员
	 */
	@RequestMapping("user_list")
	public String user_list(User user, Page<User> pageBean, HttpServletRequest request, ModelMap model) {
		QueryFilter filter = QueryFilter.newFilter();
		filter.likeAnywhere("username", user.getUsername());
		filter.likeAnywhere("name", user.getName());
		filter.eq("gender", user.getGender());
		filter.likeAnywhere("email", user.getEmail());
		filter.eq("mobile", user.getMobile());
		filter.eq("isDisabled", user.getIsDisabled());
		filter.gt("createTime", user.getStartTime());
		if (StringUtils.isNotBlank(user.getEndTime())) {
			filter.lt("createTime", user.getEndTime() + " 23:59:59");
		}
		filter.page(pageBean);

		// 查询所有权限，并放入会话
		pageBean = userService.findPageByFilter(filter);

		model.addAttribute("pageBean", pageBean);
		model.addAttribute("user", user);
		return "admin/user/user_list";
	}

	/**
	 * 去增加用户页面
	 */
	@RequestMapping("user_add")
	public String user_add(HttpServletRequest request, ModelMap model) {
		return "admin/user/user_add";
	}

	/**
	 * 保存用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping("user_save")
	public void user_save(User user, Writer writer) throws IOException {
		user.setPassword(DigestUtils.md5Hex("123456"));
		user.setCreateTime(DateUtils.getCurrentDateTime());

		userService.save(user);
		writer.write(Constants.OK_FLAG);
	}

	/**
	 * 去修改用户页面
	 */
	@RequestMapping("user_edit")
	public String user_edit(Long id, HttpServletRequest request, ModelMap model) {
		User user = userService.findById(id);

		model.addAttribute("user", user);

		return "admin/user/user_edit";
	}

	/**
	 * 修改用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping("user_update")
	public void user_update(User user, Writer writer) throws IOException {
		User quser = userService.findById(user.getId());

		quser.setName(user.getName());
		quser.setEmail(user.getEmail());
		quser.setGender(user.getGender());
		quser.setIsDisabled(user.getIsDisabled());

		userService.update(quser);
		writer.write(Constants.OK_FLAG);
	}

	/**
	 * 修改用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping("user_exist")
	public void user_exist(String username, Writer writer) throws IOException {
		QueryFilter filter = QueryFilter.newFilter();
		filter.eq("username", username);
		User user = userService.findByFilter(filter);
		if (user != null) {
			writer.write(Constants.ERROR_FLAG);
		}

		writer.write(Constants.OK_FLAG);
	}

	/**
	 * 删除用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping("user_delete")
	public void user_delete(Long id, Writer writer) throws IOException {
		userService.deleteById(id);
		writer.write(Constants.OK_FLAG);
	}

	@RequestMapping("user_resetPwd")
	public void user_resetPwd(Long id, Writer writer) throws IOException {
		User user = userService.findById(id);
		;

		user.setPassword(DigestUtils.md5Hex("123456"));
		userService.update(user);

		writer.write(Constants.OK_FLAG);
	}

	/**
	 * 角色列表
	 * 
	 * @return 页面跳转标志
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("user_allocRole")
	public String user_allocRole(Long userId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		User user = userService.findById(userId);
		Set<Long> roleIds = new HashSet<Long>();
		for (Role role : user.getRoles()) {
			roleIds.add(role.getId());
		}

		QueryFilter filter = QueryFilter.newFilter();
		List<Role> roles = roleService.findListByFilter(filter);

		model.addAttribute("userId", userId);
		model.addAttribute("roles", roles);
		model.addAttribute("roleIds", roleIds);

		return "admin/user/user_allocRole";
	}

	/**
	 * 设置员工的角色 <功能详细描述>
	 * 
	 * @throws IOException
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("user_doAllocRole")
	public void user_doAllocRole(Long userId, String roleIds, Writer writer) throws IOException {
		User user = userService.findById(userId);

		if (StringUtils.isNotBlank(roleIds)) {
			QueryFilter filter = QueryFilter.newFilter();

			String[] arr = roleIds.split(",");
			Long[] ids = new Long[arr.length];
			for (int i = 0; i < arr.length; i++) {
				ids[i] = Long.parseLong(arr[i]);
			}

			filter.in("id", ids);

			List<Role> roles = roleService.findListByFilter(filter);

			user.setRoles(new HashSet<Role>(roles));
		} else {
			user.setRoles(null);
		}

		userService.update(user);

		writer.write(Constants.OK_FLAG);
	}

	/**
	 * 去修改用户页面
	 */
	@RequestMapping("self_changeInfo")
	public String self_edit(HttpServletRequest request, ModelMap model) {
		QueryFilter filter = QueryFilter.newFilter();
		filter.eq("username", userManager.getCurrentUsername());
		User user = userService.findByFilter(filter);

		model.addAttribute("user", user);
		return "admin/user/self_changeInfo";
	}

	@RequestMapping("self_doChangeInfo")
	public void self_update(User user, Writer writer) throws IOException {
		User quser = userService.findById(user.getId());

		if (StringUtils.isNotBlank(user.getPassword())) {
			quser.setPassword(DigestUtils.md5Hex(user.getPassword()));
		}
		quser.setName(user.getName());
		quser.setEmail(user.getEmail());
		quser.setGender(user.getGender());

		userService.update(quser);

		writer.write(Constants.OK_FLAG);
	}

	@RequestMapping("self_changePwd")
	public String self_changePwd(String password, String newpassword, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "admin/user/self_changePwd";
	}

	@RequestMapping("self_doChangePwd")
	public void self_doChangePwd(String password, String newpassword, Writer writer) throws IOException {
		User user = userManager.getCurrentUser();
		if (!user.getPassword().equals(DigestUtils.md5Hex(password))) {
			user.setPassword(DigestUtils.md5Hex(newpassword));
			userService.update(user);
			writer.write(Constants.ERROR_FLAG);
			return;
		}

		writer.write(Constants.OK_FLAG);
	}

}
