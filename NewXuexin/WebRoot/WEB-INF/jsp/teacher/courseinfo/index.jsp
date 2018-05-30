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
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">教师评审管理&nbsp;&gt;&gt;&nbsp;开课课程信息管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">
			
				<table>
					<tr>
						<td>课程名称: <input type="text" class="l_textbox" id="coursename"
							name="coursename" value="">
						</td>
						<td>课程代码: <input type="text" class="l_textbox" id="coursecode"
							name="coursecode" value="">
						</td>
						<td>选课课号: <input type="text" class="l_textbox" id="selectedcourseno"
							name="selectedcourseno" value="">
						</td>
						<td>教师工号: <input type="text" class="l_textbox" id="employNo" name="employNo"
							value="">
						</td>
						<td>学年：<select id="academicyear" class="easyui-combobox"
							style="width:100px;" editable="false" id="academicyear" name="academicyear">
								<option value="" selected="selected">所有</option>
								<option value="2010-2011">2010-2011</option>
								<option value="2011-2012">2011-2012</option>
								<option value="2012-2013">2012-2013</option>
								<option value="2013-2014">2013-2014</option>
								<option value="2014-2015">2014-2015</option>
								<option value="2015-2016">2015-2016</option>
								<option value="2016-2017">2016-2017</option>
								<option value="2017-2018">2017-2018</option>
								<option value="2018-2019">2018-2019</option>
								<option value="2019-2020">2019-2020</option>
								<option value="2020-2021">2020-2021</option>
								<option value="2021-2022">2021-2022</option>
								<option value="2022-2023">2022-2023</option>
								<option value="2023-2024">2023-2024</option>
								<option value="2024-2025">2024-2025</option>
								<option value="2025-2026">2025-2026</option>
						</select></td>
						<td>学期：<select id="term" class="easyui-combobox"
							style="width:70px;" editable="false" data-options=""
							id="term" name="term">
								<option value="" selected="selected">所有</option>
								<option value="1">1</option>
								<option value="2">2</option>
						</select></td>
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
						<th data-options="field:'coursecode',width:120">课程代码</th>
						<th data-options="field:'coursename',width:120">课程名称</th>
						<th data-options="field:'academicyear',width:120">学年</th>
						<th data-options="field:'term',width:120">学期</th>
						<th data-options="field:'employNo',width:120">教师工号</th>
						<th data-options="field:'employName',width:120">教师姓名</th>
						<th data-options="field:'selectedcourseno',width:120">选课课号</th>
						<th data-options="field:'coursetype',width:120">课程性质</th>
						<th data-options="field:'totalhours',width:100">总学时</th>
						<th data-options="field:'labhours',width:120">实验学时</th>
						<th data-options="field:'classinfo',width:120">教学班组成</th>
						<th data-options="field:'limitstudentnum',width:120">限选人数</th>
						<th data-options="field:'studentnum',width:120">选课人数</th>
						<th data-options="field:'credit',width:120">学分</th>
						<th data-options="field:'belongto',width:120">课程归属</th>
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
			url : 'tea/courseInfoAction_getList.action',
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
		initWindow("dg_add","新增开课课程信息",750,400,"tea/courseInfoAction_viewAdd.action");
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
		initWindow("dg_edit","修改开课课程信息",750,400,"tea/courseInfoAction_viewEdit.action?id="+id);
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
		initWindow("dg_edit","开课课程信息详情",750,400,"tea/courseInfoAction_viewDetail.action?id="+id);
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
					$.post("tea/courseInfoAction_delete.action", {
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
		initWindow("dg_import","导入开课课程信息",450,200,"tea/courseInfoAction_viewImport.action"); 
	}
	
	//导出
	function exportExcel(){
		queryForm.action="tea/courseInfoAction_exportExcelList.action";
		queryForm.submit();
	}

	
</script>

