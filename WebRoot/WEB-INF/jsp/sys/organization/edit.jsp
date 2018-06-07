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
		<form id="editForm" action="" method="post">
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
									<input id="tell" type="text" name="tell"
										class="easyui-numberbox l_textbox" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;传真:</div>
								<div class="edit_right">
									<input id="fax" type="text" class="l_textbox" name="fax"
										value="">
								</div>
							</div>

						</div></td>
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
										class="radio-style" name="status" value="1" />启用
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

						</div></td>

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
	$(function() {
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("sys/organizationAction_getOrganization.action", {
			id : orgId
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.result;
				$('#orgName').val(o.orgName);
				$('#contactMan').val(o.contactMan);
				$('#tell').val(o.tell);
				$('#fax').val(o.fax);
				$('#email').val(o.email);
				$('#postCode').val(o.postCode);
				if (o.status == "0") {
					$('#state0').attr("checked", "checked");
				} else {
					$('#state1').attr("checked", "checked");
				}
				$('#address').val(o.address);
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
			var status = $("input[name=status]:checked").val();
			$.post('sys/organizationAction_updateOrganization.action', {
				id : orgId,
				orgName : $('#orgName').val(),
				contactMan : $('#contactMan').val(),
				tell : $('#tell').val(),
				fax : $('#fax').val(),
				email : $('#email').val(),
				postCode : $('#postCode').val(),
				status : status,
				address : $('#address').val()

			}, function callback(txt) {
				var json = $.parseJSON(txt);
				if (json.status == "ok") {
								//关闭窗口
								doColse(orgId);
							} else {
								$.messager.show({
									title : '提示信息!',
									msg : json.msg,
									timeout : 3000,
									showType : 'fade',
									style : {
										right : '',
										bottom : ''
									}
								});
							}

			});
			/* $
					.ajax({
						type : 'post',
						url : 'sys/organizationAction_updateOrganization.action?id='
								+ orgId + '&tell=' + tell,
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#editForm").serialize(),
						success : function(result) {
							if (result.status == "ok") {
								//关闭窗口
								doColse(orgId);
							} else {
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

					}); */
		}
	}
	function doCancel() {
		//关闭窗口
		window.parent.closeWindow('#tg_edit');
	}

	function doColse(value) {
		//关闭窗口
		window.parent.closeWindow('#tg_edit', value);
	}
</script>