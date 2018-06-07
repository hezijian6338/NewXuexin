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
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">培训日期:</div>
								<div class="edit_right">
									<input id="trainsdate" type="text"
										class="easyui-datebox l_textbox" name="trainsDateStr"
										data-options="formatter:myformatter,parser:myparser,required:true"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">培训编号:</div>
								<div class="edit_right">
									<input type="text" id="traincode" name="traincode"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'不能为空!',validType:'maxLength[20]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">
									培训对象<br>年级:
								</div>
								<div class="edit_right">
									<input type="text" id="trainsgrade" name="trainsgrade"
										class="easyui-validatebox l_textbox"
										data-options="validType:'maxLength[40]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;负责人:</div>
								<div class="edit_right">
									<input id="manager" type="text"
										class="easyui-validatebox l_textbox" name="manager"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">培训主题:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="trainstopic"
										name="trainstopic" rows="4" cols="20"
										data-options="validType:'maxLength[200]'"></textarea>
								</div>
							</div>
						</div>
					</td>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">培训内容:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="trainscontent"
										name="trainscontent" rows="4" cols="20"
										data-options="validType:'maxLength[500]'"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">培训地点:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="trainsaddress"
										name="trainsaddress" rows="4" cols="20"
										data-options="validType:'maxLength[200]'"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="memo" name="memo" rows="4"
										cols="20" data-options="validType:'maxLength[200]'"></textarea>
								</div>
							</div>
						</div></td>
				</tr>

				<tr>
					<td></td>
					<td><a id="btnsummit" class="easyui-linkbutton"
						onclick="doSave()">确定</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
						id="btncancer" class="easyui-linkbutton" onclick="doClose()">取消</a>
					</td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
	});

	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.post("tea/trainInfoMasterAction_save.action", $("#addForm")
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
						msg : '保存失败或编号已存在!',
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