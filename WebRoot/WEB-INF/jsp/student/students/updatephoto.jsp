<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="/pub/headmeta.jsp"%>
<link rel="stylesheet" type="text/css" href="css/css_form.css">
<!-- 图片预览 -->
<style type="text/css">
#preview {
	width: 160px;
	height: 190px;
	border: 1px solid #000;
	overflow: hidden;
}

#imghead {
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
}
</style>
<script type="text/javascript">
	//图片上传预览    IE是用了滤镜。
	function previewImage(file) {
		var MAXWIDTH = 260;
		var MAXHEIGHT = 180;
		var div = document.getElementById('preview');
		if (file.files && file.files[0]) {
			if (!checkFileSizeAndType(file.files[0])) {
				file.value = "";
				return;
			}
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.onload = function() {
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
						img.offsetWidth, img.offsetHeight);
				img.width = rect.width;
				img.height = rect.height;
				//                 img.style.marginLeft = rect.left+'px';
				img.style.marginTop = rect.top + 'px';
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				img.src = evt.target.result;
			}
			reader.readAsDataURL(file.files[0]);
		} else //兼容IE
		{
			var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width
					+ ',' + rect.height);
			div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
		}
	}
	function clacImgZoomParam(maxWidth, maxHeight, width, height) {
		var param = {
			top : 0,
			left : 0,
			width : width,
			height : height
		};
		if (width > maxWidth || height > maxHeight) {
			rateWidth = width / maxWidth;
			rateHeight = height / maxHeight;

			if (rateWidth > rateHeight) {
				param.width = maxWidth;
				param.height = Math.round(height / rateWidth);
			} else {
				param.width = Math.round(width / rateHeight);
				param.height = maxHeight;
			}
		}
		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}
	function checkFileSizeAndType(fileInfo) {
		//默认大小
		maxSize = 1024 * 1024;
		//默认类型
		allowType = new Array('jpg', 'png', 'jpeg');
		var types = allowType;
	
		var fileName = fileInfo.name;
		//获取文件后缀名
		var file_typename = fileName.substring(fileName.lastIndexOf('.') + 1,
				fileName.length).toLowerCase();
		//定义标志是否可以提交上传
		var isUpload = true;
		//定义一个错误参数：1代表大小超出 2代表类型不支持
		var errNum = 0;
		if (fileInfo) {
			if (fileInfo.size > maxSize) {
				isUpload = false;
				errNum = 1;
			} else {
				for ( var i in types) {
					if (types[i] == file_typename) {
						isUpload = true;
						return isUpload;
					} else {
						isUpload = false;
						errNum = 2;
					}
				}
			}
		}
		//对错误的类型进行对应的提示
		if (!isUpload) {
			var msg = "文件错误";
			if (errNum == 1) {
				var size = maxSize / 1024 / 1024;
				msg = "上传的文件必须为小于" + size + "M的图片！";
			} else if (errNum == 2) {
				msg = "上传的" + file_typename + "文件类型不支持！只支持" + types.toString()
						+ "格式";
			} else {
				msg = "没有选择文件";
			}
			$.messager.alert('提示信息', msg);
			return isUpload;
		}
	}
</script>
</script>
</head>
<body>
	<div class="space_flow_20"></div>
	<!-- 表单区 -->
	<form
		action='${isStu?"stu/studentsAction_updateSelfPhoto.action":"stu/studentsAction_updatePhoto.action" }'
		method="post" id="importForm" enctype="multipart/form-data"
		target="uploadFrame">
		<input type="hidden" id="stuId" name="stuId" value="" /> <input
			type="hidden" id="studentno" name="studentno" value="" />
		<div class="edit_winRow">
			<div class="edit_left">文件</div>
			<div class="edit_right">
				<div id="preview">
					<img id="imghead" width="100%" height="auto" border="0">
				</div>
				<input type="file" name="photo" id="photo" class="l_filebox"
					onchange="previewImage(this)" />

			</div>
		</div>
		<div class="space_flow_10"></div>
		<div class="edit_btn">
			<a id="btnsummit" class="easyui-linkbutton" onclick="doImport();"
				data-options="iconCls:'icon-save'">上传</a>
		</div>
		<div class="edit_btn">
			<a id="btncancer" class="easyui-linkbutton"
				data-options="iconCls:'icon-redo'" onclick="doClose();">取消</a>
		</div>
	</form>
	<iframe id="uploadFrame" name="uploadFrame" style="display: none;"></iframe>
</body>
</html>

<script type="text/javascript">
	var stuId = '${param.stuId}';
	var studentno = '${param.studentno}';
	$(function() {
		$("#imghead")
				.attr(
						'src',
						${isStu?"'stu/studentsAction_getSelfPhotoFile.action'":"'stu/studentsAction_getPhotoFile.action?studentno='+ studentno"});
	});
	$(document).ready(function() {
		$("#uploadFrame").bind("load", uploadResult);
	});
	//上传
	function doImport() {
		var val = $("#photo").val();
		if (val) {
			$("#stuId").val(stuId);
			$("#studentno").val(studentno);
			$("#importForm").submit();
		} else {
			$.messager.show({
				title : '提示信息',
				msg : '请先选择文件',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
		}
	}
	//取消上传
	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_upload');
	}
	//上传后回调
	function uploadResult() {
		var code = document.uploadFrame.resultCode;

		if (code == 0) {
			$.messager.alert('操作提示', "修改头像成功！", 'info', function() {
				doClose();
			});
		} else if (code == 1) {
			$.messager.alert('操作提示', "只支持后缀为jpg,jpeg,png的照片!", 'info');
		} else if (code == 2) {
			$.messager.alert('操作提示', "文件大小不能超过1兆!", 'info');
		} else {
			$.messager.alert('操作提示', "错误!", 'info');
		}
	}
</script>
