<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script type="text/javascript" src="js/common/windowmodal.js"></script>
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
								<div class="edit_left">所选课程:</div>
								<div class="edit_right">
									<input id="coursename" name="coursename" value="" class="easyui-validatebox l_textbox" disabled="disabled"
										style="width:150px;">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">考试类型:</div>
								<div class="edit_right">
									<select id="retakeflag" class="easyui-combobox" editable="false"
										name="sscd.retakeflag" style="width:150px;">
										<option value="0" selected="selected">正常</option>
										<option value="1">补考</option>
									</select>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成绩:</div>
								<div class="edit_right">
									<input id="finalscore" type="text"
										class="easyui-validatebox l_textbox" name="sscd.finalscore"
										style="width:150px;" data-options="required:true,missingMessage:'成绩必填!',validType:'finalscore'" value="">
								</div>
							</div>
							<div class="edit_winRow" id="resitscore_div">
								<div class="edit_left">补考成绩:</div>
								<div class="edit_right">
									<input id="resitscore" type="text"
										class="easyui-validatebox l_textbox" name="sscd.resitscore"
										style="width:150px;" data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">成绩描述:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="memo" name="sscd.memo" rows="4" cols="19"></textarea>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td><a id="btnsummit" style="margin-left:45%" class="easyui-linkbutton"
						onclick="doSave()">保存</a> &nbsp;&nbsp;&nbsp; <a id="btncancer"
						class="easyui-linkbutton" onclick="doClose()">取消</a></td>
				</tr>
			</table>

		</form>
</body>
</html>
<script type="text/javascript">
	var id = '${param.id}';
	var masterid = '${param.masterid}';
	$(function() {
		//先隐藏补考成绩输入框
		$('#resitscore_div').hide();
		//自定义easyui表单验证
		easyui_form_validator();
		//添加成绩类型变化监听
		$('#retakeflag').combobox({
			onChange:function(newValue,oldValue){
       			 if(newValue==1){
       			 	$('#resitscore_div').show();
       			 	$('#resitscore').validatebox({
    					required: true,
    					validType: 'finalscore',
    					missingMessage:'补考成绩必填!'
					});
       			 }else{
       			 	$('#resitscore_div').hide();
       			 	$('#resitscore').validatebox({
       			 		required: false,
    					validType: null,
       			 	});
       			 	$('#resitscore').val('');
       			 }
       		}
		});
		loadData();
	});

	function loadData() {
		$.post("stu/stuscoreconfDetailAction_getSelf.action", {
			'sscd.id' : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var sscd = json.sscd;
				$('#coursename').val(sscd.coursename);
				$('#retakeflag').combobox('setValue',sscd.retakeflag);
				$('#finalscore').val(sscd.finalscore);
				$('#memo').val(sscd.memo);
				if(sscd.retakeflag==1){
					$('#resitscore').val(sscd.resitscore);
					$('#resitscore_div').show();
       			 	$('#resitscore').validatebox({
    					required: true,
    					validType: 'finalscore',
    					missingMessage:'补考成绩必填!'
					});
				}
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
			data = $("#editForm").serialize();
			data = data + "&sscd.id=" + id;
			$.post('stu/stuscoreconfDetailAction_updateSelf.action', data,
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
		window.parent.closeWindow('#dg_updateInfo');

	}
</script>