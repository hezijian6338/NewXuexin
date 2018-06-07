<%@ page language="java" pageEncoding="UTF-8"%>
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
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/RowNumberWidth.js"></script>
<script type="text/javascript">
	var myUrl = 'sys/loginLogAction_getList.action';
	$(function() {
		$('#dg').datagrid({
			idField : 'logicId',
			loadMsg : '数据加载中请稍后……',
			fit : true,
			url : myUrl,
			fitColumns : true,
			striped : true, //隔行变色特性
			rownumbers : true,
			remoteSort : false, //将本地排序去掉
			sortName : 'loginTime',//设置排序列
			sortOrder : 'desc',
			method : 'post',
			pagination : true,
			pageSize : 10,
			pageList : [ 5, 10, 15, 20, 50 ],
			onLoadSuccess:function(data){   
    			$(this).datagrid("fixRownumber");
			}
		});

	});

	//格式化时间------------------start
	function myformatter(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		//var h = date.getHours();
		//var min = date.getMinutes();
		//var sec = date.getSeconds();
		//return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
		//		+ (d < 10 ? ('0' + d) : d) + ' ' + (h < 10 ? ('0' + h) : h)
		//		+ ':' + (min < 10 ? ('0' + min) : min) + ':'
		//		+ (sec < 10 ? ('0' + sec) : sec);
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
				+ (d < 10 ? ('0' + d) : d);
	}
	function myparser(s) {
		if (!s)
			return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		//var h = s.substring(ss[3], 10);
		//var min = s.substring(ss[4], 10);
		//var sec = s.substring(ss[5], 10);
		//if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min)
		//		&& !isNaN(sec)) {return new Date(y, m - 1, d, h, min, sec);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
	//格式化时间------------------end

	function doQuery() {
		var obj = {};
		$.each($("#queryForm").serializeArray(), function(index) {
			if (obj[this['name']]) {
				obj[this['name']] = obj[this['name']] + ',' + this['value'];
			} else {
				obj[this['name']] = this['value'];
			}
		});
		$('#dg').datagrid('load', obj);
	}
	function clearForm() {
		$('#queryForm').form('clear');
		$('#dg').datagrid('load', {});
	}

</script>

</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">系统管理&nbsp;&gt;&gt;&nbsp;登录日志</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色编号:<input type="text" name="employNo" value="">
				时间从: <input name="startTime" class="easyui-datetimebox"
					style="width:170px"
					data-options="formatter:myformatter,parser:myparser"
					editable="false"> 到: <input name="endTime"
					class="easyui-datetimebox" style="width:170px"
					data-options="formatter:myformatter,parser:myparser"
					editable="false"> <a href="javascript:void(0)"
					id="findByTime" class="easyui-linkbutton" iconCls="icon-search"
					onclick="doQuery()">查询</a> <a id="clearbtn"
					class="easyui-linkbutton" iconCls="icon-clear"
					onclick="clearForm()">清空</a>
				<!-- <a href="javascript:void(0)"
					id="deleteLog" class="easyui-linkbutton" iconCls="icon-remove"
					onclick="deleteLog()">删除</a> -->
			</form>

		</div>
		<div data-options="region:'center',border:false">
			<table id="dg" style="height:450px">
				<thead>
					<tr>
						<!-- <th data-options="field:'ck',checkbox: true"></th> -->
						<th data-options="field:'employNo',width:60,align:'center'">用户编号</th>
						<th data-options="field:'loginIp',width:60,align:'center'">客户端IP</th>
						<th data-options="field:'loginTime',width:40,align:'center'">登录时间</th>
						<th data-options="field:'ifSuccess',width:80,align:'center'">是否成功</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>
