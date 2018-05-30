<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;准考证号:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox"
						id="zkhNo" name="zkhNo" disabled="disabled"
						data-options="required:true,missingMessage:'准考证号必填!',validType:['maxLength[16]','charOrNum']"
						value="">
				</div>
			</div>
			<c:if test = "${!isStu}" >
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;身份证号:</div>
				<div class="edit_right">
					<input type="text" class="easyui-validatebox l_textbox"
						id="idcardno" name="idcardno" disabled="disabled"
						data-options="required:true,missingMessage:'身份证号必填!',validType:'idCode'"
						value="">
				</div>
			</div>

			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
				<div class="edit_right">
					<input type="text" id="stuname" name="stuname"
						class="easyui-validatebox l_textbox" 
						data-options="required:true,missingMessage:'姓名必填!',validType:'maxLength[20]'"
						value="">
				</div>
			</div>
			</c:if>
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;课程名称:</div>
				<div class="edit_right">
					<input type="text" id="coursename" name="coursename" disabled="disabled"
						class="easyui-validatebox l_textbox" 
						value="">
				</div>
			</div>
			
			<div class="edit_winrow">
				<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成绩:</div>
				<div class="edit_right">
					<input type="text" id="score" name="score"
						class="easyui-validatebox l_textbox"
						data-options="required:true,missingMessage:'成绩必填!',validType:['integ','range[0,9999]']"
						value="">
				</div>
			</div>
			
			
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
	var id = '${param.id}';
	//自定义easyui表单验证
	easyui_form_validator();
	$(function() {
		//组织机构下拉框
		/* $('#orgId').combotree({
			url : 'sys/organizationAction_getOrgTree.action',
			editable : false,
			loadFilter : function(data) {
				return data.result;
			}
		}); */
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post('${isStu?"stu/nceeScoreAction_getNceeScoreSelf.action":"stu/nceeScoreAction_getNceeScore.action"}', { 
			gsId : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(id);
				$('#zkhNo').val(o.zkhNo);
				$('#idcardno').val(o.idcardno);
				$('#stuname').val(o.stuname);
				$('#coursename').val(o.coursename);
				$('#score').val(o.score);
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
			$.post('${isStu?"stu/nceeScoreAction_updateSelf.action":"stu/nceeScoreAction_update.action"}'+'?idcardno='+$('#idcardno').val()+"&zkhNo="+$('#zkhNo').val(), $("#editForm")
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