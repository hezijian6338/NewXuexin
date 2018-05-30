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
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="studentno"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">奖励名称:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="rewardname" name="rewardname" rows="3"
										cols="25"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">获奖级别:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="rewardlevel" name="rewardlevel" rows="3"
										cols="25"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">获奖等级:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="rewardgrade" name="rewardgrade" rows="3"
										cols="25"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">指导老师:</div>
								<div class="edit_right">
									<input id="guidteacher" type="text"
										class="easyui-validatebox l_textbox" name="guidteacher"
										data-options="" value="">
								</div>
							</div>

						</div>
					</td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
								<div class="edit_right">
									<input id="academicyear" type="text"
										class="easyui-validatebox l_textbox" name="academicyear"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
								<div class="edit_right">
									<input id="term" type="text"
										class="easyui-validatebox l_textbox" name="term"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">授予单位:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="grantunits" name="grantunits" rows="3"
										cols="25"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">获奖项目:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="rewardproject" name="rewardproject" rows="3"
										cols="25"></textarea>
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">获奖时间:</div>
								<div class="edit_right">
									<input id="rewarddate" type="text"
										class="easyui-datebox l_textbox" name="rewarddateStr"
										data-options="formatter:myformatter,parser:myparser,required:true,missingMessage:'获奖时间必填!'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="memo" name="memo" rows="3"
										cols="25"></textarea>
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
		//组织机构下拉框
		$('#orgId').combotree({
			url : 'sys/organizationAction_getOrgTree.action',
			editable : false,
			loadFilter : function(data) {
				return data.result;
			}
		});
	});

	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.post("stu/subjectContestAction_save.action", $("#addForm")
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
						msg : '保存失败、学号不存在或学号和姓名不一致!',
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