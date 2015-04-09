<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>管理系统</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<link rel="stylesheet" type="text/css" href="${ctx}/resourse/admin/css/login.css"></link>
	<script type="text/javascript" src="${ctx}/resourse/admin/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function () {
	    if(top.location!=self.location)
	    {
	        top.location=self.location;
	    }
	    refreshCaptcha();
	    
		$(document).keypress(function(event) { 
		    var e = event ? event :window.event;  
	        var currKey=e.keyCode||e.which||e.charCode;
	        
	        if(currKey==13){  
	            loginSubmit();  
	        }    
		});
	});
	
	//刷新验证码
	function refreshCaptcha(){
		$('#captcha').val("");
		$('#captcha')[0].focus();
		//注意后面必须要加上随机数 否则浏览器不会向服务器发送请求
		$('#captchaImg').attr('src','${ctx}/admin/captcha.do?r='+Math.random());
	}
	
	//登陆
    function loginSubmit()
    {
    	$("#login-tip").html('');
    	$("#login-tip").attr('class','');
    	
    	var name=$.trim($("#username").val());
    	if(name.length==0){
    		$("#login-tip").addClass("login-error");
    		$("#login-tip").html("请输入用户名");
    		$("#username")[0].focus();
    		return;
    	}
    	var pwd=$.trim($("#password").val());
    	if(pwd.length==0){
    		$("#login-tip").addClass("login-error");
    		$("#login-tip").html("请输入密码");
    		$("#password")[0].focus();
    		return;
    	}

    	var code=$.trim($("#captcha").val());
    	if(code.length==0){
    		$("#login-tip").addClass("login-error");
    		$("#login-tip").html("请输入验证码");
    		$("#captcha")[0].focus();
    		return;
    	}
    	
    	$("#login-tip").addClass("login-loading");
    	$("#login-tip").html("登陆中。。。");
    	$.ajax({
			type: "POST",
			async: true,
			cache:false,
			url: '${ctx}/admin/doLogin.do',
			data: {username:name,password:pwd,captcha:code},
			dataType: "text",
			success: function(data){
				$("#login-tip").html('');
				$("#login-tip").attr('class','');
				if(data=="0"){
					window.location.href='${ctx}/admin/main.do'; 
				}
				else
				{
					$("#login-tip").addClass("login-error");
					if(data=="2")
					{
						$("#username").val("");
						$("#password").val("");
						$("#captcha").val("");
						$("#username")[0].focus();
						$("#login-tip").html("用户名错误");
					}
					else if(data=="4")
					{
						$("#password").val("");
						$("#captcha").val("");
						$("#password")[0].focus();
						$("#login-tip").html("密码错误");
					}
					else if(data=="1")
					{
						$("#captcha").val("");
						$("#captcha")[0].focus();
						$("#login-tip").html("验证码错误");
						refreshCaptcha();
					}
					else if(data=="3")
					{
						$("#login-tip").html("该用户被停用");
					}
					else
					{
						$("#login-tip").html("系统出错");
					}
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
	<div class="login">
		<div class="login_form">
			<div class="form_info">
				<div class="field">
					<label>用户名：</label>
					<input id="username" type="text" class="text" size="20">
				</div>
				<div class="field">
					<label>密　码：</label>
					<input id="password" type="password" class="text" size="20">
				</div>
				<div class="field">
					<label>验证码：</label>
					<input id="captcha" type="text" class="text" size="10">
                    <img id="captchaImg" title="看不清？请点击图片更换！" align="absmiddle" src="${ctx}/admin/captcha.do" onclick="refreshCaptcha();" style="cursor:pointer"/>
				</div>
				<div class="field">
					<span id="login-tip"></span>
				</div>
				<div class="field">
					<label></label>
					<button class="button" style="margin-left:50px;_margin-left:48px" onclick="javascript:loginSubmit();"></button>
				</div>
			</div>
		</div>
	</div>
</body>
