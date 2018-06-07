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
				<div class="nav_left">学生学习情况管理&nbsp;&gt;&gt;&nbsp;学籍异动管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<td>姓名: <input type="text" class="l_textbox" id="stuname"
							name="stuname" value="">
						</td>
						<td>学号: <input type="text" class="l_textbox" id="studentno"
							name="studentno" value="">
						</td>
						<td>异动前学院名称: <input type="text" class="l_textbox" id="ydqcollege"
							name="ydqcollege" value="">
						</td>
						<td>异动后学院名称: <input type="text" class="l_textbox" id="ydhcollege"
							name="ydhcollege" value="">
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
		</div>

		<div data-options="region:'center'">
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true,width:20"></th>
						<th data-options="field:'transactionno',width:100">异动编号</th>
						<th data-options="field:'tansactionclass',width:120">异动后行政班</th>
						<th data-options="field:'studentno',width:100">学号</th>
						<th data-options="field:'stuname',width:100">姓名</th>
						<th
							data-options="field:'sex',width:50,formatter:function(r){return showSex(r);}">性别</th>
						<th data-options="field:'processsymbols',width:120">处理文号</th>
						<th data-options="field:'tansactiontype',width:100">异动类别</th>
						<th data-options="field:'tansactionreason',width:100">异动原因</th>
						<th data-options="field:'tansactiondate',width:120,formatter:function(r){return formatShortDate(r);}">异动时间</th>
						<th data-options="field:'handledate',width:120,formatter:function(r){return formatShortDate(r);}">行文时间</th>
						<th data-options="field:'canceldate',width:120,formatter:function(r){return formatShortDate(r);}">撤销时间</th>
						<th data-options="field:'tansactionmemo',width:200">异动说明</th>
						<th data-options="field:'zxqschool',width:200">转学前学校名称</th>
						<th data-options="field:'zxqgrade',width:120">转学前所在年级</th>
						
						<th data-options="field:'zxqmajor',width:120">转学前专业</th>
						<th data-options="field:'ydqcollege',width:120">异动前学院</th>
						<th data-options="field:'ydqdepartment',width:120">异动前系</th>
						<th data-options="field:'ydqmajor',width:120">异动前专业</th>
						<th data-options="field:'ydqlength',width:120">异动前学制</th>
						<th data-options="field:'ydqmajorfield',width:120">异动前专业方向</th>
						<th data-options="field:'ydqcultivatedirection',width:120">异动前培养方向</th>
						<th data-options="field:'ydqclassname',width:120">异动前行政班</th>
						<th data-options="field:'ydqschoolstatus',width:120">异动前学籍转态</th>
						<th data-options="field:'zchschool',width:120">转出后学校名称</th>
						<th data-options="field:'zchgrade',width:120">转出后年级</th>
						
						<th data-options="field:'zchmajor',width:120">转出后专业</th>
						<th data-options="field:'ydhcollege',width:120">异动后学院</th>
						<th data-options="field:'ydhdepartment',width:120">异动后系</th>
						<th data-options="field:'ydhmajor',width:120">异动后专业</th>
						<th data-options="field:'ydhlength',width:120">异动后学制</th>
						<th data-options="field:'ydhmajorfield',width:120">异动后专业方向</th>
						<th data-options="field:'ydhcultivatedirection',width:120">异动后培养方向</th>
						<th data-options="field:'ydhschoolstatus',width:120">异动后学籍状态</th>
						<th data-options="field:'ydqgrade',width:120">异动前所在年级</th>
						<th data-options="field:'ydhgrade',width:120">异动后所在年级</th>
						<th data-options="field:'academicyear',width:100">学年</th>
						<th data-options="field:'term',width:50">学期</th>
						<th data-options="field:'operator',width:120">操作人</th>
						<th data-options="field:'operatortime',width:120">操作日期</th>
						<th data-options="field:'ydqinschool',width:120,formatter:function(r){return showYN(r);}">异动前是否在校</th>
						<th data-options="field:'ydhinschool',width:120,formatter:function(r){return showYN(r);}">异动后是否在校</th>
						<th data-options="field:'ydqmajorcode',width:120">异动前专业代码</th>
						<th data-options="field:'ydhmajorcode',width:120">异动后专业代码</th>
						
						<th data-options="field:'ydqisregiste',width:120,formatter:function(r){return showYN(r);}">异动前是否注册</th>
						<th data-options="field:'ydhisregiste',width:120,formatter:function(r){return showYN(r);}">异动后是否注册</th>
						<th data-options="field:'memo',width:100">备注</th>
						<th data-options="field:'ydqeducation',width:120">异动前学历层次</th>
						<th data-options="field:'ydheducation',width:120">异动后学历层次</th>
						<th data-options="field:'ydqmajorcategory',width:120">异动前专业类别</th>
						<th data-options="field:'ydhmajorcategory',width:120">异动后专业类别</th>
						<th data-options="field:'ydresult',width:120">异动结果</th>
						<th data-options="field:'studentcategory',width:120">学生类别</th>
						<th data-options="field:'examinateno',width:120">考生号</th>
						<th data-options="field:'idcardno',width:120">身份证号</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<power:Import>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-upload',plain:true"
				onclick="importStuents()">导入</a>
		</power:Import>
		<power:Export>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-download',plain:true"
				onclick="exportStudents()">导出</a>
		</power:Export>
		<power:Add>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">增加</a>
		</power:Add>
		<power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdate()">编辑</a>
		</power:Edit>
		<power:Del>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
		</power:Del>
	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<power:Edit>
			<div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div>
		</power:Edit>
		<power:Del>
			<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
		</power:Del>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'stu/enrollTransactionAction_getList.action',
			lines : true,
			animate : true,
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
			}
		});
	});
	
	//关闭窗口，提供给子页调用
	function closeWindow(id){
		$(id).window('destroy');
		$('#dg').datagrid('reload');//刷新
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
	
	function showSex(sex){
		if(sex=='0'){
			return '男';
		}else{
		 	return '女';
		} 
	}
	
	function showYN(r){
		if(r=='Y'){
			return '是';
		}else{
		 	return '否';
		} 
	}
	
	
	
	function clearForm() {
		$('#queryForm').form('clear');
		$('#dg').datagrid('load', {});
	}

	function onAdd() {
		initWindow("dg_add","新增学籍异动信息",1100,400,"stu/enrollTransactionAction_viewAdd.action");
	}

	function onUpdate() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {//只能单选
			$('#dg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择一行编辑!',
				showType : 'fade',
				timeout: 3000,
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
		}
		var id = arr[0].id;
		initWindow("dg_edit","修改学籍异动信息",1100,400,"stu/enrollTransactionAction_viewEdit.action?id="+id);
	}
	
	//取消选中项
	function reject() {
		$('#dg').datagrid('unselectAll');
	}
	
	//删除
	function doDelete() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length <= 0) {
			$.messager.show({
				title : '提示信息!',
				msg : '请选择...!',
				showType:'fade',
						style:{								
						right:'',
						bottom:''
					}
			});
		} else {
			$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {
					var ids = '';
					for ( var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post('stu/enrollTransactionAction_delete.action', {
						ids : ids
					}, function callback(txt) {
					var json = $.parseJSON(txt);
						if (json.status == "ok") {
							$('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除成功!',
								showType:'fade',
								timeout: 3000,
								style:{								
								right:'',
								bottom:''
								}
							});
						} else {
							$.messager.show({
								title : '提示信息',
								msg : '删除失败!',
								showType:'fade',
								timeout: 3000,
								style:{								
								right:'',
								bottom:''
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
	
	function importStuents() {
		initWindow("dg_import","导入学籍异动信息",450,200,"stu/enrollTransactionAction_viewImport.action"); 
	}
	
	//导出
	function exportStudents(){
		//location.href="${ctx}/channeldistr!exportExcelList.action";
		queryForm.action="stu/enrollTransactionAction_exportExcelList.action";
		queryForm.submit();
	}
	

	
</script>

