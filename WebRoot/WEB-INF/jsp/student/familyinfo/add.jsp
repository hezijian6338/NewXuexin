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
</head>
<body>
	<!--添加弹出框  -->
	<div>
		<div class="space_flow_20"></div>
		<form id="addForm" method="post">
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
						<c:if test="${!isStu}">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
						</c:if>
							<div class="edit_winRow">
								<div class="edit_left">成员名称:</div>
								<div class="edit_right">
									<input id="name" type="text"
										class="easyui-validatebox l_textbox" name="name"
										data-options="required:true,missingMessage:'名称必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">电话号码:</div>
								<div class="edit_right">
									<input id="telno" type="text"
										class="easyui-validatebox l_textbox" name="telno"
										data-options="required:true,missingMessage:'电话号码必填!',validType:'mobile2'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;单位:</div>
								<div class="edit_right">
									<div class="edit_right">
										<textarea class="l_textarea" id="company" name="company"
											rows="4" cols="25"></textarea>
									</div>
								</div>

							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职务:</div>
								<div class="edit_right">
									<input id="jobduty" type="text"
										class="easyui-validatebox l_textbox" name="jobduty"
										data-options="" value="">
								</div>
							</div>
						</div></td>
					<td>

						<div style="width: 350px;">
						<c:if test="${!isStu}">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="studentno" name="studentno" 
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
						</c:if>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称呼:</div>
								<div class="edit_right">
									<input id="call" type="text"
										class="easyui-validatebox l_textbox" name="call"
										data-options="required:true,missingMessage:'称呼必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">政治面貌:</div>
								<div class="edit_right">
									<input id="politicalstatus" type="text"
										class="easyui-validatebox l_textbox" name="politicalstatus"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">单位地址:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="companyaddress"
										name="companyaddress" rows="4" cols="25"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">单位邮编:</div>
								<div class="edit_right">
									<input id="postcode" type="text"
										class="easyui-validatebox l_textbox" name="postcode"
										data-options="" value="">
								</div>
							</div>

						</div></td>
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
			$.post('${isStu?"stu/familyInfoAction_saveFamilyInfo.action":"stu/familyInfoAction_save.action"}', $(
					"#addForm").serialize(), function callback(txt) {

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
						msg : '保存失败或学号不存在!',
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
		window.parent.closeWindow('#dg_add');
	}
</script>