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
<script type="text/javascript" src="js/common/loading.js"></script>
</head>
<body>
	<div class="space_flow_20"></div>
	<!-- 表单区 -->
	<form action="tea/courseInfoStudentsAction_importScoreFile.action" method="post"
		id="importForm" enctype="multipart/form-data" target="uploadFrame">
		<div class="edit_winRow">
			<div class="edit_left">文件</div>
			<div class="edit_right">
				<input type="file" name="excel" id="excel" class="l_filebox"
					accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
			</div>
		</div>
		<div class="edit_winRow">
			<div class="edit_left">模板下载</div>
			<div class="edit_right">
				<a style="text-decoration: none;" href="tea/courseInfoStudentsAction_exportImportScoreTemplate.action">《学期成绩总表导入模板》</a>
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
		var val = $("#excel").val();
		if (val) {
			MaskUtil.mask();
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
		//取消遮罩
		MaskUtil.unmask();
		var code = document.uploadFrame.resultCode;
		var totalCount = document.uploadFrame.totalCount;
		var insertCount = document.uploadFrame.insertCount;
		var updateCount = document.uploadFrame.updateCount;
		var errorCount = document.uploadFrame.errorCount;
		var normalcount = document.uploadFrame.normalcount;
		var resitcount = document.uploadFrame.resitcount;
		var repaircount = document.uploadFrame.repaircount;
		if (code == 0) {
			
			var message = "共" + totalCount + "条数据!，其中更新了" + updateCount
					+ "条信息,新插入"+insertCount+"条信息,异常信息" + errorCount + "条。";

			$.messager.alert('操作提示', message, 'info', function() {

				doClose();

			});

		} else if (code == 1) {
			$.messager.alert('操作提示', "只能上传Excel文件!", 'info', function() {

				doClose();

			});
		}
	}
</script>
