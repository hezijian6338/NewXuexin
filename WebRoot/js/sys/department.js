var flag ; //判断走的是保存还是修改方法
		var myUrl = 'departmentAction_getAll.action';
		$(function(){		
			//公司下拉框
			
			$('#cc').combobox({
				url:'companyAction_companiesToCombobox.action',
				valueField:'id',   
				textField:'companyName',
				editable:false
			});
			$('#cc').click(function(){
				$('#cc').combobox('reload');
			});
		
			$('#tg').treegrid({
				loadMsg : '数据加载中请稍后……',
				url: myUrl,
				lines: true,
				iconCls: 'icon-search',
				rownumbers: true,
				animate: true,
				nowrap: false,
				height:500,
				fitColumns: true,
				method: 'post',
				idField: 'id', //数据表格要有主键
				treeField: 'deptName',//树形结构主键
				toolbar: '#tg_toolbar',//数据表工具栏
				onContextMenu: function(e,row){
					e.preventDefault(); //屏蔽浏览器的菜单
					$(this).treegrid('unselectAll');//清除所有选中项
					$(this).treegrid('select',row.id);
					$('#op_menu').menu('show',{
						left: e.pageX,
						top:e.pageY
					});
				}
			});
			
			$('#btncancer').click(function(){
				$('#tg_dd').dialog('close');
			});
			//表单确定提交按钮
			$('#btnsummit').click(function(){
			if($('#myform').form('validate')){
				//alert($('#myform').find('input[name=companyID]').val());
			if(flag == 'add'){				
				//保存方法
				//1、前台保存 注意: 没有保存id
				var node=$('#tg').treegrid('getSelected');
				if(node==null){
				//2、后台保存
				$.ajax({
					type:'post',
					url:'departmentAction_save.action',
					cache:false,
					dataType:'json',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',  
					data:{
						pid:-1,
						deptNo:$('#myform').find('input[name=deptNo]').val(),
						deptName:$('#myform').find('input[name=deptName]').val(),
						companyID:$('#myform').find('input[name=companyID]').val(),
						memo:$('#myform').find('textarea[name=memo]').val()
					},
					success:function(result){
						//刷新节点 : 刷新当前选中节点		
						$('#tg').treegrid('reload');
						$.messager.show({
							title:'提示信息',
							msg:'添加新部门成功！',
							showType:'fade',
							style:{
								 right:'',
								 bottom:''
							}
						});
						
					}
				});
				
				}else{
					$('#tg').treegrid('append',{
					parent:node.id,
					data:[{
						deptNo:$('#myform').find('input[name=deptNo]').val(),
						pid:node.id,
						deptName:$('#myform').find('input[name=deptName]').val(),
						companyID:$('#myform').find('input[name=companyID]').val(),
						memo:$('#myform').find('textarea[name=memo]').val()
					}]
				});
				//2、后台保存
				$.ajax({
					type:'post',
					url:'departmentAction_save.action',
					cache:false,
					dataType:'json',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',  
					data:{
						pid:node.id,
						deptNo:$('#myform').find('input[name=deptNo]').val(),
						deptName:$('#myform').find('input[name=deptName]').val(),
						companyID:$('#myform').find('input[name=companyID]').val(),
						memo:$('#myform').find('textarea[name=memo]').val()
					},
					success:function(result){
						//刷新节点 : 刷新当前选中节点		
						$('#tg').treegrid('reload',node.id);
						$.messager.show({
							title:'提示信息',
							msg:'添加子部门成功！',
							showType:'fade',
							style:{
								 right:'',
								 bottom:''
							}
						});
						
					}
				});
				}
				
				//3、关闭窗口
				$('#tg_dd').dialog('close');
				
			}else{
			alert($('#myform').find('input[name=companyID]').val());
				//后台更新
				$.ajax({
					type:'post',
					url:'departmentAction_update.action',
					cache:false,
					dataType:'json',
					data:{
						id:$('#myform').find('input[name=id]').val(),
						pid:$('#myform').find('input[name=pid]').val(),
						deptNo:$('#myform').find('input[name=deptNo]').val(),
						deptName:$('#myform').find('input[name=deptName]').val(),
						companyID:$('#myform').find('input[name=companyID]').val(),
						memo:$('#myform').find('textarea[name=memo]').val()
					},
					success:function(result){
						//刷新节点  :如果当前选中的节点是叶子节点的话,刷新该节点的父亲 ,如果不是叶子节点,刷新当前选中节点即可
						var node = $('#tg').treegrid('getSelected');
						var pid= $('#myform').find('input[name=pid]').val();
						if(pid!='-1'){
						var parent = $('#tg').treegrid('getParent' , node.id);
						$('#tg').treegrid('reload' , parent.id);
						}else{
						$('#tg').treegrid('reload');
						}
						$.messager.show({
							title:'提示信息',
							msg:'更新成功！',
							showType:'fade',
							style:{
								 right:'',
								 bottom:''
							}
						});
						
					}
				});
				//3、关闭窗口
				$('#tg_dd').dialog('close');
			}
			}else{
				$.messager.show({
							title:'提示信息',
							msg:'信息填写不完整！',
							showType:'fade',
							style:{
								 right:'',
								 bottom:''
							}
						});
			}	
			});
		
			
			
		});
		
		function collapseAll(){
			$('#tg').treegrid('collapseAll');
		}
		function expandAll(){
			$('#tg').treegrid('expandAll');
		}
		
		function onAdd(){
			flag='add';
			//1、清空表单数据
			$('#myform').form('clear');
			$('#tg_dd').dialog('open');	
		}
		
		function onUpdate(){
			flag='edit';
			$('#myform').form('clear');
			//2填充表单回显数据
			var  node  = $('#tg').treegrid('getSelected');
			if(node==null){
				$.messager.show({
							title:'提示信息',
							msg:'没有选择部门！',
							showType:'fade',
							style:{
								 right:'',
								 bottom:''
							}
						});
			}else{
				$('#myform').form('load',{
					id:node.id,
					pid:node.pid,
					deptNo:node.deptNo,
					deptName:node.deptName,
					companyID:node.companyID,
					memo:node.memo
				});	
				$('#tg_dd').dialog('open');
			}				
		}
		
		function onDelete(){
			var  node = $('#tg').treegrid('getSelected');
			if(node==null){
				$.messager.show({
							title:'提示信息',
							msg:'没有选择部门！',
							showType:'fade',
							style:{
								 right:'',
								 bottom:''
							}
						});
			}else{
					$.messager.confirm("提示信息","确认删除?",function(r){
					if(r){
						// 1前台删除
						var  node  = $('#tg').treegrid('getSelected');
						$('#tg').treegrid('remove',node.id);
						// 2后台删除 
						$.post('departmentAction_delete.action', {id:node.id}, function(result){
						if(result){				
							$('#tg').treegrid('unselectAll');
							$('#tg').treegrid('reload');
							$.messager.show({
								title:'提示信息',
								msg:'删除成功!',
								showType:'fade',
								style:{
									 right:'',
									 bottom:''
								}
							});
						}else{
							$.messager.show({
								title:'提示信息',
								msg:'删除失败!',
								showType:'fade',
								style:{
									 right:'',
									 bottom:''
								}
							});
						}							
						});
					} else {
					return ;
					}				
				});
			}			
		}
		