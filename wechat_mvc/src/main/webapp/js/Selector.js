(function($){
		$.fn.Selectors = {
				/*
				 * _id:{
				 * 	_opts:_opts,
				 * 	selectIds:[],
				 * 	selectedValue:{}
				 * }
				 * 
				 */
		};
		$.fn.Selector = function(option, param){
			var $this = $(this);
			var _id = $this.attr("id");
			if("getValueById" == option){
				var _v = 0;
				var _select_id = "select_"+param+"_"+_id;
				var s = $("#"+_select_id);
				if(s.size() > 0){
					_v = s.val();
					if(/[0-9]+/.test(_v)){
						_v = parseInt(_v);
					}
				}
				return _v;
			}
			var count = 1;
			var _t = 100;
			var setSelectValue = function(_select_id, val, t){
				var obj = $("#"+_select_id);
				if(val && obj && ($("option", obj).size() > 0)){
					obj.val(val);
					obj.change();
				}else{
					return;
				}
				if(t > 50){
					return;
				}
				if($("option[value="+val+"]", obj).size() > 0){
					return;
				}
				window.setTimeout(function(){
					var t2 = (t+1);
					setSelectValue(_select_id, val, t2);
				},_t*t);
			};
			if("setValueById" == option){
				for(var i in param){
					var _select_id = ("select_"+i+"_"+_id);
					var val = param[i];
					setSelectValue(_select_id, val, count);
				}
				return 0;
			}
			var _opts = $.extend({},$.fn.Selector.defaults, option);
			_opts.groupId = _id;
			var _selectIds = [];
			$.fn.Selectors[_id] = {
				_opts:_opts,
				selectIds:_selectIds
			}
			$this.hide();
			var _select_index = 0;
			for(var i in _opts.cfg){
				var _select_cfg = _opts.cfg[i];
				var _select_id = "select_"+_select_cfg.id+"_"+_id;
				_selectIds.push(_select_id);
				var _select_name = "";
				if(_select_cfg.selectName){
					_select_name += (" name='"+_select_cfg.selectName+"' ");
				}
				var _select = $("<select id='"+_select_id+"' " + _select_name + " si='"+_select_index+"'></select>");
				if(!_opts.viewAll && _select_index > 0 ){
					_select.hide();	
				}
				if(_select_index == 0 ){
					var url = _opts.url;
					if(_opts.cfg[0].url){
						url = _opts.cfg[0].url;
					}
					$.postJSON(url,{},function(data){
						var _objData = $.fn.Selector.getData(data, _id, 0);
						var _select_id = $.fn.Selectors[_id].selectIds[0];
						var _select_this = $("#"+_select_id);
						var _str = "";
						for(var i in _objData){
							_select_this.append("<option value='"+_objData[i][_opts.cfg[0].value]+"'>"+_objData[i][_opts.cfg[0].title]+"</option>");
							_str += _objData[i][_opts.cfg[0].title];
						}
						_select_this.clone(true).insertAfter(_select_this);//("<select id='"+_select_id+"' si='"+_select_this.attr("si")+"'>"+_select_this.html()+"</select>");
						_select_this.remove();
						//$("#"+_select_id).change(function(){$.fn.Selector._setSelectValue($(this));});
					});
				}
				_select_index ++;
				if(_opts.showDefault){
					var _defaultTitle = _opts.defaultTitle?_opts.defaultTitle:"";
					var _defaultValue = _opts.defaultValue?_opts.defaultValue:"";
					if(_select_cfg.defaultTitle){
						_defaultTitle = _select_cfg.defaultTitle;
						if(_select_cfg.defaultValue){
							_defaultValue = _select_cfg.defaultValue;
						}
					}
					_opts.useDefault = true;
					_select.append("<option value='"+_defaultValue+"'>"+_defaultTitle+"</option>");
				}else{
					_opts.useDefault = false;
				}
				_select.change(function(){$.fn.Selector._setSelectValue($(this));});
				$this.before(_select);
			}
			return this;
		};
		$.fn.Selector.defaults = {
				url: "",
				groupId:0,
				root:"",
				defaultTitle:"请选择",
				defaultValue:0,
				showDefault:false,
				superiorName:"",
				levelName:"levelId",
				cfg:[{
					id:"",
					name:"",
					url:"",
					title:"",
					value:"",
					level:0,
					selectName:"",
					defaultTitle:"",
					defaultValue:0,
					onchange:function(){alert(3);}
				}],
				viewAll:false
		};
		$.fn.Selector.getData = function (data, _id, index){
			var rootData = data;
			var _opts;
			if(!!$.fn.Selectors[_id]){
				_opts = $.fn.Selectors[_id]._opts;
			}
			if(_opts && _opts.root){
				rootData = rootData[_opts.root];
			}
			var obj = _opts.cfg[index];
			var objData = rootData[obj.name];
			return objData;
		}
		$.fn.Selector._setSelectValue = function(_this){
			var _select_id = _this.attr("id");
			var _groupId = $.fn.Selector._getGroupId(_select_id);
			var _selector = $.fn.Selectors[_groupId];
			
			var _index = _this.attr("si");
			if(_index){
				_index = parseInt(_index);
			}else{
				_index = 0;
			}
			
			var _opts = _selector._opts;
			var url = _opts.url;
			if(_opts.cfg[_index].url){
				url = _opts.cfg[_index].url;
			}
			var _selectIds = _selector.selectIds;
			var _next_index = (_index+1);
			if(_selectIds.length > _next_index){
				var param = {};
				var value = _this.val();
				if(_opts.superiorName){
					param[_opts.superiorName] = value;
				}else if(_opts.cfg[_index].value){
					param[_opts.cfg[_index].value] = value;
				}
				param[_opts.levelName] = _next_index;
				var _st = -1;
				if(_opts.useDefault){
					_st = 0;
				}
				for(var j = _next_index; j < _selectIds.length; j ++){
					var _ovObj = $("#"+_selectIds[j]);
					$("option:gt("+_st+")", _ovObj).remove();
					if(j==_next_index){
						_ovObj.show();
					}
				}
				if(_next_index > 0 && param[_opts.cfg[_index].value]){
					$.postJSON(url,param,function(data){
						var _objData = $.fn.Selector.getData(data, _groupId, _next_index);
						var _select_id = $.fn.Selectors[_groupId].selectIds[_next_index];
						var _select_this = $("#"+_select_id);
						for(var i in _objData){
							_select_this.append("<option value='"+_objData[i][_opts.cfg[_next_index].value]+"'>"+_objData[i][_opts.cfg[_next_index].title]+"</option>");
						}
						_select_this.clone(true).insertAfter(_select_this);//("<select id='"+_select_id+"' si='"+_select_this.attr("si")+"'>"+_select_this.html()+"</select>");
						_select_this.remove();
						//$("#"+_select_id).change(function(){$.fn.Selector._setSelectValue($(this));});
					});
				}
			}
		};
		$.fn.Selector._getGroupId = function(selectId){
			if(selectId){
				var _selectIds = selectId.split("_");
				if(_selectIds.length == 3){
					return _selectIds[2];
				}
			}
			return 0;
		};
	})(jQuery);