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
		<div data-options="region:'center'">
			<table id="dg" style="height:400px;margin: 0;" align="center"  cellspacing="0" class="editTable">
				<thead id='thead'>
				</thead>
				<tbody id="tdbody"></tbody>
			</table>
		</div>

</body>
</html>
<script type="text/javascript">
	var id = '${param.id}';
	var selectedcourseno = '${param.selectedcourseno}';
	$(function() {
		createTable();
	});

	//创建表数据
	function createTable() {

		$.post("tea/attendanceDetailAction_getDete.action", {
			selectedcourseno : selectedcourseno
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			var arr = json.result;
			var s = "";
			s += "<tr>";
			s += "<th class='editTable_th' style='width: 120px;'>学号</th>";
			s += "<th class='editTable_th' style='width: 100px;'>姓名</th>";
			s += "<th class='editTable_th' style='width: 60px;'>性别</th>";
			s += "<th class='editTable_th' style='width: 150px;'>班级</th>";
			for ( var i = 0; i < arr.length; i++) {
				s += "<th class='editTable_th' style='width: 60px;'>" + arr[i].substring(5,10) + "</th>";
			}
			s += "</tr>";
			$('#thead').html(s);
		});
		$.post("tea/attendanceDetailAction_getDetailList.action", {
			selectedcourseno : selectedcourseno
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			var arr = json.rows;

			var s = "";

			for ( var i = 0; i < arr.length; i++) {
				var o = arr[i];
				var sex = "";
				if (o.sex == '0') {
					sex = '男';
				} else {
					sex = '女';
				}
				s += "<tr>";
				s += "<td class='editTable_td' style='text-align: center;'>" + o.studentno + "</td>";
				s += "<td class='editTable_td' style='text-align: center;'>" + o.stuname + "</td>";
				s += "<td class='editTable_td' style='text-align: center;'>" + sex + "</td>";
				s += "<td class='editTable_td' style='text-align: center;'>" + o.classname + "</td>";
				var rs = o.attendanceStatus;
				for(var j=0;j<rs.length;j++){
					s += "<td class='editTable_td' style='text-align: center;'>" + rs[j] + "</td>";
				}
				s += "</tr>";
			}
			$('#tdbody').html(s);
		});
	}
</script>

