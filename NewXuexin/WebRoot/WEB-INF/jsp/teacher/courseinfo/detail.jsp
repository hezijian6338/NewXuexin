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
								<div class="edit_left">课程代码:</div>
								<div class="edit_right">
									<input id="coursecode" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="coursecode"
										data-options="required:true,missingMessage:'课程代码必填!',validType:'maxLength[20]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">课程名称:</div>
								<div class="edit_right">
									<input id="coursename" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="coursename"
										data-options="required:true,missingMessage:'课程名称必填!',validType:'maxLength[100]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
								<div class="edit_right">
									<input type="text" id="academicyear" disabled="disabled" name="academicyear"
										class="easyui-validatebox l_textbox"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
								<div class="edit_right">
									<input type="text" id="term" name="term"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">教师工号:</div>
								<div class="edit_right">
									<input id="employNo" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="employNo"
										data-options="validType:'maxLength[10]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">教师姓名:</div>
								<div class="edit_right">
									<input id="employName" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="employName"
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
										class="easyui-validatebox l_textbox" disabled="disabled" name="selectedcourseno"
										data-options="validType:'maxLength[50]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">选课性质:</div>
								<div class="edit_right">
									<input id="coursetype" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="coursetype"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;总学时:</div>
								<div class="edit_right">
									<input id="totalhours" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="totalhours"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">实验学时:</div>
								<div class="edit_right">
									<input id="labhours" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="labhours"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">限选人数:</div>
								<div class="edit_right">
									<input id="limitstudentnum" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="limitstudentnum"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">选课人数:</div>
								<div class="edit_right">
									<input id="studentnum" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="studentnum"
										data-options="validType:'number'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学分:</div>
								<div class="edit_right">
									<input id="credit" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="credit"
										data-options="validType:['number','maxLength[2]']" value="">
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
								<textarea class="l_textarea" id="classinfo" disabled="disabled" name="classinfo" rows="4"
									cols="25" data-options="validType:'maxLength[200]'"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">课程归属:</div>
							<div class="edit_right">
								<textarea class="l_textarea" disabled="disabled" id="belongto" name="belongto" rows="4"
									cols="25" data-options="validType:'maxLength[100]'"></textarea>
							</div>

						</div></td>

				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
							<div class="edit_right">
								<textarea class="l_textarea" disabled="disabled" id="memo" name="memo" rows="4"
									cols="25" data-options="validType:'maxLength[200]'"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow"></div></td>

				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">

	var id = '${param.id}';
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("tea/courseInfoAction_getCourseInfo.action", {
			sid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#coursecode').val(o.coursecode);
				$('#coursename').val(o.coursename);
				$('#academicyear').val(o.academicyear);
				$('#term').val(o.term);
				$('#employNo').val(o.employNo);
				$('#employName').val(o.employName);
				$('#selectedcourseno').val(o.selectedcourseno);
				$('#coursetype').val(o.coursetype);
				$('#totalhours').val(o.totalhours);
				$('#labhours').val(o.labhours);
				$('#classinfo').val(o.classinfo);
				$('#studentnum').val(o.studentnum);
				$('#credit').val(o.credit);
				$('#belongto').val(o.belongto);
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
			$.post("tea/courseInfoAction_update.action", $("#editForm")
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