(function(config){
    config['extendDrag'] = true; // 注意，此配置参数只能在这里使用全局配置，在调用窗口的传参数使用无效
    config['lock'] = true;
	config['background'] =  'url(/CEE-NTS/res/admin/img/bb.png)'; /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
	
    // [more..]
})($.dialog.setting);

function opdg( win_name, win_content,width,height){
 if(width==null||width==undefined)width='1000px';
 if(height==null||height==undefined)height='440px';
 var dg = new $.dialog({ title:win_name, content:'url:'+win_content, width:width, height:height });
 dg.ShowDialog();
}
//普通弹窗
function opdg_max( win_name, win_content ){
var dg = new $.dialog({ title:win_name, content:'url:'+win_content}).max();
dg.ShowDialog();
} 

var api,W,d;
(function(init){
	if(frameElement != null && frameElement != undefined && frameElement.api != null)
	{
	    api = frameElement.api,
		W = api.opener, 
		D = document;
	}
})($.dialog);

/**
 *各种弹出框类
 *引用页面需要先引入lhgdialog.js
*/
var Pop ={
	alert : function(content){//alert弹出
		if(W != null && W != undefined )
		{
			var dlg = W.$.dialog({
				title : '提示',
			    content: content,
			    icon:'alert.gif'
			});
		}
		else
		{
			$.dialog({
				title : '提示',
			    content: content,
			    icon:'alert.gif'
			});
		}
	},
	success : function(content){//操作成功提示
		if(W != null && W != undefined )
		{
			var dlg = W.$.dialog({
				title : '提示',
			    content: content,
			    time:2,
			    icon:'success.gif'
			});
		}
		else
		{
			$.dialog({
				title : '提示',
			    content: content,
			    time:2,
			    icon:'success.gif'
			});
		}
	},
	error : function(content){//错误提示
		if(W != null && W != undefined )
		{
		  W.$.dialog({
			title : '提示',
		    content: content,
		    icon:'error.gif'
		  });
		}
		else
		{
		 $.dialog({
			title : '提示',
		    content: content,
		    icon:'error.gif'
		  });
		}

	},
	/**
	*弹出div
	*url：路径 width：宽度 height:高度 title:标题
	*zindex:zindex值，最小不小于1000，多层弹出时务必保证子层比父层的大如：父：2000，子：3000
	**/
	window : function(url,width,height,title,zindex){//弹出自定义大小窗口
	    if(zindex == null  || zindex == 'undefined')
	    {
	    	zindex = 2000;
	    }
	      //窗口定义
	     if( W != null && W != undefined)
	     {
	       var ww =api.iwin;
	       var dlg = ww.$.dialog({
		    id: 'popWin',
		    title: title,
		    content: 'url:'+url,
		    width: width,
		    height: height,
		    max:false,
		    min:false,
		    parent:api,
		    lock:true,
		    zIndex:zindex
		  }).zindex().focus();
	     }
	     else
	     {
	     	var dlg = $.dialog({
			    id: 'popWin',
			    title: title,
			    content: 'url:'+url,
			    width: width,
			    height: height,
			    max:false,
			    min:false,
			    lock:true,
			    resize: false,
			    zIndex:zindex
			  }).zindex().focus();
	     }

	},
	
	dialog : function(url,width,height,title,zindex){//弹出自定义大小窗口
	    if(zindex == null  || zindex == 'undefined')
	    {
	    	zindex = 2000;
	    }
	      //窗口定义
	     if( W != null && W != undefined)
	     {
	       var ww =api.iwin;
	       var dlg = ww.$.dialog({
		    id: 'popWin',
		    title: title,
		    content: url,
		    width: width,
		    height: height,
		    max:false,
		    min:false,
		    parent:api,
		    lock:true,
		    zIndex:zindex
		  }).zindex().focus();
	     }
	     else
	     {
	     	var dlg = $.dialog({
			    id: 'popWin',
			    title: title,
			    content: url,
			    width: width,
			    height: height,
			    max:false,
			    min:false,
			    lock:true,
			    resize: false,
			    zIndex:zindex
			  }).zindex().focus();
	     }

	},
	
	/**
	*确认方法
	*content：内容，yes：点确定后回调函数,no:点取消后回调函数,
	*zindex:zindex值，如果在弹出div上弹出confirm，请保证confirm的zindex值比弹出div的zindex大，否则会被div遮住
	*parent:父页面
	**/
	confirm :function( content, yes, no, zindex,parent )
	{
		if(zindex == null || zindex =='undefined')
		{
			zindex = 3012;
		}
		return $.dialog({
			title: '确认',
			id: 'confirm.gif',
			zIndex: zindex,
			icon: 'confirm.gif',
			fixed: true,
			lock: true,
			content: content,
			resize: false,
			parent: parent || null,
			ok: function(here){
				return yes.call(this, here);
			},
			cancel: function(here){
				return no && no.call(this, here);
			}
		});
	},
	/**
	*弹出div
	*url：路径 width：宽度 height:高度 title:标题
	*zindex:zindex值，最小不小于1000，多层弹出时务必保证子层比父层的大如：父：2000，子：3000
	**/
	maxWindow : function(url,width,height,title,zindex){//弹出自定义大小窗口
	    if(zindex == null  || zindex == 'undefined')
	    {
	    	zindex = 2000;
	    }
	      //窗口定义
	     if( W != null && W != undefined)
	     {
	       var ww =api.iwin;
	       var dlg = ww.$.dialog({
		    id: 'popWin',
		    title: title,
		    content: 'url:'+url,
		    width: width,
		    height: height,
		    max:true,
		    min:false,
		    parent:api,
		    lock:true,
		    zIndex:zindex
		  }).zindex().focus();
	     }
	     else
	     {
	     	var dlg = $.dialog({
			    id: 'popWin',
			    title: title,
			    content: 'url:'+url,
			    width: width,
			    height: height,
			    max:true,
			    min:false,
			    lock:true,
			    resize: false,
			    zIndex:zindex
			  }).zindex().focus();
	     }

	}
};




