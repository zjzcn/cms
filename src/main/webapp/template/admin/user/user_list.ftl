<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>网站后台登陆</title>
    
    <#include "/admin/include/common.ftl"/>
</head>
<body>
<div class="swap">
	<table class="list-box">
		<form id="pForm" method="post" action="${ctx}/admin/user_list.do">	
		<input type="hidden" name="pageNo" value="${pageBean.pageNo}"/>
		<input type="hidden" name="orderProperty" value="${pageBean.orderProperty}"/>
		<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
		<tr>
			<td colspan="20"  class="query">
			    <span>登录名：</span>
			    <input type="text" size="12" name="username" value="${user.username}" class="mr20"/>
				<span>姓名：</span>
				<input type="text" size="12" name="name" value="${user.name}" class="mr20"/>
				<span>Email：</span>
				<input type="text" size="12" name="email" value="${user.email}" class="mr20"/>
				<span>性别：</span>
				<select name="gender" class="mr20">
					<option value="">---请选择---</option>
					<option value="0" <#if (user.gender==0)>selected</#if>>女</option>
					<option value="1" <#if (user.gender==1)>selected</#if>>男</option>
				</select>
				<span>用户状态：</span>
				<select name="isDisabled" class="mr20">
					<option value="">---请选择---</option>
					<option value="0" <#if (user.isDisabled==0)>selected</#if>>正常</option>
					<option value="1" <#if (user.isDisabled==1)>selected</#if>>停用</option>
				</select>
				<input type="submit" value="查询" class="btn mr10"/>
				<input type="button" value="清空" class="btn" onclick="resetForm(this.form.id);"/>
	  		</td>
		</tr>
		<tr class="box-title">
			<th colspan="20">
				<span class="list-icon fl">查询列表</span>
				<span  class="fr">
				<@shiro.hasPermission name="user_add"><a href="javascript:toAdd()">【新增用户】</a></@shiro.hasPermission>
				</span>
	  		</th>
		</tr>	
		<tr>
			<th style="width:50px">序号</th>
			<th orderby="username">登录名</th>
			<th orderby="name">姓名</th>
			<th orderby="gender">性别</th>
			<th orderby="email">Email</th>
			<th orderby="mobile">手机号</th>
			<th orderby="createTime">创建时间</th>
			<th orderby="isDisabled">用户状态</th>
			<th style="width:200px">操作</th>
		</tr>
		<#list pageBean.list as user>
		<tr <#if (user_index%2==0)>class='t1'<#else>class='t2'</#if>>
			<td>${(pageBean.pageNo-1)*pageBean.pageSize+user_index+1}</td>
			<td>${user.username}</td>
			<td>${user.name}</td>
			<td><#if ((user.gender!-1)==0)>女</#if><#if ((user.gender!-1)==1)>男</#if></td>
			<td>${user.email}</td>
			<td>${user.mobile}</td>
			<td>${user.createTime}</td>
			<td><#if ((user.isDisabled!-1)==0)>正常<#elseif ((user.isDisabled!-1)==1)><span style="color:red">停用</span></#if></td>
			<td>
				<@shiro.hasPermission name="user_edit">[<a href="javascript:user_edit(${user.id});">修改</a>]</@shiro.hasPermission>
				<@shiro.hasPermission name="user_allocRole">[<a href="javascript:user_selectRole(${user.id});">设置角色</a>]</@shiro.hasPermission>
				<@shiro.hasPermission name="user_resetPwd">[<a href="javascript:user_resetPwd(${user.id})">重置密码</a>]</@shiro.hasPermission>
				<@shiro.hasPermission name="user_delete">[<a href="javascript:del(${user.id});">删除</a>]</@shiro.hasPermission>
			</td>
		</tr>
		</#list>
		<@m.page formId="pForm" pageNo=pageBean.pageNo pageSize=pageBean.pageSize totalCount=pageBean.totalCount columns=9/>
	</table><!-- end table -->

</div>
</body>

<script type="text/javascript">
	$(function(){
		$(".list-box th[name]").click(function(){
			$('input[name="orderProperty"]').val($(this).attr('name'));
			var order;
			if($(this).attr('class')=='asc')
			{
				order='desc';
			}
			else
			{
				order='asc';
			}
			$('input[name="orderDirection"]').val(order);
			$("#pForm").submit();
		});
		/*
		$(".list-box th[name]").click(function() {
			var $this = $(this);
			var offset = $this.offset();
			//var $menuWrap = $this.closest("div.menuWrap");
			//var $popupMenu = $menuWrap.children("div.popupMenu");
			var $popupMenu = $this.children("div.popupMenu");
			$popupMenu.css({left: offset.left+$this.width()-$popupMenu.width()+10, top: offset.top + $this.height() + 2}).show();
			$this.mouseleave(function() {
				$popupMenu.hide();
			});
		});*/
	});
	
	function queryList(){
		$("#pForm").submit();
	}
	
		
	function addCallback()
	{
		parent.showMsg("ok", "新增成功！");
		queryList();
	}
	
	function editCallback()
	{
		parent.showMsg("ok", "修改成功！");
		queryList();
	}
	
   function toAdd()
   {
   	Pop.window("${ctx}/admin/user_add.do", 700, 420, "新增用户", 3000);
   }

   function user_edit(id)
   {
   	Pop.window("${ctx}/admin/user_edit.do?id="+id, 700, 420, "修改用户", 3000);
   }
   
   function user_selectRole(id)
   {
   	Pop.window("${ctx}/admin/user_allocRole.do?userId="+id, 700, 420, "角色设置", 3000);
   }
   
   function user_resetPwd(id)
   {
   		Pop.confirm("确定要重置该用户的密码吗?", function() {
			var url = "${ctx}/admin/user_resetPwd.do";
			$.ajax( {
				type : 'get',
				dataType : 'text',
				async : false,
				url : url,
				data : {id: id, ran: Math.random()},
				success : function(data) {
					if (data == 'ok') {
						parent.showMsg("ok", "删除用户成功！");
						queryList();
					} else {
						Pop.alert('error', '删除失败！');
					}
				}
			});
		
			}, function() {
	
		});
   }
   
   function del(id)
   {
	   	Pop.confirm("确定要删除这些记录吗?", function() {
			var url = "${ctx}/admin/user_delete.do";
			$.ajax( {
				type : 'get',
				dataType : 'text',
				async : false,
				url : url,
				data : {id: id, ran: Math.random()},
				success : function(data) {
					if (data == 'ok') {
						parent.showMsg("ok", "删除用户成功！");
						queryList();
					} else {
						Pop.alert('error', '删除失败！');
					}
				}
			});
		
			}, function() {
		});
   } 
</script>
</html>