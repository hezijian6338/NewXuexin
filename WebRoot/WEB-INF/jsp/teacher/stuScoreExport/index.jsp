<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
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

<title>学生政审资料管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">学生学习情况管理&nbsp;&gt;&gt;&nbsp;学生成绩导出</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form action="tea/stuScoreExportAction_exportScore.action"  method="post">
				<table>
					<tr>
						<td>学年：<select id="academicyear" class="easyui-combobox"
							style="width:100px;" editable="false" id="academicyear" name="query.academicYear">
								<option value="2014-2015">2014-2015</option>
								<option value="2015-2016">2015-2016</option>
								<option value="2016-2017">2016-2017</option>
								<option value="2017-2018">2017-2018</option>
								<option value="2018-2019">2018-2019</option>
								<option value="2019-2020">2019-2020</option>
								<option value="2020-2021">2020-2021</option>
								<option value="2021-2022">2021-2022</option>
								<option value="2022-2023">2022-2023</option>
								<option value="2023-2024">2023-2024</option>
								<option value="2024-2025">2024-2025</option>
								<option value="2025-2026">2025-2026</option>
						</select></td>
						<td>学期：<select id="term" class="easyui-combobox"
							style="width:50px;" editable="false" data-options=""
							id="term" name="query.term">
								<option value="1">1</option>
								<option value="2">2</option>
						</select></td>
						<td>年级：<select id="term" class="easyui-combobox"
							style="width:80px;" editable="false" data-options=""
							id="term" name="query.grade">
								<option value="2014">2014</option>
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
								<option value="2021">2021</option>
								<option value="2022">2022</option>
								<option value="2023">2023</option>
						</select></td>
						<td>学院：<input id="orgName" name="query.orgName" value="" 
										style="width:120px;"></td>
						<td>班级：<input id="classname" name="query.classname" value=""
										style="width:200px;"></td>
						<td>学号：<input id="no" name="query.no" value="" class="l_textbox"
										style="width:200px;"></td>
						<td><input id="btnfind" 
							 type="submit" value="导出"></input>
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="importExcel()">导入</a>
						</td>
					</tr>
				</table>
				<div class="space_flow_10"></div>
			</form>
		</div>

		
	</div>
	<script type="text/javascript">
	$(function(){
		$.post('tea/stuScoreExportAction_listParam.action', function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				$('#orgName').combobox({
					data : json.orgName,
					valueField : 'value',
					textField : 'name',
					editable : false,
				});
				$('#classname').combobox({
					data : json.classname,
					valueField : 'value',
					textField : 'name',
					editable : false,
				});
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
	});
	function importExcel() {
		initWindow("dg_import","导入成绩",450,200,"tea/stuScoreExportAction_viewImport.action"); 
	}		
	
	//关闭窗口，提供给子页调用
	function closeWindow(id){
		$(id).window('destroy');
	}
	</script>
</body>
</html>
