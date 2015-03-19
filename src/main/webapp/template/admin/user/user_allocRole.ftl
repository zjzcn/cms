<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <title>角色列表</title>
   <#include "/admin/include/common.ftl"/>
   
   <script type="text/javascript">
	        function saveInfo()
	        {
	            var roleCodes = "";
	            var roleCodeCheckBoxs = document.getElementsByName("roleId");
	            for(var i=0;i<roleCodeCheckBoxs.length ;i+=1)
	            {
	                if( roleCodeCheckBoxs[i].checked == true )
	                {
	                    roleCodes += roleCodeCheckBoxs[i].value + ",";
	                }
	            }
	           Pop.confirm("确定授予员工这些角色吗?",function()
               {
           	        $.ajax({
           	        type:'post',
           	        dataType:'text',async: false,
           	        url:'${base}/admin/user_doAllocRole.do',
                    data:{id:${userId}, roleCodes:roleCodes},
                    success: function(json) {
                       if(json == "success")
                       {
                           Pop.success("员工授权保存成功！");  
                            api = frameElement.api;
							 W = api.opener;
							 W.queryList();
		        			 window.close();
                       }
                       else
                       {
                         Pop.alert(json);
                       }
                    },
                    failure: function(form, action) {
                       Pop.error("操作失败！");     
                    }
                }); 
               },function(){},4000,this);
	        }
	        
	function setAllSelected(obj,objName){
		if($(obj).attr("checked")==undefined){
			$("#checkAll").removeAttr("checked"); 
		}else{
			var flag = true;
			$("[name='"+objName+"']").each(function(){     
		       if(!$(this).attr("checked"))     
			   {     
	     		flag = false;
	     		return;
			   }     
		    });
			if(flag){
				$("#checkAll").attr("checked",'true');  
			}
		}
	}

	    //选择所有
	function selectAllObj(objs){
		if($("#checkAll").attr("checked")==undefined){
			 $("[name='"+objs+"']").each(function(){     
			   $(this).removeAttr("checked"); 
			});
		}else{
			 $("[name='"+objs+"']").each(function(){     
				 $(this).attr("checked",'true');  
			});
		}
	}
	
	$(document).ready(function(){
		var roleCodes = document.getElementsByName("roleId");
		var result=true;
		for(var i=0;i<roleCodes.length;i++)
		{
			if(roleCodes[i].checked!=true)
			{
				result=false;
				break;
			}
		}
		if(result==true){
			$("#checkAll").attr("checked", "true");
		}
	});
   </script>
</head>
    
<body>
<div style="margin:10px">
    <table class="list-box" style="margin-top:10px">
			<tr class="box-title"><th colspan="20"><span class="list-icon fl">设置权限</span></th></tr>
		<form id="pForm" action='${ctx}/admin/user_edit.do' method="post">
		<tr>
			<th><input id="checkAll" type="checkbox" onclick="selectAllObj('roleId');" /></th>
			<th>角色代码</th>
			<th>角色名称</th>
			<th>角色描述</th>
			<th>角色类型</th>
		</tr>
		<#list roles as role>
		<tr <#if (role_index%2==0)>class='t1'<#else>class='t2'</#if>>
			<td><input type='checkbox'  onclick="setAllSelected(this,'roleId');" <#if roleIds?seq_contains(role.id)>checked='true'</#if> name="roleId" value="${role.id}"/></td>
			<td>${role.id!}</td>
			<td>${role.roleName!}</td>
			<td>${role.remark!}</td>
			<td><#if ((role.isSuper!-1)==0)!>普通权限<#elseif ((role.isSuper!-1)==1)!><span style="color:red">超级权限</span></#if></td>
		</tr>
		</#list>
		</form>
		<tr>
	        <td colspan="10" style="text-align: center;">
	           <input type="button" value="保存" class="btn blue" onclick="saveInfo()" style='margin-right:20px'/>
	           <input type="button"  onclick="frameElement.api.close();" value="返回"  class="btn blue"/>
	        </td>
        </tr>
	</table><!-- end table -->
</div>
</body>
</html>


