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
			<div class="space_flow_20"></div>
			<form id="editForm" method="post">
				<div>
					<div style="clear:both" class="easyui-accordion">
						<div title="学生信息" data-options="selected:true">
							<div style="clear:both">
								<div style="padding:10px;float:left">
									学号 <input type="text" class="easyui-validatebox l_textbox"
										id="studentno" name="scfm.studentno" value=""
										disabled="disabled"
										data-options="required:true,missingMessage:'学号必填!'">
								</div>
								<div style="padding:10px;float:left">
									姓名 <input type="text" class="easyui-validatebox l_textbox"
										id="stuname" name="scfm.stuname" value="" disabled="disabled"
										data-options="required:true,missingMessage:'姓名必填!'">
								</div>
							</div>
							<div style="clear:both">
								<div style="padding:10px;float:left">
									年级 <input type="text" class="easyui-validatebox l_textbox"
										id="grade" name="scfm.grade" value="" disabled="disabled">
								</div>
								<div style="padding:10px;float:left">
									学年 <select class="easyui-combobox" id="academicyear"
										name="scfm.academicyear"
										data-options="required:true,missingMessage:'请选择学年!'"
										editable="false">
										<option value="2010-2011">2010-2011</option>
										<option value="2011-2012">2011-2012</option>
										<option value="2012-2013">2012-2013</option>
										<option value="2013-2014">2013-2014</option>
										<option value="2014-2015">2014-2015</option>
										<option value="2015-2016" selected="selected">2015-2016</option>
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
									</select>
								</div>
								<div style="padding:10px;float:left">
									学期 <select class="easyui-combobox" id="term" name="scfm.term"
										editable="false"
										data-options="required:true,missingMessage:'请选择学期!'">
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
									</select>
								</div>
							</div>
							<div style="clear:both">
								<div style="padding:10px;float:left">
									班级 <input type="text" class="easyui-validatebox l_textbox"
										id="classname" name="scfm.classname" value=""
										disabled="disabled">
								</div>
								<div style="padding:10px;float:left">
									联系方式 <input type="text" class="easyui-validatebox l_textbox"
										id="telno" name="scfm.telno" value="" disabled="disabled">
								</div>
							</div>
							<div style="clear:both;padding:10px">
								状态 <input class="easyui-validatebox l_texbox" id="status"
									name="scfm.status" disabled="disabled">
							</div>
						</div>

						<div title="学分信息" data-options="selected:false">
							<div style="padding:2px;clear:both">
								<div style="padding:2px;float:left">
									基础课应选学分 <input name="scfm.jck" id="jck" value="" />
								</div>
								<div style="padding:2px;float:left">
									基础课已选学分 <input name="scfm.jckyx" id="jckyx" value="" />
								</div>
							</div>

							<div style="padding:2px;clear:both">
								<div style="padding:2px;float:left">
									必修课应选学分 <input name="scfm.bxk" id="bxk" value="" />
								</div>
								<div style="padding:2px;float:left">
									必修课已选学分 <input name="scfm.bxkyx" id="bxkyx" value="" />
								</div>
							</div>

							<div style="padding:2px;clear:both">
								<div style="padding:2px;float:left">
									专业课应选学分 <input name="scfm.zyk" id="zyk" value="" />
								</div>
								<div style="padding:2px;float:left">
									专业课已选学分 <input name="scfm.zykyx" id="zykyx" value="" />
								</div>
							</div>

							<div style="padding:2px;float:left">
								已选通识必修课学分 <input name="scfm.tsbxkyx" id="tsbxkyx" value="" />
							</div>

							<div style="padding:2px;float:left">
								通识选修课学分 <input name="scfm.tsxxkyx" id="tsxxkyx" value="" />
							</div>

							<div style="padding:2px;float:left">
								跨领域选修课学分 <input name="scfm.klyxk" id="klyxk" value="" />
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
				</div>
				<div style="padding:10px;float:right">
					<a id="btnsummit" class="easyui-linkbutton" onclick="doSave()">保存</a>
					<a id="btnsubmit" class="easyui-linkbutton" onclick="doSubmit()">提交</a>
					<a id="btncancer" class="easyui-linkbutton" onclick="doClose()">关闭</a>
				</div>
				<!-- <div class="space_flow_5"></div> -->
			</form>
		</div>

		<div data-options="region:'center'">
			<table id="dg" style="height:40%;" class="easyui-datagrid">
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

		<div onclick="updateInfo()" data-options="iconCls:'icon-edit'">修改</div>

		<div onclick="doDelete()" data-options="iconCls:'icon-remove'">删除</div>

	</div>

