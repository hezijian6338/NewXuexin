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
		<form id="editForm" method="post">
			<input type="hidden" id="id" name="id" value="" />
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox"
						id="studentno" name="studentno" disabled="disabled"
						data-options="required:true,missingMessage:'学号必填!'" value="">
				</div>
			</div>

			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
				<div class="edit_right">
					<input type="text" id="stuname" name="stuname"
						class="easyui-validatebox l_textbox" disabled="disabled"
						data-options="required:true,missingMessage:'姓名必填!'" value="">
				</div>
			</div>

			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;导学时间:</div>
				<div class="edit_right">
					<input id="guiddate" type="text" class="easyui-datebox l_textbox"
						name="guiddateStr"
						data-options="formatter:myformatter,parser:myparser,required:true"
						value="">
				</div>
			</div>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;导学地点:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="guidaddress" name="guidaddress"
						rows="6" cols="30"></textarea>
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;导学内容:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="guidcontent" name="guidcontent"
						rows="6" cols="30"></textarea>
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;电子文档:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="docpath" name="docpath" rows="6"
						cols="30"></textarea>
				</div>
			</div>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="memo" name="memo" rows="6"
						cols="30"></textarea>
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
	var id = '${param.id}';
	$(function() {
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("stu/learningGuidInfoAction_getLearningGuidInfo.action", {
			lgId : id
		},
				function callback(txt) {
					var json = $.parseJSON(txt);
					if (json.status == "ok") {
						var o = json.info;
						$('#id').val(id);
						$('#studentno').val(o.studentno);
						$('#stuname').val(o.stuname);
						$('#guiddate').datebox('setValue',
								formatShortDate(o.guiddate));
						$('#guidcontent').val(o.guidcontent);
						$('#guidaddress').val(o.guidaddress);
						$('#docpath').val(o.docpath);
						$('#memo').val(o.memo);
					} else {
						$.messager.show({
							title : '提示信息',
							msg : '数据加载失败',
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
					}
				});
	}

	//表单确定提交按钮
	function doSave() {
		if ($('#editForm').form('validate')) {
			$.post(
					"stu/learningGuidInfoAction_update.action?studentno="
							+ $('#studentno').val(), $("#editForm").serialize(),
					function callback(txt) {
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
								msg : '该日期已存在!',
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
		window.parent.closeWindow('#dg_edit');
	}
</script>