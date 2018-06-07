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

<title>用户管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">系统管理&nbsp;&gt;&gt;&nbsp;用户管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<td>用户类型: 
						<select class="easyui-combobox" name="userType" style="width:200px;">
							<option value="" selected="selected">全部</option>
							<option value="0">教职工</option>
							<option value="1">学生</option>
						</select>
						</td>
						<td>用户名称: <input type="text" class="l_textbox"
							id="employName" name="employName" value="">
						</td>
						<td>用户编号: <input type="text" class="l_textbox"
							id="employNo" name="employNo" value="">
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
						<th data-options="field:'userId',checkbox:true,width:20"></th>
						<th data-options="field:'employNo',width:80">用户编号</th>
						<th data-options="field:'employName',width:80">用户名</th>
						<!-- <th data-options="field:'sex',width:50,formatter:function(r){return showSex(r)}">性别</th> -->
						<th data-options="field:'sex',width:50">性别</th>
						<th data-options="field:'tell',width:120">手机号</th>
						<th data-options="field:'email',width:120">电子邮箱</th>
						<th data-options="field:'address',width:120">联系地址</th>

						<th data-options="field:'roleNames',width:120">角色</th>
						<th
							data-options="field:'userType',width:50,formatter:function(r){return showType(r)}">用户类型</th>
						<th
							data-options="field:'organization',width:100,formatter:function(r){return r.orgName}">
							所属机构</th>
						<th data-options="field:'createTime',width:100">创建时间</th>
						<th
							data-options="field:'status',width:50,formatter:function(r){return showStatus(r)}">启用状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="authority()">用户授权</a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<power:Add><a href="javascript:void(0)" class="easyui-linkbutton "
			data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">增加</a></power:Add>
		<power:Edit><a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="onUpdate()">编辑</a></power:Edit>
		<power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdatePwd()">修改密码</a>
		</power:Edit>
		<power:Del><a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a></power:Del>
	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<power:Edit><div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div></power:Edit>
		<power:Edit>
			<div onclick="onUpdatePwd()" data-options="iconCls:'icon-edit'">修改密码</div>
		</power:Edit>
		<power:Del><div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div></power:Del>
	</div>

	<!--添加/修改用户弹出框  -->
	<!-- <div id="dg_add" class="easyui-window" title="新增用户"
		style="width:750px;height:400px;">
		<iframe id="addIframe" src="" frameborder="0"
			style="width:100%;height:100%;"> </iframe>
	</div>
	<div id="dg_edit" title="编辑用户" class="easyui-window"
		style="padding:10px; width: 750px; height: 350px;">
		<iframe id="editIframe" src="" frameborder="0"
			style="width:100%;height:100%;"> </iframe>
	</div> -->
</body>
</html>
<script type="text/javascript">
	$(function() {
		init_easyui_celltip();//要放datagrid前初始化
		$('#dg').datagrid({
			idField : 'userId',
			loadMsg : '数据加载中请稍后……',
			fit : true,
			url : 'sys/userAction_getUserList.action',
			lines : true,
			animate : true,
			nowrap : false,
			collapsible : true,
			fitColumns : true,
			striped : true, // 隔行变色特性F
			rownumbers : true,
			remoteSort : false, // 将本地排序去掉
			sortName : 'createTime',// 设置排序列
			sortOrder : 'desc',
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
			onLoadSuccess:function(data){   
    			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});   
			}
		});

		//初始化弹出窗口
		//openWindow('#dg_add');
		//openWindow('#dg_edit');
		//openWindow('#dg_tab');
	});
	
	//关闭窗口，提供给子页调用
	function closeWindow(id){
		//$(id).window('close');
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
	
	/* function showSex(sex){
		if(sex==0){
			return '男';
		}else{
		 	return '女';
		} 
	} */
	
	function showStatus(status){
		if(status==1){
			return '启用';
		}else{
		 	return '禁用';
		} 
	}
	
	function showType(userType){
		if(userType=='0'){
			return '教职工';
		}else{
		 	return '学生';
		} 
	}
	
	function clearForm() {
		$('#queryForm').form('clear');
		$('#dg').datagrid('load', {});
	}

	function onAdd() {
		/* $('#addIframe')[0].src = 'sys/userAction_viewAdd.action';
		$('#dg_add').window('open'); // open a window  */ 
		initWindow("dg_add","新增用户",750,400,"sys/userAction_viewAdd.action"); 
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
		var userId = arr[0].userId;
		/* $('#editIframe')[0].src = 'sys/userAction_viewEdit.action?userId='
				+ userId;
		$('#dg_edit').window('open'); // open a window  */ 
		initWindow("dg_edit","修改用户",750,400,'sys/userAction_viewEdit.action?userId='
				+ userId); 
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
						ids += arr[i].userId + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post('sys/userAction_delete.action', {
						ids : ids
					}, function callback(txt) {
					var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除用户成功!',
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
								msg : '删除用户失败!',
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
	
	function onUpdatePwd() {
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
		var userId = arr[0].userId;
		initWindow("dg_editpwd","修改密码",600,300,"sys/userAction_pwd2.action?userId="+userId);
	}
	
</script>

