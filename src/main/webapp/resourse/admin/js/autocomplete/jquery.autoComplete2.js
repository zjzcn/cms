
/*
	jquery自动完成插件
	add by QinJiaXiang at 2012-07-05
	param ajaxBoxId  联想输入框ID
	param returnId  返回结果ID
	param resultDivId 结果显示的DIV id
	param dataSourceSrc  请求远程数据源的地址
*/
var AutoComplete = function (ajaxBoxId, returnId, resultDivId, dataSourceSrc) {
	var returnIdObj = $("#" + returnId);
	var hightlightindex = -1;
	var timeoutId;
	var autoNode = $("#" + resultDivId);
	autoNode.hide();
	var wordObj = $("#" + ajaxBoxId);
	var wordOffset = wordObj.offset();
	var left = wordOffset.left;
	autoNode.css("border", "1px solid #333333").css("z-index", "2000").css("background-color", "#FFF").css("top", wordOffset.top + wordObj.height() + 5).css("left", wordOffset.left).css("width", wordObj.width() + 3 + "px");
	wordObj.keyup(function (event) {
		//只有输入字母，才将文本框中的信息发送给服务器
		var myEvent = event || window.Event;
		var keyCode = myEvent.keyCode;
		//alert(keyCode);
		if (keyCode <= 98 || keyCode == 8 || keyCode == 46) {
			var tempUrl = "";
			var stype = $('#stype').attr('value');
			if(stype != null && stype != '' && stype != undefined)
			{
				tempUrl = dataSourceSrc + "?stype="+ stype;
			}
			else
			{
				tempUrl = dataSourceSrc;
			}
			
			returnIdObj.attr('value','');
			var wordText = wordObj.val();
			if (wordText != "") {
				//对上次未完成的延迟操作进行取消
				clearTimeout(timeoutId);
				//对于服务器端进行交互延迟500ms,避免快速打字造成的频繁交互
				timeoutId = setTimeout(function () {
					$.post(tempUrl, {searchTerm:wordText}, function (data) {
						var jqueryObj = $(data);
						var wordNodes = jqueryObj.find("word");
						autoNode.html("");
						wordNodes.each(function (i) {//i为索引
							var wordNode = $(this);
							var wordNodeText = wordNode.text();
							var wordNodeTextArr = wordNodeText.split('|');
							
							var newDivNode = $("<div style='color:#666666'>").attr("id", wordNodeTextArr[1]);
							autoNode.append(newDivNode.html("<span style='padding-left:10px;font-size:12px;width:400px;'>" + wordNodeTextArr[0] + "</span>"));
							newDivNode.mouseover(function () {
								if (hightlightindex != -1) {
									autoNode.children("div").eq(hightlightindex).css("background-color", "white");
								}
								hightlightindex = newDivNode.attr("id");
								$(this).css("background-color", "#D8D8D8").css("color", "#333333").css("cursor", "pointer");
							});
							newDivNode.mouseout(function () {
								$(this).css("background-color", "white").css("color", "#666666");
								hightlightindex = -1;
							});
							newDivNode.click(function () {
								var comText = $(this).text();
								returnIdObj.val($(this).attr('id'));
								wordObj.val(comText);
								autoNode.hide();
								hightlightindex = -1;
							});
						});
						if (wordNodes.length > 0) {
							autoNode.show();
						} else {
							autoNode.hide();
							hightlightindex = -1;
						}
					}, "xml");
				}, 50);
			} else {
				autoNode.hide();
				hightlightindex = -1;
			}
		} else {
			if (keyCode == 38 || keyCode == 40) {
				var autoNodes = autoNode.children("div");
				if (keyCode == 38) {
					if (hightlightindex != -1) {
						autoNodes.eq(hightlightindex).css("background-color", "white");
						hightlightindex--;
					} else {
						hightlightindex = autoNodes.length - 1;
					}
					if (hightlightindex == -1) {
						hightlightindex = autoNodes.length - 1;
					}
					autoNodes.eq(hightlightindex).css("background-color", "red");
				} else {
					if (keyCode == 40) {
						if (hightlightindex != -1) {
							autoNodes.eq(hightlightindex).css("background-color", "white");
						}
						hightlightindex++;
						if (hightlightindex == autoNodes.length) {
							hightlightindex = 0;
						}
						autoNodes.eq(hightlightindex).css("background-color", "red");
					}
				}
			} else {
				if (keyCode == 13) {
					if (hightlightindex != -1) {
						var text = autoNode.hide().children("div").eq(hightlightindex).text();
						wordObj.val(text);
						hightlightindex = -1;
					} else {
						//alert("u6587u672cu6846u4e2du7684  " + wordObj.val() + " u88abu63d0u4ea4u4e86");
					}
				}
			}
		}
	});
};

