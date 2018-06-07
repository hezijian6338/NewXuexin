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
				<div class="nav_left">系统管理&nbsp;&gt;&gt;&nbsp;权限管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table title="查询条件">
					<tr>
						<td>权限名称: <input type="text" class="easyui-textbox l_textbox"
							name="authorityName" value=""></td>
						<td>权限类型: <input type="text" class="easyui-textbox l_textbox"
							name="authorityType" value=""></td>
						<td><a class="easyui-linkbutton"
							data-options="iconCls:'icon-search',plain:true"
							onclick="doQuery()">查询</a> <a id="clearbtn"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-clear',plain:true"
							onclick="clearForm()">清空</a></td>
					</tr>
				</table>
			</form>

		</div>
		<div data-options="region:'center',border:false">
			<div align="right" style="padding-right: 20px;">
				<power:Add>
					<a href="javascript:void(0)" class="easyui-linkbutton "
						data-options="iconCls:'icon-add',plain:true" onclick="onNewAdd()">新增</a>
				</power:Add>
				<power:Add>
					<a href="javascript:void(0)" class="easyui-linkbutton "
						data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">新增子权限</a>
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
			<table id="tg">
				<thead>
					<tr>
						<th data-options="field:'authorityName',width:120">权限名称</th>
						<th
							data-options="field:'authorityType',width:80,formatter:function(r){return showType(r);}">权限类型</th>
						<!-- <th data-options="field:'moduleName',width:80">模块名称</th> -->
						<th data-options="field:'url',width:180">目标地址</th>
						<!-- <th data-options="field:'operation',width:80">操作名称</th> -->
						<th data-options="field:'memo',width:100">描述</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="op_menu" class="easyui-menu" style="width: 120px;">
			<power:Add><div onclick="onAdd()" data-options="iconCls:'icon-edit'">新增子权限</div></power:Add>
			<power:Edit><div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div></power:Edit>
			<power:Del><div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div></power:Del>
		</div>

		<!--添加/修改用户弹出框  -->
		<div id="tg_add" class="easyui-window" title="新增权限"
			style="width:750px;height:400px">
			<iframe id="addIframe" src="" frameborder="0"
				style="width:100%;height:100%;"> </iframe>
		</div>
		<div id="tg_edit" title="修改权限" class="easyui-window"
			style="padding:10px; width: 750px; height: 400px;">
			<iframe id="editIframe" src="" frameborder="0"
				style="width:100%;height:100%;"> </iframe>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	var queryParams = serializeForm($('#queryForm'));
	$(function() {
		//初始化弹出窗口
		openWindow('#tg_add');
		openWindow('#tg_edit');
		$('#tg').treegrid({
			loadMsg : '数据加载中请稍后……',
			url : 'sys/authorityAction_getListPage.action',
			lines : true,
			rownumbers : true,
			animate : true,
			nowrap : false,
			collapsible : true,
			fitColumns : true,
			method : 'post',
			idField : 'id', //数据表格要有主键
			treeField : 'authorityName',//树形结构主键
			queryParams : queryParams,
			pagination: true,
			pageSize: 10,
			pageList: [5,10,15,20],
			//toolbar : '#tg_toolbar',//数据表工具栏
			onContextMenu : function(e, row) {
				e.preventDefault(); //屏蔽浏览器的菜单
				$(this).treegrid('unselectAll');//清除所有选中项
				$(this).treegrid('select', row.id);
				$('#op_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

		
	});

	//关闭窗口，提供给子页调用
	function closeWindow(id) {
		$(id).window('close');
		$('#tg').treegrid('reload');//刷新
	}

	function doQuery() {
		queryParams=serializeForm($('#queryForm'));
		$('#tg').treegrid("options").queryParams = queryParams;  
		$('#tg').treegrid('reload');//刷新
	}
	
	//处理权限类型
	function showType(r){//权限类型 0-菜单 1-新增 2-修改 3-查询 4-删除 5-导出 6-导入 7-授权
		if(r==0){
			return '菜单';
		}
		if(r==1){
			return '新增';
		}
		if(r==2){
			return '修改';
		}
		if(r==3){
			return '查询';
		}
		if(r==4){
			return '删除';
		}
		if(r==5){
			return '导出';
		}
		if(r==6){
			return '导入';
		}
		if(r==7){
			return '授权';
		}
		
	}

	function clearForm() {
		$('#queryForm').form('clear');
		$('#tg').treegrid('load', {});
	}
	
	function onNewAdd() {
		$('#addIframe')[0].src = 'sys/authorityAction_viewAdd.action';
		$('#tg_add').window('open'); // open a window   
	}

	function onAdd() {
		var node = $('#tg').treegrid('getSelected');//获得选中
		if(node==null){
			$.messager.show({
				title : '提示信息!',
				msg : '请选择父级权限!',
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
			return ;
		}
		$('#addIframe')[0].src = 'sys/authorityAction_viewAdd.action?authId='+node.id+'&parentIds='+node.parentIds;
		$('#tg_add').window('open'); // open a window   
	}

	function onUpdate() {
		var node = $('#tg').treegrid('getSelected');//获得选中
		if(node==null){
			$.messager.show({
				title : '提示信息!',
				msg : '请选择父级权限!',
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
			return ;
		}
	
		$('#editIframe')[0].src = 'sys/authorityAction_viewEdit.action?authId='+node.id;
		$('#tg_edit').window('open'); // open a window   
	}

	//取消选中项
	function reject() {
		$('#tg').treegrid('unselectAll');
	}

	//删除
	function doDelete() {
		var node = $('#tg').treegrid('getSelected');//获得选中
		if(node==null){
			$.messager.show({
				title : '提示信息!',
				msg : '请选择父级权限!',
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
			return ;
		}
			$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {
					$.post('sys/authorityAction_deleteAuthority.action', {
						authId : node.id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#tg').treegrid('unselectAll');
							$('#tg').treegrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除角色成功!',
								showType : 'fade',
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
	
	function reloadData(divId,value){
		if(value==""){
			$(divId).treegrid(reload);
		}else{
			$(divId).treegrid(reload,value);
		}
	}
	
	//js方法：序列化表单 			
	function serializeForm(form) {
		var obj = {};
		$.each(form.serializeArray(), function(index) {
			if (obj[this['name']]) {
				obj[this['name']] = obj[this['name']] + ',' + this['value'];
			} else {
				obj[this['name']] = this['value'];
			}
		});
		return obj;
	}
</script>
