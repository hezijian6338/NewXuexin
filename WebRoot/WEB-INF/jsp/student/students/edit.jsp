<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<input type="hidden" id="stuId" name="stuId" value="" /> 
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
						<c:if test="${!isStu }">
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
						</c:if>
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
						<c:if test="${!isStu }">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="studentno" name="studentno" disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
						</c:if>
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
	var stuId = '${param.stuId}';
	$(function() {
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post('${isStu?"stu/studentsAction_getStudentSelf.action":"stu/studentsAction_getStudent.action"}', {
			stuId : stuId
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#stuId').val(stuId);
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				if(o.sex=='男'){
					$('#sex0').attr("checked","checked");
				}else{
					$('#sex1').attr("checked","checked");
				}
				$('#politicalstatus').val(o.politicalstatus);
				$('#idcardno').val(o.idcardno);
				$('#nativeplace').val(o.nativeplace);
				$('#orgId').combotree('setValue', o.orgId);
				$('#major').val(o.major);
				$('#majorfield').val(o.majorfield);
				$('#majorcategories').val(o.majorcategories);
				var birth = formatShortDate(o.birthday);
				$('#birthday').datebox('setValue', birth);;
				$('#nation').val(o.nation);
				$('#fromPlace').val(o.fromPlace);
				$('#classname').val(o.classname);
				$('#educationsystem').val(o.educationsystem);
				var accept = formatShortDate(o.acceptancedate);
				$('#acceptancedate').datebox('setValue', accept);
				$('#middleschool').val(o.middleschool);
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
						url : '${isStu?"stu/studentsAction_updateSelf.action":"stu/studentsAction_update.action"}',
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#editForm").serialize(),
						success : function(json) {
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
									msg : json.msg,
									showType : 'fade',
									timeout : 3000,
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
		window.parent.closeWindow('#dg_edit');
	}
</script>