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
	<form action="stu/studentsAction_importFile" method="post"
		id="importForm" enctype="multipart/form-data" target="uploadFrame">
		<div class="edit_winRow">
			<div class="edit_left">文件</div>
			<div class="edit_right">
				<input type="file" name="stuFile" id="stuFile" class="l_filebox"
					accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
			</div>
		</div>
		<div class="edit_winRow">
			<div class="edit_left">模板下载</div>
			<div class="edit_right">
				<a style="text-decoration: none;" href="stu/studentsAction_exportTemplate.action">《网站配置导入模板》</a>
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
	$(document).ready(function() {
		$("#uploadFrame").bind("load", uploadResult);
	});
	//上传
	function doImport() {
		var val = $("#stuFile").val();
		if (val) {
			$("#importForm").submit();
		} else {
			$.messager.show({
				title : '提示信息',
				msg : '请先选择excel文件',
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
		window.parent.closeWindow('#dg_import');
	}
	//上传后回调
	function uploadResult() {
		var code = document.uploadFrame.resultCode;
		var total = document.uploadFrame.importCount;
		var insertCount = document.uploadFrame.insertCount;
		var updateCount = document.uploadFrame.updateCount;
		if (code == 0) {
			var message = "共导入" + total + "条数据!，其中" + updateCount
					+ "条重复URL信息已被处理，有效信息" + insertCount + "条。";

			$.messager.alert('操作提示', message, 'info', function() {

				doClose();

			});

		} else if (code == 1) {
			$.messager.alert('操作提示', "只能上传Excel文件!", 'info', function() {

				doClose();

			});
		} else {
			$.messager.alert('操作提示', "发生未知异常,请联系管理员!", 'info', function() {

				doClose();

			});
		}
	}
</script>
