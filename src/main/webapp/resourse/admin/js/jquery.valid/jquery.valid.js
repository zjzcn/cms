//====================================================================================================
//[插件名称] jQuery Valid
//----------------------------------------------------------------------------------------------------
//[描    述] jQuery Valid表单验证插件，它是基于jQuery类库
//----------------------------------------------------------------------------------------------------
//[作者网名] 	
//[邮    箱] zjzmail@126.com
//[作者博客] http://
//[插件主页] http://
//[QQ交流] 282950508
//[更新日期] 2013-06-14
//[版 本 号] V0.9.9 内部测试版
//[开源协议] LGPL
//====================================================================================================


(function($){  
	//匿名函数
	var args=(function(){
		var path, args; 
		/*!
		 * path 获取组件核心文件valid.js所在的绝对路径
		 * args 获取valid.js文件后的url参数组，如：valid.js?skin=small中的?后面的内容
		 */
		var script=document.getElementsByTagName('script');

		for( var i=0; i < script.length; i++ )
		{
			var src = !!document.querySelector ? script[i].src : script[i].getAttribute('src',4);

			var p=src.substr(0,src.lastIndexOf('/')+1);
			if( p.indexOf('valid') !== -1 ){
				path=p;
				src=src.split('?'); 
				args = src[1];

				break;
			}
		}

		var argsMap={};
		if( args )
		{
			var args = args.split('&');
			for( var i=0; i < args.length; i++ )
			{
				var arg = args[i].split('=');
				if(arg[1]==='true'){
					arg[1]=true;
				}
				argsMap[arg[0]]=arg[1];
			}
		}		
		
		var skin = argsMap.skin || 'default';
		/*! 在最顶层页面添加样式文件 */
		if(!document.getElementById('valid-link'))
		{
			var head = document.getElementsByTagName('head')[0],
			link = document.createElement('link');

			link.href = path + 'skins/' + skin + '/style.css';
			link.rel = 'stylesheet';
			link.id = 'valid-link';
			head.insertBefore(link, head.firstChild);
		}
		
		return argsMap;
	})();

//jquery 插件部分	
$.fn.extend({
	valid:function(rules){
		var validObj = new $.Valid(this[0], rules);
		$.data(this[0], 'validObj', validObj);
	},

	check:function(callback){
		var validObj = $.data(this[0], 'validObj');
		validObj.settings.submitCallback=callback;
		
		if(validObj.checkForm( this[0] ))
		{
			callback();
		}
	},
	checkElement:function(){
		var validObj = $.data(this[0].form, 'validObj');
		
		validObj.checkElement(this[0]);
	}
});

//构造函数
$.Valid = function(formEle, rules) {
	this.currentForm=formEle;
	this.rulesMap=rules || {};
	this.settings=$.extend({},$.Valid.defaults,args);
	this.checkingMap={};
	this.isvalidMap={};

	rulesMap=this.rules();
	
	this.bindEvent();
};

//类变量、函数（静态变量、静态函数）
$.extend($.Valid, {
	defaults: {
		debug: false,
		submitCallback:function(){ return false;}
	},

	regexMap: {
		integer:"^-?[1-9]\\d*$",				//整数
		integer1:"^[1-9]\\d*$",					//正整数
		integer2:"^-[1-9]\\d*$",				//负整数
		number:"^([+-]?)\\d*\\.?\\d+$",			//数字
		number1:"^[1-9]\\d*|0$",				//正数（正整数 + 0）
		number2:"^-[1-9]\\d*|0$",				//负数（负整数 + 0）
		float:"^([+-]?)\\d*\\.\\d+$",			//浮点数
		float1:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",　　  //正浮点数
		float2:"^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",　 //负浮点数
		float3:"^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",　 //浮点数
		float4:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",　　 //非负浮点数（正浮点数 + 0）
		float5:"^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",　　//非正浮点数（负浮点数 + 0）
		email:"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
		color:"^[a-fA-F0-9]{6}$",				//颜色
		url:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",	//url
		ascii:"^[\\x00-\\xFF]+$",				//仅ACSII字符
		zipcode:"^\\d{6}$",						//邮编
		mobile:"^(13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$)",				//手机
		ip4:"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",	//ip地址
		notempty:"^\\S+$",						//非空
		picture:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",	//图片
		rar:"(.*)\\.(rar|zip|7zip|tgz)$",								//压缩文件
		date:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",					//日期
		qq:"^[1-9]*[1-9][0-9]*$",				//QQ号码
		tel:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",	//电话号码的函数(包括验证国内区号,国际区号,分机号)
		username:"^\\w+$",						//用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
		letter:"^[A-Za-z]+$",					//字母
		uppercase:"^[A-Z]+$",					//大写字母
		lowercase :"^[a-z]+$",					//小写字母
		idcard:"^[1-9]([0-9]{14}|[0-9]{16}([0-9]|X|x))$",	//身份证
		en:"^[a-zA-Z0-9]+$",			//数字和字母
		cn:"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",					//仅中文
		cn_en:"^[a-zA-Z0-9\\u4e00-\\u9fa5]+$"	//中文、数字和英文
	},

	messages: {
		ok:"验证通过",
		error:"验证错误",
		required: "必填字段",
		ajaxWait: "正在验证数据...",
		ajaxError:"验证出错",

		minlength: "请输入一个长度最少是 {0} 的字符串",
		maxlength: "请输入一个长度最多是 {0} 的字符串",
		rangelength: "请输入一个长度介于 {0} 和 {1} 之间的字符串",
		min: "请输入一个最小为 {0} 的值",
		max: "请输入一个最大为 {0} 的值",
		range: "请输入一个介于 {0} 和 {1} 之间的值",
		compare: "当前输入的{0}必须  {1} {2}",

		integer: "只能输入整数",
		integer1:"正整数",					//正整数
		integer2:"负整数",				//负整数
		number: "请输入合法的数字",
		number1:"^正数（正整数 + 0）",				//正数（正整数 + 0）
		number2:"负数（负整数 + 0）",				//负数（负整数 + 0）
		float:"^浮点数",			//浮点数
		float1:"正浮点数",　　  //正浮点数
		float2:"负浮点数",　 //负浮点数
		float3:"浮点数",　 //浮点数
		float4:"非负浮点数（正浮点数 + 0）",　　 //非负浮点数（正浮点数 + 0）
		float5:"非正浮点数（负浮点数 + 0）",　　//非正浮点数（负浮点数 + 0）
		email: "请输入正确格式的电子邮件",
		color:"颜色",				//颜色
		url: "请输入合法的网址",
		ascii:"仅ACSII字符",				//仅ACSII字符
		zipcode:"邮编",						//邮编
		mobile:"请输入正确的手机号码",				//手机
		ip4:"ip地址",	//ip地址
		notempty:"非空",						//非空
		picture:"图片",	//图片
		rar:"压缩文件",								//压缩文件
		date: "请输入合法的日期",
		qq: "qq格式输入不正确",
		tel:"电话号码格式错误",	//电话号码的函数(包括验证国内区号,国际区号,分机号)
		username: "用户名只能包含英文、数据、下划线",						//用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
		letter:"字母",					//字母
		uppercase:"大写字母",					//大写字母
		lowercase :"小写字母",					//小写字母
		idcard: "请输入合法的省份证号",
		number_letter:"数字和字母",			//数字和字母
		cn:"仅中文",					//仅中文
		cn_en: "请输入合法的中文或英文字符"
	},
	//用法：$.Valid.debug("test");
	debug: function(arg){
		Date.prototype.format = function(format){ 
			var o = {
					"y+" : this.getFullYear(),
					"M+" : this.getMonth()+1, //month 
					"d+" : this.getDate(), //day 
					"h+" : this.getHours(), //hour 
					"m+" : this.getMinutes(), //minute 
					"s+" : this.getSeconds(), //second 
					"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
					"S+" : this.getMilliseconds() //millisecond 
			} 

			for(var k in o) { 
				if(new RegExp("("+ k +")").test(format)) { 

					var t="0000"+o[k];
					format = format.replace(RegExp.$1, t.substr( t.length-RegExp.$1.length, t.length));
					//format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
				} 
			} 
			return format; 
		} 

		args.debug && window.console && console.log("["+new Date().format("yyyy-MM-dd hh:mm:ss.SSS")+"] [DEBUG] " + arg);
	},
	//对象变量、函数
	prototype: {
		rules: function(){
			var rules = this.rulesMap;
			
			var _this=this;
			$.each(this.currentForm.elements, function(){
				if($(this).attr('valid')!=undefined){
					rules[this.name]=$.extend({},rules[this.name],_this.attrRules(this, 'valid'));
				}

				if(this.name in rules){
					// make sure required is at front
					if ('required' in rules[this.name]) {
						var param = rules[this.name].required;
						delete rules[this.name].required;
						rules[this.name]=$.extend({required: param}, rules[this.name]);
					}
					// make sure ajax is at end
					if ('ajax' in rules[this.name]) {
						var param = rules[this.name].ajax;
						delete rules[this.name].ajax;
						$.extend(rules[this.name], {ajax: param});
					}

					rules[this.name].messages=$.extend({}, $.Valid.messages, rules[this.name].messages);
				}
			});

			return rules;
		},

		attrRules: function (element, attr){
			var data = "{}";

			if( $(element).attr(attr)!= undefined ){
				data=$(element).attr(attr);

				if ( data.indexOf( '{' ) <0 ){
					data = "{" + data + "}";
				}
			}

			data = eval("(" + data + ")");

			return data;
		},

		bindEvent: function() {
			var elements=this.elements();
			
			var _this=this;
			$.each(elements, function(){
				var nodeName=this.nodeName.toLowerCase();
				if(nodeName=="input" || nodeName=="textarea"){
					$(this).bind({
						//失去焦点
						blur: function(){
							_this.checkElement(this);
						},
						focus: function(){
							//_this.checkElement(this);
						},
						keyup:function(){
							if(!('ajax' in _this.rulesMap[this.name])){
								_this.checkElement(this);
							}
						}
					});				
				}
				else if(nodeName=="select"){
					$(this).bind({
						//获得焦点
						focus: function(){	
							_this.checkElement(this);
						},
						//失去焦点
						blur: function(){
							_this.checkElement(this);
						},
						//选择项目后触发
						change: function(){
							_this.checkElement(this);
						}
					});	
				}
			});

			$(_this.currentForm).bind({
				//form提交
				submit: function(){
					return _this.checkForm();
				}
			});	
		},

		elements: function() {
			var rules=this.rulesMap;

			//解决IE678与IE9、chrome的问题
			var $elements=$.browser.msie ? $(this.currentForm).find(":input") : $([]).add(this.currentForm.elements).filter(":input");
			var _this = this;
			return $elements.filter(function() {
				if($(this).attr('valid')!=undefined){
					$.Valid.debug("valid [name]: " + this.name);
					return true;
				}
//				if(this.name!=undefined && this.name in rules){
//					Valid.debug("rules:"+$(this));
//					return true;
//				}
				else{
					return false;
				}
			});
		},

		checkForm: function() {
			this.isSubmit=true;
			var elements=this.elements();
			
			var _this=this;
			$.each(elements, function(){
				_this.checkElement(this);
				$.Valid.debug("checkElement("+this.name+") isvalid:" + _this.isvalidMap[this.name]+", checking:" + _this.checkingMap[this.name]);
			});
			
			var result=this.isCheckAll();
			this.isSubmit=false;
			$.Valid.debug("checkForm result: " + result);
			return result;
		},
		
		isCheckAll: function() {
			var elements=this.elements();
			var result=true;
			var _this=this;
			
			try {
				$.each(elements, function(){
					if(_this.checkingMap[this.name]==true)
					{
						throw "正在checking。。。"
					}
					
					var isvalid=_this.isvalidMap[this.name];
					if(isvalid!=undefined){
						result=result && isvalid;
					}
				});
	        } catch (e) {
	            return false;
	        }
	        $.Valid.debug("isCheckAll result: " + result);
			return result;
		},
		
		checkElement: function( element ) {
			var eleRules=this.rulesMap[element.name];

			this.removeMsg(element);

			if(eleRules['required']==true || this.getLength(element)>0 || 'compare' in eleRules){
				for( method in eleRules ) {
					if(method in this.methods)
					{
						this.isvalidMap[element.name]=undefined;

						var result=this.methods[method].call( this, element, eleRules[method] );

						this.isvalidMap[element.name]=result;

						if(method != 'ajax'){
							if(result==true)
							{
								this.showOk(element, eleRules.messages["ok"]);
							}
							else if(result==false)
							{
								var msg=eleRules.messages[eleRules[method]];
								if(method=='regex'){
									msg=eleRules.messages[eleRules[method]];
									if(msg==undefined)
									{
										msg=eleRules.messages[method];
									}
									msg = this.format(msg, eleRules[method])
								}
								else if(method=='compare'){
									msg=eleRules.messages[method];
									var datatype=eleRules[method].datatype;
									var destEle=this.findByName(element.form, eleRules[method].destname);
									var destval=$(destEle).val();
									if(datatype=="number")
									{
										msg=this.format(msg, ['数值',eleRules[method].op, destval]);
									}
									else if(datatype=="date"){
										msg=this.format(msg, ['日期',eleRules[method].op, destval]);
									}
									else if(datatype=="datetime")
									{
										msg=this.format(msg, ['时间',eleRules[method].op, destval]);
									}
									else{
										msg=this.format(msg, ['字符',eleRules[method].op, destval]);
									}
								}
								else{
									msg=eleRules.messages[method];
									msg=this.format(msg, eleRules[method]);
								}
								
								if(msg==undefined){msg=eleRules.messages.error};
								this.showError(element, msg);

								return false;
							}
						}
						else{
							return false;
						}
					}
				}
			}

			return true;
		},

		methods: {
			required: function(element, params) {
				var result=false;

				switch( element.nodeName.toLowerCase() ) {
				case 'select':
					var options = $("option:selected", element);
					result= options.length > 0 && ( element.type == "select-multiple" || ($.browser.msie && !(options[0].attributes['value'].specified) ? options[0].text : options[0].value).length > 0);
					break;
				default:
					result = this.getLength(element) > 0;
				}

				return result;
			},

			minlength : function(element, params)
			{
				var len=this.getLength(element);

				var result= len>=params;

				return result;
			},

			maxlength : function(element, params)
			{
				var len=this.getLength(element);

				var result= len<=params;

				return result;
			},

			rangelength : function(element, params)
			{
				var len=this.getLength(element);

				var result = (params[0]<=len && len<=params[1]);

				return result;
			},

			min : function(element, params)
			{
				var val=parseFloat($.trim(element.value));

				var result= val>=params;

				return result;
			},

			max : function(element, params)
			{
				var val=parseFloat($.trim(element.value));
				var result = val<=params;

				return result;
			},

			range : function(element, params)
			{
				var val=parseFloat($.trim(element.value));

				var result = (params[0]<=val && val<=params[1]);

				return result;
			},

			regex : function(element, params)
			{
				var reg; 
				if(/^\^[\w\W]+\$$/.test(params))
				{
					reg=new RegExp(params);
				}
				else
				{
					reg=new RegExp($.Valid.regexMap[params]);
				}
				var result = reg.test($.trim(element.value));

				return result;
			},

			compare : function(element, params)
			{
				var srcval = $.trim(element.value);

				var destval = $.trim(this.findByName(element.form, params.destname).val());

				if(destval.length==0){
					return;
				}

				var datatype = params.datatype;

				if(datatype=="number")
				{
					if(!isNaN(srcval) && !isNaN(destval)){
						srcval = parseFloat(srcval);
						destval = parseFloat(destval);
					}
					else{
						return;
					}
				}
				if(datatype=="date" || datatype=="datetime")
				{
					srcval = new Date(srcval);
					destval = new Date(destval)
				}

				var result=false;
				switch(params.op)
				{
				case "=":
					if(srcval == destval){result=true;}
					break;
				case "!=":
					if(srcval != destval){result=true;}
					break;
				case ">":
					if(srcval > destval){result=true;}
					break;
				case ">=":
					if(srcval >= destval){result=true;}
					break;
				case "<": 
					if(srcval < destval){result=true;}
					break;
				case "<=":
					if(srcval <= destval){result=true;}
					break;
				}

				return result;
			},

			ajax : function(element, params){
				this.checkingMap[element.name]=true;
				var rules=this.rulesMap[element.name];
				var messages=rules.messages;

				var name=$(element).attr("name");
				var value=$.trim(element.value)
				var data = {};
				data[name] = value;
				var _this=this;
				$.ajax({
					type: "POST",
					async: true,
					cache:false,
					url: params,
					data: data,
					dataType: "text",
					success: function(data){
						_this.checkingMap[element.name]=false;
						data=$.trim(data).split(/\||;|&|\,/);//用|或;或&或,隔开

						if(data[0]=="yes"||data[0]=="ok"||data[0]=="success"||data[0]==true||data[0]=="1"){
							_this.isvalidMap[element.name]=true;
							_this.showOk(element,rules.messages.ok);
							
							if( _this.isCheckAll()){
								_this.settings.submitCallback();
							}
						}
						else if(data[0]=="no"||data[0]=="error"||data[0]=="failure"||data[0]==false||data[0]=="0"){
							if(data[1]==undefined){
								data[1]=rules.messages.ajaxError;
							}

							_this.isvalidMap[element.name]=false;
							_this.showError(element, data[1]);
						}
						else{
							_this.isvalidMap[element.name]=false;
							_this.showWarn(element, rules.messages.ajaxError);
						}
					},
					error: function(data){
						_this.checkingMap[element.name]=false;
						if(data.statusText!=="abort"){
							_this.isvalidMap[element.name]=false;
							_this.showError(element, rules.messages.ajaxError);
						}
					},
					beforeSend : function(xhr){
						_this.isvalidMap[element.name]=undefined;
						//再服务器没有返回数据之前，先回调提交按钮
						_this.showWait(element, rules.messages.ajaxWait);
					}
				});
			}
		},


		showWarn:function(element, msg){
			this.showMsg(element, msg, 'warn');
		},

		showError:function(element, msg){
			this.showMsg(element, msg, 'error');
		},

		showOk:function(element, msg){
			this.showMsg(element, msg, 'ok');
		},

		showWait:function(element, msg){
			this.showMsg(element, msg, 'wait');
		},
		//msgType:0-空值; 1-错误；2-正确；3-加载中
		showMsg: function(element, msg, msgType) {
			this.removeMsg(element);
			if(msgType=='warn')
			{
				$(element).parent().append('<label class="valid-msg-warn">' + msg + '</label>');
			}
			else if(msgType=='error')
			{
				$(element).addClass('valid-ele-error');
				$(element).parent().append('<label class="valid-msg-error">' + msg + '</label>');
			}
			else if(msgType=='ok')
			{
				$(element).parent().append('<label class="valid-msg-ok">'+msg+'</label>');
			}
			else if(msgType=='wait')
			{
				$(element).parent().append('<label class="valid-msg-wait">'+msg+'</label>');
			}
		},

		removeMsg: function(element) {
			$(element).removeClass('valid-ele-error');
			$(element).parent().find("[class^=valid-msg]").remove();
		},

		findByName: function(formEle, name) {
			// select by name and filter by form for performance over form.find("[name=...]")
			var form = formEle;
			return $(document.getElementsByName(name)).map(function(index, element) {
				return element.form == formEle && element.name == name && element  || null;
			});
		},

		getLength: function(element) {
			var nodeName=element.nodeName.toLowerCase();
			if(nodeName=='select'){
				return $("option:selected", element).length;
			}
			else if(nodeName=='input'){
				var type=element.type.toLowerCase()
				if ( type=='radio'|| type=='checkbox' ){
					return $("input[name='"+element.name+"']").filter(':checked').length;
				}
				else{
					return $.trim(element.value).length;
				}
			}
			else{
				return $.trim(element.value).length;
			}
		},
		
		format: function(source, params) {
			if ( params.constructor != Array ) {
				params = [ params ];
			}
			$.each(params, function(i, n) {
				source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
			});
			return source;
		}
	}
});

})(jQuery); 

