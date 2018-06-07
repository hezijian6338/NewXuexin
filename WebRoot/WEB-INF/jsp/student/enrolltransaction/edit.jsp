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
	<!--添加弹出框  -->
	<div>
		<div class="space_flow_20"></div>
		<form id="editForm" method="post">
			<input type="hidden" id="id" name="id" value="" />
			<table>
				<tr>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">　　　异动编号:</div>
								<div class="edit_right">
									<input type="text" id="transactionno" name="transactionno"
										class="easyui-validatebox l_textbox"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　异动后行政班:</div>
								<div class="edit_right">
									<input type="text" id="tansactionclass" name="tansactionclass"
										class="easyui-validatebox l_textbox"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　　学号:</div>
								<div class="edit_right">
									<input type="text" id="studentno" name="studentno"
										class="easyui-validatebox l_textbox"
										data-options="required:true,missingMessage:'学号必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　　姓名:</div>
								<div class="edit_right">
									<input type="text" id="stuname" name="stuname"
										class="easyui-validatebox l_textbox" 
										data-options="required:true,missingMessage:'姓名必填!'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　　性别:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="sex" id="sex0"
										value="0"  />男 <input type="radio" id="sex1"
										class="radio-style" name="sex" value="1" />女
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">　　　处理文号:</div>
								<div class="edit_right">
									<input id="processsymbols" type="text"
										class="easyui-validatebox l_textbox" name="processsymbols"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　异动类别:</div>
								<div class="edit_right">
									<input id="tansactiontype" type="text"
										class="easyui-validatebox l_textbox" name="tansactiontype"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　异动原因:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="tansactionreason"
										name="tansactionreason" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　异动时间:</div>
								<div class="edit_right">
									<input id="tansactiondate" type="text"
										class="easyui-datebox l_textbox" name="tanDate"
										data-options="formatter:myformatter,parser:myparser"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　行文时间:</div>
								<div class="edit_right">
									<input id="handledate" type="text"
										class="easyui-datebox l_textbox" name="hanDate"
										data-options="formatter:myformatter,parser:myparser"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　撤销时间:</div>
								<div class="edit_right">
									<input id="canceldate" type="text"
										class="easyui-datebox l_textbox" name="canDate"
										data-options="formatter:myformatter,parser:myparser"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　异动说明:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="tansactionmemo"
										name="tansactionmemo" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">转学前学校名称:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="zxqschool" name="zxqschool"
										rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">转学前所在年级:</div>
								<div class="edit_right">
									<input id="zxqgrade" type="text"
										class="easyui-validatebox l_textbox" name="zxqgrade"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　转学前专业:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="zxqmajor" name="zxqmajor"
										rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　异动前学院:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydqcollege" name="ydqcollege"
										rows="2" cols="23"></textarea>
								</div>
							</div>

							<div class="edit_winRow">
								<div class="edit_left">　　　异动前系:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydqdepartment"
										name="ydqdepartment" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　异动前专业:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydqmajor" name="ydqmajor"
										rows="2" cols="23"></textarea>
								</div>
							</div>

						</div>
					</td>
					<td>
						<div style="width: 350px;">

							<div class="edit_winRow">
								<div class="edit_left">异动前专业方向:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydqmajorfield"
										name="ydqmajorfield" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前培养方向:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydqcultivatedirection"
										name="ydqcultivatedirection" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　异动前行政班:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydqclassname"
										name="ydqclassname" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前学籍转态:</div>
								<div class="edit_right">
									<input id="ydqschoolstatus" type="text"
										class="easyui-validatebox l_textbox" name="ydqschoolstatus"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">转出后学校名称:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="zchschool" name="zchschool"
										rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　转出后年级:</div>
								<div class="edit_right">
									<input id="zchgrade" type="text"
										class="easyui-validatebox l_textbox" name="zchgrade"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　转出后专业:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="zchmajor" name="zchmajor"
										rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　异动后学院:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydhcollege" name="ydhcollege"
										rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　异动后系:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydhdepartment"
										name="ydhdepartment" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　异动后专业:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydhmajor" name="ydhmajor"
										rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　异动后学制:</div>
								<div class="edit_right">
									<input id="ydhlength" type="text"
										class="easyui-numberbox l_textbox" name="ydhlength"
										data-options="min:0" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后专业方向:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydhmajorfield"
										name="ydhmajorfield" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后培养方向:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydhcultivatedirection"
										name="ydhcultivatedirection" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后学籍状态:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydhschoolstatus"
										name="ydhschoolstatus" rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前所在年级:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydqgrade" name="ydqgrade"
										rows="2" cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后所在年级:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="ydhgrade" name="ydhgrade"
										rows="2" cols="23"></textarea>
								</div>
							</div>

						</div></td>
					<td>
						<div style="width: 350px;">
							<div class="edit_winRow">
								<div class="edit_left">　　异动前学制:</div>
								<div class="edit_right">
									<input id="ydqlength" type="text"
										class="easyui-numberbox l_textbox" name="ydqlength"
										data-options="min:0" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　　学年:</div>
								<div class="edit_right">
									<input id="academicyear" type="text"
										class="easyui-validatebox l_textbox" name="academicyear"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　　学期:</div>
								<div class="edit_right">
									<input id="term" type="text"
										class="easyui-validatebox l_textbox" name="term"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　操作人:</div>
								<div class="edit_right">
									<input id="operator" type="text"
										class="easyui-validatebox l_textbox" name="operator"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　操作日期:</div>
								<div class="edit_right">
									<input id="operatortime" type="text"
										class="easyui-datebox l_textbox" name="operatortime"
										data-options="formatter:myformatter2,parser:myparser2"
										value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前是否在校:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="ydqinschool"
										id="ydqinschool0" value="Y"  />是 <input
										type="radio" id="ydqinschool1" class="radio-style"
										name="ydqinschool" value="N" />否
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后是否在校:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="ydhinschool"
										id="ydqinschool0" value="Y"  />是 <input
										type="radio" id="ydhinschool1" class="radio-style"
										name="ydhinschool" value="N" />否
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前专业代码:</div>
								<div class="edit_right">
									<input id="ydqmajorcode" type="text"
										class="easyui-validatebox l_textbox" name="ydqmajorcode"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后专业代码:</div>
								<div class="edit_right">
									<input id="ydhmajorcode" type="text"
										class="easyui-validatebox l_textbox" name="ydhmajorcode"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前是否注册:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="ydqisregiste"
										id="ydqisregiste0" value="Y" />是 <input
										type="radio" id="ydqisregiste1" class="radio-style"
										name="ydqisregiste" value="N" />否
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后是否注册:</div>
								<div class="edit_right">
									<input type="radio" class="radio-style" name="ydhisregiste"
										id="ydhisregiste0" value="Y" />是 <input
										type="radio" id="ydhisregiste1" class="radio-style"
										name="ydhisregiste" value="N" />否
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　　备注:</div>
								<div class="edit_right">
									<textarea class="l_textarea" id="memo" name="memo" rows="2"
										cols="23"></textarea>
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前学历层次:</div>
								<div class="edit_right">
									<input id="ydqeducation" type="text"
										class="easyui-validatebox l_textbox" name="ydqeducation"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后学历层次:</div>
								<div class="edit_right">
									<input id="ydheducation" type="text"
										class="easyui-validatebox l_textbox" name="ydheducation"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动前专业类别:</div>
								<div class="edit_right">
									<input id="ydqmajorcategory" type="text"
										class="easyui-validatebox l_textbox" name="ydqmajorcategory"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">异动后专业类别:</div>
								<div class="edit_right">
									<input id="ydhmajorcategory" type="text"
										class="easyui-validatebox l_textbox" name="ydhmajorcategory"
										data-options="" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　异动结果:</div>
								<div class="edit_right">
									<input id="ydresult" type="text"
										class="easyui-validatebox l_textbox" name="ydresult"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　学生类别:</div>
								<div class="edit_right">
									<input id="studentcategory" type="text"
										class="easyui-validatebox l_textbox" name="studentcategory"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　　考生号:</div>
								<div class="edit_right">
									<input id="examinateno" type="text"
										class="easyui-validatebox l_textbox" name="examinateno"
										data-options="validType:'maxLength[20]'" value="">
								</div>
							</div>
							<div class="edit_winRow">
								<div class="edit_left">　　　身份证号:</div>
								<div class="edit_right">
									<input id="idcardno" type="text"
										class="easyui-validatebox l_textbox" name="idcardno"
										data-options="validType:'idCode'" value="">
								</div>
							</div>

						</div></td>
				</tr>

				<tr>
					<td></td>
					<td align="center"><a id="btnsummit" class="easyui-linkbutton"
						onclick="doSave()" style="height: 35px; width: 75px"><h2>确定</h2></a> &nbsp;&nbsp;&nbsp;&nbsp; <a
						id="btncancer" class="easyui-linkbutton" onclick="doClose()" style="height: 35px; width: 75px"><h2>取消</h2></a>
					</td>
					<td></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var id = '${param.id}';
	$(function() {
		//自定义easyui表单验证
		easyui_form_validator();
		//加载历史数据
		loadData();
	});

	function loadData() {
		$.post("stu/enrollTransactionAction_getEnrollTransaction.action", {
			eid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#id').val(o.id);
				$('#transactionno').val(o.transactionno);
				$('#tansactionclass').val(o.tansactionclass);
				$('#studentno').val(o.studentno);
				$('#stuname').val(o.stuname);
				if (o.sex == "0") {
					$('#sex0').attr("checked", "checked");
				} else {
					$('#sex1').attr("checked", "checked");
				}
				$('#processsymbols').val(o.processsymbols);
				$('#tansactiontype').val(o.tansactiontype);
				$('#tansactionreason').val(o.tansactionreason);
				$('#tansactiondate').datebox('setValue', formatShortDate(o.tansactiondate));
				$('#handledate').datebox('setValue', formatShortDate(o.handledate));
				$('#canceldate').datebox('setValue', formatShortDate(o.canceldate));
				$('#tansactionmemo').val(o.tansactionmemo);
				$('#zxqschool').val(o.zxqschool);
				$('#zxqgrade').val(o.zxqgrade);
				$('#zxqmajor').val(o.zxqmajor);
				$('#ydqcollege').val(o.ydqcollege);
				$('#ydqdepartment').val(o.ydqdepartment);
				$('#ydqmajor').val(o.ydqmajor);
				$('#ydqlength').val(o.ydqlength);
				$('#ydqmajorfield').val(o.ydqmajorfield);
				$('#ydqcultivatedirection').val(o.ydqcultivatedirection);
				$('#ydqclassname').val(o.ydqclassname);
				$('#ydqschoolstatus').val(o.ydqschoolstatus);
				$('#zchschool').val(o.zchschool);
				$('#zchgrade').val(o.zchgrade);
				$('#zchmajor').val(o.zchmajor);
				$('#ydhcollege').val(o.ydhcollege);
				$('#ydhdepartment').val(o.ydhdepartment);
				$('#ydhmajor').val(o.ydhmajor);
				$('#ydhlength').val(o.ydhlength);
				$('#ydhmajorfield').val(o.ydhmajorfield);
				$('#ydhcultivatedirection').val(o.ydhcultivatedirection);
				$('#ydhschoolstatus').val(o.ydhschoolstatus);
				$('#ydqgrade').val(o.ydqgrade);
				$('#ydhgrade').val(o.ydhgrade);
				$('#academicyear').val(o.academicyear);
				$('#term').val(o.term);
				$('#academicyear').val(o.academicyear);
				$('#operator').val(o.operator);
				$('#operatortime').val(o.operatortime);
				if (o.ydqinschool == "Y") {
					$('#ydqinschool0').attr("checked", "checked");
				} else {
					$('#ydqinschool1').attr("checked", "checked");
				}
				if (o.ydhinschool == "Y") {
					$('#ydhinschool0').attr("checked", "checked");
				} else {
					$('#ydhinschool1').attr("checked", "checked");
				}
				$('#ydqmajorcode').val(o.ydqmajorcode);
				$('#ydhmajorcode').val(o.ydhmajorcode);
				if (o.ydqisregiste == "Y") {
					$('#ydqisregiste0').attr("checked", "checked");
				} else {
					$('#ydqisregiste1').attr("checked", "checked");
				}
				if (o.ydhisregiste == "Y") {
					$('#ydhisregiste0').attr("checked", "checked");
				} else {
					$('#ydhisregiste1').attr("checked", "checked");
				}
				$('#memo').val(o.memo);
				$('#ydheducation').val(o.ydheducation);
				$('#ydqeducation').val(o.ydqeducation);
				$('#ydqmajorcategory').val(o.ydqmajorcategory);
				$('#ydhmajorcategory').val(o.ydhmajorcategory);
				$('#ydresult').val(o.ydresult);
				$('#studentcategory').val(o.studentcategory);
				$('#examinateno').val(o.examinateno);
				$('#idcardno').val(o.idcardno);
			} else {
				$.messager.show({
					title : '提示信息',
					msg : '数据加载失败',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		});
	}

	//表单确定提交按钮
	function doSave() {
		if ($('#editForm').form('validate')) {
			$.post("stu/enrollTransactionAction_update.action", $("#editForm")
					.serialize(), function callback(txt) {
				var X = $('#btnsummit').offset().top; 
				var Y = $('#btnsummit').offset().left; 
				var json = $.parseJSON(txt);
				if (json.status == "ok") {
					$.messager.show({
						title : '提示信息',
						msg : '保存成功',
						timeout : 3000,
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					doClose();
				} else if(json.status == "SnoOrSnameIsError"){
					$.messager.show({
						title : '提示信息!',
 //2017/4/17 余锡鸿 学籍异动模块中新增保存界面修改错误提示信息
						msg : '保存失败:学号不存在或者姓名错误!',
						showType : 'fade',
						timeout : 3000,
						style : {
							top : X-250,
        					left :Y-40,
							right : '',
							bottom : ''
						}
					});
				}else if (json.status == "IdcardnoIsError") {
					$.messager.show({
						title : '提示信息!',
						msg : '保存失败:身份证号跟姓名不一致!',
						showType : 'fade',
						timeout : 3000,
						style : {
							top : X-250,
        					left :Y-40,
							right : '',
							bottom : ''
						}
					});
				}else {
					$.messager.show({
						title : '提示信息!',
						msg : '保存失败:未知错误!',
						showType : 'fade',
						timeout : 3000,
						style : {
							top : X-250,
        					left :Y-40,
							right : '',
							bottom : ''
						}
					});
				}
			});
		}

	}

	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_edit');
	}
</script>