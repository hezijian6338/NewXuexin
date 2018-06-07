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
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
				<div class="edit_right">
					<input id="academicyear" type="text"
						class="easyui-validatebox l_textbox" name="academicyear"
						data-options="validType:'maxLength[20]'" value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
				<div class="edit_right">
					<input id="term" type="text" class="easyui-validatebox l_textbox"
						name="term" data-options="validType:'maxLength[20]'" value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">课程名称:</div>
				<div class="edit_right">
					<input id="coursename" type="text"
						class="easyui-validatebox l_textbox" name="coursename"
						data-options="required:true,missingMessage:'课程名称!',validType:'maxLength[50]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">选课课号:</div>
				<div class="edit_right">
					<input type="text" id="selectedcourseno" name="selectedcourseno"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'选课课号!',validType:'maxLength[50]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">教师姓名:</div>
				<div class="edit_right">
					<input id="employName" type="text"
						class="easyui-validatebox l_textbox" name="employName"
						data-options="required:true,missingMessage:'教师姓名!',validType:'maxLength[20]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">教师工号:</div>
				<div class="edit_right">
					<input type="text" id="employNo" name="employNo"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'教师工号!',validType:'maxLength[10]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">开课单位:</div>
				<div class="edit_right">
					<input id="orgId" type="text" class="easyui-combotree l_textbox"
						name="orgId" data-options="required:true" value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">上课时间:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="schooltime" name="schooltime"
						rows="4" cols="25" data-options="validType:'maxLength[100]'"></textarea>
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">上课地点:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="address" name="address" rows="4"
						cols="25" data-options="validType:'maxLength[100]'"></textarea>
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="memo" name="memo" rows="4"
						cols="25" data-options="validType:'maxLength[200]'"></textarea>
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
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		//组织机构下拉框
		$('#orgId').combotree({
			url : 'sys/organizationAction_getOrgTree.action',
			editable : false,
			loadFilter : function(data) {
				return data.result;
			}
		});
	});

	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.post("tea/attendanceMasterAction_save.action", $("#addForm")
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
						msg : '保存失败或选课号已存在!',
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