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
				<div class="nav_left">系统管理&nbsp;&gt;&gt;&nbsp;组织机构管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">
				<input type="hidden" id="isSearch" name="isSearch" value="0">
			</form>
			<div align="right" style="padding-right: 20px;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
				<power:Add>
					<a href="javascript:void(0)" class="easyui-linkbutton "
						data-options="iconCls:'icon-add',plain:true" onclick="onNewAdd()">新增机构</a>
				</power:Add>
				<power:Add>
					<a href="javascript:void(0)" class="easyui-linkbutton "
						data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">新增子机构</a>
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
		</div>
		<div data-options="region:'center',border:false">		
			<table id="tg">
				<thead>
					<tr>
						<th data-options="field:'orgName',width:120">机构名称</th>
						<th data-options="field:'contactMan',width:80">负责人</th>
						<th data-options="field:'tell',width:80">电话</th>
						<th data-options="field:'fax',width:80">传真</th>
						<th data-options="field:'email',width:80">邮箱</th>
						<th data-options="field:'postCode',width:80">邮编</th>
						<th data-options="field:'status',width:40,formatter:function(r){return showState(r);}">状态</th>
						<th data-options="field:'address',width:120">地址</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="op_menu" class="easyui-menu" style="width: 120px;">
			<power:Add><div onclick="onAdd()" data-options="iconCls:'icon-edit'">新增子机构</div></power:Add>
			<power:Edit><div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div></power:Edit>
			<power:Del><div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div></power:Del>
		</div>

		<!--添加/修改用户弹出框  -->
		<div id="tg_add" class="easyui-window" title="新增组织机构"
			style="width:750px;height:400px">
			<iframe id="addIframe" src="" frameborder="0"
				style="width:100%;height:100%;"> </iframe>
		</div>
		<div id="tg_edit" title="修改组织机构" class="easyui-window"
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
		//初始化树表
		$('#tg').treegrid({
			loadMsg : '数据加载中请稍后……',
			url : 'sys/organizationAction_getList.action',
			lines : true,
			rownumbers : true,
			animate : true,
			nowrap : false,
			collapsible : true,
			fitColumns : true,
			method : 'post',
			idField : 'id', //数据表格要有主键
			treeField : 'orgName',//树形结构主键
			queryParams : queryParams,
			//toolbar : '#tg_toolbar',//数据表工具栏
			loadFilter : function(data,parentId) {
			//alert(JSON.stringify(data.result));
				return data.result;
			},
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
	
	function showState(status){
		if(status=='1'){
			return '启用';
		}else{
		 	return '禁用';
		} 
	}

	//关闭窗口，提供给子页调用
	function closeWindow(id,value) {
		$(id).window('close');
		reloadData('#tg',value);
	}
	
	function doQuery() {
		$("#isSearch").val("1");//是否为条件查询   0-否，1-是
		queryParams=serializeForm($('#queryForm'));
		$('#tg').treegrid("options").queryParams = queryParams;  
		$('#tg').treegrid('reload');//刷新
				
	}

	function clearForm() {
		$('#queryForm').form('clear');
		$('#tg').treegrid('load', {});
	}
	
	function onNewAdd() {
		$('#addIframe')[0].src = 'sys/organizationAction_viewAdd.action';
		$('#tg_add').window('open'); // open a window   
	}

	function onAdd() {
		var node = $('#tg').treegrid('getSelected');//获得选中
		if(node==null){
			$.messager.show({
				title : '提示信息!',
				msg : '请选择父级机构!',
				timeout: 3000,
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
			return ;
		}
		$('#addIframe')[0].src = 'sys/organizationAction_viewAdd.action?orgId='+node.id+'&parentIds='+node.parentIds;
		$('#tg_add').window('open'); // open a window   
	}

	function onUpdate() {
		var node = $('#tg').treegrid('getSelected');//获得选中
		if(node==null){
			$.messager.show({
				title : '提示信息!',
				msg : '请选择修改机构!',
				showType : 'fade',
				timeout: 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return ;
		}
	
		$('#editIframe')[0].src = 'sys/organizationAction_viewEdit.action?orgId='+node.id;
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
				msg : '请选择删除信息!',
				timeout: 3000,
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
					$.post('sys/organizationAction_delete.action', {
						id : node.id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#tg').treegrid('unselectAll');
							$('#tg').treegrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除成功!',
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
								msg : '删除失败!',
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
	
	function reloadData(divId,value){
		$(divId).treegrid('reload');
		if(value==null || value==""){
			expandAll();
		}else{
			expandTo(value);
		}		
	}
	
	function collapseAll(){
		$('#tg').treegrid('collapseAll');
	}
	
	function expandAll(){
		$('#tg').treegrid('expandAll');
	}
	
	function expandTo(id){
		$('#tg').treegrid('expandTo',id).treegrid('select',id);
	}
</script>
