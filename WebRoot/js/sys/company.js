	var flag = null;//标识表单信息
	$(document).ready(function() {
			//数据表格
		$('#userMsg').datagrid({
			title:'公司管理',
			loadMsg : '数据加载中请稍后……',
			fitColumns:true,
			idField:"id",
			height:500,
			url:'companyAction_findCompanies.action',
			iconCls:'icon-search',
			striped:true,
			nowrap:true,
			rownumbers:true,
			singleSelect:true,
			columns:[[
				{
					field:'companyCode',
					title:'公司编号',
					align:'center',
					width:10
				},
				{
					field:'companyName',
					title:'公司名称',
					align:'center',
					width:10
				},
				{
					field:'address', 
					title:'公司地址',
					align:'center',
					width:10
				},
				{
					field:'telephone', 
					title:'公司电话',
					align:'center',
					width:20
				},
				{
					field:'email', 
					title:'邮箱',
					align:'center',
					width:20
				},
				{
					field:'createTime', 
					title:'创建时间',
					align:'center',
					width:10
				},
				{
					field:'fax', 
					title:'传真号码',
					align:'center',
					width:10
				},
				{
					field:'memo', 
					title:'备注',
					align:'center',
					width:20
				}
			]],
			toolbar:'#tg_toolbar',
			pagination:true,
			pageSize:10,
			pageList:[5,10,15,20],
			remoteSort:false
		});
		
		//取消按钮，关闭输入框
		$('#btncancer').click(function(){
			closeDialog();
		});
		//提交按钮，提交表单信息
		$('#btnsummit').click(function(){
			if(flag=='add'){
				if($('#userForm').form('validate')){
				$.post('companyAction_save.action',
						$('#userForm').serialize(), 
						function(result) {
						if (result) {
							$('#userMsg').datagrid('unselectAll');
							$('#userMsg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '增加公司操作成功!',
								showType:'fade',
								style:{
									 right:'',
									 bottom:''
								}
							});
							closeDialog();
						} else {
							$.messager.show({
								title : '提示信息',
								msg : '增加公司操作失败!',
								showType:'fade',
								style:{
									 right:'',
									 bottom:''
								}
							});
						}
					});
				}else{
					$.messager.show({
						title:'提示信息!' ,
						msg:'数据验证不通过,不能保存!',
						showType:'fade',
						style:{
							right:'',
							bottom:''
						}
					});
				};
			}
			if(flag=='edit'){
				if($('#userForm').form('validate')){
					$.post('companyAction_update.action', 
							$('#userForm').serialize(),
							function(result) {
							if (result) {
								$('#userMsg').datagrid('unselectAll');
								$('#userMsg').datagrid('reload');
								$.messager.show({
									title : '提示信息',
									msg : '修改公司操作成功!',
									showType:'fade',
									style:{
										 right:'',
										 bottom:''
									}
								});
								closeDialog();
							} else {
								$.messager.show({
									title : '提示信息',
									msg : '修改公司操作失败!',
									showType:'fade',
									style:{
										 right:'',
										 bottom:''
									}
								});
							}
						});
					}else{
						$.messager.show({
							title:'提示信息!' ,
							msg:'数据验证不通过,不能保存!',
							showType:'fade',
							style:{
								 right:'',
								 bottom:''
							}
						});
					};
				}
			
		});
    });	
	
	//增加用户
	 function onAdd(){
		 flag='add';
		 $('#userMsg').datagrid('unselectAll');
		 $('#dg_dia').form('clear');		 
		 $('#dg_dia').dialog({
			title:'增加公司',
			iconCls: 'icon-add'
		 });
		 openDialog();
	 }
	//编辑用户
	function onUpdate(){		
		flag='edit';
		$('#dg_dia').dialog({
			title:'编辑公司',
			iconCls: 'icon-edit'
		 });
		var  node = $('#userMsg').datagrid('getSelected');
		if(node==null){
			$.messager.show({
				title : '提示信息!',
				msg : '没有选择公司!',
				showType:'fade',
				style:{
					 right:'',
					 bottom:''
				}
			});
		}else{
			$('#dg_dia').form('clear');
			$('#dg_dia').form('load',{
				id:node.id,
				companyCode : node.companyCode,
				companyName : node.companyName,
				telephone : node.telephone,
				email : node.email,
				fax:node.fax,
				address : node.address,
				memo:node.memo
			});
			openDialog();
		}		
	}
	//删除用户
	function onDelete(){
		var  node = $('#userMsg').datagrid('getSelected');
		if(node==null){
			$.messager.show({
				title : '提示信息!',
				msg : '没有选择公司!',
				showType:'fade',
				style:{
					 right:'',
					 bottom:''
				}
			});
		}else{
			$.messager.confirm("提示信息", "确认删除?", function(r) {
				if (r) {					
					$.post('companyAction_delete.action', {
						id : node.id
					}, function(result) {
						if (result) {
							$('#userMsg').datagrid('unselectAll');
							$('#userMsg').datagrid('reload');
							$.messager.show({
								title : '提示信息',
								msg : '删除成功!',
								showType:'fade',
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
	}//--end of 删除用户
	
	//打开输入框
	function openDialog(){
		$('#dg_dia').dialog('open');
	}
	//关闭输入框
	function closeDialog(){
		$('#dg_dia').dialog('close');
	}