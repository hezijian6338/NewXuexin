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

<title>问题记录查看</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
<script type="text/javascript" src="js/common/RowNumberWidth.js"></script>
<style type="text/css">
</style>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center'">
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'coursecode',width:120">课程代码</th>
						<th data-options="field:'courseName',width:120">课程名称</th>
						<th data-options="field:'studentno',width:120">学号</th>
						<th data-options="field:'stuname',width:120">姓名</th>
						<th data-options="field:'tfinalscore',width:120">老师导入总评</th>
						<th data-options="field:'finalscore',width:120">学生录入总评</th>
						<th data-options="field:'tresitscore',width:120">老师导入补考</th>
						<th data-options="field:'resitscore',width:120">学生录入补考</th>
						<th data-options="field:'memo',width:120">正考备注</th>
						<th data-options="field:'resitmemo',width:120">补考备注</th>
					</tr>
				</thead>
			</table> 	
		</div>
	</div>

	<div id="dt" align="right">
		<power:Import>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="exportExcel()">导出</a>
		</power:Import>
	</div>

	

</body>
</html>
<script type="text/javascript">

	$(function() {		
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'tea/teaScoreConfirmAction_getCheckScoreList.action',
			queryParams:{selectcourseno:'${param.selectcourseno}'},
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
			pageList : [ 5, 10, 15, 20, 50 ],
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
			}
		});
		
		
	});
	
	
	
	//关闭窗口，提供给子页调用
	function closeWindow(id){
		$(id).window('destroy');
		$('#dg').datagrid('reload');//刷新
	}
	
	
	function exportExcel() {
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
		//这里需要传入课程唯一标记
		initWindow("dg_import","导入开课课程信息",450,200,"tea/courseInfoAction_viewImport.action"); 
	}
	
	
</script>

