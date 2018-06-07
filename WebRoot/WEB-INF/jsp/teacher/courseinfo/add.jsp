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
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
								<div class="edit_right">
									<select id="academicyear" class="easyui-combobox"
						    	style="width:100px;" editable="false" id="academicyear" name="academicyear">
								<option value="2010-2011">2010-2011</option>
								<option value="2011-2012">2011-2012</option>
								<option value="2012-2013">2012-2013</option>
								<option value="2013-2014">2013-2014</option>
								<option value="2014-2015">2014-2015</option>
								<option value="2015-2016">2015-2016</option>
								<option value="2016-2017">2016-2017</option>
								<option value="2017-2018">2017-2018</option>
								<option value="2018-2019">2018-2019</option>
								<option value="2019-2020">2019-2020</option>
								<option value="2020-2021">2020-2021</option>
								<option value="2021-2022">2021-2022</option>
								<option value="2022-2023">2022-2023</option>
								<option value="2023-2024">2023-2024</option>
								<option value="2024-2025">2024-2025</option>
								<option value="2025-2026">2025-2026</option>
						</select>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
								<div class="edit_right">
									<input type="text" id="term" name="term"
										class="easyui-validatebox l_textbox"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">教师工号:</div>
								<div class="edit_right">
									<input id="employNo" type="text"
										class="easyui-validatebox l_textbox" name="employNo"
										data-options="validType:'maxLength[10]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">教师姓名:</div>
								<div class="edit_right">
									<input id="employName" type="text"
										class="easyui-validatebox l_textbox" name="employName"
										data-options="validType:'maxLength[60]'" value="">
								</div>
							</div>

						</div></td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">选课课号:</div>
								<div class="edit_right">
									<input id="selectedcourseno" type="text"
										class="easyui-validatebox l_textbox" name="selectedcourseno"
										data-options="validType:'maxLength[50]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">选课性质:</div>
								<div class="edit_right">
									<input id="coursetype" type="text"
										class="easyui-validatebox l_textbox" name="coursetype"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;总学时:</div>
								<div class="edit_right">
									<input id="totalhours" type="text"
										class="easyui-validatebox l_textbox" name="totalhours"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">实验学时:</div>
								<div class="edit_right">
									<input id="labhours" type="text"
										class="easyui-validatebox l_textbox" name="labhours"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">限选人数:</div>
								<div class="edit_right">
									<input id="limitstudentnum" type="text"
										class="easyui-validatebox l_textbox" name="limitstudentnum"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">选课人数:</div>
								<div class="edit_right">
									<input id="studentnum" type="text"
										class="easyui-validatebox l_textbox" name="studentnum"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学分:</div>
								<div class="edit_right">
									<input id="credit" type="text"
										class="easyui-validatebox l_textbox" name="credit"
										data-options="validType:'number'" value="">
								</div>
							</div>

						</div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">教学班组成:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="classinfo" name="classinfo" rows="4"
									cols="25" data-options="validType:'maxLength[1000]'"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">课程归属:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="belongto" name="belongto" rows="4"
									cols="25" data-options="validType:'maxLength[100]'"></textarea>
							</div>

						</div></td>

				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="memo" name="memo" rows="4"
									cols="25" data-options="validType:'maxLength[200]'"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow"></div></td>

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
	});

	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.post("tea/courseInfoAction_save.action", $("#addForm")
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
						msg : '保存失败或选课课号已存在!',
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