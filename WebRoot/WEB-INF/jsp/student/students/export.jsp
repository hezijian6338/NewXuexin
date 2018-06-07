<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学生政审资料管理</title>
<%@ include file="/pub/headmeta.jsp"%>
</head>

<body>


	<form action="stu/studentsAction_export.action" method="post">
		<table>
			<tr>
				<td>年级：<select id="term" class="easyui-combobox"
					style="width:80px;" editable="false" data-options="" id="term"
					name="query.grade">
						<option value="2014">2014</option>
						<option value="2015">2015</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
				</select></td>
			</tr>
			<tr>
				<td>学院：<input id="orgName" name="query.orgName" value=""
					style="width:120px;"></td>
			</tr>
			<tr>		
				<td>班级：<input id="classname" name="query.classname" value=""
					style="width:200px;"></td>
			</tr>
			<tr>		
				<td>学号：<input id="no" name="query.no" value="" class="l_textbox"
					style="width:200px;"></td>
			</tr>
			<tr>		
				<td><input id="btnfind" type="submit" value="导出"></input></td>
			</tr>
		</table>
		<div class="space_flow_10"></div>
	</form>

	<script type="text/javascript">
		$(function() {
			$.post('stu/studentsAction_listParam.action', function callback(txt) {
				var json = $.parseJSON(txt);
				if (json.status == "ok") {
					$('#orgName').combobox({
						data : json.orgName,
						valueField : 'value',
						textField : 'name',
						editable : false,
					});
					$('#classname').combobox({
						data : json.classname,
						valueField : 'value',
						textField : 'name',
						editable : false,
					});
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
		});
	</script>
</body>
</html>
