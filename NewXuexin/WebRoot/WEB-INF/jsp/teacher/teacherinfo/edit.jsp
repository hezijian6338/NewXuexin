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
			<input type="hidden" id="id" name="id" value="" /> <input
				type="hidden" id="createTime" name="createDate" value="" /> <input
				type="hidden" id="creator" name="creator" value="" />
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">教师工号:</div>
								<div class="edit_right">
									<input type="text" id="employNo" name="employNo"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'教师工号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">教师姓名:</div>
								<div class="edit_right">
									<input type="text" id="employName" name="employName"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="sex" id="sex0"
										value="0" />男 <input type="radio" id="sex1"
										class="radio-style" name="sex" value="1" />女
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">出生日期:</div>
								<div class="edit_right">
									<input id="birthday" type="text"
										class="easyui-datebox l_textbox" name="birth"
										data-options="formatter:myformatter,parser:myparser,required:true"
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
								<div class="edit_left">&nbsp;科室(系):</div>
								<div class="edit_right">
									<input id="department" type="text"
										class="easyui-validatebox l_textbox" name="department"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">联系电话:</div>
								<div class="edit_right">
									<input id="telno" type="text"
										class="easyui-numberbox l_textbox" name="telno"
										data-options="min:0" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">电子邮箱:</div>
								<div class="edit_right">
									<input id="email" type="text"
										class="easyui-validatebox l_textbox" name="email"
										data-options="validType:'email'" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">教师类别:</div>
								<div class="edit_right">
									<input id="category" type="text"
										class="easyui-validatebox l_textbox" name="category"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学历:</div>
								<div class="edit_right">
									<input id="education" type="text"
										class="easyui-validatebox l_textbox" name="education"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学位:</div>
								<div class="edit_right">
									<input id="degree" type="text"
										class="easyui-validatebox l_textbox" name="degree"
										data-options="" value="">
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
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职称:</div>
								<div class="edit_right">
									<input id="acdemictitle" type="text"
										class="easyui-validatebox l_textbox" name="acdemictitle"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">可否监考:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="invigilatorflag"
										id="invigilatorflag0" value="T" />可以<input type="radio"
										id="invigilatorflag1" class="radio-style"
										name="invigilatorflag" value="F" />不可以
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
								<div class="edit_left">毕业院校:</div>
								<div class="edit_right">
									<input id="graduate" type="text"
										class="easyui-validatebox l_textbox" name="graduate"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">教师资格:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style"
										name="qualificationflag" id="qualificationflag0" value="Y" />有<input
										type="radio" id="qualificationflag1" class="radio-style"
										name="qualificationflag" value="N" />无
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">在职状态:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="jobstatus"
										id="jobstatus0" value="Y" />在职<input type="radio"
										id="jobstatus1" class="radio-style" name="jobstatus" value="N" />离职
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">教师级别:</div>
								<div class="edit_right">
									<input id="teacherLevel" type="text"
										class="easyui-validatebox l_textbox" name="teacherLevel"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">
									是否实验<br>室人员:
								</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="islab"
										id="islab0" value="Y" />是<input type="radio" id="islab1"
										class="radio-style" name="islab" value="N" />否
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">是否外聘:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="isouthire"
										id="isouthire0" value="Y" />是<input type="radio"
										id="isouthire1" class="radio-style" name="isouthire" value="N" />否
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

						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;民族:</div>
							<div class="edit_right">
								<input id="nation" type="text"
									class="easyui-validatebox l_textbox" name="nation"
									data-options="" value="">
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left"></div>
							<div class="edit_right"></div>
						</div></td>

				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">研究方向:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="researchdirection"
									name="researchdirection" rows="4" cols="25"></textarea>
							</div>
						</div></td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">简介:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="introduce" name="introduce"
									rows="4" cols="25"></textarea>
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
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("tea/teacherInfoAction_getTeacherInfo.action", {
			tid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#employNo').val(o.employNo);
				$('#employName').val(o.employName);
				if (o.sex == "0") {
					$('#sex0').attr("checked", "checked");
				} else {
					$('#sex1').attr("checked", "checked");
				}
				var birth = formatShortDate(o.birthday);
				$('#birthday').datebox('setValue', birth);
				$('#orgId').combotree('setValue', o.orgId);
				$('#department').val(o.department);
				$('#telno').val(o.telno);
				$('#email').val(o.email);
				$('#category').val(o.category);
				$('#education').val(o.education);
				$('#degree').val(o.degree);
				$('#duty').val(o.duty);
				$('#acdemictitle').val(o.acdemictitle);
				if (o.invigilatorflag == "T") {
					$('#invigilatorflag0').attr("checked", "checked");
				} else {
					$('#invigilatorflag1').attr("checked", "checked");
				}
				$('#major').val(o.major);
				$('#graduate').val(o.graduate);
				if (o.qualificationflag == "Y") {
					$('#qualificationflag0').attr("checked", "checked");
				} else {
					$('#qualificationflag1').attr("checked", "checked");
				}
				if (o.jobstatus == "Y") {
					$('#jobstatus0').attr("checked", "checked");
				} else {
					$('#jobstatus1').attr("checked", "checked");
				}
				$('#teacherLevel').val(o.teacherLevel);
				if (o.islab == "Y") {
					$('#islab0').attr("checked", "checked");
				} else {
					$('#islab1').attr("checked", "checked");
				}
				if (o.isouthire == "Y") {
					$('#isouthire0').attr("checked", "checked");
				} else {
					$('#isouthire1').attr("checked", "checked");
				}
				$('#politicalstatus').val(o.politicalstatus);
				$('#nation').val(o.nation);
				$('#researchdirection').val(o.researchdirection);
				$('#introduce').val(o.introduce);
				$('#createTime').val(formatDate(o.createTime));
				$('#creator').val(o.creator);
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
			$.post("tea/teacherInfoAction_update.action", $("#editForm")
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