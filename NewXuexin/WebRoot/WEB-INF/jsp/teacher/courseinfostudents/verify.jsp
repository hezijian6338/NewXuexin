<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="/pub/headmeta.jsp"%>
<link rel="stylesheet" type="text/css" href="css/css_form.css">
<script type="text/javascript" src="js/common/RowNumberWidth.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">学生学习情况管理&nbsp;&gt;&gt;&nbsp;学生选课及成绩明细校验</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form action="tea/courseInfoStudentsAction_verifyFile.action"
				method="post" id="importForm" enctype="multipart/form-data"
				target="uploadFrame">

				<table>
					<tr>
						<td>文件: <input type="file" name="excel" id="excel"
							class="l_filebox"
							accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
						</td>
						<td>
							<div class="edit_btn">
								<a id="btnsummit" class="easyui-linkbutton"
									onclick="doImport();" data-options="iconCls:'icon-save'">上传</a>
							</div>
						</td>
					</tr>
				</table>
				<div class="space_flow_10"></div>
			</form>
		</div>

		<div data-options="region:'center'">
			<table id="dg" style="height:550px">
				<thead>
					<tr>
						<th data-options="field:'row',width:50">行号</th>
						<th data-options="field:'msg',width:450">错误信息</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<iframe id="uploadFrame" name="uploadFrame" style="display: none;"></iframe>

	<script type="text/javascript">
	$(document).ready(function() {
		$("#uploadFrame").bind("load", uploadResult);
		$('#dg').datagrid({
			data: [],
		});
	});
	//上传
	function doImport() {
		var val = $("#excel").val();
		if (val) {
			$("#importForm").submit();
		} else {
			$.messager.show({
				title : '提示信息',
				msg : '请先选择excel文件',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
		}
	}
	
	//上传后回调
	function uploadResult() {
		var result = document.uploadFrame.result;
		if(result.status == 'ok'){
			$('#dg').datagrid({
				data: result.info,
				loadMsg : '数据加载中请稍后……',
				lines : true,
				animate : true,
				fitColumns : false,//适应宽度
				nowrap : false,
				fit : true,
				striped : true, // 隔行变色特性F
				rownumbers : true,
				onLoadSuccess : function () {
   		 	 		$(this).datagrid("fixRownumber");
 			 	},
			});
		}else{
			$.messager.show({
				title : '提示信息',
				msg : result.msg,
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
		}	
	}
</script>
</body>
</html>
