<%@ page language="java" pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="css/multiselect/jquery.multiselect2side.css" type="text/css" />

<script type="text/javascript"
	src="js/common/jquery.multiselect2side.js"></script>
</head>
<body>
	<!--添加弹出框  -->

	<div class="easyui-tabs">

		<div title="设置角色用户" style="overflow:hidden;">
			<div style="margin-left: 30px;">
				<form id="queryForm" method="post">
					<input type="hidden" class="l_textbox" id="companyId"
						name="companyId" value="">
					<table>
						<tr>
							<!-- <td>用户类型: <select class="easyui-combobox" name="userType"
								style="width:200px;">
									<option value="1">教职工</option>
									<option value="2">学生</option>
							</select> -->
							<td>归属机构: <input id="orgId" type="text" class="easyui-combotree l_textbox"
										name="orgId" value="">
							</td>
							<td><a id="btnfind" class="easyui-linkbutton"
								data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a>
							</td>
							<td><a class="easyui-linkbutton"
								data-options="iconCls:'icon-group_add'"
								onclick="summitUserToRole()">保存</a> </td> 
						</tr>
					</table>
				</form>
				<form id="userForm">
					<div id="selUser"></div>
				</form>
			</div>
		</div>

		<div id="authorize_div" title="设置角色权限"
			style="padding: 10px;height: 400px;">
			<div id="tg_toolbar" align="right" style="height:7%">
				<a href="javascript:void(0)" class="easyui-linkbutton lb"
					data-options="iconCls:'icon-arrow_in_longer',plain:true"
					onclick="expandAll()">展开全部</a>&nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton lb"
					data-options="iconCls:'icon-arrow_out_longer',plain:true"
					onclick="collapseAll()">关闭全部</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton lb"
					data-options="iconCls:'icon-undo'" onclick="doCancel()">取消</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton lb"
					data-options="iconCls:'icon-save'" onclick="getChecked()">确定</a>
			</div>
			<div class="space_flow_10"></div>
			<div>
				<ul id="authority_aid" class="easyui-tree"></ul>
			</div>

		</div>

	</div>
