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
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="studentno"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
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
										class="easyui-validatebox l_textbox" name="coursename" disabled="disabled"
										data-options="required:true,missingMessage:'课程名称必填!',validType:'maxLength[100]'"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">学院名称:</div>
								<div class="edit_right">
									<input id="orgId" type="text"
										class="easyui-combotree l_textbox" disabled="disabled" name="orgId"
										data-options="required:true" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级:</div>
								<div class="edit_right">
									<input id="classname" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="classname"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">专业代码:</div>
								<div class="edit_right">
									<input id="majorcode" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="majorcode"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业:</div>
								<div class="edit_right">
									<input id="major" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="major"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">是否补考:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" disabled="disabled" name="retakeflag"
										id="retakeflag0" value="Y" checked="checked" />是 <input
										type="radio" id="retakeflag1" class="radio-style"
										name="retakeflag" value="N" />否
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left"></div>
							</div>
						</div>
					</td>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">平时成绩:</div>
								<div class="edit_right">
									<input id="usualscore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="usualscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">期中成绩:</div>
								<div class="edit_right">
									<input id="middlescore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="middlescore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">期末成绩:</div>
								<div class="edit_right">
									<input id="endscore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="endscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">实验成绩:</div>
								<div class="edit_right">
									<input id="labscore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="labscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">总评成绩:</div>
								<div class="edit_right">
									<input id="finalscore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="finalscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">折算成绩:</div>
								<div class="edit_right">
									<input id="convertscore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="convertscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">补考成绩:</div>
								<div class="edit_right">
									<input id="resitscore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="resitscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">重修成绩:</div>
								<div class="edit_right">
									
									<input id="repairscore" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="repairscore"
										data-options="validType:'mone'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;绩点:</div>
								<div class="edit_right">
									<input id="gradepoint" type="text"
										class="easyui-validatebox l_textbox" disabled="disabled" name="gradepoint"
										data-options="validType:'mone'" value="">
								</div>
							</div>

						</div></td>
				</tr>
				<tr>
					<td>
						<div class="edit_winRow">
							<div class="edit_left" disabled="disabled">
								补考成绩<br>备注:
							</div>
							<div class="edit_right" disabled="disabled">
								<textarea class="l_textarea" disabled="disabled" id="resitmemo" name="resitmemo"
									rows="4" cols="20" data-options="validType:'maxLength[200]'"></textarea>
							</div>
						</div>
					</td>
					<td valign="top">
						<div class="edit_winRow" disabled="disabled">
							<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</div>
							<div class="edit_right">
								<textarea class="l_textarea" disabled="disabled" id="memo" name="memo" rows="4"
									cols="20" data-options="validType:'maxLength[200]'"></textarea>
							</div>
						</div>
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
		//组织机构下拉框
		$('#orgId').combotree({
			url : 'sys/organizationAction_getOrgTree.action',
			editable : false,
			loadFilter : function(data) {
				return data.result;
			}
		});
		//自定义easyui表单验证
		easyui_form_validator();
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("tea/courseInfoStudentsAction_getCourseInfoStudents.action", {
			sid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				$('#orgId').combotree('setValue', o.orgId);
				$('#classname').val(o.classname);
				$('#majorcode').val(o.majorcode);
				$('#major').val(o.major);
				$('#coursecode').val(o.coursecode);
				$('#coursename').val(o.coursename);
				if (o.retakeflag == "Y") {
					$('#retakeflag0').attr("checked", "checked");
				} else {
					$('#retakeflag1').attr("checked", "checked");
				}
				$('#usualscore').val(o.usualscore);
				$('#middlescore').val(o.middlescore);
				$('#endscore').val(o.endscore);
				$('#labscore').val(o.labscore);
				$('#finalscore').val(o.finalscore);
				$('#convertscore').val(o.convertscore);
				$('#resitscore').val(o.resitscore);
				$('#resitmemo').val(o.resitmemo);
				$('#repairscore').val(o.repairscore);
				$('#gradepoint').val(o.gradepoint);
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
			$.post("tea/courseInfoStudentsAction_update.action?studentno="+$('#studentno').val(), $("#editForm")
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
						msg : '姓名输入错误!',
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