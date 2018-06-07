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
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="studentno"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!',validType:'maxLength[16]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox" 
										data-options="required:true,missingMessage:'姓名必填!',validType:'maxLength[20]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="sex" id="sex0"
										value="0" checked="checked" />男 <input type="radio" id="sex1"
										class="radio-style" name="sex" value="1" />女
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">派遣性质:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="dispatchtype"
										name="dispatchtype" rows="4" cols="25"
										data-options="validType:'maxLength[100]'"></textarea>
								</div>
							</div>
						</div>
					</td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">EMS编号:</div>
								<div class="edit_right">
									<input id="emsno" type="text"
										class="easyui-validatebox l_textbox" name="emsno"
										data-options="required:true,validType:'maxLength[20]'" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">
									北理工编<br>号:
								</div>
								<div class="edit_right">
									<input id="schoolno" type="text"
										class="easyui-validatebox l_textbox" name="schoolno"
										data-options="required:true,validType:'maxLength[30]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业:</div>
								<div class="edit_right">
									<input id="major" type="text"
										class="easyui-validatebox l_textbox" name="major"
										data-options="validType:'maxLength[100]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">主管单位:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="chargeunit" name="chargeunit"
										rows="4" cols="25" data-options="validType:'maxLength[100]'"></textarea>
								</div>
							</div>

						</div></td>
				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">&nbsp;&nbsp;&nbsp;邮件号*:</div>
							<div class="edit_right">
								<input id="mailno" type="text"
									class="easyui-validatebox l_textbox" name="mailno"
									data-options="required:true,validType:'maxLength[30]'" value="">
							</div>
						</div>
					</td>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="memo" name="memo" rows="4"
									cols="25" data-options="validType:'maxLength[200]'"></textarea>
							</div>
						</div>
					</td>

				</tr>

				<tr>
					<td></td>
					<td><a id="btnsummit" class="easyui-linkbutton"
						onclick="doSave()">确定</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
						id="btncancer" class="easyui-linkbutton" onclick="doClose()">取消</a>
					</td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var id = '${param.id}';
	//自定义easyui表单验证
	easyui_form_validator();
	$(function() {
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
		$.post("stu/postInfoAction_getPostInfo.action", {
			gsId : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				if (o.sex == "0") {
					$('#sex0').attr("checked", "checked");
				} else {
					$('#sex1').attr("checked", "checked");
				}
				$('#major').val(o.major);
				$('#dispatchtype').val(o.dispatchtype);
				$('#chargeunit').val(o.chargeunit);
				$('#mailno').val(o.mailno);
				$('#emsno').val(o.emsno);
				$('#schoolno').val(o.schoolno);
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
			$.post("stu/postInfoAction_update.action?studentno="+$('#studentno').val(), $("#editForm")
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