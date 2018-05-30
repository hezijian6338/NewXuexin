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
</head>
<body>
	<!--编辑弹出框  -->
	<div>
		<div class="nav">
			<div class="nav_left">学生基本信息管理&nbsp;&gt;&gt;&nbsp;修改密码</div>
		</div>
		<div class="edit-line"></div>
		<div class="space_flow_20"></div>
		<div class="space_flow_20"></div>
		<form id="editForm" action="" method="post">
			<input type="hidden" id="stuId" name="stuId" value="" />
			<!-- <div class="edit_row">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
				<div class="edit_right">
					<input type="password" class="easyui-validatebox l_textbox"
						id="employNo" name="employNo"
						data-options="required:true,missingMessage:'学号必填!'" value="">
				</div>
			</div> -->
			<div class="edit_row">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;新密码:</div>
				<div class="edit_right">
					<input type="password" class="easyui-validatebox l_textbox"
						id="password" name="password"
						data-options="required:true,missingMessage:'密码必填!',validType:'minLength[6]'"
						value="">
				</div>
			</div>
			<div class="edit_row">
				<div class="edit_left">确认密码:</div>
				<div class="edit_right">
					<input type="password" class="easyui-validatebox l_textbox"
						id="password2" name="password2"
						data-options="required:true,missingMessage:'密码必填!',validType:'minLength[6]'"
						value="">
				</div>
			</div>
			<div class="space_flow_20"></div>
			<div class="edit_btn">
				<a id="btnsummit" class="easyui-linkbutton" onclick="doSave()"
					data-options="iconCls:'icon-save'">保存</a>
			</div>
			<div class="edit_btn">
				<a id="btncancer" class="easyui-linkbutton"
					data-options="iconCls:'icon-redo'" onclick="resetForm('editForm')">重置</a>
			</div>
			<div class="edit_btn">
				<a id="btncancer" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="doClose()">关闭</a>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var stuId = '${param.stuId}';
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
	});
	//表单重置
	function resetForm(formId) {
		$('#' + formId).form('clear');
	};
	//表单确定提交按钮
	function doSave() {
		if ($('#editForm').form('validate')) {
			if ($('#password').val() == $('#password2').val()) {
				$('#stuId').val(stuId);
				$.post("stu/studentsAction_updatePwd.action", $("#editForm")
						.serialize(), function callback(txt) {
					var json = $.parseJSON(txt);
					if (json.status == "ok") {
						$.messager.show({
							title : '提示信息',
							msg : json.message,
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						//setTimeout(doClose(), 5000);
					} else {
						$.messager.show({
							title : '提示信息!',
							msg : json.message,
							showType : 'fade',
							timeout : 3000,
							style : {
								right : '',
								bottom : ''
							}
						});
					}
				});
			} else {
				$.messager.show({
					title : '提示信息!',
					msg : '密码不一致',
					showType : 'fade',
					timeout : 3000,
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		}
	}
	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_editpwd');
	}
</script>