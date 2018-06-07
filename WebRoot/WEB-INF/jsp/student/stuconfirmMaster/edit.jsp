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
<title>从表</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',border:1">
			<div class="nav">
				<div class="nav_left">学生选课确认&nbsp;&gt;&gt;&nbsp;学生选课详细信息表</div>
			</div>
			<div class="edit-line"></div>
			<!-- <div class="space_flow_20"></div> -->
			<form id="editForm" method="post">
				<div class="easyui-accordion">
					<div title="学生信息" data-options="selected:false">
						<div>
							<div style="clear:both;padding:10px">
								<div style="padding:10px;float:left">
									学号 <input type="text" class="easyui-validatebox l_textbox"
										id="studentno" name="scfm.studentno" value=""
										data-options="required:true,missingMessage:'学号必填!'"
										disabled="disabled">
								</div>
								<div style="padding:10px;float:left">
									姓名 <input type="text" class="easyui-validatebox l_textbox"
										id="stuname" name="scfm.stuname" value="" disabled="disabled"
										data-options="required:true,missingMessage:'姓名必填!'">
								</div>
							</div>
							<div style="clear:both;padding:10px">
								<div style="padding:10px;float:left">
									班级 <input type="text" class="easyui-validatebox l_textbox"
										id="classname" name="scfm.classname" value=""
										disabled="disabled" data-options="">
								</div>
								<div style="padding:10px;float:left">
									 年级<input class="easyui-validatebox l_textbox" id="grade"
										name="scfm.grade" value="" disabled="disabled">
								</div>
							</div>
							<div style="clear:both;padding:10px">
								<div style="padding:10px;float:left">
									学年 <select id="academicyear" class="easyui-combobox"
										style="width:180px;" editable="false" disabled="disabled"
										data-options="required:true,missingMessage:'请选择学年!'"
										name="scfm.academicyear">
										<option value="2010-2011">2010-2011</option>
										<option value="2011-2012">2011-2012</option>
										<option value="2012-2013">2012-2013</option>
										<option value="2013-2014">2013-2014</option>
										<option value="2014-2015">2014-2015</option>
										<option value="2015-2016">2015-2016</option>
										<option value="2016-2017" selected="selected">2016-2017</option>
										<option value="2017-2018">2017-2018</option>
										<option value="2018-2019">2018-2019</option>
										<option value="2019-2020">2019-2020</option>
										<option value="2020-2021">2020-2021</option>
										<option value="2021-2022">2021-2022</option>
										<option value="2022-2023">2022-2023</option>
										<option value="2023-2024">2023-2024</option>
										<option value="2024-2025">2024-2025</option>
										<option value="2025-2026">2025-2026</option>
									</select>
								</div>
								<div style="padding:10px;float:left">
									学期 <select id="term" class="easyui-combobox"
										style="width:180px;" editable="false" disabled="disabled"
										data-options="required:true,missingMessage:'请选择学期!'"
										name="scfm.term">
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
									</select>
								</div>
							</div>
							<div style="clear:both;padding:10px">
								<div style="padding:10px;float:left">
									状态 <input class="easyui-validatebox l_textbox" id="status"
										name="scfm.status" value="" disabled="disabled">
								</div>
								<div style="padding:10px;float:left">
									联系方式 <input type="text" class="asyui-validatebox l_textbox"
										id="telno" name="scfm.telno" value="" data-options=""
										disabled="disabled">
								</div>
							</div>
						</div>
					</div>
					<div title="学分信息" data-options="selected:false">
						<div style="padding:2px;clear:both">
							<div style="padding:2px;float:left">
								基础课应选学分 <select class="easyui-combobox" name="scfm.jck" id="jck"
									data-options="required:true,missingMessage:'请选择学期!'">
								</select>
								<!-- <input name="scfm.jck" id="jck" value="" /> -->
							</div>
							<div style="padding:2px;float:left">
								基础课已选学分 <select class="easyui-combobox" name="scfm.jckyx"
									id="jckyx" data-options="required:true,missingMessage:'请选择学期!'">
								</select>
							</div>
						</div>

						<div style="padding:2px;clear:both">
							<div style="padding:2px;float:left">
								必修课应选学分 <select class="easyui-combobox" name="scfm.bxk" id="bxk"
									data-options="required:true,missingMessage:'请选择学期!'">
								</select>
							</div>
							<div style="padding:2px;float:left">
								必修课已选学分 <select class="easyui-combobox" name="scfm.bxkyx"
									id="bxkyx" data-options="required:true,missingMessage:'请选择学期!'">
								</select>
							</div>
						</div>

						<div style="padding:2px;clear:both">
							<div style="padding:2px;float:left">
								专业课应选学分 <select class="easyui-combobox" name="scfm.zyk" id="zyk"
									data-options="required:true,missingMessage:'请选择学期!'">
								</select>
							</div>
							<div style="padding:2px;float:left">
								专业课已选学分 <select class="easyui-combobox" name="scfm.zykyx"
									id="zykyx" data-options="required:true,missingMessage:'请选择学期!'">
								</select>
							</div>
						</div>

						<div style="padding:2px;float:left">
							已选通识必修课学分 <select class="easyui-combobox" name="scfm.tsbxkyx"
								id="tsbxkyx"
								data-options="required:true,missingMessage:'请选择学期!'">
							</select>
						</div>

						<div style="padding:2px;float:left">
							通识选修课学分 <select class="easyui-combobox" name="scfm.tsxxkyx"
								id="tsxxkyx"
								data-options="required:true,missingMessage:'请选择学期!'">
							</select>
						</div>

						<div style="padding:2px;float:left">
							跨领域选修课学分 <select class="easyui-combobox" name="scfm.klyxk"
								id="klyxk" data-options="required:true,missingMessage:'请选择学期!'">
							</select>
						</div>
						<div style="clear:both">
							<div style="padding:2px;float:left">
								其他课程 <input type="text" class="easyui-validatebox l_textbox"
									id="othercourse" name="scfm.othercourse" style="width:50px;"
									value="">
							</div>
							<div style="padding:2px;float:left">
								学分 <input type="text" class="easyui-validatebox l_textbox"
									id="othercredit" name="scfm.othercredit" value=""
									style="width:25px;">
							</div>
						</div>
					</div>

				</div>
				<div style="padding:10px;float:right">
					<a id="btnsummit" class="easyui-linkbutton" onclick="doSave()">保存</a>
					<a id="btnsubmit" class="easyui-linkbutton" onclick="doSubmit()">提交</a>
					<a id="btncancer" class="easyui-linkbutton" onclick="doClose()">关闭</a>
				</div>
				<div class="space_flow_5"></div>

			</form>
		</div>
		<div data-options="region:'center'">
			<table id="dg" style="height:50%" class="easyui-datagrid">
			</table>
		</div>
	</div>

	<div id="dt" align="right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>

		<a href="javascript:void(0)" class="easyui-linkbutton "
			data-options="iconCls:'icon-add',plain:true" onclick="onAddInfo()">增加</a>

		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="updateInfo()">编辑</a>

		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>

	</div>

	<div id="op_menu" class="easyui-menu" style="width: 120px;">

		<div onclick="UpdateInfo()" data-options="iconCls:'icon-edit'">修改</div>

		<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>

	</div>

