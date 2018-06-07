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
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="sex" id="sex0"
										value="0" checked="checked" />男 <input type="radio" id="sex1"
										class="radio-style" name="sex" value="1" />女
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
							
						</div></td>
					<td>

						<div style="width: 350px;">
					
						<div class="edit_winRow">
								<div class="edit_left">学院名称:</div>
								<div class="edit_right">
									<input id="orgId" type="text" class="easyui-combotree l_textbox"
										name="orgId" data-options="required:true" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业:</div>
								<div class="edit_right">
									<input id="major" type="text"
										class="easyui-validatebox l_textbox" name="major"
										data-options="" value="">
								</div>
							</div>
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
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级:</div>
								<div class="edit_right">
									<input id="grade" type="text"
										class="easyui-validatebox l_textbox" name="grade"
										data-options="" value="">
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">获奖名称:</div>
							<div class="edit_right">
								<input id="rewardname" type="text"
										class="easyui-validatebox l_textbox" name="rewardname"
										data-options="" value="">
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left"></div>
							<div class="edit_right">
							</div>
						</div></td>

				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">
								本校奖学<br>金情况:
							</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="schoolScholarInfo" name="schoolScholarInfo"
									rows="6" cols="30"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">获省级及<br>省级以上<br>奖学金情<br>况:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="provinceScholarIinfo" name="provinceScholarIinfo"
									rows="6" cols="30"></textarea>
							</div>

						</div></td>
				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">
								评优获奖<br>情况:
							</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="awardInfo" name="awardInfo"
									rows="6" cols="30"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">比赛、竞<br>赛类获奖<br>情况:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="competitionInfo" name="competitionInfo"
									rows="6" cols="30"></textarea>
							</div>

						</div></td>
				</tr>
				
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">
								担任职务:
							</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="duty" name="duty"
									rows="6" cols="30"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="memo" name="memo"
									rows="6" cols="30"></textarea>
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
			$.post("stu/countryScholarshipAction_save.action", $("#addForm")
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
						msg : '学号不存在或姓名输入错误!',
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