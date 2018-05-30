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
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级:</div>
								<div class="edit_right">
									<input id="grade" type="text"
										class="easyui-validatebox l_textbox" name="grade"
										data-options="validType:'maxLength[40]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级:</div>
								<div class="edit_right">
									<input id="classname" type="text"
										class="easyui-validatebox l_textbox" name="classname"
										data-options="validType:'maxLength[100]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
							<%--2017/4/20 余锡鸿 学生职务编辑界面解锁学号可编辑--%>
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="studentno" 
										class="easyui-validatebox l_textbox" 
										data-options="required:true,issingMessage:'学号必填!',validType:'maxLength[16]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'姓名必填!',validType:'maxLength[20]'" value="">
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

						</div></td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职务:</div>
								<div class="edit_right">
									<input id="duty" type="text"
										class="easyui-validatebox l_textbox" name="duty"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机:</div>
								<div class="edit_right">
									<input id="telno" type="text"
										class="easyui-validatebox l_textbox" name="telno"
										data-options="validType:'mobile'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;短号:</div>
								<div class="edit_right">
									<input id="shorttelno" type="text"
										class="easyui-validatebox l_textbox" name="shorttelno"
										data-options="validType:'maxLength[16]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;宿舍:</div>
								<div class="edit_right">
									<input id="address" type="text"
										class="easyui-validatebox l_textbox" name="address"
										data-options="validType:'maxLength[100]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
								<div class="edit_right">
									<input id="memo" type="text"
										class="easyui-validatebox l_textbox" name="memo"
										data-options="validType:'maxLength[200]'" value="">
								</div>
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
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("tea/studentDutysAction_getStudentDutys.action", {
			sid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#grade').val(o.grade);
				$('#classname').val(o.classname);
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				if (o.sex == "0") {
					$('#sex0').attr("checked", "checked");
				} else {
					$('#sex1').attr("checked", "checked");
				}
				$('#duty').val(o.duty);
				$('#telno').val(o.telno);
				$('#shorttelno').val(o.shorttelno);
				$('#address').val(o.address);
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
			$.post("tea/studentDutysAction_update.action", $("#editForm")
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
				}  else {
					$.messager.show({
						title : '提示信息!',
						//2017/4/20 余锡鸿 修改学生职务编辑界面学号跟姓名不一致的错误提示
						msg : '保存失败:学号不存在或者姓名错误!',
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