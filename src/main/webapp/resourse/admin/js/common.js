/**
 * 字符串的trim方法
 * @return
 */
String.prototype.trim = function(){ 
	return this.replace(/(^\s*)|(\s*$)/g, "");
} 
//去掉字符串前后空格

function trim(str)
{
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function(){ 
	return this.replace(/(^\s*)/g, ""); 
} 
String.prototype.rtrim = function(){ 
	return this.replace(/(\s*$)/g, ""); 
} 
String.prototype.getByteLength = function()
{
    var trimedStr = this.replace(/(^\s*)|(\s*$)/g, "");
    return trimedStr.replace(/[^\x00-\xff]/g,"***").length;
}
function isBlank( s ){
	if( s == null || s.trim() == ''){
		return  true ;
	}
	return false ;
}
/**
 * 隔行换色功能
 * @param id
 * @param tag
 * @return
 */
function BtZebraStrips(id,tag) {
	var ListId = document.getElementById(id);
	if(ListId){
	var tags  = ListId.getElementsByTagName(tag);
	for(var i=1;i<tags.length;i+=1) {
	tags[i].className += (i%2>0)?" t1":" t2"; 
	tags[i].onmouseover = function(){this.className += " t3"}
	tags[i].onmouseout  = function(){this.className = this.className.replace(" t3","")}}}
}
/**
 * 全选与全不选功能
 * @param mark 指示全选还是反选
 * @param name 对应的HTML元素的name
 * @return
 */
function selectAll(mark , name) {
	var checkboxs = document.getElementsByName(name);
	if (mark){
		for (var i=0; i<checkboxs.length; i+=1) {
			checkboxs[i].checked = true;
		}
	}else{
		for (var i=0; i<checkboxs.length; i+=1) {
			checkboxs[i].checked = false;
		}
	}
}
/**
 * 判断复选框有无选中
 * 如果有选中则返回true 否则返回false
 * @param name
 * @return
 */
function checkSelect(name){
	var checkboxs = document.getElementsByName(name);
	for(var i=0;i<checkboxs.length;i+=1){
		if(checkboxs[i].checked == true ){
			return true ;
		}
	}
	return false ;
}


/**
 * 判断是否只有一个复选框选中
 * 如果有选中则返回true 否则返回false
 * @param name
 * @return
 */
function checkSimpleSelect(name){
    var checkboxs = document.getElementsByName(name);
    var checkedNum = 0;
    for(var i=0;i<checkboxs.length;i+=1){
        if(checkboxs[i].checked == true ){
            checkedNum ++ ;
        }
    }
    if(checkedNum==1)
    {
        return true;
    }
    return false ;
}
//防止重复提交
var mypretime = 0;
function refuseDoubleSubmit() 
{ 
    Today = new Date(); 
    var NowHour = Today.getHours(); 
    var NowMinute = Today.getMinutes(); 
    var NowSecond = Today.getSeconds(); 
    var mysec = (NowHour*3600)+(NowMinute*60)+NowSecond;
    if (mypretime>0)
    { 
       if((mysec-mypretime)>300) 
       { 
            mypretime=mysec; 
       } 
       else 
       { 
           alert('请勿重复提交，请耐心等待'); 
           return false; 
       } 
    }
    else
    {
        mypretime=mysec; 
    }
    return true;
} 
/**
 * 折叠显示员工查询条件 最好将tab的display的初始值定义为block
 */
function toggleTab(id){
	var tab = document.getElementById(id) ;
	var dropbutton = document.getElementById("drop_button");
	if( tab.style.display =="block"){
		tab.style.display ="none" ;
		dropbutton.className="win_but win_but_cloes" ;
	} else {
		tab.style.display ="block";
		dropbutton.className="win_but_open" ;
	}
}
/**
 * 检查员工状态选中情况
 */
function checkStatus(name,allStatusId){
	var checkboxs = document.getElementsByName(name);
	var i = 0 ;
	for(i=0;i<checkboxs.length;i+=1){
		if( checkboxs[i].checked == false )break;
	}
	if( i == checkboxs.length )document.getElementById(allStatusId).checked = true;
	else document.getElementById(allStatusId).checked = false;
}
/**
 * 选中所有的员工状态
 */
function checkAll(name,allStatusId){
	var checkboxs = document.getElementsByName(name);
	var checkAllStatus = document.getElementById(allStatusId);
	if(checkAllStatus.checked == true ){
		for(var i=0;i<checkboxs.length;i+=1){
			checkboxs[i].checked = true ;
		}
	} else {
		for(var i=0;i<checkboxs.length;i+=1){
			checkboxs[i].checked = false ;
		}
	}
}



/**
 * 弹出框
 * @param div_id
 * @return
 */
function popup(div_id,t) { 
	var div_obj = $("#"+div_id); 
	var windowWidth = document.documentElement.clientWidth; 
	var windowHeight = document.documentElement.clientHeight; 
	var popupHeight = div_obj.height(); 
	var popupWidth = div_obj.width(); 
	var topValue;
	if( t == null || t == "" )
	{
	   topValue = windowHeight/2-popupHeight/2
	}
	else
	{
	   topValue = t;
	}
	//添加并显示遮罩层 
	$("<div id='mask'></div>")
		.css("position","absolute")
		.css("left","0px")
		.css("top","0px")
		.width(windowWidth) 
		.height(windowHeight).css("background-color","#C0C0C0").css("border","0px").css("filter","alpha(opacity=50)") 
	//	.click(function() {hidePopup(div_id); }) 
	    .click(function() {; }) 
		.appendTo("body") 
		.fadeIn(200); 
	div_obj.css({"position": "absolute"}).css("z-index","10")
		.animate({left: windowWidth/2-popupWidth/2, 
	top: topValue, opacity: "show" }, "slow"); 
} 

function hidePopup(div_id) { 
	$("#mask").remove(); 
	$("#" + div_id).animate({left: 0, top: 0, opacity: "hide" }, "slow"); 
}

/**
 * AJAX展示员工简要信息
 * 
 * @param event
 * @param empId
 * @param divId
 * @return
 */
var contextPath = "oa";
function ajaxShowEmpDetail(event ,empId, divId){
	var url = "/"+contextPath+"/hr/emp/ajaxShowEmpInfo.action";
	$.getJSON(url,{empId:empId,ran:Math.random()},function(json){
    	if(json.length > 0){
        	var empJsonInfo = eval("("+json+")");
        	$("#empPhoto").html("<img src='/"+contextPath+"/hr/emp/getEmpPhoto.action?empId="+empId+"' style='width:110px;height:145px;'/>");
        	$("#empNo").html(empJsonInfo.no);
        	$("#empName").html(empJsonInfo.name);
        	$("#empJob").html(empJsonInfo.jobName);
        	$("#empOrg").html(empJsonInfo.orgName);
        	$("#empPos").html(empJsonInfo.positionName);
    	}
    });
	locate(event,divId);
}

/**
 * 定位div到鼠标处
 * @param e
 * @param divId
 * @return
 */
function locate(e,divId){
	var posx=0,posy=0;
	if(e==null) e=window.event;
	if(e.pageX || e.pageY){
		posx=e.pageX; posy=e.pageY;
	}
	else if(e.clientX || e.clientY){
		if(document.documentElement.scrollTop){
			posx=e.clientX+document.documentElement.scrollLeft;
			posy=e.clientY+document.documentElement.scrollTop;
		}
		else{
			posx=e.clientX+document.body.scrollLeft;
			posy=e.clientY+document.body.scrollTop;
		}
	}
	var downHeight = $(document).height()-posy; 
	var divHeight = $("#"+divId).height();
	if(divHeight > (downHeight - 20)){
		$("#"+divId).css("top",(posy-divHeight-10));
	}else{
		$("#"+divId).css("top",(posy+10));
	}
	$("#"+divId).css("left",(posx-20));
	$("#"+divId).show();
}
/**
 * 按照yyy-MM-dd来格式化日期
 * @param d 需要格式化的日期
 */
function formatDate(d){
	var mon=d.getMonth()+1;
	var year=d.getFullYear();
	var date=d.getDate();
	return year+"-"+(mon<10?"0"+mon:mon)+"-"+(date<10?"0"+date:date) ;
}

 function trimFormValue(FormName)
 {
	 var elements = document.forms(FormName).elements;
	 var element ;
     for(var i = 0; i < elements.length; i++)
     {
        element = elements[i];
        if (!element.disabled)
        {
           if((elements[i].type) == 'text' || (elements[i].type == 'password') ||(elements[i].type == 'textarea'))
           {
               elements[i].value = trim(elements[i].value);
           }            
        }
     }
 }
 
 
 //重置FORM
    function resetForm(FormName)
    {
       var elements = document.getElementById(FormName).elements;
       var element ;
       var j = 0;
       var elementSel = null;
       for(var i = 0; i < elements.length; i++)
       {
          element = elements[i];
          if (!element.disabled)
          {
            if((elements[i].type == 'text') || (elements[i].type == 'password')||(elements[i].name == 'orderProperty')||(elements[i].name == 'orderDirection'))
            {
              j = j+1;
              elements[i].value = "";
              $(".list-box th[orderby]").attr('class','');
            }
            else if(elements[i].type == 'select-one')
            {
              j = j+1;
              elements[i].selectedIndex = 0;
            }
            if (j==1)
            {
              elementSel = elements[i]
            }
          }       
       }
       if (elementSel != null)
       {
         elementSel.focus();
       }
    }
    
    //重置FORM
    function resetMultiForm(FormName,idone,idtwo)
    {
       var elements = document.getElementById(FormName).elements;
       var element ;
       var j = 0;
       var elementSel = null;
       for(var i = 0; i < elements.length; i++)
       {
          element = elements[i];
          if (!element.disabled)
          {
            if((elements[i].type == 'text') || (elements[i].type == 'password'))
            {
              j = j+1;
              elements[i].value = "";
            }
            else if(elements[i].type == 'select-one')
            {
              j = j+1;
              elements[i].selectedIndex = 0;
            }
            if (j==1)
            {
              elementSel = elements[i]
            }
          }       
       }
       if (elementSel != null)
       {
         elementSel.focus();
       }
       
       var selectNodeOne = document.getElementById(idone);
       
       resetOptions(selectNodeOne,"--请选择中区--");
       
       var selectNodeTwo = document.getElementById(idtwo);
       
       resetOptions(selectNodeTwo,"--请选择小区--");
    }
    
    
    function resetOptions(selectNode,text)
    { 
       selectNode.length = 1;
   
       selectNode.options[0].text = text;
       selectNode.options[0].value = "";
  
       selectNode.options[0].selected = true; 
    }
    
    function checkNumber(value)
    {
        if(value!=""&&isNaN(value))
        {
           alert("数字不合法！");
           return false;
        }
        return true;
    }
    
    function checkNaN(obj)
    {
        if(obj.value!="" && isNaN(obj.value))
        {
           alert("数字不合法！");
           obj.focus();
           return false;
        }
    }

	function formatMoney(s, n)//将数字转换成逗号分隔的样式,保留两位小数s:value,n:小数位数      
	{   
	     n = n > 0 && n <= 20 ? n : 2;   
	     s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
	     var l = s.split(".")[0].split("").reverse(),   
	     r = s.split(".")[1];   
	     t = "";   
	     for(i = 0; i < l.length; i ++ )   
	     {   
	     t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
	     }   
	     return t.split("").reverse().join("") + "." + r;   
	}
	function formatDouble(s, n)//将数字转换成保留n位小数的样式,s:value,n:小数位数      
	{   
	     n = n > 0 && n <= 20 ? n : 2;   
	     s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
	     return s;   
	}   
	//还原金额   
	function rmoney(s)   
	{   
	     return parseFloat(s.replace(/[^\d\.-]/g, ""));   
	} 
	
	//计算包含英文与汉字的字符串长度
	 function countCharacters(str){
	   var totalCount = 0; 
	   for (var i=0; i<str.length; i++) { 
	       var c = str.charCodeAt(i); 
	       if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
	            totalCount++; 
	        }else {     
	            totalCount+=2; 
	        } 
	    }
	   return totalCount;
	 }

//window.event在firefox下面不支持    
/**    document.onkeydown=keyDown;
	function keyDown()
	{ 
		var key=window.event.keyCode;
		if(key==13)
		{
			window.event.returnValue = false;
		}
	}
	*/
	 
		$(function(){
			$(".list-box th[orderby]").click(function(){
				$('input[name="orderProperty"]').val($(this).attr('orderby'));
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
				$(".list-box form").submit();
			});
			
			$(".list-box th[orderby]").each(function(){
				if($(this).attr('orderby')==$('input[name="orderProperty"]').val())
				{
					$(this).addClass($('input[name="orderDirection"]').val());
				}
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
	