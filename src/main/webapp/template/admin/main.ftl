<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站内容管理系统</title>
<#include "/admin/include/common.ftl"/>
<script type="text/javascript">
	//初始化窗口大小
	$(function () {
	    resetLayout();
	    $(window).resize(function()
		{
			resetLayout();
		});

		function resetLayout()
		{
			/**/	
			var window_height = $(window).height();
			var center_height = window_height-68;
			$(".center").height(center_height);
		    $("#main").height(center_height-36);
			
			var window_width = $(window).width(); //浏览器时下窗口可视区域宽度
			var left_width = $(".left").width();
			var switch_width = $("#side_switch").width();
			var right_width = window_width - left_width - switch_width;
			$(".right").width(right_width);
		}
	});
	
	//菜单初始化
	$(function(){ 
		//头部菜单	 	
		$('.top_nav a').click(function(){
			$('.top_nav a').removeClass('selected');
			$(this).addClass('selected');
		});
		//侧面一级菜单
		$('.left_title:first').addClass('selected');
		$('.left_title:first').next('.side').show();;
		$('.left_title').click(function(){
			$('.left_title').removeClass('selected');
			$('.side').slideUp(250);
			
			if($(this).next().is('ul:hidden')) {
				$(this).addClass('selected');
				$(this).next().slideDown(250);
			}
		});
		//侧面二级菜单
		$('.left a').click(function(){
			$('.left a').removeClass('selected');
			$(this).addClass('selected');

			$('.nav_info').html(/*$('.top_nav a.selected').html()+" > "+*/$('.left_title.selected span').html()+" > "+$('.left a.selected').html());
		});

	});
	
	//侧边切换
	$(function(){
		$("#side_switch").click(function(){
			//$(".left").toggle()
			if(!$(".left").is(':hidden'))
			{
				$(".left").hide();
				$("#side_switch").removeClass("side_switch").addClass("side_switchl");

				var width = $(window).width();
				$('.right').width(width-10);
			}
			else
			{
				$(".left").show();
				$("#side_switch").removeClass("side_switchl").addClass("side_switch");
				var width = $(window).width();
				$('.right').width(width-210);
			}
		});
	});

	function showMsg(flag, msg){
		if(flag&&msg){
			$.msgbox.show({
		        message: msg,
		        icon: flag,
		        timeOut: 2000
		    });
		}
	}

	//退出
	function logout()
	{
		 var dlg = $.dialog({
		 	background:'url(/CEE-NTS/res/admin/img/bb.png)',
			title: '确认',
			id: 'confirm.gif',
			zIndex: 3012,
			icon: 'confirm.gif',
			fixed: true,
			lock: true,
			content: "确定要退出系统吗？",
			resize: false,
			parent: parent || null,
			ok: function() {
				window.location.replace("${ctx}/admin/logout.do");
				},
			cancel: function() {}
		});
	}
		
	function self_changeInfo()
	{
		Pop.window("${ctx}/admin/self_changeInfo.do", 600, 350, "修改个人信息", 3000);
	}
	
	function self_changePwd()
	{
		Pop.window("${ctx}/admin/self_changePwd.do", 400, 230, "修改密码", 3000);
	}
	
</script>
</head>
<body style="overflow:hidden;">
<div class="top">
	<div class="top_about">	
		<span class="mr20">您好，admin!</span>
		<a class="mr20" href="javascript:self_changeInfo();">修改个人信息</a>
		<a class="mr20" href="javascript:self_changePwd();">修改密码</a>
		<a class="mr20" href="${ctx}/admin/overview.do" onclick="$('.nav_info').html('系统首页');" target='iframe'>系统首页</a>
		<a href="javascript:logout();">退出</a>
	</div>
	<div class="admin_logo">
		<img src="${ctx}/resourse/admin/images/admin_logo.jpg">
	</div>
	<!--
	<div class="top_nav">
			<ul>
				<li><a href="#" >后台首页</a></li>
				<li><a href="#" class="selected" >网站首页管理</a></li>
				<li><a href="#">菜单设置</a></li>
				<li><a href="#">文章管理</a></li>
				<li><a href="#">采集设置</a></li>
				<li><a href="#">广告管理</a></li>
				<li><a href="#">数据管理</a></li>
				<li><a href="#">用户管理</a></li>
				<li><a href="#">系统设置</a></li>
			</ul>
	</div>-->
</div>
<div class="center clearfix">
<div class="left">
	<#list menus as menu>
	<div class="left_title"><i class="icons"></i><span>${menu.name}</span></div>
	<ul class="side">
	<#list menu.children as childMenu>
	   <li><a href="${ctx}/admin/${childMenu.url}.do" target="iframe" <#if (childMenu_index==0)>class="selected"</#if>>${childMenu.name}</a></li>
	</#list> 
	</ul>
	</#list>
</div>
<div class="side_switch" id="side_switch">
</div>

<div class="right">
	<div class="nav_info">系统首页</div>
	<p class="line"></p>
	<iframe style="overflow:visible" id="main" name="iframe" src="${ctx}/admin/overview.do" frameBorder=0 width="100%" scrolling="yes" height="100%"></iframe>
</div>
</div>
</body>
</html>
 