<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<c:if test="${!isStu}">
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
							<c:if test="${!isStu}">
								<div class="edit_winRow">
									<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
									<div class="edit_right">
										<input type="text" class="easyui-validatebox l_textbox"
											id="studentno" name="studentno" disabled="disabled"
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
						</div>
					</td>
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
	var id = '${param.id}';
	$(function() {
		//加载历史数据
		loadData();
	});

	function loadData() {
		$
				.post(
						'${isStu?"stu/learningExperienceAction_getLearningExperienceSelf.action":"stu/learningExperienceAction_getLearningExperience.action"}',
						{
							id : id
						}, function callback(txt) {
							var json = $.parseJSON(txt);
							if (json.status == "ok") {
								var o = json.info;
								$('#id').val(id);
								$('#studentno').val(o.studentno);
								$('#stuname').val(o.stuname);
								$('#duration').val(o.duration);
								$('#schoolname').val(o.schoolname);
								$('#duty').val(o.duty);
								$('#witness').val(o.witness);
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
		if ($('#addForm').form('validate')) {
			$
					.ajax({
						type : 'post',
						url : '${isStu?"stu/learningExperienceAction_updateSelf.action":"stu/learningExperienceAction_update.action"}',
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#editForm").serialize(),
						success : function(result) {
							//关闭窗口
							$('#dg').datagrid('reload');//刷新
							$.messager.show({
								title : '提示信息',
								msg : "保存成功",
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							doClose();
						},
						error : function(result) {
							$.meesager.show({
								title : '提示信息',
								msg : "保存失败",
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							//doClose();
						}

					});

		}

	}

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_add');
	}
</script>