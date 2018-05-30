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
				type="hidden" id="stuId" name="stuId" value="" /> <input
				type="hidden" id="createTime" name="createTime" value="" /> <input
				type="hidden" id="creator" name="creator" value="" />
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">专业班级:</div>
								<div class="edit_right">
									<input id="classname" type="text"
										class="easyui-validatebox l_textbox" name="classname"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">辅导时间:</div>
								<div class="edit_right">
									<input id="guiddateStr" type="text"
										class="easyui-datebox l_textbox" name="guiddateStr"
										data-options="formatter:myformatter,parser:myparser,editable:false,required:true,missingMessage:'辅导时间必填!'"
										value="">
								</div>
							</div>

						</div>
					</td>
					<td>

						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="studentno" name="studentno" disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">辅导导师:</div>
								<div class="edit_right">
									<input id="counselor" type="text"
										class="easyui-validatebox l_textbox" name="counselor"
										data-options="" value="">
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left"></div>
								<div class="edit_right"></div>
							</div>
						</div></td>
				</tr>
				<tr>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">辅导内容:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="guidcontent" name="guidcontent"
									rows="4" cols="25"></textarea>
							</div>
						</div>
					</td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">辅导地址:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="guidaddress" name="guidaddress"
									rows="4" cols="25"></textarea>
							</div>
						</div>
					</td>

				</tr>
				<tr>
					<td valign="top">
						<div class="edit_winRow">
							<div class="edit_left">
								多媒体<br>文件路径:
							</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="mediapath" name="mediapath"
									rows="4" cols="25"></textarea>
							</div>
						</div>
					</td>
					<td>
						<div class="edit_winRow">
							<div class="edit_left">电子文档:</div>
							<div class="edit_right">
								<textarea class="l_textarea" id="docpath" name="docpath"
									rows="4" cols="25"></textarea>
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
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("stu/guidanceInfoAction_getGuidanceInfo.action", {
			infoId : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				$('#stuId').val(o.stuId);
				var createTime = formatShortDate(o.createTime);
				$('#createTime').val(o.createTime);
				;
				$('#creator').val(o.creator);
				$('#counselor').val(o.counselor);
				$('#classname').val(o.classname);
				var guiddate = formatShortDate(o.guiddate);
				$('#guiddateStr').datebox('setValue', guiddate);
				$('#guidcontent').val(o.guidcontent);
				$('#mediapath').val(o.mediapath);
				$('#docpath').val(o.docpath);
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

	//表单确定提交按钮
	function doSave() {
		if ($('#editForm').form('validate')) {
			$.post("stu/guidanceInfoAction_update.action?studentno="+$('#studentno').val(), $("#editForm")
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
			/* $
					.ajax({
						type : 'post',
						url : "stu/guidanceInfoAction_update.action",
						cache : false,
						dataType : 'json',
						contentType : 'application/x-www-form-urlencoded; charset=utf-8',
						data : $("#editForm").serialize(),
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

					}); */
		}

	}

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_add');
	}
</script>