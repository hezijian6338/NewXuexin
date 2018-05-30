var flag = null;// 标识表单信息
$(document).ready(
		function() {
			// 数据表格
			$('#userMsg').datagrid({
				title : '职位管理',
				loadMsg : '数据加载中请稍后……',
				fitColumns : true,
				idField : 'id',
				height : 500,
				url : 'positionAction_findPositions.action',
				iconCls : 'icon-search',
				striped : true,
				nowrap : true,
				rownumbers : true,
				singleSelect : true,
				columns : [ [ {
					field : 'positionCode',
					title : '职位编号',
					align : 'center',
					width : 10
				}, {
					field : 'positionName',
					title : '职位名称',
					align : 'center',
					width : 10
				} ] ],
				toolbar : '#tg_toolbar',
				pagination : true,
				pageSize : 5,
				pageList : [ 5, 10, 15, 20 ],
				remoteSort : false
			});

			// 取消按钮，关闭输入框
			$('#btncancer').click(function() {
				closeDialog();
			});
			// 提交按钮，提交表单信息
			$('#btnsummit').click(
					function() {
						if (flag == 'add') {
							if ($('#userForm').form('validate')) {
								$.post('positionAction_save.action', $(
										'#userForm').serialize(), function(
										result) {
									if (result) {
										$('#userMsg').datagrid('unselectAll');
										$('#userMsg').datagrid('reload');
										$.messager.show({
											title : '提示信息',
											msg : '增加职位操作成功!',
											showType : 'fade',
											style : {
												right : '',
												bottom : ''
											}
										});
										closeDialog();
									} else {
										$.messager.show({
											title : '提示信息',
											msg : '增加职位操作失败!',
											showType : 'fade',
											style : {
												right : '',
												bottom : ''
											}
										});
									}
								});
							} else {
								$.messager.show({
									title : '提示信息!',
									msg : '数据验证不通过,不能保存!',
									showType : 'fade',
									style : {
										right : '',
										bottom : ''
									}
								});
							}
							;
						}
						if (flag == 'edit') {
							if ($('#userForm').form('validate')) {
								alert();
								$.post('positionAction_update.action', $(
										'#userForm').serialize(), function(
										result) {
									if (result) {
										$('#userMsg').datagrid('unselectAll');
										$('#userMsg').datagrid('reload');
										$.messager.show({
											title : '提示信息',
											msg : '修改职位操作成功!',
											showType : 'fade',
											style : {
												right : '',
												bottom : ''
											}
										});
										closeDialog();
									} else {
										$.messager.show({
											title : '提示信息',
											msg : '修改职位操作失败!',
											showType : 'fade',
											style : {
												right : '',
												bottom : ''
											}
										});
									}
								});
							} else {
								$.messager.show({
									title : '提示信息!',
									msg : '数据验证不通过,不能保存!',
									showType : 'fade',
									style : {
										right : '',
										bottom : ''
									}
								});
							}
							;
						}
					});
		});

// 增加用户
function onAdd() {
	flag = 'add';
	$('#userMsg').datagrid('unselectAll');
	$('#dg_dia').form('clear');
	$('#dg_dia').dialog({
		title : '增加职位',
		iconCls : 'icon-add'
	});
	openDialog();
}
// 编辑用户
function onUpdate() {
	flag = 'edit';
	$('#dg_dia').dialog({
		title : '编辑职位',
		iconCls : 'icon-edit'
	});
	var node = $('#userMsg').datagrid('getSelected');
	if (node == null) {
		$.messager.show({
			title : '提示信息!',
			msg : '没有选择职位!',
			showType : 'fade',
			style : {
				right : '',
				bottom : ''
			}
		});
	} else {
		$('#dg_dia').form('clear');
		$('#dg_dia').form('load', {
			id : node.id,
			positionCode : node.positionCode,
			positionName : node.positionName
		});
		openDialog();
	}
}
// 删除用户
function onDelete() {
	var node = $('#userMsg').datagrid('getSelected');
	if (node == null) {
		$.messager.show({
			title : '提示信息!',
			msg : '没有选择职位!',
			showType : 'fade',
			style : {
				right : '',
				bottom : ''
			}
		});
	} else {
		$.messager.confirm("提示信息", "确认删除?", function(r) {
			if (r) {
				
				$.post('positionAction_delete.action', {
					id:node.id
				}, function(result) {
					if (result) {
						$('#userMsg').datagrid('unselectAll');
						$('#userMsg').datagrid('reload');
						$.messager.show({
							title : '提示信息',
							msg : '删除成功!',
							showType : 'fade',
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
}// --end of 删除用户

// 打开输入框
function openDialog() {
	$('#dg_dia').dialog('open');
}
// 关闭输入框
function closeDialog() {
	$('#dg_dia').dialog('close');
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
