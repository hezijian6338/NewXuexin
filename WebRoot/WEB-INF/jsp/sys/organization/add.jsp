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
<style>
.edit_winRow {
	margin-left: 15%;
	margin-bottom: 15px;
	overflow: hidden;
	width: auto;
	height: auto;
	_zoom: 1;
}
</style>
</head>
<body>
	<!--编辑弹出框  -->
	<div class="page_container">
		<div class="space_flow_20"></div>
		<form id="addForm" action="" method="post">
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">机构名称:</div>
								<div class="edit_right">
									<input type="text" id="orgName" name="orgName"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'机构名称必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;负责人:</div>
								<div class="edit_right">
									<input id="contactMan" type="text" class="l_textbox"
										name="contactMan" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话:</div>
								<div class="edit_right">
									<input id="tell" type="text" class="easyui-numberbox l_textbox"
										data-options="min:0" name="tell" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;传真:</div>
								<div class="edit_right">
									<input id="fax" type="text" class="l_textbox" name="fax"
										value="">
								</div>
							</div>

						</div>
					</td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱:</div>
								<div class="edit_right">
									<input id="email" type="text"
										class="easyui-validatebox l_textbox"
										data-options="validType:'email'" name="email" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮编:</div>
								<div class="edit_right">
									<input id="postCode" type="text" class="l_textbox"
										name="postCode" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">启用状态:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="status"
										id="state0" value="0" />禁用 <input type="radio" id="state1"
										class="radio-style" name="status" value="1" checked="checked" />启用
								</div>
							</div>

						</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<div class="edit_winRow" style="margin-left: 7.5%;">
							<div class="edit_left">联系地址:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="address" name="address"
									rows="4" cols="40"></textarea>
							</div>

						</div>
					</td>

				</tr>
				<tr>
					<td></td>
					<td><a id="btnsummit" class="easyui-linkbutton"
						onclick="doSave()">确定</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
						id="btncancer" class="easyui-linkbutton" onclick="doCancel()">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var orgId = '${param.orgId}';
	var parentIds = '${param.parentIds}';
	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.ajax({
						type : 'post',
						url : 'sys/organizationAction_saveOrganization.action?parentId='
								+ orgId + '&parentIds=' + parentIds,
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#addForm").serialize(),

						success : function(result) {
							if (result.status == "ok") {
								//关闭窗口
								doColse(orgId);
							} else {
								$.messager.show({
									title : '提示信息!',
									msg : result.msg,
									timeout : 3000,
									showType : 'fade',
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
								timeout : 2000,
								style : {
									right : '',
									bottom : ''
								}
							});
						}

					});
		}
	}
	function doCancel() {
		//关闭窗口
		window.parent.closeWindow('#tg_add');
	}

	function doColse(value) {
		//关闭窗口
		window.parent.closeWindow('#tg_add', value);
	}
</script>