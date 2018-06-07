<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
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
<title>学生政审资料管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
<script type="text/javascript" src="js/common/RowNumberWidth.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:150">
			<div class="nav">
				<div class="nav_left">学生选课确认&nbsp;&gt;&gt;&nbsp;选课确认表</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<c:if test="${!isStu}">
							<td>姓名: <input type="text" class="l_textbox" id="stuname"
								name="scfm.stuname" value="">
							</td>
							<td>学号: <input type="text" class="l_textbox" id="studentno"
								name="scfm.studentno" value="">
							</td>
						</c:if>
						<td>学年：<select id="academicyear" class="easyui-combobox"
							style="width:100px;" editable="false" name="scfm.academicyear">
								<option value="" selected="selected">所有</option>
								<option value="2010-2011">2010-2011</option>
								<option value="2011-2012">2011-2012</option>
								<option value="2012-2013">2012-2013</option>
								<option value="2013-2014">2013-2014</option>
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
							style="width:70px;" editable="false" data-options=""
							name="scfm.term">
								<option value="" selected="selected">所有</option>
								<option value="1">1</option>
								<option value="2">2</option>
						</select></td>
						<td>状态: <select id="status" class="easyui-combobox"
							name="scfm.status" style="width:70px;">
								<option value="" selected="selected">所有</option>
								<option value="新建">新建</option>
								<option value="提交">提交</option>
								<option value="重置">重置</option>
								<option value="确认">确认</option>

						</select>
						</td>
						<td><a id="btnfind" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a> <a
							id="clearbtn" class="easyui-linkbutton"
							data-options="iconCls:'icon-clear'" onclick="clearForm()">清空</a>
						</td>
					</tr>
				</table>
				<div class="space_flow_10"></div>
			</form>
			<c:if test="${isStu}">
				<div style="clear: both">
					<div style="float:left;padding-right: 2%;">
						导师名称: <input type="text" class="easyui-validatebox l_textbox"
							id="teachername" name="scfm.teachername" value=""
							disabled="disabled">
					</div>
					<div style="float:left;">
						导师工号: <input type="text" class="easyui-validatebox l_textbox"
							id="teacherno" name="scfm.teacherno" value="" disabled="disabled">
					</div>
				</div>
			</c:if>
		</div>

		<div data-options="region:'center'">
			<table id="dg" style="height:40%">
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<a href="javascript:void(0)" class="easyui-linkbutton "
			data-options="iconCls:'icon-detailview',plain:true"
			onclick="onDetail()">详情</a>
		<c:if test="${isStu }">
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-ok',plain:true" onclick="doSubmit()">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">增加</a>

			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdate()">编辑</a>

			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
		</c:if>
		<c:if test="${!isStu }">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok',plain:true" onclick="doVerify()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true" onclick="doReset()">重置</a>
		</c:if>
		<c:if test="${isStu }">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-download',plain:true"
				onclick="exportExcel()">导出</a>
		</c:if>
		<%-- 		<div class="edit-line"></div>
		<div style="clear:both">
			<c:if test="${!isStu }">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok',plain:true" onclick="doVerify()">确认</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload',plain:true" onclick="doReset()">重置</a>
			</c:if>
		</div> --%>
	</div>
	<c:if test="${isStu }">
		<div id="op_menu" class="easyui-menu" style="width: 120px;">
			<div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div>
			<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
		</div>
	</c:if>
	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<div onclick="onDetail()" data-options="iconCls:'icon-detailview'">详情</div>
		<c:if test="${isStu }">
			<div onclick="doSubmit()" data-options="iconCls:'icon-ok'">提交</div>
			<div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div>
			<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
		</c:if>
		<c:if test="${!isStu }">
			<div data-options="iconCls:'icon-ok'" onclick="doVerify()">确认</div>
			<div data-options="iconCls:'icon-reload'" onclick="doReset()">重置</div>
		</c:if>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		loadData();
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'stu/stuconfirmMasterAction_list.action',
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
			columns:[[
				{field:'id',title:'',width:30,checkbox:true},
				{field:'grade',title:'年级',width:30},
				{field:'academicyear',title:'学年',width:100},
				{field:'term',title:'学期',width:100},
				{field:'studentno',title:'学号',width:80},
				{field:'stuname',title:'姓名',width:80},
				{field:'status',title:'确认状态',width:80},
				{field:'confirmdate',title:'确认时间', width:80,
					formatter: function(value,row,index){
						return formatShortDate(value);
					}
				},
				{field:'teachername',title:'确认导师信息',width:80},
			]],
			onRowContextMenu : function(e, row, data) {
				e.preventDefault(); //屏蔽浏览器的菜单
				$(this).datagrid('unselectAll');//清除所有选中项
				$(this).datagrid('selectRow', row);
				//如果有菜单才显示
				if($('#op_menu>div').length > 1){
					$('#op_menu').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				}
			},
			onLoadSuccess : function() {
				$(this).datagrid('clearChecked');
				$(this).datagrid("fixRownumber");
			}
		});

	});
	function loadData() {
		$.post('stu/stuconfirmMasterAction_loadSelf.action', {
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.scfm;
				$('#teacherno').val(o.teacherno);
				$('#teachername').val(o.teachername);
			}
		});
	}
	
	function dataFormater(val,row){
		if(val == "" || val == null){
			return "";
		}
		var data = new Date(val);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + m + '-' + d;
	}

	//关闭窗口，提供给子页调用
	function closeWindow(id) {
		$(id).window('destroy');
		$('#dg').datagrid('reload');//刷新
	}
	
	function doSubmit(){
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		if (arr[0].status != '重置' && arr[0].status != '新建') {
			$.messager.show({
				title : '提示信息!',
				msg : '只能提交状态为新建和重置的信息!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		
		$.messager.confirm("提示信息", "确认提交?提交后将无法修改此表的任何信息!", function(r) {
			if (r) {
				if (r) {
					var id = arr[0].id;
					$.post("stu/stuconfirmMasterAction_submit.action", {
						"scfm.id" : id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							//$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '提交成功!',
								showType : 'fade',
								timeout : 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
						} else {
							$.messager.show({
								title : '提示信息',
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
				} else {
					return;
				}
			}
		});
	}
	
	function doVerify() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		} else {
			if (arr[0].status != '提交') {
				$.messager.show({
					title : '提示信息!',
					msg : '只能确认状态为提交的信息!',
					showType : 'fade',
					timeout : 3000,
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			}
			$.messager.confirm("提示信息", "确认该选课已正确?", function(r) {
				if (r) {
					var id = arr[0].id;
					$.post("stu/stuconfirmMasterAction_verify.action", {
						"scfm.id" : id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							//$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '确认成功!',
								showType : 'fade',
								timeout : 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
						} else {
							$.messager.show({
								title : '提示信息',
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
				} else {
					return;
				}
			});
		}
	}
	
	function doReset() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		} else {
			if (arr[0].status != '提交') {
				$.messager.show({
					title : '提示信息!',
					msg : '只能重置状态为提交的信息!',
					showType : 'fade',
					timeout : 3000,
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			}
			$.messager.confirm("提示信息", "确认该将该成绩表状态设为重置?", function(r) {
				if (r) {
					var id = arr[0].id;
					$.post("stu/stuconfirmMasterAction_reset.action", {
						"scfm.id" : id
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '修改成功!',
								showType : 'fade',
								timeout : 3000,
								style : {
									right : '',
									bottom : ''
								}
							});

						} else {
							$.messager.show({
								title : '提示信息',
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
				} else {
					return;
				}
			});
		}
	}
	

	function doQuery() {
		var obj = {};
		$.each($("#queryForm").serializeArray(), function(index) {
			if (obj[this['name']]) {
				obj[this['name']] = obj[this['name']] + ',' + this['value'];
			} else {
				obj[this['name']] = this['value'];
			}
		});
		$('#dg').datagrid('load', obj);
	}

	function clearForm() {
		$('#queryForm').form('clear');
		if (${!isStu}) {
			$('#grade').combobox('setValue', '');
		}
		$('#academicyear').combobox('setValue', '');
		$('#term').combobox('setValue', '');
		$('#status').combobox('setValue', '');
		$('#dg').datagrid('load', {});
	}

	function onAdd() {
		if(!$('#teachername').val()){
			$.messager.show({
							title : '提示信息',
							msg : '没有导学老师信息,请联系导学老师导入!',
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
			return;
		}
		/**
		在此处应该是弹出从表界面，然后或许当前登录者信息在从表表头，然后再主表的表单中增加一条信息。
		 */
		 initWindow("dg_add", "新增选课信息", 750, 1200,
		 "stu/stuconfirmMasterAction_viewAdd.action",function(){
		 $('#dg').datagrid('reload');
		 }); 
		/* location.replace("stu/stuconfirmDetailAction_index.action"); */
	}

	function onUpdate() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行编辑!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		if (arr[0].status != '新建' && arr[0].status != '重置') {
			$.messager.show({
				title : '提示信息!',
				msg : '只能编辑状态为新建或重置的信息!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		var id = arr[0].id;
		initWindow("dg_edit", "修改选课信息", 750, 730,
		 	"stu/stuconfirmMasterAction_viewEdit.action?scfm.id=" + id,
		 function() {
			 $('#dg').datagrid('reload');
		});
/* 		location.replace("stu/stuconfirmDetailAction_viewEdit.action？scfm.id="+id,function(){
			$('#dg').datagrid('reload') ;
		}); */
	}
	
	function onDetail(){
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行查看!',
				showType : 'fade',
				timeout : 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		var id = arr[0].id;
		initWindow("dg_detail", "查看信息", 750, 730,
				"stu/stuconfirmMasterAction_viewDetail.action?scfm.id=" + id);
	}

	//取消选中项
	function reject() {
		$('#dg').datagrid('unselectAll');
	}
	
	//导出表格
	function exportExcel(){
		var arr = $('#dg').datagrid('getSelections');
		if(arr.length != 1){
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title:'提示信息！',
				msg:'只能选择一行!',
				showType:'fade',
				timeout:30000,
				style:{
				right:'',
				bottom:''
				}
			});
			return;
		}
		var id = arr[0].id;
		window.location.href="stu/stuconfirmMasterAction_exportExcelList.action?scfm.id="+id;
	}

	//删除
	function doDelete() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length <= 0) {
			$.messager.show({
				title : '提示信息!',
				msg : '请选择...!',
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
		} else {
			for (var i = 0; i < arr.length; i++) {
					if (arr[i].status != '新建' && arr[i].status != '重置') {
						$.messager.show({
							title : '提示信息!',
							msg : '只能删除状态为新建或重置的信息!',
							showType : 'fade',
							timeout : 3000,
							style : {
								right : '',
								bottom : ''
							}
						});
						return;
					}
				}
				$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {
					var ids = '';
					for ( var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post("stu/stuconfirmMasterAction_deleteSelf.action", {
						ids : ids
					}, function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除成功!',
								showType : 'fade',
								timeout : 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
						} else {
							$.messager.show({
								title : '提示信息',
								msg : '删除失败!',
								showType : 'fade',
								timeout : 3000,
								style : {
									right : '',
									bottom : ''
								}
							});
						}

					});
				} else {
					return;
				}
			});
		}
	}

</script>

