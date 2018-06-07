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
	<div class="page_container">
		<div class="space_flow_20"></div>
		<form id="editForm" action="" method="post">
			<div class="edit_row">
				<div class="edit_left">权限名称:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox" id="authorityName" name="authorityName"
						data-options="required:true,missingMessage:'权限名称不能为空!'" maxlength="32" value="">
				</div>
			</div>
			<div class="edit_row">
				<div class="edit_left">权限类型:</div>
				<div class="edit_right">
					<input id="authorityType" type="text" class="easyui-combobox l_textbox"
						name="authorityType" data-options="required:true" value="">
				</div>
			</div>
			<div class="edit_row">
				<div class="edit_left">模块名称:</div>
				<div class="edit_right">
					<input type="text" class="l_textbox" id="moduleName" name="moduleName" value="">
				</div>
			</div>
			<div class="edit_row">
				<div class="edit_left">排序号:&nbsp;&nbsp;&nbsp;</div>
				<div class="edit_right">
					<input type="text" class="l_textbox" id="menuNo" name="menuNo" value="">
				</div>
			</div>
			<div class="edit_row">
				<div class="edit_left">访问地址:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="url" name="url"
						rows="4" cols="40">#</textarea>
				</div>
			</div>
			
			<div class="edit_row">
				<div class="edit_left">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="memo" name="memo"
						rows="4" cols="40"></textarea>
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
					data-options="iconCls:'icon-undo'" onclick="doCancel()">取消</a>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var authId = '${param.authId}';
	$(function() {
		//权限类型下拉框
		$('#authorityType')
				.combobox(
						{
							url : 'sys/authorityAction_getAuthorityTypeList.action',
							valueField : 'typeId',
							textField : 'typeName',
							editable : false,
							loadFilter : function(data) {
								return data.result;
							}
						});	
		//加载历史数据
		loadData();
	});
	
	function loadData() {
		$.post("sys/authorityAction_getAuthority.action", {
			authId : authId
		}, function callback(txt) {
			var json = $.parseJSON(txt);

			if (json.status == "ok") {
				var o = json.result;
				$('#authorityName').val(o.authorityName);
				$('#authorityType').combobox('select',o.authorityType);
				$('#moduleName').val(o.moduleName);
				$('#menuNo').val(o.menuNo);
				$('#url').val(o.url);
				$('#memo').val(o.memo);
			} else {
				$.messager.show({
								title : '提示信息',
								msg : '数据加载失败',
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
			$
					.ajax({
						type : 'post',
						url : 'sys/authorityAction_updateAuthority.action?authId='+authId,
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#editForm").serialize(),
						success : function(result) {
						//alert(result.status);
							if(result.status=="ok"){
								//关闭窗口
								doColse(authId);
							}else{
								$.messager.show({
									title : '提示信息!',
									msg : result.msg,
									showType : 'fade',
									timeout : 3000,
									style : {
										right : '',
										bottom : ''
									}
								});
							}			
							
						},
						error : function(result) {
							$.meesager.show({
								title : '提示信息',
								msg : '修改失败',
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
						}

					});
		}	
	}
	function doCancel(){
		//关闭窗口
		window.parent.closeWindow('#tg_edit');
	}
	
	function doColse(value){
		//关闭窗口
		window.parent.closeWindow('#tg_edit',value);
	}
</script>