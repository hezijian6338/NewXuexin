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
</head>
<body>
	<!--编辑弹出框  -->
	<div><div class="space_flow_20"></div>
		<form id="editForm" action="" method="post">
			<input type="hidden" id="roleId" name="roleId" value="" />
			<div class="edit_row">
				<div class="edit_left">角色编号:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox" id="roleNo" name="roleNo"
						data-options="required:true,missingMessage:'角色编号必填!'" value="">
				</div>
			</div>
			<div class="edit_row">
				<div class="edit_left">角色名称:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox" id="roleName" name="roleName"
						data-options="required:true,missingMessage:'角色名称必填!'" value="">
				</div>
			</div>
			
			<div class="edit_row">
				<div class="edit_left">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="memo" name="memo" rows="4"
						cols="40"></textarea>
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
					data-options="iconCls:'icon-redo'" onclick="clearForm()">重置</a>
			</div>
			<div class="edit_btn">
				<a id="btncancer" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="doCancel()">关闭</a>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		//加载历史数据
		loadData();
	});
	
	//加载历史数据
	function loadData() {
		$.post("sys/roleAction_getRole.action", {
			roleId : '${param.roleId}'
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var info = json.info;
				$('#roleId').val(info.roleId);
				$('#roleNo').val(info.roleNo);
				$('#roleName').val(info.roleName);
				$('#memo').val(info.memo);
			} else {
				$.messager.show({
								title : '提示信息',
								msg : '数据加载失败',
								showType : 'fade',
								timeout: 3000,
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
			$
					.ajax({
						type : 'post',
						url : 'sys/roleAction_updateRole.action',
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#editForm").serialize(),
						success : function(result) {
							$.messager.show({
								title : '提示信息',
								msg : result.message,
								showType : 'fade',
								timeout: 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
							//关闭窗口
							window.parent.closeWindow('#dg_edit');
						},
						error : function(result) {
							$.meesager.show({
								title : '提示信息',
								msg : result.message,
								showType : 'fade',
								timeout: 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
						}

					});
		}	
	}
	function clearForm() {
		$('#editForm').form('clear');
	}
	function doCancel(){
		//关闭窗口
		window.parent.closeWindow('#dg_edit');
	}
</script>