</body>
</html>
<script type="text/javascript">
	id = null;
	$(function() {
		//简单的表单验证
		easyui_form_validator();

		//加载数据
		loadData();

		//数据框的设置导入
		$('#dg').datagrid({
			idField : 'id',
			loadMsg : '数据加载中请稍后……',
			url : 'stu/stuconfirmDetailAction_listSelf.action',
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

			//右键菜单设置
			onRowContextMenu : function(e, row, data) {
				e.preventDefault(); //屏蔽浏览器的菜单
				$(this).datagrid('unselectAll');//清除所有选中项
				$(this).datagrid('selectRow', row);
				$('#op_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},

			//菜单加载成功后清楚已选选项
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

		$.post('stu/stuconfirmMasterAction_loadSelf.action', {

		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.scfm;
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				$('#grade').val(o.grade);
				if (o.classname == null || o.classname == "") {
					$.messager.show({
						title : '提示信息',
						msg : '课程信息为空，检查个人信息是否完整，可能会导致课程信息无法正确获取！',
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
				}
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

	function doClose() {
		window.parent.closeWindow('#dg_add');
	}

	//关闭窗口，提供给子页调用
	function closeWindow(id1) {
		$(id1).window('destroy');
		$('#dg').datagrid('load', {
			'scfd.masterId' : id
		});//刷新
	}

	//查询函数
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

	//清除查询框
	function clearForm() {
		$('#queryForm').form('clear');
		$('#dg').datagrid('load', {});
	}

	//增加按钮调用函数（链接到detail-add）
	function onAddInfo() {
		if (id == null || $('#status').val() == '未保存') {
			$.messager.alert('错误', '请先保存主表信息！');
			return;
		}
		initWindow("dg_addInfo", "新增选课详细信息", 500, 370,
				"stu/stuconfirmDetailAction_viewAdd.action?masterid=" + id);
	}

	//提交按钮调用函数
	function doSubmit() {
		if (id == null || $('#status').val() == '未保存') {
			$.messager.alert('错误', '请先保存主表信息！')
			return;
		}
		$.messager.confirm("提示信息", "确认提交？提交够无法修改此表的任何信息！", function(r) {
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

	//查看按钮函数（调用detail-check）
	function onCheck() {
		var arr = $('#dg').datagrid('getSelections');
		if (arr.length != 1) {
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
		initWindow("dg_check", "查看选课详细信息", 750, 370,
				"stu/stuconfirmDetailAction_viewAdd.action?scfm.id=" + id);
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
			var isSave = true;
			var data = $("editForm").serialize();
			data = data + '&scfm.studentno=' + $('#studentno').val()
					+ '&scfm.Academicyear='
					+ $('#academicyear').combobox('getValue') + '&scfm.Term='
					+ $('#term').combobox('getValue') + '&scfm.jck='
					+ $('#jck').combobox('getValue') + '&scfm.jckyx='
					+ $('#jckyx').combobox('getValue') + '&scfm.bxk='
					+ $('#bxk').combobox('getValue') + '&scfm.bxkyx='
					+ $('#bxkyx').combobox('getValue') + '&scfm.zyk='
					+ $('#zyk').combobox('getValue') + '&scfm.zykyx='
					+ $('#zykyx').combobox('getValue') + '&scfm.tsbxkyx='
					+ $('#tsbxkyx').combobox('getValue') + '&scfm.tsxxkyx='
					+ $('#tsxxkyx').combobox('getValue') + '&scfm.klyxk='
					+ $('#klyxk').combobox('getValue') + '&scfm.othercourse='
					+ $('#othercourse').val() + '&scfm.othercredit='
					+ $('#othercredit').val();
			var url = 'stu/stuconfirmMasterAction_saveSelf.action';
			if (id != null && $('#status').val() != '未保存') {
				url = 'stu/stuconfirmMasterAction_updateSelf.action';
				data = data + '&scfm.id=' + id;
				isSave = false;
			}
			$.post(url, data,
					function callback(txt) {
						var json = $.parseJSON(txt);
						if (json.status == "ok") {
							if (isSave) {
								var o = json.newscfm;
								id = o.id;
								$('#studentno').val(o.studentno);
								$('#stuname').val(o.stuname);
								$('#grade').val(o.grade);
								$('#academicyear').combobox('setValue',
										o.academicyear);

								//当用户点击保存按钮，保存了主表的信息之后，使得学年可选框不可再次编辑
								$('#academicyear').combobox({
									disabled : true
								});
								$('#term').combobox('setValue', o.term);

								//当用户点击保存按钮，保存了主表的信息之后，使得学期可选框不可再次编辑
								$('#term').combobox({
									disabled : true
								});
								$('#classname').val(o.classname);
								$('#status').val(o.status);
								$('#telno').val(o.telno);

								//按钮取消点击功能
								$('#btnsummit').linkbutton('disable');
							}
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

	//返回
	function onReturn() {
		location.replace("stu/stuconfirmMasterAction_index.action");
	}
</script>
