// 格式化时间------------------start
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}

function myformatter2(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d)
			+ ' ' + (h < 10 ? ('0' + h) : h) + ':'
			+ (min < 10 ? ('0' + min) : min) + ':'
			+ (sec < 10 ? ('0' + sec) : sec);
}

function myparser2(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	var h = s.substring(ss[3], 10);
	var min = s.substring(ss[4], 10);
	var sec = s.substring(ss[5], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min)
			&& !isNaN(sec)) {
		return new Date(y, m - 1, d, h, min, sec);
	} else {
		return new Date();
	}
}

//=========================================日期格式化
//毫秒日期格式化为yyyy-MM-dd HH:mm:ss
function formatDate(time) {
	if(time==null||time==""){
		return "";
	}
	var date = new Date(time);
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var H = date.getHours();
	var n = date.getMinutes();
	var s = date.getSeconds();
	return date.getFullYear() + "-" + (m < 10 ? ('0' + m) : m) + "-" + (d < 10 ? ('0' + d) : d) + " " + (H < 10 ? ('0' + H) : H) + ":" + (n < 10 ? ('0' + n) : n) + ":" + (s < 10 ? ('0' + s) : s);
}

//毫秒格式化日期为yyyy-MM-dd
function formatShortDate(time) {
	if(time==null||time==""){
		return "";
	}
	var date = new Date(time);
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return date.getFullYear() + "-" + (m < 10 ? ('0' + m) : m) + "-" + (d < 10 ? ('0' + d) : d);
}

//yyyyMMddHHmmss日期格式化为yyyy-MM-dd HH:mm:ss
function formatDate2(s) {
	return s.substr(0, 4) + "-" + s.substr(4, 2) + "-" + s.substr(6, 2) + " " + s.substr(8, 2) + ":" + s.substr(10, 2) + ":" + s.substr(12, 2);
}

// 格式化时间------------------end
// js方法：序列化表单
function serializeForm(form) {
	alert(form.serialize());
	var obj = {};
	$.each(form.serializeArray(), function(index) {
		if (obj[this['name']]) {
			obj[this['name']] = obj[this['name']] + ',' + this['value'];
		} else {
			obj[this['name']] = this['value'];
			alert(obj[this['name']] + ',' + this['value']);
		}
	});
	return obj;
}

// 设置dialog显示样式
function setDialog(id) {
	$(id).dialog({
		// collapsible:true,
		// minimizable:true,
		// maximizable:true,
		resizable : true,
		modal : true, // 是否是模式对话框
		closed : true,
		// width:fixWidth(0.4),
		// height:200,
		fitColumns : true,// 适应父容器的大小
		// doSize:true,
		// 事件绑定
		onMove : function(left, top) {
			var right, bottom;
			// alert($("body").width()+"---"+left +"----"+
			// $("#divDialog").width()+"="+($("#divDialog").width()+left));
			var bodyWidth = $("body").width();
			var bodyHeight = $("body").height();
			var dialogwidth = $(id).width();
			var dialogHeight = $(id).height();
			if (left < 0) {
				$(id).dialog("move", {
					left : 0,
					top : top
				});
			} else if ((left + dialogwidth) > (bodyWidth - 50)) {
				right = bodyWidth - dialogwidth - 50;
				$(id).dialog("move", {
					left : right,
					top : top
				});
			}
			if (top < 0) {
				$(id).dialog("move", {
					left : left,
					top : 0
				});
			} else if (top > (bodyHeight - dialogHeight - 50)) {
				bottom = bodyHeight - dialogHeight - 50;
				$(id).dialog("move", {
					left : left,
					top : bottom
				});
			}
		}
	});
}

