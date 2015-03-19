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
	            	parent.showMsg('ok','信息修改成功！');
					frameElement.api.close();
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
			<tr class="box-title"><th colspan="50"><span class="edit-icon">个人信息</span></th></tr>
		      <form id="pForm" action='${base}/admin/self_doChangeInfo.do' method="post">
		      	  <input type="hidden" name="id" value="${user.id}"/>
		          <tr>
			         <th width="150px">登录名：</th>
			         <td><input type="text" name="userName" disabled value="${user.username}"/></td>
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
			            <input type="text" name="email" value="${(user.email)!}" valid="{required:true,regex:'email',maxlength:50}"/>
			         </td>
			      </tr>
			      <tr>
			         <th>性别：</th>
			         <td>
			            <select name="gender">
							<option value="">---请选择---</option>
							<option value="0" <#if (((user.gender)!-1)==0)>selected="true"</#if>>女</option>
							<option value="1" <#if (((user.gender)!-1)==1)>selected="true"</#if>>男</option>
						</select>
			         </td>			         
		          </tr>  

			      <tr>
			         <th>手机号：</th>
			         <td>
			            <input type="text" name="mobile" value="${user.mobile}" valid="{regex:'mobile'}"/>
			         </td>			         
		          </tr>
                  <tr>
                  <td colspan="2" style="text-align: center;">
                      <input type="button" value="保存" class="btn mr20" onclick="saveInfo()"/>
                      <input type="button" value="返回" class="btn" onclick="frameElement.api.close();" />
                  </td>
                  </tr>		          		            		          		           
		      </form>
	</table> 	

</div>
</body>
</html>