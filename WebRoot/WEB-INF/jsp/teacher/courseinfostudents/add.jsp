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
								<div class="edit_left">课程代码:</div>
								<div class="edit_right">
									<input id="coursecode" type="text"
										class="easyui-validatebox l_textbox" name="coursecode"
										data-options="required:true,missingMessage:'课程代码必填!',validType:'maxLength[20]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">课程名称:</div>
								<div class="edit_right">
									<input id="coursename" type="text"
										class="easyui-validatebox l_textbox" name="coursename"
										data-options="required:true,missingMessage:'课程名称必填!',validType:'maxLength[100]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">学院名称:</div>
								<div class="edit_right">
									<input id="orgId" type="text"
										class="easyui-combotree l_textbox" name="orgId"
										data-options="required:true" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级:</div>
								<div class="edit_right">
									<input id="classname" type="text"
										class="easyui-validatebox l_textbox" name="classname"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">专业代码:</div>
								<div class="edit_right">
									<input id="majorcode" type="text"
										class="easyui-validatebox l_textbox" name="majorcode"
										data-options="" value="">
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
								<div class="edit_left">是否补考:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="retakeflag"
										id="retakeflag0" value="Y" checked="checked" />是 <input
										type="radio" id="retakeflag1" class="radio-style"
										name="retakeflag" value="N" />否
								</div>
							</div>
						</div>
					</td>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">平时成绩:</div>
								<div class="edit_right">
									<input id="usualscore" type="text"
										class="easyui-validatebox l_textbox" name="usualscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">期中成绩:</div>
								<div class="edit_right">
									<input id="middlescore" type="text"
										class="easyui-validatebox l_textbox" name="middlescore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">期末成绩:</div>
								<div class="edit_right">
									<input id="endscore" type="text"
										class="easyui-validatebox l_textbox" name="endscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">实验成绩:</div>
								<div class="edit_right">
									<input id="labscore" type="text"
										class="easyui-validatebox l_textbox" name="labscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">总评成绩:</div>
								<div class="edit_right">
									<input id="finalscore" type="text"
										class="easyui-validatebox l_textbox" name="finalscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">折算成绩:</div>
								<div class="edit_right">
									<input id="convertscore" type="text"
										class="easyui-validatebox l_textbox" name="convertscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">补考成绩:</div>
								<div class="edit_right">
									<input id="resitscore" type="text"
										class="easyui-validatebox l_textbox" name="resitscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">重修成绩:</div>
								<div class="edit_right">
									<input id="repairscore" type="text"
										class="easyui-validatebox l_textbox" name="repairscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;绩点:</div>
								<div class="edit_right">
									<input id="gradepoint" type="text"
										class="easyui-validatebox l_textbox" name="gradepoint"
										data-options="validType:'mone'" value="">
								</div>
							</div>

						</div></td>
				</tr>
				<tr>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">
								补考成绩<br>备注:
							</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="resitmemo" name="resitmemo"
									rows="4" cols="20" data-options="validType:'maxLength[200]'"></textarea>
							</div>
						</div>
					</td>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="memo" name="memo" rows="4"
									cols="20" data-options="validType:'maxLength[200]'"></textarea>
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
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
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
			$.post("tea/courseInfoStudentsAction_save.action", $("#addForm")
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
						msg : '学号不存在或姓名错误或课程代码错误!',
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