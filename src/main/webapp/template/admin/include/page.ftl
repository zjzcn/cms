<#-- 自定义的分页指令 (powered by qiujy)
    属性：
   formId	        查询from的id值
   pageNo      当前页号(int类型)
   pageSize    每页要显示的记录数(int类型)
   totalCount 总记录数(int类型)
 -->
<#macro page formId pageNo pageSize totalCount columns>  
  <#-- 定义局部变量pageCount保存总页数 -->
  <#assign pageCount=((totalCount + pageSize - 1) / pageSize)?int>  
 <#if totalCount==0><#return/></#if>
<#-- 输出分页样式 -->
<style type="text/css">
	.page-nav {padding:6px 5px;float:right;font-size:12px;}
	.page-nav a, .page-nav a:link, .page-nav a:visited, .page-nav span, .page-nav a:hover, .page-nav a:active {margin:2px 4px; padding:0px 5px; border: 1px solid #0099FF;text-decoration: none;color: #006699;background-color: #FFF;}
	.page-nav a.page-curr,.page-nav a:hover, .page-nav a:active {border: 1px solid #346890;font-weight: bold; background-color: #346890; color:#FFF;}
	.page-nav span.page-gray {border: 1px solid #ccc; color: #ddd;cursor:default;}
</style>
<#-- 页号越界处理 -->
  <#if (pageNo > pageCount)>
    <#assign pageNo=pageCount>
  </#if>
  <#if (pageNo < 1)>
    <#assign pageNo=1>
  </#if>
<#-- 输出分页表单 -->
<#if (pageNo == pageCount && totalCount+1<=pageCount*pageSize)>
	<#list totalCount+1..pageCount*pageSize as i>
	<tr class="<#if i%2==0>t2<#else>t1</#if>">
		<#list 1..columns as j>
			<td></td>
		</#list>
	</tr>
	</#list>
</#if>
<tr><td colspan="50">
<div class="page-nav">
<#-- 上一页处理 -->
  <#if (pageNo == 1)>
	<span class="page-prev page-gray">&laquo;&nbsp;上一页</span>
  <#else>
	<a class="page-prev" href="javascript:turnOverPage(${pageNo - 1})">&laquo;&nbsp;上一页</a>
  </#if>
<#-- 如果前面页数过多,显示... -->
 <#assign start=1>
 <#if (pageNo > 4)>
    <#assign start=(pageNo - 2)>
	<a href="javascript:turnOverPage(1)">1</a>
	<label>...</label>
 </#if>
<#-- 显示当前页号和它附近的页号 -->
 <#assign end=(pageNo + 2)>
 <#if (end > pageCount)>
  <#assign end=pageCount>
 </#if>
  <#list start..end as i>
    <#if (pageNo==i)>
    	<a class="page-curr" href="javascript:turnOverPage(${i})">${i}</a>
  	<#else>
		<a href="javascript:turnOverPage(${i})">${i}</a>
    </#if>
  </#list>
<#-- 如果后面页数过多,显示... -->
  <#if (end < pageCount - 1)>
  	<label>...</label>
  </#if>
  <#if (end < pageCount)>
	<a href="javascript:turnOverPage(${pageCount})">${pageCount}</a>
  </#if>
<#-- 下一页处理 -->
  <#if (pageNo == pageCount)>
	<span class="page-next page-gray">下一页&nbsp;&raquo;</span>
  <#else>
	<a class="page-next" href="javascript:turnOverPage(${pageNo + 1})">下一页&nbsp;&raquo;</a>
  </#if>
  
  <attr style="margin-left:10px;padding:2px;">共${totalCount}条</attr>
  <attr style="margin:2px 4px;">每页
	<select name="pageSize">
		<option value="15" <#if (pageSize==15)>selected="true"</#if>>15</option>
		<option value="10" <#if (pageSize==10)>selected="true"</#if>>10</option>
		<option value="20" <#if (pageSize==20)>selected="true"</#if>>20</option>
		<option value="30" <#if (pageSize==30)>selected="true"</#if>>30</option>
		<option value="50" <#if (pageSize==50)>selected="true"</#if>>50</option>
		<option value="100" <#if (pageSize==100)>selected="true"</#if>>100</option>
	</select> 条
	</attr>
<script language="javascript">
  function turnOverPage(no)
  {
    if(no>${pageCount}){no=${pageCount};}
    if(no<1){no=1;}
    $("input[name='pageNo']").val(no);
    $("#${formId}").submit();
  }
</script>
</div>
</td>
</tr> 
</#macro>