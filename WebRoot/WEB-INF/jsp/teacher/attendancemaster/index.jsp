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
		<div data-options="region:'north',height:100,border:false">
			<div class="nav">
				<div class="nav_left">学生学习情况管理&nbsp;&gt;&gt;&nbsp;学生考勤登记管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<td>课程名称: <input type="text" class="l_textbox"
							id="coursename" name="coursename" value="">
						</td>
						<td>选课号: <input type="text" class="l_textbox"
							id="selectedcourseno" name="selectedcourseno" value="">
						</td>
						<!-- <td>教师姓名: <input type="text" class="l_textbox"
							id="employName" name="employName" value=""></td> -->
						<td>开课单位: <input type="text" class="l_textbox" id="orgName"
							name="orgName" value=""></td>
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

		<div data-options="region:'center',border:false">
			<table id="dg" style="height:200px" data-options="border:false">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'academicyear',width:100">学年</th>
						<th data-options="field:'term',width:50">学期</th>
						<th data-options="field:'coursename',width:180">课程名称</th>
						<th data-options="field:'selectedcourseno',width:250">选课号</th>
						<th data-options="field:'orgName',width:180">开课单位</th>
						<th data-options="field:'employName',width:120">教师姓名</th>
						<th data-options="field:'schooltime',width:300">上课时间</th>
						<th data-options="field:'address',width:200">上课地点</th>
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
				onclick="uploadFile()">上传附件</a>
		</power:Import>
		<power:Export>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-download',plain:true"
				onclick="downloadFile()">下载附件</a>
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
		<power:Import>
			<div onclick="uploadFile()" data-options="iconCls:'icon-upload'">上传附件</div>
		</power:Import>
		<power:Export>
			<div onclick="downloadFile()" data-options="iconCls:'icon-download'">下载附件</div>
		</power:Export>
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
	init_easyui_celltip();//要放datagrid前初始化
	$(function() {
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'tea/attendanceMasterAction_getList.action',
			lines : true,
			animate : true,
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
			},
			onLoadSuccess:function(data){   
    			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});   
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
		initWindow("dg_add", "新增学生考勤登记", 750, 400,
				"tea/attendanceMasterAction_viewAdd.action");
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
		initWindow("dg_edit", "修改学生考勤登记", 750, 400,
				"tea/attendanceMasterAction_viewEdit.action?id=" + id);
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
					for ( var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post("tea/attendanceMasterAction_delete.action", {
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
		initWindow("dg_import", "导入学生考勤登记", 450, 200,
				"tea/attendanceMasterAction_viewImport.action");
	}

	//导出
	function exportExcel() {
		queryForm.action = "tea/attendanceMasterAction_exportExcelList.action";
		queryForm.submit();
	}
	
	function uploadFile() {
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
		initWindow("dg_upload", "上传学生考勤登记附件", 450, 200,
				"tea/attendanceMasterAction_viewUpload.action?id="+id);
	}
	
	function downloadFile() {
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
		initWindow("dg_download", "学生考勤登记附件", 450, 200,
				"tea/attendanceMasterAction_viewDownload.action?id="+id);
	}
	
	function toDetail() {
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
		var selectedcourseno = arr[0].selectedcourseno;
		initWindow("dg_detail", "学生考勤登记详情", 750, 400,
				"tea/attendanceMasterAction_viewDetail.action?id="+id+"&selectedcourseno="+selectedcourseno);
	}
</script>

