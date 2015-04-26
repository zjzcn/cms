<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统管理</title>
	<#include "/admin/include/common.ftl"/>  
</head>
<body>
<div class="swap">
	<table class="list-box">
		<form id="pForm" method="post" action="${ctx}/admin/log_list.do">	
		<input type="hidden" name="pageNo" value="${pageBean.pageNo}"/>
		<input type="hidden" name="orderProperty" value="${pageBean.orderProperty}"/>
		<input type="hidden" name="orderDirection" value="${pageBean.orderDirection}"/>
		<tr>
			<td colspan="20" class="query">
			    <span>用户名：</span>
			    <input type="text" size="12"  name="username" value="${log.username}" class="mr20"/>
			    <span>用户IP：</span>
			    <input type="text" size="12"  name="ip" value="${log.ip}" class="mr20"/>			    
			    <span>日志名称：</span>
			    <input type="text" size="12"  name="operation" value="${log.name}" class="mr20"/>
			    <span>记录时间：</span>
				<input type="text" size="12" class="Wdate" name="startTime" value="${log.startTime}" onclick="WdatePicker()" readonly/>到
				<input type="text" size="12" class="Wdate mr20" name="endTime" value="${log.endTime}" onclick="WdatePicker()" readonly/>	
				<!--<span>日志类型：</span>
				<select name="logType" class="mr20">
					<option value="">---请选择---</option>
					<option value="0" <#if ((log.logType!-1)==0)>selected</#if>>操作日志</option>
					<option value="1" <#if ((log.logType!-1)==1)>selected</#if>>异常日志</option>
				</select>-->
						    
				<input type="submit" value="查询" class="btn mr10"/>
				<input type="button" value="清空" class="btn" onclick="resetForm(this.form.id);"/>
	  		</td>
		</tr>
		<tr class="box-title">
			<th colspan="20">
				<span class="list-icon fl">查询列表</span>
	  		</th>
		</tr>
		<tr>
			<th style="width:50px">序号</th>
			<th orderby="username">登录名</th>
			<th orderby="ip">用户IP</th>
			<th orderby="name">日志名称</th>
			<!--<th>内容</th>-->
			<th orderby="createTime">记录时间</th>
			<!--<th>日志类型</th>-->
			<th style="width:80px">操作</th>
		</tr>
		<#list pageBean.resultList as log>
		<tr <#if (log_index%2==0)>class='t1'<#else>class='t2'</#if> >
			<td>${(pageBean.pageNo-1)*pageBean.pageSize+log_index+1}</td>
			<td>${log.username}</td>
			<td>${log.ip}</td>
			<td>${log.name}</td>
			<!--<td>${log.content}</td>-->
			<td>${log.createTime}</td>
			<!--<td><#if ((log.logType!-1)==0)!>操作日志<#elseif ((log.logType!-1)==1)!><span style="color:red">异常日志</span></#if></td>-->
			<td>
			 	<@shiro.hasPermission name="log_delete">[<a href="javascript:log_delete('${log.id}');">删除</a>]</@shiro.hasPermission>
			</td>
		</tr>
		</#list> 
		<@m.page formId="pForm" pageNo=pageBean.pageNo pageSize=pageBean.pageSize totalCount=pageBean.totalCount columns=6/>
		</form>
	</table><!-- end table -->

</div>
</body>

<script type="text/javascript">
	function queryList(){
		$("#pForm").submit();
	}

   function logDetail(css_id)
   {
   	var log=$("#log_"+css_id).html();
   	Pop.dialog(log, 1000, 550, "æ¥å¿è¯¦ç»åå®¹", 3000);
   }

	function log_delete(id)
	{
	   	Pop.confirm("确定要删除日志吗?", function() {
			var url = "${ctx}/admin/log_delete.do";
			$.ajax( {
				type : 'get',
				dataType : 'text',
				async : false,
				url : url,
				data : {id: id, ran: Math.random()},
				success : function(data) {
					if (data == 'ok') {
						//parent.showMsg("ok", "删除角色成功！");
						parent.$.msgbox.show({
					        message: "删除角色成功！",
					        icon: "ok",
					        timeOut: 2000
					    });
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