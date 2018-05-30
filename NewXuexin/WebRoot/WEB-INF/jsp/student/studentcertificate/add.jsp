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
		<form id="addForm" method="post">
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox"
						id="studentno" name="studentno"
						data-options="required:true,missingMessage:'学号必填!',validType:'maxLength[16]'"
						value="">
				</div>
			</div>

			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
				<div class="edit_right">
					<input type="text" id="stuname" name="stuname"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'姓名必填!',validType:'maxLength[20]'"
						value="">
				</div>
			</div>
			<div class="edit_winRow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;证书名称:</div>
				<div class="edit_right">
					<input id="certificatename" type="text"
						class="easyui-validatebox l_textbox" name="certificatename"
						data-options="validType:'maxLength[50]'" value="">
				</div>
			</div>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;证书描述:</div>
				<div class="edit_right">
					<textarea class="l_textarea" id="description" name="description"
						rows="4" cols="40" data-options="validType:'maxLength[200]'"></textarea>
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
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		//组织机构下拉框
		/* $('#orgId').combotree({
			url : 'sys/organizationAction_getOrgTree.action',
			editable : false,
			loadFilter : function(data) {
				return data.result;
			}
		}); */
	});

	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.post("stu/studentCertificateAction_save.action", $("#addForm")
					.serialize(), function callback(txt) {
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

	//上传后回调
	function uploadResult() {
		var code = document.uploadFrame.resultCode;

		if (code == 0) {
			$.messager.alert('操作提示', "上传附件成功！", 'info', function() {
				doClose();
			});
		} else {
			$.messager.alert('操作提示', "发生未知异常,请联系管理员!", 'info', function() {
				doClose();
			});
		}
	}

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_add');
	}
</script>