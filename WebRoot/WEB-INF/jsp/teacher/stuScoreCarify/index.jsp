<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xht>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">

<title>教师评审管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
<script type="text/javascript" src="js/common/RowNumberWidth.js"></script>
<style type="text/css">
</style>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:30">
			<div class="nav">
				<div class="nav_left">教师评审管理&nbsp;&gt;&gt;&nbsp;教师成绩导入与审核</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>
		</div>

		<div data-options="region:'center'">
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'year',width:300">学年</th>
						<th data-options="field:'term',width:300">学期</th>
						<th data-options="field:'activity',width:300">活动名称</th>
						<th data-options="field:'type',width:300">类型</th>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<power:Query>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick=" toDetail()">进入</a>
		</power:Query>
	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<power:Query>
		<div onclick="toDetail()" data-options="iconCls:'icon-detailview'">查看</div>
		</power:Query>
	</div>

</body>
</html>
<script type="text/javascript">

	$(function() {		
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'tea/teaScoreConfirmAction_getMainList.action',
			${isAdmin?"//":""}queryParams:{employNo:'${session.session_user.employNo}'},
			lines : true,
			animate : true,
			fitColumns : false,//适应宽度
			nowrap : false,
			fit : true,
			striped : true, // 隔行变色特性F
			rownumbers : true,
			method : 'post',
			toolbar : '#dt',
			pagination : true,
			pageSize : 10,
			pageList : [  10,20  ],
			//动态修改行号列的宽度--2017.04.23-lianhaowen
	   		onLoadSuccess : function () {
   		 	 	$(this).datagrid("fixRownumber");
 			 },
			onRowContextMenu : function(e, row, data) {
				e.preventDefault(); //屏蔽浏览器的菜单
				$(this).datagrid('unselectAll');//清除所有选中项
				$(this).datagrid('selectRow', row);
				$('#op_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onLoadSuccess : function() {
				$(this).datagrid('clearChecked');
			}
		});
		
		
	});
	
	
	
	//关闭窗口，提供给子页调用
	function closeWindow(id){
		$(id).window('destroy');
		$('#dg').datagrid('reload');//刷新
	}
	
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

	function toDetail() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行编辑!',
				showType : 'fade',
				timeout: 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		var year = arr[0].year;
		var term = arr[0].term;
		var type = arr[0].type;
		if(type == "正考")
			type = "0";
		else
			type = "1";
		initWindow("dg_edit","学期开课课程信息详情",1000,900,"tea/teaScoreConfirmAction_viewEdit.action?academicyear="+year+"&semester="+term+"&type="+type);
	}

	//取消选中项
	function reject() {
		$('#dg').datagrid('unselectAll');
	}
	
</script>

