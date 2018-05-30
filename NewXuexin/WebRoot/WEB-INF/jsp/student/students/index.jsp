<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="power" uri="http://com.my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>学生信息管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
<script type="text/javascript" src="js/common/RowNumberWidth.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',height:100">
			<div class="nav">
				<div class="nav_left">基础数据&nbsp;&gt;&gt;&nbsp;学生基本信息管理</div>
			</div>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>
			<c:if test="${!isStu }">
			<form id="queryForm" method="post">
				<table>
					<tr>
						<td>姓名: <input type="text" class="l_textbox" id="stuname"
							name="stuname" value=""></td>
						<td>学号: <input type="text" class="l_textbox" id="studentno"
							name="studentno" value=""></td>
						<td>学院名称: <input type="text" class="l_textbox" id="orgName"
							name="orgName" value=""></td>
						<td>专业: <input type="text" class="l_textbox" id="major"
							name="major" value=""></td>
						<td><a id="btnfind" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a> <a
							id="clearbtn" class="easyui-linkbutton"
							data-options="iconCls:'icon-clear'" onclick="clearForm()">清空</a>
						</td>
					</tr>
				</table>
				<div class="space_flow_10"></div>
			</form>
			</c:if>
		</div>

		<div data-options="region:'center'">
			<table id="dg" style="height:200px">
				<thead>
					<tr>
						<th data-options="field:'stuId',checkbox:true,width:20"></th>
						<th data-options="field:'studentno',width:120">学号</th>
						<th data-options="field:'stuname',width:80">姓名</th>
						<th
							data-options="field:'sex',width:50">性别</th>
						<th
							data-options="field:'birthday',width:110,formatter:function(r){return formatShortDate(r);}">出生日期</th>
						<th data-options="field:'politicalstatus',width:80">政治面貌</th>
						<th data-options="field:'nation',width:50">民族</th>
						<th data-options="field:'nativeplace',width:50">籍贯</th>
						<th data-options="field:'fromPlace',width:120">来源地区</th>
						<th data-options="field:'idcardno',width:120">身份证号码</th>
						<th data-options="field:'orgName',width:120">学院名称</th>
						<th data-options="field:'department',width:120">系</th>
						<th data-options="field:'major',width:120">专业名称</th>
						<th data-options="field:'majorfield',width:150">专业方向</th>
						<th data-options="field:'majorcategories',width:120">专业类别</th>
						<th data-options="field:'cultivatedirection',width:120">培育方向</th>
						<th data-options="field:'classname',width:150">行政班</th>
						<th data-options="field:'educationsystem',width:50">学制</th>
						<th data-options="field:'schoolinglength',width:50">学习年限</th>
						<th
							data-options="field:'acceptancedate',width:110,formatter:function(r){return formatShortDate(r)}">入学日期</th>
						<th data-options="field:'middleschool',width:120">毕业中学</th>
						<th data-options="field:'mobileno',width:120">本人电话</th>
						<th data-options="field:'familytelno',width:120">家庭电话</th>
						<th data-options="field:'postcode',width:120">邮政编码</th>
						<th data-options="field:'travelrange',width:120">乘车区间</th>
						<th data-options="field:'address',width:120">家庭地址</th>
						<th data-options="field:'skill',width:120">特长</th>
						<th data-options="field:'selfdescription',width:160">简介</th>
						<th data-options="field:'awards',width:160">所获奖励</th>
						<th data-options="field:'schoolstatus',width:160">学籍状态</th>
						<th data-options="field:'dqszj',width:120">DQSZJ</th>
						<th data-options="field:'graduateflag',width:120">毕业审核标志</th>
						<th data-options="field:'photopath',width:120">头像</th>
						<th data-options="field:'createTime',width:120,formatter:function(r){return formatDate(r)}">创建时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<power:Query>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-detailview',plain:true"
				onclick="toDetail()">详情</a>
		</power:Query>
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
		<power:Export>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-download',plain:true"
				onclick="exportView()">导出报表</a>
		</power:Export>
		<power:Add>
			<a href="javascript:void(0)" class="easyui-linkbutton "
				data-options="iconCls:'icon-add',plain:true" onclick="onAdd()">增加</a>
		</power:Add>
		<power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdate()">编辑</a>
		</power:Edit>
		<c:if test="${isAdmin}">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdatePwd()">修改密码</a>
		</c:if>
		<power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true"
				onclick="updatePhoto()">编辑头像</a>
		</power:Edit>
		<%-- <power:Edit>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true" onclick="onUpdatePwd()">修改密码</a>
		</power:Edit> --%>
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
		<c:if test="${isAdmin}">
			<div onclick="onUpdatePwd()" data-options="iconCls:'icon-edit'">修改密码</div>
		</c:if>
		<power:Del>
			<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>
		</power:Del>
	</div>

	<!--添加/修改用户弹出框  -->
	<!-- <div id="dg_add" class="easyui-window" title="新增用户"
		data-options="footer:'#footer'" style="width:750px;height:400px;">
		<iframe id="addIframe" src="" frameborder="0"
			style="width:100%;height:100%;"> </iframe>
	</div>
	<div id="dg_edit" title="编辑用户" class="easyui-window"
		style="padding:10px; width: 750px; height: 350px;display: none;"
		data-options="footer:'#footer'">
		<iframe id="editIframe" src="" frameborder="0"
			style="width:100%;height:100%;"> </iframe>
	</div>
 -->
	<!-- <div id="dg_import" title="导入学生信息" class="easyui-window"
		style="padding:10px; width: 450px; height: 200px;display: none;">
		<iframe id="importIframe" src="" frameborder="0"
			style="width:100%;height:100%;"> </iframe> 
	</div>-->
