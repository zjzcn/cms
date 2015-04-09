<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="Author" content="kudychen@gmail.com" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>网站后台登陆</title>
    <#include "/admin/include/common.ftl"/>
    
<script type="text/javascript" >
	$(document).ready(function(){
	   $('#pForm').valid();
	});
	
	function saveInfo(){
	       var options = { success: function(data) {
	            if(data == "ok")
	            {
	            	frameElement.api.opener.editCallback();
	            }
	            else
	            {
	                Pop.error(data);
	            }
	        }};
	        
	        $('#pForm').check(function(){
	        	$('#pForm').ajaxSubmit(options);
	        });
	}
</script>
</head>
<body>
<div style="margin:10px">
	<table class="field-box">
		<form id="pForm" action='${ctx}/admin/user_update.do' method="post">
		<input type="hidden" name="id" value="${user.id}"/>
		<tr class="box-title">
			<th colspan="50">
			<span class="edit-icon">用户信息</span>
			</th>
		</tr>
		<tr>
		   <th width="150px"><span class="required">*</span>登录名：</th>
		   <td>
		      <input type="text" name="username" disabled='true' value="${user.username}"/>
		   </td>
		</tr>
		<tr>
		   <th><span class="required">*</span>姓名：</th>
		   <td>
		      <input type="text" name="name" value="${user.name}" valid="{required:true,regex:'cn_en',rangelength:[2,10]}"/>
		   </td>
		</tr>
		<tr>
		   <th><span class="required">*</span>Email：</th>
		   <td>
		      <input type="text" name="email" value="${user.email}" valid="{required:true,regex:'email',maxlength:50}"/>
		   </td>
		</tr>
		<tr>
		   <th>性别：</th>
		   <td>
		      <select name="gender">
				<option value="">---请选择---</option>
				<option value="0" <#if (((user.gender)!-1)==0)>selected</#if>>女</option>
				<option value="1" <#if (((user.gender)!-1)==1)>selected</#if>>男</option>
			</select>
		   </td>			         
		</tr>  

		<tr>
		   <th><span class="required">*</span>用户状态：</th>
		   <td>
		      <select name="isDisabled" valid="{required:true}">
				<option value="">---请选择---</option>
				<option value="0" <#if ((user.isDisabled!-1)==0)>selected="true"</#if>>正常</option>
				<option value="1" <#if ((user.isDisabled!-1)==1)>selected="true"</#if>>停用</option>
			  </select>
		   </td>
		</tr> 
        <tr>
	        <td colspan="2" style="text-align: center;">
	            <input type="button" value="保存" class="btn mr10" onclick="saveInfo()"/>
	            <input type="button" value="返回" class="btn" onclick="frameElement.api.close();"/>
	        </td>
        </tr>		          		            		          		           
		</form>
	</table> 	
</div>
</body>
</html>