</body>
</html>
<script type="text/javascript">
	var roleId = "${param.roleId}";
	$(function() {
		//组织机构下拉框
		$('#orgId')
				.combotree(
						{
							url : 'sys/organizationAction_getOrgTree.action',
							editable : false,
							loadFilter : function(data) {
								return data.result;
							}
						});
		//加载数据
		loadData();
	});

	//加载数据
	function loadData() {
		//加载用户数据
		doQuery();
		//加载权限数据
		var authorityUrl = "sys/authorityAction_getAuthorityTree.action";
		var hasAuthUrl = "sys/roleAuthorityAction_getAuthoritiesByRoleId.action";
		bingAuthority("#authority_aid", {
			roleId : roleId
		}, authorityUrl, hasAuthUrl);
	}

	//加载用户数据
	function doQuery() {
		//1、清空数据表
		//2
		var orgId = $('#orgId').combotree('getValue');
		var userType = $('#userType').val();
		//alert(orgId);
		var params;
		if (orgId == "") {
			params = {
				userType : userType
			};
		} else {
			params = {
				userType : userType,
				orgId : orgId
			};
		}
		bingUsers("#selUser", params, roleId,false);//默认只加载角色归属公司的用户,不可用$("#queryForm").serialize()，由于combox
	}

	// 绑定用户
	function bingUsers(divId, params, roleId, allFlag) {
		var url = "sys/userAction_getUsers.action?page=1&rows=99999";
		$
				.post(
						url,
						params,
						function(result) {
							var json = JSON.parse(result);
							if (json != null) {
								var s = '<select name="listKey" multiple="multiple" id="searchable">';
								if (allFlag)
									s += '<option value="">--请选择--</option>';
								for ( var i = 0; i < json.rows.length; i++) {
									var o = json.rows[i];
									s += "<option value='" + o.userId + "'>"
											+ o.employName + "</option>";
								}
								s += '</select>';
								$(divId).html(s);
								initSelector("#searchable");
								initSelectedUser(roleId);
							} else {
								alert("暂无用户可选");
							}
						});
	}

	// 初始化左右选择框
	function initSelector(selId) {
		$(selId).multiselect2side({
			search : "待选区-搜索：<img src='easyui/images/img/search.gif' />",
			selectedPosition : 'right',
			moveOptions : false,
			labelsx : '待选区',
			labeldx : '已选区',
			autoSortAvailable : true,
			autoSort : true
		});
	}

	// 初始化用户选择列表
	function initSelectedUser(roleId) {
		var url = "sys/userRoleAction_getUserByRoleId.action";
		$.post(url, {
			roleId : roleId
		}, function(data) {
			var json = JSON.parse(data);
			if (json.result != null) {
				$('#listKeyms2side__dx option')
						.each(
								function() {
									if ($(this).text() != null
											|| $(this).text() != "") {
										$(this).clone().appendTo(
												$('#listKeyms2side__sx'))
												.removeAttr("selected");
										$(this).remove().appendTo(
												$('#searchable')).removeAttr(
												"selected");
									}
								});
				$.each(json.result, function(i, obj) {
					$('#listKeyms2side__sx option').each(
							function() {
								if ($(this).val() == obj.userId) {
									$(this).remove();
									$(this).remove().appendTo(
											$('#listKeyms2side__dx'))
											.removeAttr("selected");
									$(
											'#searchable option[value="'
													+ $(this).val() + '"]').eq(
											0).remove();
								}
							});

				});
			} else {
				$('#listKeyms2side__dx option')
						.each(
								function() {
									if ($(this).text() != null
											|| $(this).text() != "") {
										$(this).clone().appendTo(
												$('#listKeyms2side__sx'))
												.removeAttr("selected");
										$(this).remove().appendTo($(selId))
												.removeAttr("selected");
									}
								});
			}
		});
	}

	// 绑定权限树
	function bingAuthority(divId, params, url, hasUrl) {
		var on_off = false;//开关，on_off为false时不执行onCheck事件，
		$(divId)
				.tree(
						{
							url : url,
							animate : true,
							checkbox : true,
							lines : true,
							cascadeCheck : false,
							loadFilter : function(data) {
								if (data.result) {
									return data.result;
								} else {
									return data;
								}
							},
							onLoadSuccess : function(row, data) {
								$.post(hasUrl, params, function(data) {
									var json = JSON.parse(data);
									$.each(json.result, function(i, obj) {
										var n = $(divId).tree('find', obj.id);
										if (n) {
											$(divId).tree('check', n.target);
										}
									});
								});
								on_off = true;
								//* $(".tree-checkbox",tree).unbind(".tree").bind("click.tree",function(){
								//	alert();
								//}); */

							},
							onClick : function(node) {
								if (node.checked) {
									var parentNode = $(divId).tree('getParent',
											node.target);
									if (parentNode != null) {
										$(divId).tree('check',
												parentNode.target);
									}
								} else {
									var childNode = $(divId).tree(
											'getChildren', node.target);
									if (childNode.length > 0) {
										for ( var i = 0; i < childNode.length; i++) {
											$(divId).tree('uncheck',
													childNode[i].target);
										}
									}
								}
							},
							onCheck : function(node, checked) {
								if (on_off) {
									if (checked) {
										var parentNode = $(divId).tree(
												'getParent', node.target);
										if (parentNode != null) {
											$(divId).tree('check',
													parentNode.target);
										}
									} else {
										var childNode = $(divId).tree(
												'getChildren', node.target);
										//alert(childNode.length);
										if (childNode.length > 0) {
											for ( var i = 0; i < childNode.length; i++) {
												on_off = false;
												$(divId).tree('uncheck',
														childNode[i].target);
												on_off = true;
											}
										}
									}
								}

							}
						});
	}

	//通过id保存关联用户
	function summitUserToRole() {
		var uids = '';
		$('#listKeyms2side__dx option').each(function() {
			if (uids != '')
				uids += ',';
			uids += $(this).val();
			;
		});
		//alert(uids);
		$.post('sys/userRoleAction_saveUserRole.action', {
			uids : uids,
			roleId : roleId
		}, function(result) {
			if (result.status == "ok") {
				$.messager.show({
					title : '提示信息',
					msg : '绑定用户成功',
					timeout: 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			} else {
				$.messager.show({
					title : '提示信息',
					msg : '绑定用户失败',
					timeout: 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		}, 'json');
	}

	//获得选中权限并提交
	function getChecked() {
		var nodes = $('#authority_aid').tree('getChecked');//获取权限树
		var aids = '';
		for ( var i = 0; i < nodes.length; i++) {
			if (aids != '')
				aids += ',';
			aids += nodes[i].id;
		}
		//alert(roleId);
		$.post('sys/roleAuthorityAction_saveRoleAuthority.action', {
			aids : aids,
			roleId : roleId
		}, function(result) {
			if (result.status == "ok") {
				
				$.messager.show({
					title : '提示信息!',
					msg : '授权成功',
					showType : 'fade',
					timeout : 3000,
					style : {
						right : '',
						bottom : ''
					}
				});
				$('#authority_aid').tree('reload');
			} else {
				$.messager.show({
					title : '提示信息',
					msg : '授权失败',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		}, 'json');
	}

	function expandAll() {
		$('#authority_aid').tree('expandAll');
	}

	function collapseAll() {
		$('#authority_aid').tree('collapseAll');
	}

	function doCancel() {
		//关闭窗口
		window.parent.closeWindow('#dg_tab');
	}
//-->
</script>
