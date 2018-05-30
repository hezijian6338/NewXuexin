 function initWindow(id,title,width,height,url,close){
	$('<div></div>').window({
		id : id,
        title : title,
        width : width,
        height : height,
        closed : false,
        cache : false,
        //href : url,
        modal : true,
		minimizable : true,
		maximizable : true,
		resizable : true,
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
				$(this).window("move", {
					left : 0,
					top : top
				});
			} else if ((left + dialogwidth) > (bodyWidth - 50)) {
				right = bodyWidth - dialogwidth - 50;
				$(this).window("move", {
					left : right,
					top : top
				});
			}
			if (top < 0) {
				$(this).window("move", {
					left : left,
					top : 0
				});
			} else if (top > (bodyHeight - dialogHeight - 50)) {
				bottom = bodyHeight - dialogHeight - 50;
				$(this).window("move", {
					left : left,
					top : bottom
				});
			}
		},
		onBeforeOpen: function(){
			var right, bottom;
			var bodyWidth = $("body").width();
			var bodyHeight = $("body").height();
			var dialogwidth = $(this).width();
			var dialogHeight = $(this).height();
			var top = (bodyHeight-dialogHeight)*0.5;
			var left = (bodyWidth-dialogwidth)*0.5;
			if (left < 0) {
				$(this).window("resize", {
					left : 0,
					top : top
				});
			} else if ((left + dialogwidth) > (bodyWidth - 50)) {
				right = bodyWidth - dialogwidth - 50;
				$(this).window("resize", {
					left : right,
					top : top
				});
			}
			if (top < 0) {
				$(this).window("resize", {
					height: bodyHeight,
					left : left,
					top : 0
				});
			} else if (top > (bodyHeight - dialogHeight - 50)) {
				bottom = bodyHeight - dialogHeight - 50;
				$(this).window("resize", {
					left : left,
					top : bottom
				});
			}
		},
		onClose : function() {
            $(this).dialog('destroy');
            
        },
		onBeforeClose:function(){ 
			if(close){
            	close();
            }
	   }
	});
	
	var win = $("#"+id);
	win.append("<iframe id='"+id+"_iframe' src='"+url+"' frameborder='0' style='width:100%;height:100%;'> </iframe>");
} 