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
<!--添加弹出框  -->
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>
<body>
	<div>
		<div class="space_flow_20"></div>
		<form id="editForm" method="post">
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="sscm.studentno"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">所在年级:</div>
								<div class="edit_right">
									<input id="grade" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.grade"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">联系方式:</div>
								<div class="edit_right">
									<input id="telno" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.telno"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">导学老师:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="teachername"
										   name="sscm.teachername" disabled="disabled"></input>
								</div>

							</div>

							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
								<div class="edit_right">
									<input id="academicyear" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.academicyear"
										data-options="" value="">
								</div>
							</div>
						</div>
					</td>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</div>
								<div class="edit_right">
									<input type="text" class="easyui-validatebox l_textbox"
										id="stuname" name="sscm.stuname" disabled="disabled"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="classname"
										disabled="disabled" name="sscm.classname"></input>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="status"
										name="sscm.status" disabled="disabled"></input>
								</div>

							</div>
                            <div class="edit_winRow">
                                <div class="edit_left">确认时间:</div>
                                <div class="edit_right">
                                    <input id="confirmdate" type="text" disabled="disabled"
                                           class="easyui-validatebox l_textbox" name="sscm.confirmdate"
                                           data-options="" value="">
                                </div>
                            </div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
								<div class="edit_right">
									<input id="term" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="sscm.term"
										data-options="" value="">
								</div>
							</div>
						</div>

					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><a class="easyui-linkbutton"
						onclick="doClose()">关闭</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>

		</form>
		<div data-options="region:'center'">
			<table id="dg" style="height:460px" class="easyui-datagrid">
				<thead>
					<tr>
						<!-- <th data-options="field:'id',checkbox:true,width:20"></th> -->
						<th data-options="field:'coursename',width:200">课程名称</th>
						<th data-options="field:'employName',width:100">授课老师</th>
						<th data-options="field:'retakeflag',width:100,"
							formatter="formatRetakeflag">考试类型</th>
						<th data-options="field:'finalscore',width:100">总评成绩</th>
						<th data-options="field:'resitscore',width:100">补考成绩</th>
						<th data-options="field:'memo',width:100">成绩描述</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

</body>
</html>
<script type="text/javascript">
	var id = '<%=request.getAttribute("sscm.id")%>';
	loadData();
	$(function() {
		//加载历史数据
		$('#dg')
				.datagrid(
						{
							idField : 'id',
							loadMsg : '数据加载中请稍后……',
							url : 'stu/stuscoreconfDetailAction_list${isStu?"Self":""}.action?sscd.masterId='
									+ id,
							lines : true,
							animate : true,
							fitColumns : true,//适应宽度
							nowrap : false,
							fit : true,
							striped : true, // 隔行变色特性F
							rownumbers : true,
							method : 'post',
							toolbar : '#dt',
							pagination : true,
							pageSize : 10,
							pageList : [ 5, 10, 15, 20, 50 ],
							onRowContextMenu : function(e, row, data) {
								e.preventDefault(); //屏蔽浏览器的菜单
								$(this).datagrid('unselectAll');//清除所有选中项
								$(this).datagrid('selectRow', row);
								$('#op_menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							},
							onLoadSuccess : function() {
								$(this).datagrid('clearChecked')
							}
						});

	});

	function loadData() {
		$.post('stu/stuscoreconfMasterAction_get${isStu?"Self":""}.action', {
			'sscm.id' : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.sscm;
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				$('#grade').val(o.grade);
				$('#academicyear').val(o.academicyear);
				$('#term').val(o.term);
				$('#classname').val(o.classname);
				$('#confirmdate').val(dateFormater(o.confirmdate));
				$('#teachername').val(o.teachername);
				$('#status').val(o.status);
				$('#telno').val(o.telno);
			} else {
				$.messager.show({
					title : '提示信息',
					msg : json.msg,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		});
	}

	function formatRetakeflag(val, row) {
		if (val == 0) {
			return '正常';
		} else if (val == 1) {
			return '补考';
		} else {
			return '重修';
		}
	}
	function dateFormater(val) {
		if (val == "" || val == null) {
			return "";
		}
		var date = new Date(val);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + m + '-' + d;
	}
	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_detail');
	}
</script>