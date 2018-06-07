<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
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

<title>学生信息管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>

<body>
	<div data-options="region:'center',fit:true">
		<table id="dg" style="margin: 0;" align="center"
			cellspacing="0" class="editTable">
			<thead id='thead'>
				<tr>
					<th class='editTable_th' style='width: 100;'>学年</th>
					<th class='editTable_th' style='width: 50;'>学期</th>
					<th class='editTable_th' style='width: 100;'>课程代码</th>
					<th class='editTable_th' style='width: 150;'>课程名称</th>
					<th class='editTable_th' style='width: 120;'>课程性质</th>
					<th class='editTable_th' style='width: 120;'>课程归属</th>
					<th class='editTable_th' style='width: 50;'>学分</th>
					<th class='editTable_th' style='width: 50;'>绩点</th>
					<th class='editTable_th' style='width: 50;'>成绩</th>
					<th class='editTable_th' style='width: 50;'>辅修标记</th>
					<th class='editTable_th' style='width: 50;'>补考成绩</th>
					<th class='editTable_th' style='width: 50;'>重修成绩</th>
					<th class='editTable_th' style='width: 150;'>学院名称</th>
					<th class='editTable_th' style='width: 100;'>备注</th>
				</tr>
			</thead>
			<tbody id="tdbody"></tbody>
		</table>
	</div>
</body>
</html>
<script type="text/javascript">
	var studentno = window.parent.getStudentno();//关闭窗口
	$(function() {
		createTable();
	});

	//创建表数据
	function createTable() {
		$
				.post(
						"tea/courseInfoStudentsAction_getCourseDetail.action",
						{
							studentno : studentno
						},
						function callback(txt) {
							var json = $.parseJSON(txt);
							var arr = json.result;
							var s = "";
							for ( var i = 0; i < arr.length; i++) {
								var ob = arr[i];
								s += "<tr>";
								for ( var j = 0; j < ob.length; j++) {
									s += "<td class='editTable_td' style='text-align: center;'>"
											+ ob[j] + "</td>";
								}
								s += "</tr>";
							}
							$('#tdbody').html(s);
						});
	}
</script>

