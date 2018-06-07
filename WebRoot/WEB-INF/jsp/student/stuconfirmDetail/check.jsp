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
			<div>
				<div class="edit_winrow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程代码:</div>
					<div class="edit_right">
						<input type="text" class="easyui-validatebox l_textbox"
							id="studentno" name="studentno"
							data-options="" value="">
					</div>
				</div>

				<div class="edit_winrow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程名字:</div>
					<div class="edit_right">
						<input type="text" id="stuname" name="stuname"
							class="easyui-validatebox l_textbox"
							data-options="" value="">
					</div>
				</div>

				<div class="edit_winrow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程性质:</div>
					<div class="edit_right">
						<input id="classname" type="text"
							class="easyui-validatebox l_textbox" name="classname"
							data-options="" value="">
					</div>
				</div>
				<div class="edit_winrow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预定时间:</div>
					<div class="edit_right">
					<div class="col-xs-3">
						<div class="form-group">
							<input type="text" value="Disabled" disabled="disabled"
								class="form-control" />
						</div>
					</div>
				</div>
				</div>
				<%-- /**
       * 何子健
       * 在原有的模块上增加一些添加信息
       * @type {String}
       */ --%>
				<div class="edit_winRow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否选上:</div>
					<div class="edit_right">
						<input id="academicyear" type="text"
							class="easyui-validatebox l_textbox" name="academicyear"
							data-options="" value="">
					</div>
				</div>
				<%-- 结束 --%>
				<div class="edit_winRow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预定时间:</div>
					<div class="edit_right">
						<input id="academicyear" type="text"
							class="easyui-validatebox l_textbox" name="academicyear"
							data-options="" value="">
					</div>
				</div>
			</div>
			<div>
				<div class="edit_winRow">
					<div class="edit_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未选上原因:</div>
					<div class="edit_right">
						<input id="academicyear" type="text"
							class="easyui-validatebox l_textbox" name="academicyear"
							data-options="" value="">
					</div>
				</div>
			</div>
			<%-- end --%>
			<div class="edit-line"></div>
			<div class="space_flow_20"></div>
			<div class="edit_btn">
				<a id="btncancer" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="doClose()">取消</a>
			</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function doClose() {
		//关闭窗口
		window.parent.closeWindow('#dg_check');
	}
</script>
