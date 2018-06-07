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
	<div>
		<div class="space_flow_20"></div>
		<form id="editForm" method="post">
			<div class="nav">
				<div class="nav_left">学生选课确认&nbsp;&gt;&gt;&nbsp;选课查看详细信息表</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_10"></div>
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="scfm.studentno"
										class="easyui-validatebox l_textbox" disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">所在年级:</div>
								<div class="edit_right">
									<input id="grade" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="scfm.grade"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">联系方式:</div>
								<div class="edit_right">
									<input id="telno" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="scfm.telno"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">确认时间:</div>
								<div class="edit_right">
									<input id="confirmdate" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="scfm.confirmdate"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年:</div>
								<div class="edit_right">
									<input id="academicyear" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="scfm.academicyear"
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
										id="stuname" name="scfm.stuname" disabled="disabled"
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="classname"
										disabled="disabled" name="scfm.classname"></input>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="status"
										name="scfm.status" disabled="disabled"></input>
								</div>

							</div>
							<div class="edit_winRow">
								<div class="edit_left">确认老师:</div>
								<div class="edit_right">
									<input class="easyui-validatebox l_textbox" id="teachername"
										name="scfm.teachername" disabled="disabled"></input>
								</div>

							</div>
							<div class="edit_winRow">
								<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期:</div>
								<div class="edit_right">
									<input id="term" type="text" disabled="disabled"
										class="easyui-validatebox l_textbox" name="scfm.term"
										data-options="" value="">
								</div>
							</div>
						</div></td>
				</tr>
				<tr>
					<td>
						<div style="width:350px;"></div>
					</td>
					<td>
						<div style="float:right">
							<a id="btncancer" class="easyui-linkbutton" onclick="doClose()">关闭</a>
						</div>
					</td>
				</tr>
			</table>
		</form>

		<div class="space_flow_10"></div>

		<div data-options="region:'center'">
			<table id="dg" style="height:500px" class="easyui-datagrid">
<!-- 				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'coursename',width:40">课程名称</th>
						<th data-options="field:'coursetype',width:100">课程性质</th>
						<th data-options="field:'predate',width:80">预定时间</th>
						<th data-options="field:'isselected',width:80">最终是否选上</th>
						<th data-options="field:'reasons',width:80">未选上原因</th>
					</tr>
				</thead> -->
			</table>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var id = '<%=request.getAttribute("scfm.id")%>';
	loadData();
	$(function() {
		//加载历史数据
		$('#dg')
				.datagrid(
						{
							idField : 'id',
							loadMsg : '数据加载中请稍后……',
							url : 'stu/stuconfirmDetailAction_list${isStu?"Self":""}.action?scfd.masterId='
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
							columns : [ [ {
								field : 'id',
								title : '',
								width : 30,
								checkbox : true
							}, {
								field : 'coursename',
								title : '课程名称',
								width : 70
							}, {
								field : 'coursecode',
								title : '课程代码',
								width : 50
							}, {
								field : 'coursetype',
								title : '课程性质',
								width : 40
							}, {
								field : 'predate',
								title : '预定时间',
								width : 80,
								formatter : function(value, row, index) {
									return formatShortDate(value);
								}
							}, {
								field : 'isselected',
								title : '是否选上',
								width : 30
							}, {
								field : 'reasons',
								title : '原因',
								width : 80
							}, ] ],

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
		$.post('stu/stuconfirmMasterAction_get${isStu?"Self":""}.action', {
			'scfm.id' : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.scfm;
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				$('#grade').val(o.grade);
				$('#academicyear').val(o.academicyear);
				$('#term').val(o.term);
				$('#classname').val(o.classname);
				$('#confirmdate').val(formatShortDate(o.confirmdate));
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

	//关闭详情页面窗口
	function doClose() {
		window.parent.closeWindow('#dg_detail');
	}
</script>
