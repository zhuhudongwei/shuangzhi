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
		create_customer: function(jq){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				window.location.href = opts.createUrl;
			});
		},
		edit_customer: function(jq){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					window.location.href = opts.editUrl+"?customerId="+row.customerId+"&flag="+1;
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择要编辑的数据。'
					});
				}
			});
		},
		destroy_customer: function(jq){
			return jq.each(function(){
				var dg = $(this);
				var opts = dg.datagrid('options');
				var row = dg.datagrid('getSelected');
				if (row){
					$.messager.defaults = { ok: "确定", cancel: "取消" };
					$.messager.confirm('警告','是否真的删除该数据？',function(r){
						if (r){
							$.post(opts.destroyUrl, {customerId:row.customerId}, function(result){
								if (result.successMsg){
									dg.datagrid({url:opts.repeatUrl});
									$.messager.show({
										title:'提示',
										msg:'删除成功。'
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
										msg:result.errMsg
									});
								}
							});
						}
					});
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择数据后再进行删除。'
					});
				}
			});
		},
		view_customer: function(jq){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					window.location.href = opts.detailUrl+"?customerId="+row.customerId;
				} else {
					$.messager.show({
						title:'警告',
						msg:'请先选择要查看的数据。'
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