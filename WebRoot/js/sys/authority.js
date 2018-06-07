		var flag ; //判断走的是保存还是修改方法
		var myUrl = 'sys/authorityAction_getAll.action';
		$(function(){		
			$('#tg').treegrid({
				loadMsg : '数据加载中请稍后……',
				url: myUrl,
				lines: true,
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				nowrap: false,
				collapsible: true,
				fitColumns: true,
				method: 'post',
				idField: 'id', //数据表格要有主键
				treeField: 'authorityName',//树形结构主键
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

			$('#op_menu').hide();
			setDialog('#tg_dd');//设置dialog显示样式,来自common.js
			$('#btncancer').click(function(){
				$('#tg_dd').dialog('close');
			});
			//表单确定提交按钮
			$('#btnsummit').click(function(){
			if($('#myform').form('validate')){
			if(flag == 'add'){
				//保存方法
				//1、前台保存 注意: 没有保存id
				var node=$('#tg').treegrid('getSelected');
				if(node==null){
				//2、后台保存
				$.ajax({
					type:'post',
					url:'sys/authorityAction_save.action',
					cache:false,
					dataType:'json',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',  
					data:{
						pid:-1,
						authorityName:$('#myform').find('input[name=authorityName]').val(),
						flag:$('#myform').find('input[name=flag]').val(),
						url:$('#myform').find('input[name=url]').val(),
						memo:$('#myform').find('textarea[name=memo]').val()
					},
					success:function(result){
						//刷新节点 : 刷新当前选中节点	
						$('#tg').treegrid('reload');
						$.messager.show({
							title:'提示信息',
							msg:result.message,
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
						authorityName:$('#myform').find('input[name=authorityName]').val(),
						pid:node.id,
						flag:$('#myform').find('input[name=flag]').val(),
						url:$('#myform').find('input[name=url]').val(),
						memo:$('#myform').find('textarea[name=memo]').val()
					}]
				});
				//2、后台保存
				$.ajax({
					type:'post',
					url:'sys/authorityAction_save.action',
					cache:false,
					dataType:'json',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',  
					data:{
						pid:node.id,
						authorityName:$('#myform').find('input[name=authorityName]').val(),
						flag:$('#myform').find('input[name=flag]').val(),
						url:$('#myform').find('input[name=url]').val(),
						memo:$('#myform').find('textarea[name=memo]').val()
					},
					success:function(result){
						//刷新节点 : 刷新当前选中节点		
						$('#tg').treegrid('reload',node.id);
						$.messager.show({
							title:'提示信息',
							msg:result.message,
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
			
				//后台更新
				$.ajax({
					type:'post',
					url:'sys/authorityAction_update.action',
					cache:false,
					dataType:'json',
					data:{
						id:$('#myform').find('input[name=id]').val(),
						pid:$('#myform').find('input[name=pid]').val(),
						authorityName:$('#myform').find('input[name=authorityName]').val(),
						flag:$('#myform').find('input[name=flag]').val(),
						url:$('#myform').find('input[name=url]').val(),
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
							msg:result.message,
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
			
		} else {				
			$.messager.show({
			title:'提示信息!' ,
			msg:'数据验证不通过,不能保存!',
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
			var arr = $('#tg').datagrid('getSelections');
			if (arr.length != 1) {
			$('#tg').datagrid('unselectAll');
			$.messager.show({
				title : '提示信息!',
				msg : '只能选择单个权限编辑!',
				showType:'fade',
						style:{								
						right:'',
						bottom:''
					}
			});
		} else {
			$('#myform').form('clear');
			//2填充表单回显数据
			var  node  = $('#tg').treegrid('getSelected');
			$('#myform').form('load',{
				id:node.id,
				pid:node.pid,
				authorityName:node.authorityName,
				flag:node.flag,
				url:node.url,
				memo:node.memo
			});	
			$('#tg_dd').dialog('open');	
			}
		}
		
		function onDelete(){
		var arr = $('#tg').datagrid('getSelections');
		if (arr.length <= 0) {
			$.messager.show({
				title : '提示信息!',
				msg : '请选择角色!',
				showType:'fade',
						style:{								
						right:'',
						bottom:''
					}
			});
		} else {
			$.messager.confirm("提示信息","确认删除?",function(r){
				if(r){
					// 1前台删除
					var  node  = $('#tg').treegrid('getSelected');
					$('#tg').treegrid('remove',node.id);
					// 2后台删除 
					$.post('sys/authorityAction_delete.action', {id:node.id}, function(result){
					if(result=="ok"){				
						$('#tg').treegrid('unselectAll');
						$('#tg').treegrid('reload');
						$.messager.show({
							title:'提示信息',
							msg:'删除权限成功!',
							showType:'fade',
							style:{
								right:'',
								bottom:''
							}
						});
					}else{
						$.messager.show({
							title:'提示信息',
							msg:'删除权限失败!',
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
		
		function onFind(){
			var authorityName = $('#findform').find('input[name=authorityName]').val();
			var flag = $('#findform').find('input[name=flag]').val();
			
			if(authorityName!="" || flag!=""){
				$.post('sys/authorityAction_find.action', {authorityName:authorityName,flag:flag}, function(result){
					if(result!=""){
						$('#tg').treegrid('loadData',result);
					}else{
						
						$.messager.show({
							title:'提示信息',
							msg:'查询失败,或信息不存在'+result,
							showType:'fade',
							style:{
								right:'',
								bottom:''
							}
						});
						//alert("结果"+result);
					}								
				},'json');
			}else {
				alert("请输入查询条件");
				return ;
			}
		}
		function clearForm() {
			$('#findform').form('clear');
			$('#tg').treegrid('reload');
		}
		function reject() {
		$('#tg').datagrid('unselectAll');
		}