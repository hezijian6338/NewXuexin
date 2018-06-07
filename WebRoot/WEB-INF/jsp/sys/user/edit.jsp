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
	<!--添加弹出框  -->
	<div>
		<div class="space_flow_20"></div>
		<form id="editForm" method="post">
			<input type="hidden" id="userId" name="userId" value="" /> 
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">用户名称:</div>
								<div class="edit_right">
									<input type="text" id="employName" name="employName"
										class="easyui-validatebox l_textbox"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="sex" id="sex0"
										value="男" />男 <input type="radio" id="sex1"
										class="radio-style" name="sex" value="女" />女
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">手机号码:</div>
								<div class="edit_right">
									<input id="tell" type="text" class="easyui-numberbox l_textbox"
										name="tell" data-options="min:0" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">电子邮箱:</div>
								<div class="edit_right">
									<input id="email" type="text"
										class="easyui-validatebox l_textbox" name="email"
										data-options="validType:'email'" value="">
								</div>
							</div>

						</div></td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">用户编号:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="employNo" name="employNo"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">归属机构:</div>
								<div class="edit_right">
									<input id="orgId" type="text" class="easyui-combotree l_textbox"
										name="orgId" data-options="required:true" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">分配角色:</div>
								<div class="edit_right">
									<input id="roleIds" type="text"
										class="easyui-combobox l_textbox" name="roleIds" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">是否启用:</div>
								<div class="edit_right">
									<input id="status0" type="radio" class="radio-style"
										name="status" value="1" />是 <input
										id="status1" type="radio" class="radio-style" name="status"
										value="0" />否
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">用户类型:</div>
							<input id="userType1" type="radio" class="radio-style"
								name="userType" value="0" />教职工 <input
								id="userType2" type="radio" class="radio-style" name="userType"
								value="1" />学生
						</div></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="edit_winRow" style="margin-left: 7.5%;">
							<div class="edit_left">联系地址:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="address" name="address"
									rows="4" cols="40"></textarea>
							</div>

						</div></td>

				</tr>
				<tr>
					<td></td>
					<td><a id="btnsummit" class="easyui-linkbutton"
						onclick="doSave()">确定</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
						id="btncancer" class="easyui-linkbutton" onclick="doCancel()">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
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
		$('#roleIds').combobox({
			url : 'sys/roleAction_getRoles.action',
			valueField : 'roleId',
			textField : 'roleName',
			editable : false,
			multiple : true,
			loadFilter : function(data) {
				//alert(JSON.stringify(data));
				return data.result;
			}
		});	
		//加载历史数据
		loadData();
		$('#employNo').attr("disabled", "disabled");
	});
	
	//加载历史数据
	function loadData() {
		$.post("sys/userAction_getUser.action", {
			userId : '${param.userId}'
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == 0) {
				var o = json.info;
				$('#userId').val(o.userId);
				$('#employNo').val(o.employNo);
				$('#employName').val(o.employName);
				if(o.sex==0){
					$('#sex0').attr("checked","checked");
				}else{
					$('#sex1').attr("checked","checked");
				}
				var org = o.organization;
				$('#orgId').combotree('setValue', org.orgId);
				var s = o.roleIds;
				s=s.substring(1, s.length-1);
				var roleIds = s.split(",");
				for(var i=0;i<roleIds.length;i++){
					$('#roleIds').combobox('select',roleIds[i]);
				}
				if(o.status==1){
					$('#status0').attr("checked","checked");
				}else{
					$('#status1').attr("checked","checked");
				}
				if(o.userType=='0'){
					$('#userType1').attr("checked","checked");
				}else{
					$('#userType2').attr("checked","checked");
				}
				$('#tell').val(o.tell);
				$('#email').val(o.email);
				$('#address').val(o.address);
			} else {
				$.messager.show({
								title : '提示信息',
								msg : info.message,
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
		var tell = ($('#tell').val());
		if ($('#editForm').form('validate')) {
			$('#tell').val(tell);
			$
					.ajax({
						type : 'post',
						url : 'sys/userAction_edit.action?employNo='+$('#employNo').val(),
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#editForm").serialize(),
						success : function(result) {
							//关闭窗口
							//window.parent.closeWindow('#dg_edit');
							
							$.messager.show({
								title : '提示信息',
								msg : result.message,
								showType : 'fade',
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
								style : {
									right : '',
									bottom : ''
								}
							});
							//关闭窗口
							window.parent.closeWindow('#dg_edit');
						}

					});

		}else{
			
		}
		
	}
	
	function doCancel(){
		//关闭窗口
		window.parent.closeWindow('#dg_edit');
	}

</script>