<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/sys/main/left.css">
	
	<script type="text/javascript" src="js/common/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="js/sys/main/left.js"></script>

  </head>
  
  <body>
  	<div class="wrap-menu">
	</div>
	<script>
		var myMenu=<%= session.getAttribute("treeMenu")%>;
			$(function(){
				new AccordionMenu({menuArrs:myMenu});
			});
	</script>
  </body>
</html>
