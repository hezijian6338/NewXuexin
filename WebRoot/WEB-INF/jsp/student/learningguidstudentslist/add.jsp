<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<div class="edit_left">&nbsp;&nbsp;&nbsp;专业班级:</div>
				<div class="edit_right">
					<input id="classname" type="text"
						class="easyui-validatebox l_textbox" name="classname"
						data-options="" value="">
				</div>
			</div>
			<%--2017/3/29 余锡鸿 导学名单保存新增界面提示导学老师编辑栏目为导师姓名必填--%>
			<c:if test="${!isAdmin}">
			<div class="edit_winrow">
				<s:set var="teachername" value="#session.session_user.employName" />
				<div class="edit_left">&nbsp;&nbsp;&nbsp;导学老师:</div>
				<div class="edit_right">
					<input id="teachername" type="text"
						class="easyui-validatebox l_textbox" name="teachername"  readonly  style="border:0px;"
						data-options="required:true,missingMessage:'导师姓名必填!'" value="${teachername}">
				</div>
			</div>
			</c:if>
			<c:if test="${isAdmin}">
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;导学老师:</div>
				<div class="edit_right">
					<input id="teachername" type="text"
						class="easyui-validatebox l_textbox" name="teachername"
						data-options="required:true,missingMessage:'导师姓名必填!'" value="">
				</div>
			</div>
			</c:if>
			<%--2017/4/12 余锡鸿 导学名单保存新增界面将学年文本框修改成动态下拉列表--%>
			<%--2017/4/13 余锡鸿 导学名单保存新增界面将学年动态下拉列表修改成必选项--%>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
				<div class="edit_right">
					<select id="academicyear" name="academicyear"
						class="easyui-validatebox l_combobox"
						style="background-color:white"
						data-options="required:true,missingMessage:'学年必选!'" value="">
						<option value="" selected>请选择：</option>
						<%
							Calendar cal = Calendar.getInstance();
							int year = cal.get(Calendar.YEAR) + 2;
							for (int i = 0; i <= 5; i++) {
								String str = String.valueOf(year);
								year = year - 1;
								String str1 = String.valueOf(year);
								String str2 = str1 + "-" + str;
						%>
						<option><%=str2%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<%--2017/4/12 余锡鸿 导学名单保存新增界面将学期文本框修改成下拉列表--%>
			<%--2017/4/13 余锡鸿 导学名单保存新增界面将学期下拉列表修改成必选项--%>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
				<div class="edit_right">
					<select id="term" name="term" class="easyui-validatebox l_textbox"
						style="background-color:white"
						data-options="required:true,missingMessage:'学期必选!'" value="">
						<option value="" selected>请选择：</option>
						<option>1</option>
						<option>2</option>
					</select>
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
			$.post("stu/learningGuidStudentsListAction_save.action", $("#addForm")
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
				} else if(json.status == "exist"){
					$.messager.show({
							title : '提示信息',
							msg : '保存失败：已存在相同内容的记录!',
							showType : 'fade',
							timeout : 3000,
							style : {
								right : '',
								bottom : ''
							}
						});
					
				}else if(json.status == "teachernameError"){
					$.messager.show({
//2017/4/19 余锡鸿 导学名单编辑界面修改错误提示信息
							title : '提示信息',
							msg : '保存失败：导师姓名错误!',
							showType : 'fade',
							timeout : 3000,
							style : {
								right : '',
								bottom : ''
							}
						});
					
				} else {
					$.messager.show({
						title : '提示信息!',
						msg : '保存失败:学号不存在或者姓名错误!',
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