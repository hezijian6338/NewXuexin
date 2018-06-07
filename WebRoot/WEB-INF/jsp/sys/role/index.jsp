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
<%@ include file="/pub/headmeta.jsp"%>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">系统管理&nbsp;&gt;&gt;&nbsp;角色管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table title="查询条件">
					<tr>
						<td>角色编号: <input type="text" name="roleNo" value="">
						</td>
						<td>角色名称: <input type="text" name="roleName" value="">
						</td>
						<td><a id="btnfind" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a> <a
							id="clearbtn" class="easyui-linkbutton"
							data-options="iconCls:'icon-clear'" onclick="clearForm()">清空</a>
						</td>
					</tr>
				</table>
			</form>

		</div>
		<div data-options="region:'center',border:false">
			<table id="dg" style="height:450px">
				<thead>
					<tr>
						<th data-options="field:'roleId',checkbox:true,width:20">
						<th data-options="field:'roleName',width:120">角色名称</th>
						<th data-options="field:'roleNo',width:80">角色编号</th>
						<th data-options="field:'createTime',width:80">创建时间</th>
						<th data-options="field:'memo',width:120">描述</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="dt" align="right">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
			<power:Authorization>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-group_key',plain:true"
					onclick="onTab()">授权</a>
			</power:Authorization>
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
					data-options="iconCls:'icon-remove',plain:true"
					onclick="doDelete()">删除</a>
			</power:Del>
		</div>

		<div id="op_menu" class="easyui-menu" style="width: 120px;">
			<power:Authorization>
				<div onclick="onTab()" data-options="iconCls:'icon-group_key'">绑定关联</div>
			</power:Authorization>
			<power:Edit>
				<div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div>
			</power:Edit>
			<power:Del>
				<div onclick="doDelete" data-options="iconCls:'icon-remove'">删除</div>
			</power:Del>
		</div>

		<!--添加/修改用户弹出框  -->
		<div id="dg_add" class="easyui-window" title="新增角色"
			style="width:750px;height:400px">
			<iframe id="addIframe" src="" frameborder="0"
				style="width:100%;height:100%;"> </iframe>
		</div>
		<div id="dg_edit" title="编辑用户" class="easyui-window"
			style="padding:10px; width: 750px; height: 400px;">
			<iframe id="editIframe" src="" frameborder="0"
				style="width:100%;height:100%;"> </iframe>
		</div>
		<div id="dg_tab" class="easyui-window" title="关联用户 -绑定权限"
			align="center" style="width: 800px; height: 400px;">
			<iframe id="tabIframe" src="" frameborder="0"
				style="width:100%;height:100%;"> </iframe>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			idField : 'roleId',
			loadMsg : '数据加载中请稍后……',
			fit : true,
			url : 'sys/roleAction_getRoleList.action',
			lines : true,
			animate : true,
			nowrap : false,
			collapsible : true,
			fitColumns : true,
			striped : true, // 隔行变色特性F
			rownumbers : true,
			remoteSort : false, // 将本地排序去掉
			sortName : 'roleNo',// 设置排序列
			sortOrder : 'asc',
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
			}
		});

		//初始化弹出窗口
		openWindow('#dg_add');
		openWindow('#dg_edit');
		openWindow('#dg_tab');
	});

	//关闭窗口，提供给子页调用
	function closeWindow(id) {
		$(id).window('close');
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

	function onTab() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一个角色进行操作!',
				showType : 'fade',
				timeout: 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		var roleId = arr[0].roleId;
		$('#tabIframe')[0].src = 'sys/roleAction_viewTab.action?roleId='
				+ roleId;
		$('#dg_tab').window('open'); // open a window   
	}

	function onAdd() {
		$('#addIframe')[0].src = 'sys/roleAction_viewAdd.action';
		$('#dg_add').window('open'); // open a window   
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
		var roleId = arr[0].roleId;
		$('#editIframe')[0].src = 'sys/roleAction_viewEdit.action?roleId='
				+ roleId;
		$('#dg_edit').window('open'); // open a window   
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
				msg : '请选择用户!',
				showType : 'fade',
				timeout: 3000,
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
						ids += arr[i].roleId + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post('sys/roleAction_deleteRole.action', {
						ids : ids
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除角色成功!',
								showType : 'fade',
								timeout: 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
						} else {
							$.messager.show({
								title : '提示信息',
								msg : '删除角色失败!',
								showType : 'fade',
								timeout: 3000,
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
</script>
