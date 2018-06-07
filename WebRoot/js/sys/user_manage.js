var flag; // 判断走的是保存还是修改方法
$(function() {
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
		pageList : [ 5, 10, 15, 20, 50 ]
	});

	setDialog('#dg_add');// 设置dialog显示样式,来自common.js
	setDialog('#dg_edit');// 设置dialog显示样式,来自common.js
	setDialog('#dg_tab');// 设置dialog显示样式,来自common.js

});

function onDelete() {
	var arr = $('#dg').datagrid('getSelections');
	if (arr.length <= 0) {
		$.messager.show({
			title : '提示信息!',
			msg : '请选择用户!',
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
				$.post('sys/userAction_deleteUsers.action', {
					ids : ids
				}, function(result) {
					if (result == "ok") {
						$('#dg').datagrid('unselectAll');
						$('#dg').datagrid('reload');
						$.messager.show({
							title : '提示信息',
							msg : '删除用户成功!',
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
					} else {
						$.messager.show({
							title : '提示信息',
							msg : '删除用户失败!',
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
}
function reject() {
	$('#dg').datagrid('unselectAll');
}
function openSearch() {
	$('#lay').layout('expand', 'north');

}
function searchUser() {

	$('#dg').datagrid('load', serializeForm($('#searchFrom')));

}

function clearForm() {
	$('#searchFrom').form('clear');
	$('#dg').datagrid('load', {});

}

function readExcel() {

	$('#rfileForm').form('clear');
	$('#readFile').dialog('open');
}

function authority() {
	var arr = $('#dg').datagrid('getSelections');
	if (arr.length != 1) {
		$('#dg').datagrid('unselectAll');
		$.messager.show({
			title : '提示信息!',
			msg : '只能选择单个用户授权!',
			showType : 'fade',
			style : {
				right : '',
				bottom : ''
			}
		});
	} else {
		$('#userAuthority').dialog('open');
		var uid = arr[0].id;
		alert(uid);
		$("#tt").tree({
			url : 'sys/authorityAction_getAllForTree.action',
			method : 'get',
			animate : true,
			checkbox : true,
			cascadeCheck : false,
			lines : true,
			onLoadSuccess : function(node, data) {
				$.post('sys/userAuthorityAction_getAllUserAuthority.action', {
					uid : uid
				}, function(result) {
					$.each(JSON.parse(result), function(i, obj) {
						var n = $("#tt").tree('find', obj.id);
						if (n) {
							$("#tt").tree('check', n.target);
						}
					});
				});
				$('#tt').tree('expandAll');
			}
		});

	}
}

// 获得选中权限并提交
function getChecked() {
	var nodes = $('#tt').tree('getChecked');// 获取权限树
	var node = $('#dg').datagrid('getSelections')[0];// 获取选用户
	var aids = '';
	for ( var i = 0; i < nodes.length; i++) {
		if (aids != '')
			aids += ',';
		aids += nodes[i].id;
	}
	$.post('sys/userAuthorityAction_addUserAuthority.action', {
		aids : aids,
		uid : node.id
	}, function(result) {
		$('#userAuthority').dialog('close');
		if (result.status == "ok") {
			$('#tg').treegrid('loadData', result);
			$.messager.show({
				title : '提示信息',
				msg : '组授权成功',
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
		} else {
			$.messager.show({
				title : '提示信息',
				msg : '组授权失败',
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
		}
	}, 'json');

}

function cancel() {
	$('#userAuthority').dialog('close');
}
// 格式化时间------------------start
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	// var h = date.getHours();
	// var min = date.getMinutes();
	// var sec = date.getSeconds();
	// return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
	// + (d < 10 ? ('0' + d) : d) + ' ' + (h < 10 ? ('0' + h) : h)
	// + ':' + (min < 10 ? ('0' + min) : min) + ':'
	// + (sec < 10 ? ('0' + sec) : sec);
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	// var h = s.substring(ss[3], 10);
	// var min = s.substring(ss[4], 10);
	// var sec = s.substring(ss[5], 10);
	// if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min)
	// && !isNaN(sec)) {return new Date(y, m - 1, d, h, min, sec);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
// 格式化时间------------------end
// js方法：序列化表单
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