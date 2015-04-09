<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="dialog-bg">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>网站后台登陆</title>
    <#include "/admin/include/common.ftl"/>
<style>
table th{ height:40px; text-align:right; }
input[type="password"] {width:180px; padding:4px;}
#login-tip{color:red}
</style>

<script>
    function loginSubmit()
    {
    	$("#login-tip").html('');
    	$("#login-tip").attr('class','');
    	
    	var name=$.trim($("#password").val());
    	if(name.length==0){
    		$("#login-tip").addClass("login-error");
    		$("#login-tip").html("请输入旧密码");
    		$("#password")[0].focus();
    		return;
    	}
    	var pwd=$.trim($("#newpassword").val());
    	if(pwd.length==0){
    		$("#login-tip").addClass("login-error");
    		$("#login-tip").html("请输入新密码");
    		$("#newpassword")[0].focus();
    		return;
    	}

    	var code=$.trim($("#repassword").val());
    	if(code.length==0){
    		$("#login-tip").addClass("login-error");
    		$("#login-tip").html("请再次输入新密码");
    		$("#repassword")[0].focus();
    		return;
    	}
    	else if(code!==pwd){
    		$("#login-tip").addClass("login-error");
    		$("#login-tip").html("两次输入的新密码不一致");
    		$("#repassword")[0].focus();
    		return;
    	}
    	
    	$("#login-tip").addClass("login-loading");
    	$("#login-tip").html("正在修改密码。。。");
    	$.ajax({
			type: "POST",
			async: true,
			cache:false,
			url: "${ctx}/admin/self_doChangePwd.do",
			data: {password:name,newpassword:pwd},
			dataType: "text",
			success: function(data){
				$("#login-tip").html('');
				$("#login-tip").attr('class','');
				if(data=="ok"){
					parent.showMsg('ok','密码修改成功！');
					frameElement.api.close();
				}
				else
				{
					$("#login-tip").addClass("login-error");
					$("#login-tip").html("旧密码输入错误");
				}
			},
			error: function(data){
				$("#login-tip").html('');
				$("#login-tip").attr('class','');
				$("#login-tip").addClass("login-error");
				$("#login-tip").html("系统出错");
			}
		});
    }
</script>
</head>
<body>
<div style="padding:20px">
	<table>
	<form method="post">
	<tr>
		<th>旧密码：</th>
		<td><input id="password" name="password" type="password"></td>
	</tr>
	<tr>
		<th>新密码：</th>
		<td><input id="newpassword" name="newpassword" type="password"></td>
	</tr>	
	<tr>
		<th>再次输入新密码：</th>
		<td><input id="repassword" name="repassword" type="password"></td>
	</tr>
	<tr>
		<td></td>
		<td><span id="login-tip"></span></td>
	</tr>
	<tr>
		<th></th>
	    <td>
	        <input type="button" value="保存" class="btn mr20" onclick="loginSubmit();"/>
	        <input type="button" value="返回" class="btn" onclick="frameElement.api.close();"/>
	    </td>
	</tr>
	</form>
	</table>
</div>
</body>
