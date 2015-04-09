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
		<form id="pForm" method="post" action="${ctx}/admin/role_list.do">	
		<input type="hidden" name="pageNo" value="${pageBean.pageNo}"/>
		<input type="hidden" name="orderProperty" value="${pageBean.orderProperty}"/>
		<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
		<tr>
			<td colspan="20" class="query">
			    <span>角色名称：</span>
			    <input type="text" size="12" name="name" value="${role.name}" class="mr20"/>
				<span>角色类型：</span>
				<select name="isSuper" class="mr20">
					<option value="">---请选择---</option>
					<option value="0" <#if ((role.isSuper!-1)==0)>selected="true"</#if>>普通角色</option>
					<option value="1" <#if ((role.isSuper!-1)==1)>selected="true"</#if>>超级角色</option>
				</select>
				<input type="submit" value="查询" class="btn mr10"/>
				<input type="button" value="清空" class="btn" onclick="resetForm(this.form.id);"/>
	  		</td>
		</tr>
		<tr class="box-title">
			<th colspan="20">
				<span class="list-icon fl">查询列表</span>
				<span  class="fr">
				<@shiro.hasPermission name="role_add"><a href="javascript:role_add();">【新增角色】</a></@shiro.hasPermission>
				</span>
	  		</th>
		</tr>		
		<tr>
			<th style="width:50px">序号</th>
			<th orderby="name">角色名称
			<!--
			<div class="popupMenu" style="left: 220px; top: 59px; display: none;">
				<ul id="pageSizeOption">
					<li>
						<a href="javascript:;" val="asc">升序</a>
					</li>
					<li>
						<a href="javascript:;" class="current" val="desc">降序</a>
					</li>
				</ul>
			</div>-->
			</th>
			<th orderby="remark">角色描述</th>
			<th orderby="isSuper">角色类型</th>
			<th style="width:120px">操作</th>
		</tr>
		<#list pageBean.list as role>
		<tr <#if (role_index%2==0)>class='t1'<#else>class='t2'</#if>>
			<td>${(pageBean.pageNo-1)*pageBean.pageSize+role_index+1}</td>
			<td>${role.name}</td>
			<td>${role.remark}</td>
			<td><#if ((role.isSuper!-1)==0)!>普通角色<#elseif ((role.isSuper!-1)==1)!><span style="color:red">超级角色</span></#if></td>
			<td>
			<@shiro.hasPermission name="role_edit">[<a href="javascript:role_edit(${role.id});">修改</a>]</@shiro.hasPermission>
			<@shiro.hasPermission name="role_delete">[<a href="javascript:role_delete(${role.id});">删除</a>]</@shiro.hasPermission>
			</td>
		</tr>
		</#list>
		<@m.page formId="pForm" pageNo=pageBean.pageNo pageSize=pageBean.pageSize totalCount=pageBean.totalCount columns=5/>
		</form>		
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
	
	function queryList()
	{
		$("#pForm").submit();
	}
	
	function addCallback()
	{
		parent.showMsg("ok", "新增角色成功！");
		queryList();
	}
	
	function editCallback()
	{
		parent.showMsg("ok", "修改角色成功！");
		queryList();
	}
		
	function role_add()
	{
		Pop.window("${ctx}/admin/role_add.do", 800, 500, "新增角色", 3000);
	}
	
	function role_edit(id)
	{
		Pop.window("${ctx}/admin/role_edit.do?id="+id, 800, 500, "修改角色", 3000);
	}
	
	function role_delete(id)
	{
	   	Pop.confirm("确定要删除这些记录吗?", function() {
			var url = "${ctx}/admin/role_delete.do";
			$.ajax( {
				type : 'GET',
				dataType : 'text',
				async : false,
				url : url,
				data : {id: id, ran: Math.random()},
				success : function(data) {
					if (data == 'ok') {
						parent.showMsg("ok", "删除角色成功！");
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