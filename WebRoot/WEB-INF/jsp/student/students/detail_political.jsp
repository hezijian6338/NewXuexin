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

<title>学生信息管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center'">
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'studentno',width:120">学号</th>
						<th data-options="field:'stuname',width:100">姓名</th>
						<th data-options="field:'joindate',width:120,formatter:function(r){return formatShortDate(r);}">入党团时间</th>
						<th data-options="field:'politicalstatus',width:150">政治面貌</th>
						<th data-options="field:'memo',width:150">备注</th>
					</tr>
				</thead>
			</table>
		</div>
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
	var studentno = window.parent.getStudentno();
	$(function() {
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'stu/politicalStatusAction_getList.action',
			lines : true,
			animate : true,
			fitColumns : true,//适应宽度
			nowrap : false,
			fit : true,
			striped : true, // 隔行变色特性F
			rownumbers : true,
			method : 'post',
			queryParams:{studentno:studentno},
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

	function onAdd() {
		initWindow("dg_add","新增党团关系信息",750,400,"stu/politicalStatusAction_viewAdd.action");
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
		initWindow("dg_add","修改党团关系信息",750,400,"stu/politicalStatusAction_viewEdit.action?id="+id);
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
					$.post("stu/politicalStatusAction_delete.action", {
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
	

	
</script>

