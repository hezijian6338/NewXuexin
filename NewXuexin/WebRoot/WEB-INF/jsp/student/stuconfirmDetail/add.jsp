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
			<div>
				<div class="edit_winrow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程名字:</div>
					<div class="edit_right">
						<input type="text" id="coursestuId" name="coursestuId" value=""
							data-options="required:true,missingMessage:'请选择课程!'">
					</div>
				</div>
				<div class="edit_winrow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程性质:</div>
					<div class="edit_right">
						<input id="coursetype" type="text"
							class="easyui-validatebox l_textbox" name="scfd.coursetype"
							data-options="" value="">
					</div>
				</div>
				<div class="edit_winrow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预定时间:</div>
					<div class="edit_right">
						<input id="predate" type="text" class="easyui-datebox"
							name="predate"
							data-options="required:true,missingMessage:'时间必填!'" value="" >
					</div>
				</div>
				<div class="edit_winRow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否选上:</div>
					<div class="edit_right">
						<select id="isselected" name="scfd.isselected" value=""
							class="easyui-combobox" editable="false">
							<option value="是" selected="selected">是</option>
							<option value="否">否</option>
						</select>
					</div>
				</div>
			</div>
			<div>
				<div class="edit_winRow" id="reasons_div">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未选上原因:</div>
					<div class="edit_right">
						<input id="reasons" type="text"
							class="easyui-validatebox l_textbox" name="scfd.reasons"
							data-options="" value="">
					</div>
				</div>
			</div>
			<%-- end --%>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>
			<div class="edit_btn">
				<a id="btnsummit" class="easyui-linkbutton" onclick="doSave()"
					data-options="iconCls:'icon-save'">保存</a>
			</div>
			<div class="edit_btn">
				<a id="btncancer" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="doClose()">取消</a>
			</div>

		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var masterid = '${param.masterid}';
	var ct = null;
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		data = "scfm.id=" + masterid;
		$.post('stu/stuconfirmMasterAction_listCoursesSelf.action', data,
				function callback(txt) {
					var json = $.parseJSON(txt);
					if (json.status == "ok") {
						$('#coursestuId').combobox(
								{
									data : json.infos,
									valueField : 'id',
									textField : 'coursename',
									editable : false,
									formatter : function(row) {
										ct = row.coursetype;
										return '<span class="item-text">'
												+ row.coursename + "(课程号:"
												+ row.coursecode + "，教学老师："
												+ row.employName + ")"
												+ '</span>';
									},
									onSelect : function(rec){
										//alert(rec.coursetype) ;
										$('#coursetype').val(rec.coursetype) ;
									}
								});
					} else {
						$.messager.show({
							title : '提示信息',
							msg : '课程数据加载失败',
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
					}
				});
		$('#reasons_div').hide();
		$('#isselected').combobox({
			onChange : function(newValue, oldValue) {
				if (newValue == '否') {
					$('#reasons_div').show();
					$('#reasons').validatebox({
						required : true,
						validType : null,
						missingMessage : '未选上原因必填'
					});
				} else {
					$('#reasons_div').hide();
					$('#reasons').validatebox({
						required : false,
						validType : null,
					});
					$('#isselected').val('');
				}
			}
		});
	});

	//表单确定提交按钮
	function doSave() {
		if ($('#editForm').form('validate')) {
			data = $("#editForm").serialize();
			data = data + "&scfd.masterId=" + masterid;
			$.post('stu/stuconfirmDetailAction_saveSelf.action', data,
					function callback(txt) {
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
								msg : json.msg,
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
		window.parent.closeWindow('#dg_addInfo');
	}
</script>
