<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<link rel="stylesheet" type="text/css" href="css/sys/main/login.css">
<!--[if IE 6]> 
<style>
	input{
		position:relative;
	}
</style>
<![endif]-->

<script type="text/javascript">
	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}
	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
</script>
</head>

<body onload="MM_preloadImages('images/land-l.png')">
	<div id="layout">
		<div id="sys_name">学生电子档案管理系统</div>
		<div class="index-1">
			<form id="loginForm" method="post">
				<div
					style="padding: 0px; border: 0px solid rgb(0, 157, 225); height: 170px; text-align: left; margin-top: 3px;">
					<div class="form_row">
						<span class="login-font">账&nbsp;&nbsp;号</span> <span
							class="login-input"><input type='text' name="employNo"
							id="employNo" class="index-2" /> </span><br />
					</div>
					<div class="form_row">
						<span class="login-font">密&nbsp;&nbsp;码</span> <span
							class="login-input"><input type="password" name="password"
							id="password" class="index-2" /><br /> </span>
					</div>
					<div class="form_row">
						<span class="login-font">用户类型</span>&nbsp;&nbsp; <input
							type="radio" name="userType" value="1" checked="checked" />教师&nbsp;&nbsp;
						<input type="radio" name="userType" value="2" />学生&nbsp;&nbsp;

					</div>
					<a href="javascript:doLogin();"
						style="position:relative;cursor:pointer;"
						onmouseout="MM_swapImgRestore()"
						onmouseover="MM_swapImage('Image1','','images/land-l.png',1)"><img
						class="login-image" src="images/land-m.png" name="Image1"
						width="250" height="40" border="0" id="Image1" /> </a>
				</div>

			</form>
		</div>
		<div style="clear:both;"></div>
		<font class="font-pe">CopyRight © 2015 All Right Reserved.
			Powered by 北京理工大学珠海学院</font>
	</div>
</body>
</html>

<script type="text/javascript">
	//自动调用初始化
	$(function() {
		$("#employNo").focus();
		$("body").keydown(function(e) {
			if (e.keyCode == 13)
				doLogin();
		});
	});

	//登录
	function doLogin() {
		if ($("#employNo").val() == "") {
			alert('帐号不能为空！');
			return;
		}
		if ($("#password").val() == "") {
			alert('密码不能为空！');
			return;
		}

		var url = "sys/loginAction_login.action";
		$
				.post(
						url,
						$("#loginForm").serialize(),
						function callback(txt) {
							var json = $.parseJSON(txt);
							if (json.code == "ok") {
								//location.replace("sys/loginAction_main.action");
								//window.location="sys/loginAction_main.action";
								//为了兼容ie，做成把表单绑定到body上后提交 ,chrome可以直接不使用body
								var bodyWrap = $(window.document.body);
								bodyWrap
										.append("<form method='post' action='sys/loginAction_main.action' id='sourceStatisticsForm' style='display: none;'></form>");
								var sourceForm = $('#sourceStatisticsForm');
								sourceForm.submit();
								sourceForm.remove();
							} else {
								alert(json.msg);
							}
						});
	}
</script>
