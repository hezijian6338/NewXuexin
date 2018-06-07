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
<link rel="stylesheet" type="text/css" href="css/css_form.css">
</head>
<body>
	<!--添加弹出框  -->
	<div>
		<div class="space_flow_20"></div>
		<form id="editForm" method="post">
			<input type="hidden" id="id" name="id" value="" />
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
				<div class="edit_right">
					<input id="academicyear" type="text"
						class="easyui-validatebox l_textbox" name="academicyear"
						data-options="validType:'maxLength[20]'" value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
				<div class="edit_right">
					<input id="term" type="text" class="easyui-validatebox l_textbox"
						name="term" data-options="validType:'maxLength[20]'" value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">课程名称:</div>
				<div class="edit_right">
					<input id="coursename" type="text"
						class="easyui-validatebox l_textbox" name="coursename"
						data-options="required:true,missingMessage:'课程名称!',validType:'maxLength[50]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">选课课号:</div>
				<div class="edit_right">
					<input type="text" id="selectedcourseno" name="selectedcourseno"
						class="easyui-validatebox l_textbox" disabled="disabled"
						data-options="required:true,missingMessage:'选课课号!',validType:'maxLength[50]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">教师姓名:</div>
				<div class="edit_right">
					<input id="employName" type="text"
						class="easyui-validatebox l_textbox" name="employName"
						data-options="required:true,missingMessage:'教师姓名!',validType:'maxLength[20]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">教师工号:</div>
				<div class="edit_right">
					<input type="text" id="employNo" name="employNo"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'教师工号!',validType:'maxLength[10]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">开课单位:</div>
				<div class="edit_right">
					<input id="orgId" type="text" class="easyui-combotree l_textbox"
						name="orgId" data-options="required:true" value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">上课时间:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="schooltime" name="schooltime"
						rows="4" cols="25" data-options="validType:'maxLength[100]'"></textarea>
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">上课地点:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="address" name="address" rows="4"
						cols="25" data-options="validType:'maxLength[100]'"></textarea>
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="memo" name="memo" rows="4"
						cols="25" data-options="validType:'maxLength[200]'"></textarea>
				</div>
			</div>

			<div class="edit-line"></div>
			<div class="space_flow_20"></div>
			<div class="edit_btn">
				<a id="btnsummit" class="easyui-linkbutton" onclick="doSave()"
					data-options="iconCls:'icon-save'">保存</a>
			</div>
			<div class="edit_btn">
				<a id="btncancer" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="doClose()">取消</a>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var id = '${param.id}';
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		//组织机构下拉框
		$('#orgId').combotree({
			url : 'sys/organizationAction_getOrgTree.action',
			editable : false,
			loadFilter : function(data) {
				return data.result;
			}
		});
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("tea/attendanceMasterAction_getAttendanceMaster.action", {
			sid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#coursename').val(o.coursename);
				$('#selectedcourseno').val(o.selectedcourseno);
				$('#employNo').val(o.employNo);
				$('#employName').val(o.employName);
				$('#orgId').combotree('setValue', o.orgId);
				$('#academicyear').val(o.academicyear);
				$('#term').val(o.term);
				$('#address').val(o.address);
				$('#schooltime').val(o.schooltime);
				$('#memo').val(o.memo);
			} else {
				$.messager.show({
					title : '提示信息',
					msg : '数据加载失败',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		});
	}

	//表单确定提交按钮
	function doSave() {
		if ($('#editForm').form('validate')) {
			$.post("tea/attendanceMasterAction_update.action?selectedcourseno="+$('#selectedcourseno').val(), $("#editForm")
					.serialize(), function callback(txt) {
				var json = $.parseJSON(txt);
				if (json.status == "ok") {
					$.messager.show({
						title : '提示信息',
						msg : '保存成功',
						timeout : 3000,
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					doClose();
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

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_edit');
	}
</script>