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

<title>学生政审资料管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
<link rel="stylesheet" type="text/css" href="css/css_form.css">
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false">
			<div id="easyui-tabs" class="easyui-tabs" data-options="fit:true,border:false">
				<div title="学生基本信息" style="overflow:hidden;">
					<iframe id='daoxue_iframe' frameborder='0' src="stu/studentsAction_index.action"
						style='width:100%;height:100%;'> </iframe>
				</div>
				<div title="学生家庭信息" style="overflow:hidden;">
					<iframe frameborder='0' style='width:100%;height:100%;'> </iframe>
				</div>
				<div title="学生学习经历" style="overflow:hidden;">
					<iframe frameborder='0' style='width:100%;height:100%;'> </iframe>
				</div>
				<div title="学生高考成绩" style="overflow:hidden;">
					<iframe frameborder='0' style='width:100%;height:100%;'> </iframe>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#easyui-tabs').tabs({
			onSelect : function(title) {
				var url = "";
				if (title == "学生基本信息") {
					url = "stu/studentsAction_index.action";
				} else if (title == "学生家庭信息") {
					url = "stu/familyInfoAction_index.action";
				} else if (title == "学生学习经历") {
					url = "stu/learningExperienceAction_index.action";
				} else if (title == "学生高考成绩") {
					url = "stu/nceeScoreAction_index.action";
				}
				var p = $(this).tabs('getTab', title);
				p.find('iframe').attr('src', url);
			}
		});
	});

	//提供给子页调用
	function getStudentno() {
		return studentno;
	}
</script>

