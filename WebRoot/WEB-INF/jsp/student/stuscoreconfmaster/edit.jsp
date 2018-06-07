<%@ page language="java" pageEncoding="UTF-8"%>
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
<%@ include file="/pub/headmeta.jsp"%>
<link rel="stylesheet" type="text/css" href="css/css_form.css">
<!--添加弹出框  -->
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>
<body>
	<div>
		<div class="space_flow_20"></div>
		<form id="editForm" method="post">
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="sscm.studentno"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">所在年级:</div>
								<div class="edit_right">
									<input id="grade" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.grade"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">联系方式:</div>
								<div class="edit_right">
									<input id="telno" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.telno"
										data-options="" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
								<div class="edit_right">
									<input id="academicyear" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.academicyear"
										data-options="" value="">
								</div>
							</div>
						</div>
					</td>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="stuname" name="sscm.stuname" disabled="disabled"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">导学老师:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="teachername"
										disabled="disabled" name="sscm.teachername"></input>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="status"
										name="sscm.status" disabled="disabled"></input>
								</div>

							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
								<div class="edit_right">
									<input id="term" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.term"
										data-options="" value="">
								</div>
							</div>
						</div>

					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><%-- <a id="btnsummit" style="margin-left:55%"
						class="easyui-linkbutton" onclick="doSave()">保存</a>--%> 
						<a class="easyui-linkbutton" onclick="doSubmit()">提交</a>
						&nbsp;&nbsp;&nbsp; <a class="easyui-linkbutton"
						onclick="doClose()">关闭</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>

		</form>
		<div data-options="region:'center'">
			<table id="dg" style="height:485px" class="easyui-datagrid">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'coursename',width:200">课程名称</th>
						<th data-options="field:'employName',width:100">授课老师</th>
						<th data-options="field:'retakeflag',width:100,"
							formatter="formatRetakeflag">考试类型</th>
						<th data-options="field:'finalscore',width:100">总评成绩</th>
						<th data-options="field:'resitscore',width:100">补考成绩</th>
						<th data-options="field:'memo',width:100">成绩描述</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<power:Add>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick="onAddInfo()">增加</a>
		</power:Add>
		<power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="updateInfo()">编辑</a>
		</power:Edit>
		<power:Del>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
		</power:Del>
	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<div onclick="updateInfo()" data-options="iconCls:'icon-edit'">修改</div>
		<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
	</div>

</body>
</html>
<script type="text/javascript">
	var id = '<%=request.getAttribute("sscm.id")%>';
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		loadData();
		//加载历史数据
		$('#dg')
				.datagrid(
						{
							idField : 'id',
							loadMsg : '数据加载中请稍后……',
							url : 'stu/stuscoreconfDetailAction_listSelf.action?sscd.masterId='
									+ id,
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

	function loadData() {
		$.post('stu/stuscoreconfMasterAction_getSelf.action', {
			'sscm.id' : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.sscm;
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				$('#grade').val(o.grade);
				$('#academicyear').val(o.academicyear);
				//$('#academicyear').combobox('setValue',o.academicyear);
				$('#term').val(o.term);
				//$('#term').combobox('setValue',o.term);
				$('#teachername').val(o.teachername);
				$('#status').val(o.status);
				$('#telno').val(o.telno);
			} else {
				$.messager.show({
					title : '提示信息',
					msg : json.msg,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		});
	}
	function onAddInfo() {
		initWindow("dg_addInfo", "添加成绩", 390, 370,
				"stu/stuscoreconfDetailAction_viewAdd.action?masterid=" + id);
	}

	function doSubmit() {
		$.messager.confirm("提示信息", "确认提交?提交后将无法修改此表的任何信息!", function(r) {
			if (r) {
				$.post('stu/stuscoreconfMasterAction_submit.action', {
					'sscm.id' : id
				}, function callback(txt) {
					var json = $.parseJSON(txt);
					if (json.status == "ok") {
						$.messager.show({
							title : '提示信息',
							msg : '提交成功',
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						doClose()
					} else {
						$.messager.show({
							title : '提示信息',
							msg : json.msg,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
					}
				});
			}
		});
	}
	function updateInfo() {
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
		initWindow("dg_updateInfo", "修改信息", 390, 370,
				"stu/stuscoreconfDetailAction_viewEdit.action?id=" + id);
	}
	<%--//表单确定提交按钮
	function doSave() {
		if ($('#editForm').form('validate')) {
			var data = $("#editForm").serialize();
			data = data + "&sscm.id=" + id;
			$.post('stu/stuscoreconfMasterAction_updateSelf.action', data,
					function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$.messager.show({
								title : '提示信息',
								msg : '保存成功',
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
						} else {
							$.messager.show({
								title : '提示信息!',
								msg : '保存失败!',
								showType : 'fade',
								timeout : 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
						}
					});
		}

	}
	--%>
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
					for (var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post("stu/stuscoreconfDetailAction_deleteSelf.action", {
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
	//取消选中项
	function reject() {
		$('#dg').datagrid('unselectAll');
	}
	//关闭窗口，提供给子页调用
	function closeWindow(id) {
		$(id).window('destroy');
		$('#dg').datagrid('load', {});
	}
	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_edit');
	}
	function formatRetakeflag(val, row) {
		if (val == 0) {
			return '正常';
		} else if (val == 1) {
			return '补考';
		} else {
			return '重修';
		}
	}
</script>