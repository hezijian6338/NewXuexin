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

<title>学生政审资料管理</title>
<%@ include file="/pub/headmeta.jsp"%>
<script type="text/javascript" src="js/common/windowmodal.js"></script>
<link rel="stylesheet" type="text/css" href="css/css_form.css">
</head>

<body>

	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center'">
			<div id="easyui-tabs" class="easyui-tabs">
				<div title="个人基本信息" style="overflow:hidden;">
					<table id="dg" style="margin: 15;" width="750" align="center"
						cellpadding="0" cellspacing="0" class="detailTable">
						<thead id='thead'>
							<tr>
								<th class='detailTable_th' colspan="5">个人信息详情</th>
							</tr>
						</thead>
						<tbody id="tdbody">
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">学号：</div>
									<div class="detail_right">
										<span id="studentno"></span>
									</div>
								</td>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">姓名：</div>
									<div class="detail_right">
										<span id="stuname"></span>
									</div>
								</td>
								<td class='detailTable_td' style="width: 130;" colspan="1"
									rowspan="4"><div>
										<img id="myphoto" alt="头像" src=""
											style="width: 130px;height: 160px;">
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">性别：</div>
									<div class="detail_right">
										<span id="sex"></span>
									</div></td>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">出生日期：</div>
									<div class="detail_right">
										<span id="birthday"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">政治面貌：</div>
									<div class="detail_right">
										<span id="politicalstatus"></span>
									</div></td>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">民族：</div>
									<div class="detail_right">
										<span id="nation"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">籍贯：</div>
									<div class="detail_right">
										<span id="nativeplace"></span>
									</div></td>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">身份证号码：</div>
									<div class="detail_right">
										<span id="idcardno"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">学院名称：</div>
									<div class="detail_right">
										<span id="orgName"></span>
									</div></td>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">系：</div>
									<div class="detail_right">
										<span id="department"></span>
									</div></td>
								<td class='detailTable_td' colspan="1" align="center">
									<div>
										<power:Edit>
											<a href="javascript:void(0)" class="easyui-linkbutton"
												data-options="iconCls:'icon-edit',plain:true"
												onclick="updatePhoto()">编辑头像</a>
										</power:Edit>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">专业名称：</div>
									<div class="detail_right">
										<span id="major"></span>
									</div></td>
								<td class='detailTable_td' colspan="3">
									<div class="detail_left">专业方向：</div>
									<div class="detail_right">
										<span id="majorfield"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">专业类别：</div>
									<div class="detail_right">
										<span id="majorcategories"></span>
									</div></td>
								<td class='detailTable_td' colspan="3">
									<div class="detail_left">培育方向：</div>
									<div class="detail_right">
										<span id="cultivatedirection"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">行政班：</div>
									<div class="detail_right">
										<span id="classname"></span>
									</div></td>
								<td class='detailTable_td' colspan="3">
									<div class="detail_left">学制：</div>
									<div class="detail_right">
										<span id="educationsystem"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">学习年限：</div>
									<div class="detail_right">
										<span id="schoolinglength"></span>
									</div></td>
								<td class='detailTable_td' colspan="3">
									<div class="detail_left">入学日期：</div>
									<div class="detail_right">
										<span id="acceptancedate"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">毕业中学：</div>
									<div class="detail_right">
										<span id="middleschool"></span>
									</div></td>
								<td class='detailTable_td' colspan="3">
									<div class="detail_left">本人电话：</div>
									<div class="detail_right">
										<span id="mobileno"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">家庭电话：</div>
									<div class="detail_right">
										<span id="familytelno"></span>
									</div></td>
								<td class='detailTable_td' colspan="3">
									<div class="detail_left">邮政编码：</div>
									<div class="detail_right">
										<span id="postcode"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="2">
									<div class="detail_left">DQSZJ：</div>
									<div class="detail_right">
										<span id="dqszj"></span>
									</div></td>
								<td class='detailTable_td' colspan="3">
									<div class="detail_left">毕业审核标志：</div>
									<div class="detail_right">
										<span id="graduateflag"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="5">
									<div class="detail_left">乘车区间：</div>
									<div class="detail_right">
										<span id="travelrange"></span>
									</div></td>

							</tr>
							<tr>
								<td class='detailTable_td' colspan="5">
									<div class="detail_left">来源地区：</div>
									<div class="detail_right" style="height: 80px;">
										<span id="fromPlace"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="5">
									<div class="detail_left">家庭地址：</div>
									<div class="detail_right" style="height: 80px;">
										<span id="address"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="5">
									<div class="detail_left">特长：</div>
									<div class="detail_right" style="height: 80px;">
										<span id="skill"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="5">
									<div class="detail_left">简介：</div>
									<div class="detail_right" style="height: 80px;">
										<span id="selfdescription"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="5">
									<div class="detail_left">所获奖励：</div>
									<div class="detail_right" style="height: 80px;">
										<span id="awards"></span>
									</div></td>
							</tr>
							<tr>
								<td class='detailTable_td' colspan="5">
									<div class="detail_left">学籍状态：</div>
									<div class="detail_right" style="height: 80px;">
										<span id="schoolstatus"></span>
									</div></td>
							</tr>
							<tr>

							</tr>
						</tbody>
					</table>
				</div>
				<c:if test="${!isStu }">
				<div title="选课成绩明细" style="overflow:hidden;">
					<iframe id='daoxue_iframe' frameborder='0'
						style='width:100%;height:100%;'> </iframe>
				</div>
				<div title="学习经历" style="overflow:hidden;">
					<iframe frameborder='0' style='width:100%;height:100%;'> </iframe>
				</div>
				<!-- <div title="家庭信息" style="overflow:hidden;">
					<iframe frameborder='0' style='width:100%;height:100%;'> </iframe>
				</div> -->
				<div title="党团关系记录" style="overflow:hidden;">
					<iframe frameborder='0' style='width:100%;height:100%;'> </iframe>
				</div>
				<div title="贷款记录" style="overflow:hidden;">
					<iframe frameborder='0' scrolling="yes" style='width:100%;height:100%;'> </iframe>
				</div>
				
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var stuId = '${param.stuId}';
	var studentno = '${param.studentno}';
	$(function() {
		//加载历史数据
		loadData();
		$("#myphoto")
				.attr(
						'src',
						${isStu?"'stu/studentsAction_getSelfPhotoFile.action'":"'stu/studentsAction_getPhotoFile.action?studentno='+ studentno"}
						);
		$('#easyui-tabs').tabs({
			onSelect : function(title) {
				var url = "";
				if (title == "选课成绩明细") {
					url = "stu/studentsAction_detailCourse.action";
				} else if (title == "学习经历") {
					url = "stu/studentsAction_detailExperience.action";
				} else if (title == "家庭信息") {
					url = "";
				} else if (title == "党团关系记录") {
					url = "stu/studentsAction_detailPolical.action";
				} else if (title == "贷款记录") {
					url = "stu/studentsAction_detailLoan.action";
				}
				var p = $(this).tabs('getTab', title);
				p.find('iframe').attr('src', url);
			}
		});
	});

	function loadData() {
		$.post('${isStu?"stu/studentsAction_getStudentSelf.action":"stu/studentsAction_getStudent.action"}', {
			stuId : stuId
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#stuId').text(stuId);
				$('#studentno').text(o.studentno);
				$('#stuname').text(o.stuname);
				$('#sex').text(o.sex);
				var birth = formatShortDate(o.birthday);
				$('#birthday').text(birth);
				$('#nation').text(o.nation);
				$('#politicalstatus').text(o.politicalstatus);
				$('#nativeplace').text(o.nativeplace);
				$('#fromPlace').text(o.fromPlace);
				$('#idcardno').text(o.idcardno);
				$('#orgName').text(o.orgName);
				$('#department').text(o.department);
				$('#major').text(o.major);
				$('#majorfield').text(o.majorfield);
				$('#majorcategories').text(o.majorcategories);
				$('#cultivatedirection').text(o.cultivatedirection);
				$('#classname').text(o.classname);
				$('#educationsystem').text(o.educationsystem);
				$('#schoolinglength').text(o.schoolinglength);
				var accept = formatShortDate(o.acceptancedate);
				$('#acceptancedate').text(accept);
				$('#middleschool').text(o.middleschool);

				$('#mobileno').text(o.mobileno);
				$('#familytelno').text(o.familytelno);
				$('#postcode').text(o.postcode);
				$('#travelrange').text(o.travelrange);
				$('#address').text(o.address);
				$('#skill').text(o.skill);
				$('#selfdescription').text(o.selfdescription);
				$('#awards').text(o.awards);
				$('#schoolstatus').text(o.schoolstatus);
				$('#dqszj').text(o.dqszj);
				$('#graduateflag').text(o.graduateflag);
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
	}

	//关闭窗口，提供给子页调用
	function closeWindow(id) {
		$(id).window('destroy');
		location.reload();
	}
	//提供给子页调用
	function getStudentno() {
		return studentno;
	}

	function updatePhoto() {
		initWindow("dg_upload", "选择头像", 450, 400,
				"stu/studentsAction_viewUpdatePhoto.action?studentno="
						+ studentno);
	}
</script>