// 设置window显示样式
function openWindow(id) {
	$(id).window({
		minimizable : true,
		maximizable : true,
		resizable : true,
		modal : true, // 是否是模式对话框
		closed : true,
		doSize: false,
		fitColumns : true,// 适应父容器的大小
		// 事件绑定
		onMove : function(left, top) {
			var right, bottom;
			var bodyWidth = $("body").width();
			var bodyHeight = $("body").height();
			var dialogwidth = $(id).width();
			var dialogHeight = $(id).height();
			if (left < 0) {
				$(id).window("move", {
					left : 0,
					top : top
				});
			} else if ((left + dialogwidth) > (bodyWidth - 50)) {
				right = bodyWidth - dialogwidth - 50;
				$(id).window("move", {
					left : right,
					top : top
				});
			}
			if (top < 0) {
				$(id).window("move", {
					left : left,
					top : 0
				});
			} else if (top > (bodyHeight - dialogHeight - 50)) {
				bottom = bodyHeight - dialogHeight - 50;
				$(id).window("move", {
					left : left,
					top : bottom
				});
			}
		},
		onBeforeOpen: function(){
			var right, bottom;
			var bodyWidth = $("body").width();
			var bodyHeight = $("body").height();
			var dialogwidth = $(id).width();
			var dialogHeight = $(id).height();
			var top = (bodyHeight-dialogHeight)*0.5;
			var left = (bodyWidth-dialogwidth)*0.5;
			if (left < 0) {
				$(id).window("resize", {
					left : 0,
					top : top
				});
			} else if ((left + dialogwidth) > (bodyWidth - 50)) {
				right = bodyWidth - dialogwidth - 50;
				$(id).window("resize", {
					left : right,
					top : top
				});
			}
			if (top < 0) {
				$(id).window("resize", {
					height: bodyHeight,
					left : left,
					top : 0
				});
			} else if (top > (bodyHeight - dialogHeight - 50)) {
				bottom = bodyHeight - dialogHeight - 50;
				$(id).window("resize", {
					left : left,
					top : bottom
				});
			}
		}
	});
}

//设置window显示样式
function alertWindow(id) {
	$(id).window({
		minimizable : false,
		maximizable : false,
		resizable : false,
		modal : true, // 是否是模式对话框
		closed : true,
		doSize: false,
		collapsible: false,
		fitColumns : false,// 适应父容器的大小
		// 事件绑定
		onMove : function(left, top) {
			var right, bottom;
			var bodyWidth = $("body").width();
			var bodyHeight = $("body").height();
			var dialogwidth = $(id).width();
			var dialogHeight = $(id).height();
			if (left < 0) {
				$(id).window("move", {
					left : 0,
					top : top
				});
			} else if ((left + dialogwidth) > (bodyWidth - 50)) {
				right = bodyWidth - dialogwidth - 50;
				$(id).window("move", {
					left : right,
					top : top
				});
			}
			if (top < 0) {
				$(id).window("move", {
					left : left,
					top : 0
				});
			} else if (top > (bodyHeight - dialogHeight - 50)) {
				bottom = bodyHeight - dialogHeight - 50;
				$(id).window("move", {
					left : left,
					top : bottom
				});
			}
		},
		onBeforeOpen: function(){
			var right, bottom;
			var bodyWidth = $("body").width();
			var bodyHeight = $("body").height();
			var dialogwidth = $(id).width();
			var dialogHeight = $(id).height();
			var top = (bodyHeight-dialogHeight)*0.5;
			var left = (bodyWidth-dialogwidth)*0.5;
			if (left < 0) {
				$(id).window("resize", {
					left : 0,
					top : top
				});
			} else if ((left + dialogwidth) > (bodyWidth - 50)) {
				right = bodyWidth - dialogwidth - 50;
				$(id).window("resize", {
					left : right,
					top : top
				});
			}
			if (top < 0) {
				$(id).window("resize", {
					height: bodyHeight,
					left : left,
					top : 0
				});
			} else if (top > (bodyHeight - dialogHeight - 50)) {
				bottom = bodyHeight - dialogHeight - 50;
				$(id).window("resize", {
					left : left,
					top : bottom
				});
			}
		}
	});
}
