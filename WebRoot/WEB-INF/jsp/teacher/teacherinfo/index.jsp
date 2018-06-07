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

<title>教师评审管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">基础数据&nbsp;&gt;&gt;&nbsp;教师基本信息管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>

			<form id="queryForm" method="post">

				<table>
					<tr>
						<td>姓名: <input type="text" class="l_textbox" id="employName"
							name="employName" value="">
						</td>
						<td>工号: <input type="text" class="l_textbox" id="employNo"
							name="employNo" value="">
						</td>
						<!-- <td>学院名称: <input type="text" class="l_textbox" id="orgName"
							name="orgName" value="">
						</td> -->
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
						<th data-options="field:'employNo',width:100">工号</th>
						<th data-options="field:'employName',width:100">姓名</th>
						<th
							data-options="field:'sex',width:50,formatter:function(r){return showSex(r)}">性别</th>
						<th
							data-options="field:'birthday',width:110,formatter:function(r){return formatShortDate(r)}">出生日期</th>
						<th data-options="field:'orgName',width:120">部门（学院）</th>
						<th data-options="field:'department',width:120">科室（系）</th>
						<th data-options="field:'telno',width:120">联系电话</th>
						<th data-options="field:'email',width:120">Email地址</th>
						<th data-options="field:'address',width:120">联系地址</th>
						<th data-options="field:'category',width:120">教职工类别</th>
						<th data-options="field:'education',width:120">学历</th>
						<th data-options="field:'degree',width:150">学位</th>
						<th data-options="field:'duty',width:150">职务</th>
						<th data-options="field:'acdemictitle',width:100">职称</th>
						<th data-options="field:'invigilatorflag',width:50,formatter:function(r){return showInviFlag(r);}">可否监考</th>
						<th data-options="field:'researchdirection',width:150">教学研究方向</th>
						<th data-options="field:'introduce',width:200">教师简介</th>
						<th data-options="field:'major',width:120">专业名称</th>
						<th data-options="field:'graduate',width:120">毕业院校</th>
						<th
							data-options="field:'qualificationflag',width:50,formatter:function(r){return showQualificationflag(r);}">教师资格</th>
						<th
							data-options="field:'jobstatus',width:50,formatter:function(r){return showJobStatus(r);}">在职状态</th>
						<th data-options="field:'teacherLevel',width:150">教师级别</th>
						<th
							data-options="field:'islab',width:50,formatter:function(r){return showIsLab(r);}">是否实验室人员</th>
						<th
							data-options="field:'isouthire',width:50,formatter:function(r){return showIsOutHire(r);}">是否外聘</th>

						<th data-options="field:'politicalstatus',width:120">政治面貌</th>
						<th data-options="field:'nation',width:100">民族</th>
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
		<!--<power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdatePwd()">修改密码</a>
		</power:Edit>
		-->
		<power:Del>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
		</power:Del>
	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">
		<power:Query>
			<div onclick="toDetail()" data-options="iconCls:'icon-detailview'">详情</div>
		</power:Query>
		<power:Edit>
			<div onclick="onUpdate()" data-options="iconCls:'icon-edit'">修改</div>
		</power:Edit>
		<power:Edit>
			<div onclick="onUpdatePwd()" data-options="iconCls:'icon-edit'">修改密码</div>
		</power:Edit>
		<power:Del>
			<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
		</power:Del>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		init_easyui_celltip();//要放datagrid前初始化
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'tea/teacherInfoAction_getList.action',
			lines : true,
			animate : true,
			nowrap : true,
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
			onLoadSuccess:function(data){   
    			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});   
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
	
	function showInviFlag(r){
		if(r=='T'){
			return '可以';
		}else{
		 	return '不可以';
		} 
	}
	function showQualificationflag(r){
		if(r=='Y'){
			return '有';
		}else{
		 	return '无';
		} 
	}
	function showJobStatus(r){
		if(r=='Y'){
			return '在职';
		}else{
		 	return '离职';
		} 
	}
	
	function showIsLab(r){
		if(r=='Y'){
			return '是';
		}else{
		 	return '否';
		} 
	}
	function showIsOutHire(r){
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
		initWindow("dg_add","新增教职工信息",750,400,"tea/teacherInfoAction_viewAdd.action");
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
		initWindow("dg_edit","修改教职工信息",750,400,"tea/teacherInfoAction_viewEdit.action?id="+id);
	}
	
	function onUpdatePwd() {
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
		initWindow("dg_editpwd","修改密码",600,300,"tea/teacherInfoAction_viewPwd.action?id="+id);
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
					$.post('tea/teacherInfoAction_delete.action', {
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
		initWindow("dg_import","导入教师基本信息",450,200,"tea/teacherInfoAction_viewImport.action"); 
	}
	
	//导出
	function exportStudents(){
		//location.href="${ctx}/channeldistr!exportExcelList.action";
		queryForm.action="tea/teacherInfoAction_exportExcelList.action";
		queryForm.submit();
	}
	
	function toDetail() {
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
		var id = arr[0].id;
		var employNo = arr[0].employNo;
		initWindow("dg_detail", "详情信息", 820, 450,
				"tea/teacherInfoAction_viewDetail.action?id="+id+"&employNo="+employNo);
	}

	
</script>

