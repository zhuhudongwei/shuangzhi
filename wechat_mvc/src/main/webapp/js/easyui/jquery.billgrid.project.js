/**
 * $('#dg').billgrid({
 *   createUrl:'',
 *   editUrl:'',
 *   destroyUrl:''
 * });
 */
(function($){
	 $.extend($.fn.validatebox.defaults.rules, {
        /*必须和某个字段相等*/
        equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
     });
	
	function buildGrid(target, options){
		var opts = $.extend({}, {
			onDblClickRow:function(){
				$(target).billgrid('edit');
			}
		}, options);
		$(target).datagrid(opts);
	}
	
	var methods = {
			create_project: function(jq){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				window.location.href = opts.createUrl;
			});
		},
		maintenance_project: function(jq){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
			    if (row){
			    	var status = row.state;
			    	if(status == 2){
						$.messager.show({
							title:'警告',
							msg:'此项目已关闭，不能修改阶段。'
						});
					}else{
						$('#dlg').dialog('open').dialog('setTitle','修改项目阶段');
						$('#pid').val(row.projectId);
						$('#shortName').val(row.shortName);
						$('#projectName').val(row.projectName);
						$('#stepName').val(row.currentStepName);
						$('.combo-value').val(row.currentStepId);
					}
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择要修改的项目。'
					});
				}
			});
		},
		edit_project: function(jq){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					window.location.href = opts.editUrl+"?projectId="+row.projectId;
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择要编辑的数据。'
					});
				}
			});
		},
		view_project: function(jq){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					window.location.href = opts.detailUrl+"?projectId="+row.projectId;
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择要查看的数据。'
					});
				}
			});
		},
		close_project: function(jq){
			return jq.each(function(){
				var dg = $(this);
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					if(row.state == 2){ //如果数据已经是关闭状态，那么提示已经关闭
						$.messager.show({
							title:'警告',
							msg:'该数据已经是不活动状态。'
						});
					} else{
						$.messager.defaults = { ok: "确定", cancel: "取消" };
						$.messager.confirm('警告','是否真的关闭该数据？',function(r){
							if (r){
								$.post(opts.closeUrl, {projectId:row.projectId}, function(result){
									if (result.errMsg == null ||result.errMsg == ""){
										dg.datagrid({url:opts.repeatUrl});
										$.messager.show({
											title:'提示',
											msg:'关闭成功。'
										});
										//设置分页控件
									     var pager = dg.datagrid('getPager'); //获得分页对象
									     $(pager).pagination({   
									          beforePageText: '第', //页数文本框前显示的汉字
											  afterPageText: '页   共 {pages}页',   
									          displayMsg: '当前显示 {from} - {to} 条记录  共{total} 条记录'
									      });
									} else {
										$.messager.show({
											title:'警告',
											msg:'关闭失败。'
										});
									}
								});
							}
						});
			
					}
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择要关闭的数据。'
					});
				}
			});
		},
		open_project: function(jq){
			return jq.each(function(){
				var dg = $(this);
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					if(row.state == 1){ //如果数据已经是活动状态，那么提示已经开启
						$.messager.show({
							title:'警告',
							msg:'该数据已经是活动状态。'
						});
					} else{
						$.messager.defaults = { ok: "确定", cancel: "取消" };
						$.messager.confirm('警告','是否真的开启该数据？',function(r){
							if (r){
								$.post(opts.openUrl, {projectId:row.projectId}, function(result){
									if (result.errMsg == null ||result.errMsg == ""){
										dg.datagrid({url:opts.repeatUrl});
										$.messager.show({
											title:'提示',
											msg:'开启成功。'
										});
										//设置分页控件
									     var pager = dg.datagrid('getPager'); //获得分页对象
									     $(pager).pagination({   
									          beforePageText: '第', //页数文本框前显示的汉字
											  afterPageText: '页   共 {pages}页',   
									          displayMsg: '当前显示 {from} - {to} 条记录  共{total} 条记录'
									      });
									} else {
										$.messager.show({
											title:'警告',
											msg:'开启失败。'
										});
									}
								});
							}
						});
					}
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择要开启的数据。'
					});
				}
			});
		}
	};
	
	$.fn.billgrid = function(options, param){
		if (typeof options == 'string'){
			var method = methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.datagrid(options, param);
			}
		}
		
		options = options || {};
		return this.each(function(){
			buildGrid(this, options);
		});
	};
})(jQuery);