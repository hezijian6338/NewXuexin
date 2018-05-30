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
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox"
						id="studentno" name="studentno"
						data-options="required:true,missingMessage:'学号必填!'" value="">
				</div>
			</div>

			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
				<div class="edit_right">
					<input type="text" id="stuname" name="stuname"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'姓名必填!'" value="">
				</div>
			</div>

			<div class="edit_winrow">
				<div class="edit_left">入党团时间:</div>
				<div class="edit_right">
					<input id="joindate" type="text" class="easyui-datebox l_textbox"
						name="joindate"
						data-options="formatter:myformatter,parser:myparser,required:true"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;政治面貌:</div>
				<div class="edit_right">
						<select id="politicalstatus" name="politicalstatus" value=""
							class="easyui-combobox" editable="false">
							<option value="共青团员" selected="selected">共青团员</option>
							<option value="预备党员">预备党员</option>
							<option value="共产党员">共产党员</option>
						</select>
				</div>
			</div>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="memo" name="memo" rows="4"
						cols="40"></textarea>
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
	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.post("stu/politicalStatusAction_save.action", $("#addForm")
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
						msg : '保存失败!!可能以下原因：<br/>1.学号不存在或信息填写错误<br/>2.已添加相关党员关系!',
						showType : 'fade',
						timeout : 3000,
						style : {
							right : '',
							bottom : '',
						 
							
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