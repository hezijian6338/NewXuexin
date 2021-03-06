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
<script type="text/javascript" src="js/common/RowNumberWidth.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">学生学习情况管理&nbsp;&gt;&gt;&nbsp;学生选课及成绩明细管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<td>学号: <input type="text" class="l_textbox" id="studentno"
							name="studentno" value="">
						</td>
						<td>姓名: <input type="text" class="l_textbox" id="stuname"
							name="stuname" value="">
						</td>
						<td><a id="btnfind" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a> <a
							id="clearbtn" class="easyui-linkbutton"
							data-options="iconCls:'icon-clear'" onclick="clearForm()">清空</a>
						</td>
					</tr>
				</table>
				<div class="space_flow_10"></div>
			</form>
		</div>

		<div data-options="region:'center'">
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'studentno',width:120">学号</th>
						<th data-options="field:'stuname',width:120">姓名</th>
						<th data-options="field:'orgName',width:120">学院名称</th>
						<th data-options="field:'classname',width:120">班级</th>
						<th data-options="field:'majorcode',width:120">专业代码</th>
						<th data-options="field:'major',width:120">专业</th>
						<th data-options="field:'selectedCoureNo',width:120">选课课号</th>
						<th data-options="field:'coursecode',width:120">课程代码</th>
						<th data-options="field:'coursename',width:120">课程名称</th>
						<th data-options="field:'academicYear',width:120">学年</th>
						<th data-options="field:'term',width:120">学期</th>
						<th data-options="field:'retakeflag',width:50">是否补考</th>
						<th data-options="field:'usualscore',width:60">平时成绩</th>
						<th data-options="field:'middlescore',width:60">期中成绩</th>
						<th data-options="field:'endscore',width:60">期末成绩</th>
						<th data-options="field:'labscore',width:60">实验成绩</th>
						<th data-options="field:'finalscore',width:60">总评成绩</th>
						<th data-options="field:'convertscore',width:60">折算成绩</th>
						<th data-options="field:'resitscore',width:60">补考成绩</th>
						<th data-options="field:'resitmemo',width:120">补考成绩备注</th>
						<th data-options="field:'repairscore',width:60">重修成绩</th>
						<th data-options="field:'gradepoint',width:60">绩点</th>
						<th data-options="field:'memo',width:120">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<power:Import>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="importExcel()">导入</a>
		</power:Import>
		<power:Import>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="verifyExcel()">数据校验</a>
		</power:Import>
		<power:Export>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-download',plain:true"
				onclick="exportExcel()">导出</a>
		</power:Export>
		<power:Add>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">增加</a>
		</power:Add>
		<power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdate()">编辑</a>
		</power:Edit>
		<power:Del>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
		</power:Del>
	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<power:Query>
		<div onclick="toDetail()" data-options="iconCls:'icon-detailview'">详情</div>
		</power:Query>
		<power:Edit>
			<div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div>
		</power:Edit>
		<power:Del>
			<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
		</power:Del>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'tea/courseInfoStudentsAction_getList.action',
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
			//动态修改行号列的宽度--2017.03.28-许彩开-成功
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
	
	function showSex(sex){
		if(sex=='0'){
			return '男';
		}else{
		 	return '女';
		} 
	}
	
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

	function onAdd() {
		initWindow("dg_add","新增开课课程学生选课及成绩明细",750,400,"tea/courseInfoStudentsAction_viewAdd.action");
	}

	function onUpdate() {
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
		var id = arr[0].id;
		initWindow("dg_edit","修改开课课程学生选课及成绩明细",750,400,"tea/courseInfoStudentsAction_viewEdit.action?id="+id);
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
		var id = arr[0].id;
		initWindow("dg_edit","开课课程学生选课及成绩明细\
		",750,400,"tea/courseInfoStudentsAction_viewDetail.action?id="+id);
	}
	
	//取消选中项
	function reject() {
		$('#dg').datagrid('unselectAll');
	}
	
	//删除
	function doDelete() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length <= 0) {
			$.messager.show({
				title : '提示信息!',
				msg : '请选择...!',
				showType:'fade',
						style:{								
						right:'',
						bottom:''
					}
			});
		} else {
			$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {
					var ids = '';
					for ( var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post("tea/courseInfoStudentsAction_delete.action", {
						ids : ids
					}, function callback(txt) {
					var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除成功!',
								showType:'fade',
								timeout: 3000,
								style:{								
								right:'',
								bottom:''
								}
							});
						} else {
							$.messager.show({
								title : '提示信息',
								msg : '删除失败!',
								showType:'fade',
								timeout: 3000,
								style:{								
								right:'',
								bottom:''
								}
							});
						}

					});
				} else {
					return;
				}
			});
		}
	}
	
	function importExcel() {
		initWindow("dg_import","导入开课课程学生选课及成绩明细",450,200,"tea/courseInfoStudentsAction_viewImport.action"); 
	}
	
	function verifyExcel() {
		initWindow("dg_verify","校验开课课程学生选课及成绩明细",600,700,"tea/courseInfoStudentsAction_viewVerify.action"); 
	}
	
	//导出
	function exportExcel(){
		queryForm.action="tea/courseInfoStudentsAction_exportExcelList.action";
		queryForm.submit();
	}
	
	
	
</script>

