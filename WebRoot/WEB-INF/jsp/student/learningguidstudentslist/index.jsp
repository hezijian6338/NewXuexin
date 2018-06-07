<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
				<div class="nav_left">学生学习情况管理&nbsp;&gt;&gt;&nbsp;导学名单管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<td>姓名: <input type="text" class="l_textbox" id="stuname"
							name="stuname" value="">
						</td>
						<td>学号: <input type="text" class="l_textbox" id="studentno"
							name="studentno" value="">
						</td>
						<c:if test="${isAdmin}">
							<td>导学老师: <input type="text" class="l_textbox"
								id="teachername" name="teachername" value="">
							</td>
						</c:if>
						<td>学年: <select id="academicyear" name="academicyear"
							class="l_textbox" style="background-color: white; width: 100px;"
							data-options="" value="">
								<option value="" selected></option>
								<%
									Calendar cal = Calendar.getInstance();
									int year = cal.get(Calendar.YEAR) + 2;
									for (int i = 0; i <= 5; i++) {
										String str = String.valueOf(year);
										year = year - 1;
										String str1 = String.valueOf(year);
										String str2 = str1 + "-" + str;
								%>
								<option><%=str2%></option>
								<%
									}
								%>
						</select>
						</td>
						<td>学期: <select id="term" name="term" class="l_textbox"
							style="background-color: white; width: 50px;" data-options=""
							value="">
								<option value="" selected></option>
								<option value="1">1</option>
								<option value="2">2</option>
						</select>
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
			<table id="dg" style="height: 200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'studentno',width:120">学号</th>
						<th data-options="field:'stuname',width:120">姓名</th>
						<th data-options="field:'classname',width:120">专业班级</th>
						<c:if test="${isAdmin}">
							<th data-options="field:'teachername',width:120">导学老师</th>
						</c:if>
						<th data-options="field:'academicyear',width:100">学年</th>
						<th data-options="field:'term',width:100">学期</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<power:Query>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick=" toDetail()">单个成绩检验</a>
		</power:Query>
		<power:Query>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick=" toAllDetail()">批量成绩检验</a>
		</power:Query>
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
			url : 'stu/learningGuidStudentsListAction_getList.action',
			lines : true,
			animate : true,
			fitColumns : true,//适应宽度
			nowrap : false,
			fit : true,
			striped : true, // 隔行变色特性F
			rownumbers : true,
			method : 'post',
			toolbar : '#dt',
			pagination : true,
			pageSize : 10,
			pageList : [ 5, 10, 15, 20, 50 ],
			//动态修改行号列的宽度--2017.03.28-余锡鸿-成功
			onLoadSuccess : function() {
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
	function closeWindow(id) {
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
		initWindow("dg_add", "新增导学信息", 750, 400,
				"stu/learningGuidStudentsListAction_viewAdd.action");
	}

	function onUpdate() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行编辑!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		var id = arr[0].id;
		initWindow("dg_edit", "修改导学信息", 750, 400,
				"stu/learningGuidStudentsListAction_viewEdit.action?id=" + id);
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
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
		} else {
			$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {
					var ids = '';
					for (var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post("stu/learningGuidStudentsListAction_delete.action",
							{
								ids : ids
							}, function callback(txt) {
								var json = $.parseJSON(txt);
								if (json.status == "ok") {
									$('#dg').datagrid('unselectAll');
									$('#dg').datagrid('reload');
									$.messager.show({
										title : '提示信息',
										msg : '删除成功!',
										showType : 'fade',
										timeout : 3000,
										style : {
											right : '',
											bottom : ''
										}
									});
								} else {
									$.messager.show({
										title : '提示信息',
										msg : '删除失败!',
										showType : 'fade',
										timeout : 3000,
										style : {
											right : '',
											bottom : ''
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
		initWindow("dg_import", "导入导学名单", 450, 200,
				"stu/learningGuidStudentsListAction_viewImport.action");
	}

	//导出
	function exportExcel() {
		//location.href="${ctx}/channeldistr!exportExcelList.action";
		queryForm.action = "stu/learningGuidStudentsListAction_exportExcelList.action";
		queryForm.submit();
	}
	
	function toDetail() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {
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
		var studentno = arr[0].studentno;
		var year = arr[0].academicyear;
		var term = arr[0].term;
		initWindow("dg_edit","学期开课课程信息详情",1000,900,"stu/learningGuidStudentsListAction_viewDetail.action?academicyear="+year+"&semester="+term+"&studentno="+studentno);
	}
	
	function toAllDetail() {
		initWindow("dg_edit","学期开课课程信息详情",1000,900,"stu/learningGuidStudentsListAction_viewAllDetail.action");
	}
</script>

