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
<link rel="stylesheet" type="text/css" href="css/css_form.css">
</head>

<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center'">
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
							<div class="detail_left">教师工号：</div>
							<div class="detail_right">
								<span id="employNo"></span>
							</div>
						</td>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">教师姓名：</div>
							<div class="detail_right">
								<span id="employName"></span>
							</div>
						</td>
						<td class='detailTable_td' style="width: 130;" colspan="1"
							rowspan="4"><div><img id="myphoto" alt="头像" src="" style="width: 130px;height: 160px;"></div></td>
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
							<div class="detail_left">学院名称：</div>
							<div class="detail_right">
								<span id="orgName"></span>
							</div></td>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">科室(系)：</div>
							<div class="detail_right">
								<span id="department"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">联系电话：</div>
							<div class="detail_right">
								<span id="telno"></span>
							</div></td>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">电子邮箱：</div>
							<div class="detail_right">
								<span id="email"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">教师类别：</div>
							<div class="detail_right">
								<span id="category"></span>
							</div></td>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">学历：</div>
							<div class="detail_right">
								<span id="education"></span>
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
							<div class="detail_left">学位：</div>
							<div class="detail_right">
								<span id="degree"></span>
							</div></td>
						<td class='detailTable_td' colspan="3">
							<div class="detail_left">职务：</div>
							<div class="detail_right">
								<span id="duty"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">职称：</div>
							<div class="detail_right">
								<span id="acdemictitle"></span>
							</div></td>
						<td class='detailTable_td' colspan="3">
							<div class="detail_left">可否监考：</div>
							<div class="detail_right">
								<span id="invigilatorflag"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">专业名称：</div>
							<div class="detail_right">
								<span id="major"></span>
							</div></td>
						<td class='detailTable_td' colspan="3">
							<div class="detail_left">毕业院校：</div>
							<div class="detail_right">
								<span id="graduate"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">教师资格：</div>
							<div class="detail_right">
								<span id="qualificationflag"></span>
							</div></td>
						<td class='detailTable_td' colspan="3">
							<div class="detail_left">在职状态：</div>
							<div class="detail_right">
								<span id="jobstatus"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">教师级别：</div>
							<div class="detail_right">
								<span id="teacherLevel"></span>
							</div></td>
						<td class='detailTable_td' colspan="3">
							<div class="detail_left">实验室人员：</div>
							<div class="detail_right">
								<span id="islab"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="2">
							<div class="detail_left">民族：</div>
							<div class="detail_right">
								<span id="nation"></span>
							</div></td>
						<td class='detailTable_td' colspan="3">
							<div class="detail_left">政治面貌：</div>
							<div class="detail_right">
								<span id="politicalstatus"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="5">
							<div class="detail_left">是否外聘：</div>
							<div class="detail_right">
								<span id="isouthire"></span>
							</div></td>

					</tr>
					<tr>
						<td class='detailTable_td' colspan="5">
							<div class="detail_left">研究方向：</div>
							<div class="detail_right" style="height: 80px;">
								<span id="researchdirection"></span>
							</div></td>
					</tr>
					<tr>
						<td class='detailTable_td' colspan="5">
							<div class="detail_left">简介：</div>
							<div class="detail_right" style="height: 80px;">
								<span id="introduce"></span>
							</div></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var id = '${param.id}';
	var employNo = '${param.employNo}';
	$(function() {
		//加载历史数据
		loadData();
		$("#myphoto").attr('src',"tea/teacherInfoAction_getPhotoFile.action?employNo="+employNo);
	});

	function loadData() {
		$.post("tea/teacherInfoAction_getTeacherInfo.action", {
			tid : id
		}, function callback(txt) {
			var json = $.parseJSON(txt);
			if (json.status == "ok") {
				var o = json.info;
				$('#employNo').text(o.employNo);
				$('#employName').text(o.employName);
				if (o.sex == "0") {
					$('#sex').text("男");
				} else {
					$('#sex').text("女");
				}
				var birth = formatShortDate(o.birthday);
				$('#birthday').text(birth);
				$('#orgName').text(o.orgName);
				$('#department').text(o.department);
				$('#telno').text(o.telno);
				$('#email').text(o.email);
				$('#category').text(o.category);
				$('#education').text(o.education);
				$('#degree').text(o.degree);
				$('#duty').text(o.duty);
				$('#acdemictitle').text(o.acdemictitle);
				if (o.invigilatorflag == "T") {
					$('#invigilatorflag').text("可以");
				} else {
					$('#invigilatorflag').text("不可以");
				}
				$('#major').text(o.major);
				$('#graduate').text(o.graduate);
				if (o.qualificationflag == "Y") {
					$('#qualificationflag').text("有");
				} else {
					$('#qualificationflag').text("无");
				}
				if (o.jobstatus == "Y") {
					$('#jobstatus').text("在职");
				} else {
					$('#jobstatus').text("离职");
				}
				$('#teacherLevel').text(o.teacherLevel);
				if (o.islab == "Y") {
					$('#islab').text("是");
				} else {
					$('#islab').text("否");
				}
				if (o.isouthire == "Y") {
					$('#isouthire').text("是");
				} else {
					$('#isouthire').text("否");
				}
				$('#politicalstatus').text(o.politicalstatus);
				$('#nation').text(o.nation);
				$('#researchdirection').text(o.researchdirection);
				$('#introduce').text(o.introduce);
				$('#createTime').text(formatDate(o.createTime));
				$('#creator').text(o.creator);
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
	
	//关闭窗口，提供给子页调用
	function closeWindow(id){
		$(id).window('destroy');
		location.reload();
	}
	
	function updatePhoto() {
		initWindow("dg_upload","选择头像",450,200,"tea/teacherInfoAction_viewUpdatePhoto.action?employNo="+employNo); 
	}
</script>

