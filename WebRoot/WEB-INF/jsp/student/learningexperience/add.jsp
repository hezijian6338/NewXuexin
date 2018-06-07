<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
							<c:if test="${!isStu }">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
							</c:if>
							<div class="edit_winRow">
								<div class="edit_left">学习期间:</div>
								<div class="edit_right">
									<input id="duration" type="text"
										class="easyui-validatebox l_textbox" name="duration"
										data-options="required:true,missingMessage:'必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">学校名称:</div>
								<div class="edit_right">
									<input id="schoolname" type="text"
										class="easyui-validatebox l_textbox" name="schoolname"
										data-options="required:true,missingMessage:'必填!'" value="">
								</div>
							</div>

						</div>
					</td>
					<td>

						<div style="width: 350px;">
							<c:if test="${!isStu }">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="studentno" name="studentno"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							</c:if>
							<div class="edit_winRow">
								<div class="edit_left">担任职务:</div>
								<div class="edit_right">
									<input id="duty" type="text"
										class="easyui-validatebox l_textbox" name="duty"
										data-options="" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;证明人:</div>
								<div class="edit_right">
									<input id="witness" type="text"
										class="easyui-validatebox l_textbox" name="witness"
										data-options="" value="">
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
	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {

			if ($('#addForm').form('validate')) {
				$.post('${isStu?"stu/learningExperienceAction_saveSelf.action":"stu/learningExperienceAction_save.action"}',
						$("#addForm").serialize(), function callback(txt) {

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
									msg : '保存失败或学号不存在!',
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

	}

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_add');
	}
</script>