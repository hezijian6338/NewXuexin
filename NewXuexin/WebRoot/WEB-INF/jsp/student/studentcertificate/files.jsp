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
	<form action="stu/studentCertificateAction_downloadFile.action"
		method="post" id="importForm" target="uploadFrame">
		<input type="hidden" id="sid" name="sid" value="" />
		<div class="edit_winRow">
			<div class="edit_left">文件</div>
			<div class="edit_right">
				<select id="attachmentFileName" name="attachmentFileName"
					style="width:250px;height: 25px;display: block;">
				</select>
			</div>
		</div>
		<div class="space_flow_10"></div>
		<div class="edit_btn">
			<a class="easyui-linkbutton" onclick="downloadFile();"
				data-options="iconCls:'icon-save'">下载</a>
		</div>
		<div class="edit_btn">
			<a class="easyui-linkbutton" onclick="deleteFile();"
				data-options="iconCls:'icon-remove'">删除</a>
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
		//加载历史数据
		loadData();
		$("#uploadFrame").bind("load", uploadResult);

	});
	function loadData() {
		$.post("stu/studentCertificateAction_getStudentCertificate.action", {
			sid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#sid').val(id);
				var files = o.docpath;
				if (files != "") {
					var names = files.split(",");
					var ops = "";
					for ( var i = 0; i < names.length; i++) {
						ops += "<option value="+names[i]+">" + names[i]
								+ "</option>";
					}
					$('#attachmentFileName').html(ops);
				}

			} else {
				$.messager.show({
					title : '提示信息',
					msg : '数据加载失败',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		});
	}
	//下载
	function downloadFile() {
		var val = $("#attachmentFileName").val();
		if (val) {
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

	function deleteFile() {
		$.post("stu/attendanceMasterAction_delteFile.action", $("#importForm")
				.serialize(), function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				$.messager.show({
					title : '提示信息',
					msg : '操作成功',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				loadData();

			} else {
				$.messager.show({
					title : '提示信息',
					msg : '操作失败',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		});
	}

	//取消上传
	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_download');
	}
</script>
