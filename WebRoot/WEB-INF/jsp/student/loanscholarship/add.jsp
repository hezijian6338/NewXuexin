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
	<div >
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
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级:</div>
								<div class="edit_right">
									<input id="classname" type="text"
										class="easyui-validatebox l_textbox" name="classname"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">身份证号:</div>
								<div class="edit_right">
									<input id="idcardno" type="text" 
										class="easyui-validatebox l_textbox" name="idcardno"
										data-options="required:true,validType:'checkIdcardno'"  value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级:</div>
								<div class="edit_right">
									<input id="grade" type="text"
										class="easyui-validatebox l_textbox" name="grade"
										data-options="required:true,missingMessage:'年级必填!'" value="">
								</div>

						</div></td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">学院名称:</div>
								<div class="edit_right">
									<input id="orgId" type="text"
										class="easyui-combotree l_textbox" name="orgId"
										data-options="required:true,validType:'checkorgId'" value="">
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
										data-options="required:true,missingMessage:'学年必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
								<div class="edit_right">
									<input id="term" type="text"
										class="easyui-validatebox l_textbox" name="term"
										data-options="required:true,missingMessage:'学期必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">贷款金额:</div>
								<div class="edit_right">
									<input id="loanamount" type="text"
										class="easyui-validatebox l_textbox" name="loanamount"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">是否通过专业学院资格审核:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="censoredflag"
										id="censoredflag0" value="Y" checked="checked" />是 <input
										type="radio" id="censoredflag1" class="radio-style"
										name="censoredflag" value="N" />否
								</div>
							</div>

						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">被拒绝原因</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="refusereason"
									name="refusereason" rows="6" cols="25"></textarea>
							</div>
						</div></td>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="memo" name="memo" rows="6"
									cols="25"></textarea>
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
		$.extend($.fn.validatebox.defaults.rules, {
			checkorgId : {
				validator : function(value, param) {
					return value != "北京理工大学珠海学院";
				},
				message : '请输入具体学院名称'
			},
			checkIdcardno : {
				validator : function(value, param) {
					return /[1-9]{1}[0-9]{16}([0-9]|[xX])$/.test(value);
				},
				message :'请输入有效身份证号'
			}
		});
	});

	//表单确定提交按钮
	function doSave() {
		if ($('#addForm').form('validate')) {
			$.post("stu/loanScholarshipAction_save.action", $("#addForm")
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
				} else if (json.status == "SnoOrSnameIsError") {
					$.messager.show({
						title : '提示信息!',
						msg : '保存失败:学号不存在或者姓名错误!',
						showType : 'fade',
						timeout : 3000,
						style : {
							right : '',
							bottom : ''
						}
					});
				} else if (json.status == "IdcardnoIsError") {
					$.messager.show({
						title : '提示信息!',
						msg : '保存失败:身份证号跟姓名不一致!',
						showType : 'fade',
						timeout : 3000,
						style : {
							right : '',
							bottom : ''
						}
					});
				} else if (json.status == "exist") {
					$.messager.show({
						title : '提示信息!',
						msg : '保存失败:此学号在该学年已存在贷款记录!',
						showType : 'fade',
						timeout : 3000,
						style : {
							right : '',
							bottom : ''
						}
					});
				} else {
					$.messager.show({
						title : '提示信息!',
						msg : '保存失败:未知错误!',
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