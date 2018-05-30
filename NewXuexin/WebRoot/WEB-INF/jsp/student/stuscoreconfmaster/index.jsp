<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>学生成绩确认</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:150">
			<div class="nav">
				<div class="nav_left">学生学习情况管理&nbsp;&gt;&gt;&nbsp;学生成绩确认</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<c:if test="${!isStu }">
							<td>年级：<select id="grade" class="easyui-combobox"
								style="width:60px;" editable="false" name="sscm.grade">
									<option value="" selected="selected">所有</option>
									<option value="2010">2010</option>
									<option value="2011">2011</option>
									<option value="2012">2012</option>
									<option value="2013">2013</option>
									<option value="2014">2014</option>
									<option value="2015">2015</option>
									<option value="2016">2016</option>
									<option value="2017">2017</option>
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020">2020</option>
									<option value="2021">2021</option>
									<option value="2022">2022</option>
									<option value="2023">2023</option>
									<option value="2024">2024</option>
									<option value="2025">2025</option>
							</select></td>
							<td>姓名: <input type="text" class="l_textbox" id="stuname"
								name="sscm.stuname" value=""></td>
							<td>学号: <input type="text" class="l_textbox" id="studentno"
								name="sscm.studentno" value=""></td>
						</c:if>
						<td>学年：<select id="academicyear" class="easyui-combobox"
							style="width:100px;" editable="false" name="sscm.academicyear">
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
							name="sscm.term">
								<option value="" selected="selected">所有</option>
								<option value="1">1</option>
								<option value="2">2</option>
						</select></td>
						<td>状态: <select id="status" class="easyui-combobox"
							name="sscm.status" style="width:70px;">
								<option value="" selected="selected">所有</option>
								<option value="新建">新建</option>
								<option value="提交">提交</option>
								<option value="重置">重置</option>
								<option value="确认">确认</option>

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
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:30"></th>
						<th data-options="field:'grade',width:30">年级</th>
						<th data-options="field:'academicyear',width:80">学年</th>
						<th data-options="field:'term',width:40">学期</th>
						<th data-options="field:'studentno',width:100">学号</th>
						<th data-options="field:'stuname',width:100">姓名</th>
						<th data-options="field:'status',width:60">状态</th>
						<th data-options="field:'teachername',width:80">导学老师</th>
						<th data-options="field:'createTime',width:80"
							formatter="dateFormater">创建时间</th>
						<th data-options="field:'confirmdate',width:80"
							formatter="dateFormater">确认时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<%-- <power:Import>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="importExcel()">导入</a>
		</power:Import>
		<power:Export>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-download',plain:true"
				onclick="exportExcel()">导出</a>
		</power:Export> --%>
		<a href="javascript:void(0)" class="easyui-linkbutton "
			data-options="iconCls:'icon-detailview',plain:true"
			onclick="onDetail()">详情</a>
		<c:if test="${isStu }">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-download',plain:true"
				onclick="exportExcel()">导出</a>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-ok',plain:true" onclick="doSubmit()">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">增加</a>

			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdate()">编辑</a>

			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
		</c:if>
		<c:if test="${!isStu }">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok',plain:true" onclick="doVerify()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true" onclick="doReset()">重置</a>
		</c:if>
	</div>
	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<div onclick="onDetail()" data-options="iconCls:'icon-detailview'">详情</div>
		<c:if test="${isStu }">
			<div onclick="exportExcel()" data-options="iconCls:'icon-download'">导出</div>
			<div onclick="doSubmit()" data-options="iconCls:'icon-ok'">提交</div>
			<div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div>
			<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
		</c:if>
		<c:if test="${!isStu }">
			<div data-options="iconCls:'icon-ok'" onclick="doVerify()">确认</div>
			<div data-options="iconCls:'icon-reload'" onclick="doReset()">重置</div>
		</c:if>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'stu/stuscoreconfMasterAction_list.action',
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
				$(this).datagrid('clearChecked')
			}
		});

	});

	function dateFormater(val, row) {
		if (val == "" || val == null) {
			return "";
		}
		var date = new Date(val);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + m + '-' + d;
	}
	//关闭窗口，提供给子页调用
	function closeWindow(id) {
		$(id).window('destroy');
		$('#dg').datagrid('reload');//刷新
	}
	//提交

	function doSubmit() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		if (arr[0].status != '新建' && arr[0].status != '重置') {
			$.messager.show({
				title : '提示信息!',
				msg : '只能提交状态为新建或重置的信息!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		$.messager.confirm("提示信息", "确认提交?提交后将无法修改此表的任何信息!", function(r) {
			if (r) {
				if (r) {
					var id = arr[0].id;
					$.post("stu/stuscoreconfMasterAction_submit.action", {
						"sscm.id" : id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							//$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '提交成功!',
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
								msg : json.msg,
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
			}
		});
	}
	//确认
	function doVerify() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		} else {
			if (arr[0].status != '提交') {
				$.messager.show({
					title : '提示信息!',
					msg : '只能确认状态为提交的信息!',
					showType : 'fade',
					timeout : 3000,
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			}
			$.messager.confirm("提示信息", "确认该成绩已正确?", function(r) {
				if (r) {
					var id = arr[0].id;
					$.post("stu/stuscoreconfMasterAction_verify.action", {
						"sscm.id" : id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							//$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '确认成功!',
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
								msg : json.msg,
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

	//确认
	function doReset() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		} else {
			if (arr[0].status != '提交') {
				$.messager.show({
					title : '提示信息!',
					msg : '只能重置状态为提交的信息!',
					showType : 'fade',
					timeout : 3000,
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			}
			$.messager.confirm("提示信息", "确认该将该成绩表状态设为重置?", function(r) {
				if (r) {
					var id = arr[0].id;
					$.post("stu/stuscoreconfMasterAction_reset.action", {
						"sscm.id" : id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '修改成功!',
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
								msg : json.msg,
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
		if (${!isStu}) {
			$('#grade').combobox('setValue', '');
		}
		$('#academicyear').combobox('setValue', '');
		$('#term').combobox('setValue', '');
		$('#status').combobox('setValue', '');
		$('#dg').datagrid('load', {});
	}

	function onAdd() {
		initWindow("dg_add", "新增信息", 750, 750,
				"stu/stuscoreconfMasterAction_viewAdd.action", function() {
					$('#dg').datagrid('reload');
				});
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
		if (arr[0].status != '新建' && arr[0].status != '重置') {
			$.messager.show({
				title : '提示信息!',
				msg : '只能编辑状态为新建或重置的信息!',
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
		initWindow("dg_edit", "修改信息", 750, 750,
				"stu/stuscoreconfMasterAction_viewEdit.action?sscm.id=" + id,
				function() {
					$('#dg').datagrid('reload');
				});
	}

	function onDetail() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行查看!',
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
		initWindow("dg_detail", "信息详情", 750, 750,
				"stu/stuscoreconfMasterAction_viewDetail.action?sscm.id=" + id);
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

			for (var i = 0; i < arr.length; i++) {
				if (arr[i].status != '新建' && arr[i].status != '重置') {
					$.messager.show({
						title : '提示信息!',
						msg : '只能删除状态为新建或重置的信息!',
						showType : 'fade',
						timeout : 3000,
						style : {
							right : '',
							bottom : ''
						}
					});
					return;
				}
			}
			$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {
					var ids = '';
					for (var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post("stu/stuscoreconfMasterAction_deleteSelf.action", {
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
								msg : json.msg,
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
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行!',
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
		window.location.href="<%=basePath%>stu/stuscoreconfMasterAction_exportExcelList.action?sscm.id="+id;
	}
</script>

