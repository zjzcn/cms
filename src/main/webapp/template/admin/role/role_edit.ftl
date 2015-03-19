<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="background-color:#fff;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>网站后台登陆</title>
    <#include "/admin/include/common.ftl"/>
</head>
<body style="background-color:#fff;">
<div style="margin:10px">
	<table class="field-box">
			<tr class="box-title"><th colspan="50"><span class="edit-icon">角色信息</span></th></tr>
		   	<form id="pForm" method="post" action="${base}/admin/role_update.do">
		      	<input type="hidden" id="permUrl" name="urls"/>
		      	<input type="hidden" name="id" value="${role.id}"/>
		        <tr>
			         <th width="150px"><span class="required">*</span>角色名称：</th>
			         <td>
			            <input type="text" name="name" value="${role.name}" valid="required:true,rangelength:[2,10]"/>
			         </td>
		         </tr>
		         <tr>
			         <th>角色描述：</th>
			         <td>
			            <textarea name="remark">${role.remark}</textarea>
			         </td>			         
		         </tr>
		         <tr>
				     <th><span class="required">*</span>角色类型：</th>
				     <td>
				        <select id="isSuper" name="isSuper" valid="required:true">
								<option value="">---请选择---</option>
								<option value="0" <#if ((role.isSuper!-1)==0)>selected="true"</#if>>普通角色</option>
								<option value="1" <#if ((role.isSuper!-1)==1)>selected="true"</#if>>超级角色</option>
						</select>
				     </td>			         
			      </tr>   
		          <tr>
		          	<th width="100px">权限分配：</th>
                      	<td colspan="4">
							<ul id="roleTree" class="ztree" <#if ((role.isSuper!-1)!=0)>style="display:none;"</#if>></ul>
	            			<div id="superRole" style="color:blue; <#if ((role.isSuper!-1)!=1)>display:none;</#if>">超级角色拥有所有操作权限</div>
		  				</td>
                   </tr>
                   <tr>
                         <td colspan="4" style="text-align: center;">
                            <input type="button" value="确定" class="btn mr20" onclick="saveRole()"/>
                            <input type="button" value="返回" class="btn" onclick="frameElement.api.close();"/>
                         </td>
                   </tr>
		      </form>
	</table>
</div>
</body>

<script type="text/javascript" >

$(document).ready(function(){
	$('#pForm').valid();
  
    $('#isSuper').bind({
				//选择项目后触发
				change: function(){
					var val=$(this).find('option:selected').val();
					if(val=='0')
					{
						$('#roleTree').show();
						$('#superRole').hide();
					}
					else if(val=='1')
					{
						$('#roleTree').hide();
						$('#superRole').show();
					}
					else
					{
						$('#roleTree').hide();
						$('#superRole').hide();
					}
				}
			});	
	
});

function saveRole()
{
      var treeObj = $.fn.zTree.getZTreeObj("roleTree");
      var nodes = treeObj.getCheckedNodes(true);
      var v = "";
      if(nodes != null && nodes.length > 0)
      {
      		for (var i=0, l=nodes.length; i<l; i++) {
				if(!nodes[i].isParent)
      			{
					v += nodes[i].url + ",";
				}
			}
      } 
       $('#permUrl').val(v);
       var options = { success: function(responseText) {
            if(responseText == "ok")
            {
		        frameElement.api.opener.editCallback();
            }
            else
            {
                Pop.error(responseText);
            }
        }};
 
        $('#pForm').check(function(){
	        if(refuseDoubleSubmit())
	        	{
	        		$('#pForm').ajaxSubmit(options);
	        	}
	    });

}
	//以下为权限树初始化代码
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view:{
				showIcon:true
			},
			callback:{
				onCheck: zTreeOnCheck,
				onClick: onClick
			}
		};

		var zNodes =${menus};
		
		var code;
		
		function setCheck() {
			showCode('setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };');
		}
		
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		
		function zTreeOnCheck(event, treeId, treeNode)
		{
			var treeObj = $.fn.zTree.getZTreeObj("roleTree");
			if(treeNode.checked)
			{
				treeObj.expandNode(treeNode, true, true, false);
			}
		}
		
		function onClick(e,treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("roleTree");
			treeObj.expandNode(treeNode);
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#roleTree"), setting, zNodes);
			setCheck();
			
									
			var a=$('#roleTree a');
			$('#roleTree a').contextMenu('myMenu1', {
				bindings: {
					'add': function(t) {
						var id = $(t).attr('id');
						var pid = $(t).attr('pid');
						var txt = $(t).children('a').text();
						alert('This node info:\n id:'+id+'\n 父ID：'+ pid+'\n文本：'+txt);
					 },
					 'edit': function(t) {
						alert('Trigger was '+t.id+'\nAction was Email');
					 },
					 'delete': function(t) {
						alert('Trigger was '+t.id+'\nAction was Delete');
					 }
				}
			});
		});
 </script>
</html>