</body>
</html>
<script type="text/javascript">
	$(function() {
		init_easyui_celltip();//要放datagrid前初始化
		$('#dg').datagrid({
			idField : 'stuId',
			loadMsg : '数据加载中请稍后……',
			url : '${isStu?"stu/studentsAction_getSelf.action":"stu/studentsAction_getList.action"}',
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
				//如果有菜单才显示
				if($('#op_menu>div').length > 1){
					$('#op_menu').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				}
			},
			onLoadSuccess:function(data){   
				$(this).datagrid('clearChecked');
    			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});  
    			$(this).datagrid("fixRownumber");
			}
		});
		//初始化弹出窗口
		/* openWindow('#dg_add');
		openWindow('#dg_edit'); */
		//openWindow('#dg_import');
		
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
	
	
	
	function clearForm() {
		$('#queryForm').form('clear');
		$('#dg').datagrid('load', {});
	}

	function onAdd() {
		initWindow("dg_add","新增学生信息",750,400,"stu/studentsAction_viewAdd.action");
	}

	function onUpdate() {
		var arr = $('#dg').datagrid('getRows');
		if(arr.length != 1){ //不止一行数据
			arr = $('#dg').datagrid('getSelections');
		}
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
		var stuId = arr[0].stuId;
		initWindow("dg_edit","修改学生信息",750,400,"stu/studentsAction_viewEdit.action?stuId="+stuId);
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
		var stuId = arr[0].stuId;
		initWindow("dg_editpwd","修改密码",600,300,"stu/studentsAction_viewPwd.action?stuId="+stuId);
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
						ids += arr[i].stuId + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post('stu/studentsAction_delete.action', {
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
		initWindow("dg_import","导入学生信息",450,200,"stu/studentsAction_viewImport.action"); 
	}
	
	function exportView() {
		initWindow("dg_import","导出学生信息",300,400,"stu/studentsAction_viewExport.action"); 
	}
	
	//导出
	function exportStudents(){
		//location.href="${ctx}/channeldistr!exportExcelList.action";
		queryForm.action="stu/studentsAction_exportExcelList.action";
		queryForm.submit();
	}
	
	function toDetail() {
		var arr = $('#dg').datagrid('getRows');
		if(arr.length != 1){//不止一行数据
			arr = $('#dg').datagrid('getSelections');
		}
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
		var stuId = arr[0].stuId;
		var studentno = arr[0].studentno;
		initWindow("dg_detail", "详情信息", 820, 450,
				"stu/studentsAction_viewDetail.action?stuId="+stuId+"&studentno="+studentno);
	}
	
	function updatePhoto() {
		var arr = $('#dg').datagrid('getRows');
		if(arr.length != 1){//不止一行数据
			arr = $('#dg').datagrid('getSelections');
		}
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
		var stuId = arr[0].stuId;
		var studentno = arr[0].studentno;
		initWindow("dg_upload", "选择头像", 450, 400,
				"stu/studentsAction_viewUpdatePhoto.action?studentno="
						+ studentno);
	}
</script>

