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
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">身份证号:</div>
								<div class="edit_right">
									<input type="text" id="idcardno" name="idcardno"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'身份证号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="sex" id="sex0"
										value="男" checked="checked" />男 <input type="radio" id="sex1"
										class="radio-style" name="sex" value="女" />女
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
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;籍贯:</div>
								<div class="edit_right">
									<input id="nativeplace" type="text"
										class="easyui-validatebox l_textbox" name="nativeplace"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学院:</div>
								<div class="edit_right">
									<!-- <input id="orgName" type="text"
										class="easyui-validatebox l_textbox" name="orgName"
										data-options="" value=""> -->
									<input id="orgId" type="text" class="easyui-combotree l_textbox"
										name="orgId" data-options="required:true" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">专业名称:</div>
								<div class="edit_right">
									<input id="major" type="text"
										class="easyui-validatebox l_textbox" name="major"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">专业方向:</div>
								<div class="edit_right">
									<input id="majorfield" type="text"
										class="easyui-validatebox l_textbox" name="majorfield"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">专业类别:</div>
								<div class="edit_right">
									<input id="majorcategories" type="text"
										class="easyui-validatebox l_textbox" name="majorcategories"
										data-options="" value="">
								</div>
							</div>

						</div></td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="studentno" name="studentno"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">出生日期:</div>
								<div class="edit_right">
									<input id="birthday" type="text"
										class="easyui-datebox l_textbox" name="birthday"
										data-options="formatter:myformatter,parser:myparser,required:true" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;民族:</div>
								<div class="edit_right">
									<input id="nation" type="text"
										class="easyui-validatebox l_textbox" name="nation"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">来源地区:</div>
								<div class="edit_right">
									<input id="fromPlace" type="text"
										class="easyui-validatebox l_textbox" name="fromPlace"
										data-options="" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;行政班:</div>
								<div class="edit_right">
									<input id="classname" type="text"
										class="easyui-validatebox l_textbox" name="classname"
										data-options="required:true,missingMessage:'请输入年级班级,例子:14软件工程6班'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学制:</div>
								<div class="edit_right">
									<input id="educationsystem" type="text"
										class="easyui-validatebox l_textbox" name="educationsystem"
										data-options="" value="">
								</div>

							</div>
							<div class="edit_winRow">
								<div class="edit_left">入学日期:</div>
								<div class="edit_right">
									<input id="acceptancedate" type="text"
										class="easyui-datebox l_textbox" name="acceptancedate"
										data-options="formatter:myformatter,parser:myparser,required:true" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">毕业中学:</div>
								<div class="edit_right">
									<input id="middleschool" type="text"
										class="easyui-validatebox l_textbox" name="middleschool"
										data-options="" value="">
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

			$
					.ajax({
						type : 'post',
						url : 'stu/studentsAction_save.action',
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#addForm").serialize(),
						success : function(result) {
							//关闭窗口
							$('#dg').datagrid('reload');//刷新
							$.messager.show({
								title : '提示信息',
								msg : "保存成功",
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							doClose();
						},
						error : function(result) {
							$.meesager.show({
								title : '提示信息',
								msg : "保存失败",
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							//doClose();
						}

					});

		}

	}

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_add');
	}
</script>