</body>
</html>
<script type="text/javascript">
	var id = '<%=request.getAttribute("scfm.id")%>';
	$(function() {
		//表单验证
		easyui_form_validator();

		//加载数据
		loadData();

		//详细数据框设置
		$('#dg')
				.datagrid(
						{
							idField : 'id',
							loadMsg : '数据加载中请稍后……',
							url : 'stu/stuconfirmDetailAction_listSelf.action?scfd.masterId='
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

							//右键菜单按钮设置
							onRowContextMenu : function(e, row, data) {
								e.preventDefault(); //屏蔽浏览器的菜单
								$(this).datagrid('unselectAll');//清除所有选中项
								$(this).datagrid('selectRow', row);
								$('#op_menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							},

							//加载成功后重新加载
							onLoadSuccess : function() {
								$(this).datagrid('clearChecked');
							}
						});

		//学分选项框的设置
		$('#jck').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

		$('#jckyx').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

		$('#bxk').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

		$('#bxkyx').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

		$('#zyk').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

		$('#zykyx').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

		$('#tsbxkyx').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

		$('#tsxxkyx').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});
		$('#klyxk').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});
		$('#othercredit').combobox({
			width : 45,
			valueField : 'id',
			textField : 'value',
			//生成可选数据函数
			data : createCreditJson(),
			//下拉选框如果自己填写数据检验
			onHidePanel : function() {
				checkComboboxValue(this);
			}
		});

	});

	//学分填入值检查是否匹配可选学分值
	function checkComboboxValue(c) {
		var valueField = $(c).combobox("options").valueField;
		var val = $(c).combobox("getValue"); //当前combobox的值
		var allData = $(c).combobox("getData"); //获取combobox所有数据
		var result = true; //为true说明输入的值在下拉框数据中不存在
		for ( var i = 0; i < allData.length; i++) {
			if (val == allData[i][valueField]) {
				result = false;
			}
		}
		if (result) {
			$(c).combobox("clear");
		}
	}

	//学分可选值得json数组生成函数（0-30）
	function createCreditJson() {
		var json = [];
		for ( var i = 0; i <= 30; i++) {
			var row = {
				id : i,
				value : i
			};
			json.push(row);
			var row = {
				id : i + 0.5,
				value : i + 0.5
			};
			json.push(row);
		}
		return json;
	}

	//加载数据函数
	function loadData() {
		$.post('stu/stuconfirmMasterAction_getSelf.action', {
			'scfm.id' : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.scfm;
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				$('#grade').val(o.grade);
				//$('#academicyear').val(o.academicyear);
				$('#academicyear').combobox('setValue', o.academicyear);
				//$('#term').val(o.term);
				$('#term').combobox('setValue', o.term);
				$('#classname').val(o.classname);
				$('#status').val(o.status);
				$('#telno').val(o.telno);
				$('#jck').combobox('setValue', o.jck);
				$('#jckyx').combobox('setValue', o.jckyx);
				$('#bxk').combobox('setValue', o.bxk);
				$('#bxkyx').combobox('setValue', o.bxkyx);
				$('#zyk').combobox('setValue', o.zyk);
				$('#zykyx').combobox('setValue', o.zykyx);
				$('#tsbxkyx').combobox('setValue', o.tsbxkyx);
				$('#tsxxkyx').combobox('setValue', o.tsxxkyx);
				$('#klyxk').combobox('setValue', o.klyxk);
				$('#othercourse').val(o.othercourse);
				$('#othercredit').val(o.othercredit);
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

	//日期格式化
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

	//增加按钮调用函数（链接到detail-add）
	function onAddInfo() {
		initWindow("dg_addInfo", "添加选课", 500, 370,
				"stu/stuconfirmDetailAction_viewAdd.action?masterid=" + id);
	}

	//提交按钮调用函数
	function doSubmit() {
		$.messager.confirm("提示信息", "确认提交?提交后将无法修改此表的任何信息!", function(r) {
			if (r) {
				$.post('stu/stuconfirmMasterAction_submit.action', {
					'scfm.id' : id
				}, function callback(txt) {
					var json = $.parseJSON(txt);
					if (json.status == "ok") {
						$.messager.show({
							title : '提示信息',
							msg : '提交成功',
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						doClose()
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
		});
	}

	//修改按钮调用函数（链接到detail-edit）
	function updateInfo() {
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
		initWindow("dg_updateInfo", "修改信息", 500, 370,
				"stu/stuconfirmDetailAction_viewEdit.action?id=" + id);
	}

	//保存按钮调用函数
	function doSave() {
		if ($('#editForm').form('validate')) {
			var data = $("#editForm").serialize();
			data = data + "&scfm.id=" + id;
			$.post('stu/stuconfirmMasterAction_updateSelf.action', data,
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

	//删除按钮调用函数
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
			$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {
					var ids = '';
					for ( var i = 0; i < arr.length; i++) {
						ids += arr[i].id + ',';
					}
					ids = ids.substring(0, ids.length - 1);
					$.post("stu/stuconfirmDetailAction_deleteSelf.action", {
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

	//关闭窗口，提供给子页调用
	function closeWindow(id) {
		$(id).window('destroy');
		$('#dg').datagrid('load', {});//刷新
	}

	//关闭编辑页面窗口（master-edit）
	function doClose() {
		window.parent.closeWindow('#dg_edit');
	}

	//取消选中项
	function reject() {
		$('#dg').datagrid('unselectAll');
	}
</script>
