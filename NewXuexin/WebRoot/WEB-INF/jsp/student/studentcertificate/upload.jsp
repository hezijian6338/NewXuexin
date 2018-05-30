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
</head>
<body>
	<div class="space_flow_20"></div>
	<!-- 表单区 -->
	<form action="stu/studentCertificateAction_uploadAttachment.action" method="post"
		id="importForm" enctype="multipart/form-data" target="uploadFrame">
		<input type="hidden" id="sid" name="sid" value="" />
		<div class="edit_winRow">
			<div class="edit_left">文件</div>
			<div class="edit_right">
				<input type="file" name="attachment" id="attachment"
					class="l_filebox" />
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
	var id = '${param.id}';
	$(document).ready(function() {
		$("#uploadFrame").bind("load", uploadResult);
	});
	//上传
	function doImport() {
		var val = $("#attachment").val();
		if (val) {
			$("#sid").val(id);
			$("#importForm").submit();
		} else {
			$.messager.show({
				title : '提示信息',
				msg : '请先选择attachment文件',
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
			$.messager.alert('操作提示', "上传附件成功！", 'info', function() {
				doClose();
			});
		} else {
			$.messager.alert('操作提示', "发生未知异常,请联系管理员!", 'info', function() {
				doClose();
			});
		}
	}
</script>
