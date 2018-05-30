		//点击修改密码弹出框
		function updateUserPwd() {
			$('#PwdForm').form('clear');
			$('#pwd_div').dialog('open');
		}
		
		//确认修改密码
		function pwdForm() {
			if ($('#PwdForm').form('validate')) {
				$.post('userAction_userUpdatePwd', serializeForm($('#PwdForm')),
					function(result) {
						if (result == "ok") {
							alert("修改密码成功!");
							location.reload();
						} else {
							alert("原密码错误或者新密码不一致，修改失败!");
						}
					});

			} else {
				$.messager.show({
					title : '提示信息!',
					msg : '数据验证不通过，不能保存!',
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}
		}
		//取消按钮，关闭弹出框
		function btncancer() {
			$('#user_div').dialog('close');
			$('#pwd_div').dialog('close');
		}
		
		//js方法：序列化表单 			
		function serializeForm(form) {
			var obj = {};
			$.each(form.serializeArray(), function(index) {
				if (obj[this['name']]) {
					obj[this['name']] = obj[this['name']] + ',' + this['value'];
				} else {
					obj[this['name']] = this['value'];
				}
			});
			return obj;
		}
		
		//打开上传图片对话框
		function upPhoto() {
			$('#up_photoForm').form('clear');
			$('#up_photo').dialog('open');
		}