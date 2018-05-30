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
		<div data-options="region:'center'">
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'coursecode',width:120">课程代码</th>
						<th data-options="field:'coursename',width:120">课程名称</th>
						<th data-options="field:'coursetype',width:120">课程性质</th>
						<th data-options="field:'belongto',width:120">课程归属</th>
						<th data-options="field:'selectedcourseno',width:120">选课课号</th>
						<th data-options="field:'classinfo',width:120">授课班级</th>
					
						</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="importExcel()">导入</a>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick=" toDetail()">查看</a>
		 <a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="exportExcel()">导出</a>
	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<div onclick="importExcel()" data-options="iconCls:'icon-upload'">导入</div>
		<div onclick="toDetail()" data-options="iconCls:'icon-detailview'">查看</div>
	</div>

</body>
</html>
<script type="text/javascript">
var type = '${param.type}';
var year ='${param.academicyear}';
var term ='${param.semester}';
$(function() {		
	$('#dg').datagrid({
		idField : 'id',
		loadMsg : '数据加载中请稍后……',
		url : 'tea/teaScoreConfirmAction_getSemesterCourseList.action',
	 	queryParams:{academicyear: '${param.academicyear}',semester:'${param.semester}'},
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
		var  selectcourseno = arr[0].selectedcourseno;
		initWindow("dg_edit","课程成绩导入纠错信息详情",950,500,"tea/teaScoreConfirmAction_viewDetail.action?academicyear="+year+"&semester="+term+"&selectcourseno="+selectcourseno);
	}
	
	
	//取消选中项
	function reject() {
		$('#dg').datagrid('unselectAll');
	}
	
	
	function importExcel() {
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
	 	var coursecode = arr[0].coursecode;
		var selectedcourseno = arr[0].selectedcourseno;
		initWindow("dg_import","导入开课课程信息",450,200,"tea/teaScoreConfirmAction_viewImport.action?type="+type+"&academicyear="+year+"&semester="+term+"&coursecode="+coursecode+"&selectedcourseno="+selectedcourseno); 
	}
	
	function exportExcel(){
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
		var queryForm = document.createElement("form"); 
		queryForm.method = "post";        
		queryForm.style.display = "none";  
		var  selectcourseno = arr[0].selectedcourseno;
		var opt = document.createElement("textarea");
		opt.name = "selectcourseno";        
	    opt.value = selectcourseno; 
	    queryForm.appendChild(opt);   
		queryForm.action="tea/teaScoreConfirmAction_exportExcelList.action" ;
		document.body.appendChild(queryForm); 
		queryForm.submit();
	}
</script>

