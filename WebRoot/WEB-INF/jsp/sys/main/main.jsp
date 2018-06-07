<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学生电子档案管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/sys/main/base.css">
<link rel="stylesheet" type="text/css" href="css/sys/main/main.css">
<link rel="stylesheet" type="text/css" href="easyui/css/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/css/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
<script type="text/javascript" src="js/common/jquery-1.8.2.js"></script>
<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:80px;background:#3399FF;padding:0px">
		<div class="header">
			<div class="titlePanel">
				<div class="logo">
					<img class="logo-image" src="images/zhbit_logo.png"
						width="55" height="55" border="0"/>
				</div>
				<div class="topTitle">
					<div class="title">
						<h1>学生电子档案管理系统</h1>
					</div>
				</div>
				<div class="subTitle">
					<p>The Management System Of Student's Electronic Archives</p>
				</div>
			</div>
			<div class="msgPanel">
				<div class="usePanel">
					<p>
						<img class="logo-image" src="images/user.png"
						width="14" height="14" border="0"/>
						<s:property value="#session.session_user.employName" />
						:
						<c:forEach items="${session_role}" var="o">[${o.roleName}]</c:forEach>
						&nbsp;&nbsp;欢迎登陆
					</p>
				</div>
				<div class="linkPanel">
					<a href="sys/loginAction_logout.action"><img class="logo-image" src="images/exit.png"
						width="14" height="14" border="0"/><span>退出</span></a>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单导航'"
		style="width:220px;margin:0;padding:3px; background: #6699FF;">
		<div class="leftbar" style="margin: 0;padding: 0;">
			<iframe src="sys/loginAction_leftMenu.action"> </iframe>
		</div>

	</div>
	<div data-options="region:'center'">
		<div class="mainbar">
			<iframe src="sys/loginAction_mainBody.action" name="mainbody"></iframe>
		</div>

	</div>
	<div data-options="region:'east',border:false"
		style="width:0px;padding:0px;"></div>
	<div data-options="region:'south',border:false"
		style="height:30px;background:#3399FF;padding:5px;text-align: center;">
		<p>CopyRight © 2015 All Right Reserved. Powered by 北京理工大学珠海学院</p>
	</div>

</body>
</html>
