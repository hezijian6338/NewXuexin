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
	<!--添加弹出框  -->
	<div>
		<div class="space_flow_20"></div>
		<form id="addForm" method="post">
			<div class="edit_winRow">
				<div class="edit_left">选课课号:</div>
				<div class="edit_right">
					<input type="text" id="selectedcourseno" name="selectedcourseno"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'选课课号!',validType:'maxLength[50]'"
						value="">
				</div>
			</div>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox"
						id="studentno" name="studentno"
						data-options="required:true,missingMessage:'学号必填!',validType:'maxLength[20]'" value="">
				</div>
			</div>

			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
				<div class="edit_right">
					<input type="text" id="stuname" name="stuname"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'姓名必填!',validType:'maxLength[20]'" value="">
				</div>
			</div>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:</div>
				<div class="edit_right">
					<input type="radio" class="radio-style" name="sex" id="sex0"
						value="0" checked="checked" />男 <input type="radio" id="sex1"
						class="radio-style" name="sex" value="1" />女
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级:</div>
				<div class="edit_right">
					<input id="classname" type="text"
						class="easyui-validatebox l_textbox" name="classname"
						data-options="validType:'maxLength[100]'" value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">考勤日期:</div>
				<div class="edit_right">
					<input id="attendanceTime" type="text"
						class="easyui-datebox l_textbox" name="attendanceTimeStr"
						data-options="formatter:myformatter,parser:myparser,required:true"
						value="">
				</div>
			</div>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;考勤结果:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="attendanceStatus" name="attendanceStatus"
						rows="6" cols="30" data-options="validType:'maxLength[30]'"></textarea>
				</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>
			<div class="edit_btn">
				<a id="btnsummit" class="easyui-linkbutton" onclick="doSave()"
					data-options="iconCls:'icon-save'">保存</a>
			</div>
			<div class="edit_btn">
				<a id="btncancer" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="doClose()">取消</a>
			</div>

		</form>
	</div>
</body>
</html>
<script type="text/javascript">
/* 	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
	}); */
	//表单确定提交按钮
	function doSave() {

		if ($('#addForm').form('validate')) {
			$.post("tea/attendanceDetailAction_save.action", $("#addForm")
					.serialize(), function callback(txt) {

				var json = $.parseJSON(txt);
				if (json.status == "ok") {
					$.messager.show({
						title : '提示信息',
						msg : '保存成功',
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					doClose();
				} else {
					$.messager.show({
						title : '提示信息!',
						msg : '学号或姓名或选课号不存在!',
						showType : 'fade',
						timeout : 3000,
						style : {
							right : '',
							bottom : ''
						}
					});
				}
			});
		}

	}

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_add');
	}
</script>