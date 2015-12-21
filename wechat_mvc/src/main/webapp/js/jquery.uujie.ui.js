/**
 * @author Administrator
 */
(function ($) {
	$.fn.UUDialog_timer = {};
	$.fn.UUDialog = function(option){
		
		var _opts = $.extend({},$.fn.UUDialog.defaults, option);
		var $this = $(this);
		var _id = $this.attr("id");
		
		if($("#UUDialog" + _id).size() > 0) {
			
			if (option == "close") {
				$("body").append($this);
				$this.hide();
			 	$("#UUDialog" + _id).remove();
				$("#FFModal"+ _id).remove();
				_opts.close();
				return;
			}
			if(option == "destory"){
			 	$("#UUDialog" + _id).remove();
				$("#FFModal"+ _id).remove();
				_opts.close();
				return;
			 }
			 else {
			 	createModal();
			 	_opts.open();
			 	$("#UUDialog" + _id).show();
			 	return;
			 }
		}
		
		
				
		try {
			var _body = $(this).clone(true).attr("style","").attr("id","");
		}catch(err){}
		
		var _dialogDom = $('<div id="UUDialog' + _id + '"> <div style="position:absolute;z-index:-1;left:-8px;top:0;width:'+_opts.width+'"><iframe style="border: none;width:100%;filter:alpha(opacity=0);-moz-opacity:0"></iframe></div>' +
								'<table class="pop_dialog_table">' +
								'<tbody>' +
									'<tr>' +
										'<td class="pop_topleft"/>' +
										'<td class="pop_border"/>' +
										'<td class="pop_topright"/>' +
									'</tr>' +
									'<tr>' +
										'<td class="pop_border"/>' +
										'<td class="pop_content">' +
											'<h2>' + 
												'<span>' + _opts.title + '</span>' +
												'<a id="closeFFDialog'+_id+'">×</a>' +
											'</h2>' +
											'<div class="dialog_content">' +
												'<div class="dialog_body">' + 
												'</div>' +
											'</div>' +
										'</td>' +
										'<td class="pop_border"/>' +
									'</tr>' +
									'<tr>' +
										'<td class="pop_bottomleft"/>' +
										'<td class="pop_border"/>' +
										'<td class="pop_bottomright"/>' +
									'</tr>' +
								'</tbody>' +
								'</table' +
							'</div>'
		);
		
		$(".dialog_body",_dialogDom).append($this);
		if(_opts.buttonposition == "left")
		{
			var _dialog_buttons = $('<div class="dialog_buttons" style="text-align:left;"></div>');
		}
		if(_opts.buttonposition == "right")
		{
			var _dialog_buttons = $('<div class="dialog_buttons" style="text-align:right;"></div>');
		}
		else{
			var _dialog_buttons = $('<div class="dialog_buttons" style="text-align:center;"></div>');
		}
		
		if(_opts.status != "" ){
			var _status = $('<span>' + _opts.status + '</span>')
			_dialog_buttons.append(_status);
		}
		
		if(_opts.buttons.confirm && _opts.buttons.confirm.show){
			var confirmButton = $('<input type="button" class="uq_btn" value="' + _opts.buttons.confirm.text + '"/>');
			confirmButton.click(function(){
				if(_opts.buttons.confirm.callback)
					_opts.buttons.confirm.callback.call(confirm);
				if(!_opts.buttons.confirm.preventDefault)
					FFClose();
				_opts.close();
				window.clearTimeout($.fn.UUDialog_timer[_id]);
			});
			_dialog_buttons.append(confirmButton);
		}
		if(_opts.buttons.cancel && _opts.buttons.cancel.show) {
			var cancelButton = $('<input type="button" class="qx_btn" value="' + _opts.buttons.cancel.text + '"/>');
			cancelButton.click(function(){
				if(_opts.buttons.cancel.callback)
					_opts.buttons.cancel.callback.call(cancel);
				if(!_opts.buttons.cancel.preventDefault)
					FFClose();
				_opts.close();
				window.clearTimeout($.fn.UUDialog_timer[_id]);
			});
			_dialog_buttons.append(cancelButton);
		}
		if(_opts.buttons.close && _opts.buttons.close.show) {
			var closeButton = $('<input type="button" class="qx_btn" value="' + _opts.buttons.close.text + '"/>')
			closeButton.click(function(){
				if(_opts.buttons.close.callback)
					_opts.buttons.close.callback.call(close);
				if(!_opts.buttons.close.preventDefault)
					FFClose();
				_opts.close();
				window.clearTimeout($.fn.UUDialog_timer[_id]);
			});
			_dialog_buttons.append(closeButton);
		}
		
		
		
		if (confirmButton || cancelButton || closeButton || _status) {
			$(".dialog_content", _dialogDom).append(_dialog_buttons);
		}
			
		
		
		createModal();
		
		
		
		$("body").append(_dialogDom);
		_opts.open();
		
		
		if (_opts.position == "center") {
			
			var browserWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
			var browserHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
			var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
			var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
			if(_opts.width == "")
			{
				var width = this.width();
			}
			else{
				var width =_opts.width;
			}
			var height = this.height();
			_dialogDom.css({
				left: (browserWidth / 2) - width/1.2 + scrollX,
				top: (browserHeight / 2) - 200 + scrollY,
				width: width + 242
			});
		}else {
			if(_opts.width == "")
			{
				var width = this.width();
			}
			else{
				var width =_opts.width;
			}
			var height = this.height();
			var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
			var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
			var scrollRight = document.documentElement.scrollRight || document.body.scrollRight;
			var scrollBottom = 100000;
			
			_dialogDom.css("width", (width + 42) + "px");
			
			if(_opts.left) {
				if(scrollLeft) {
					_dialogDom.css("left", (_opts.left + scrollLeft) + "px");
				} else {
					_dialogDom.css("left", (_opts.left) + "px");
				}
			}
			if(_opts.right) {
				if(scrollRight) {
					_dialogDom.css("right", (_opts.right + scrollRight) + "px");
				} else {
					_dialogDom.css("right", (_opts.right) + "px");
				}
			}
			if(_opts.top) {
				if(scrollTop) {
					_dialogDom.css("top", (_opts.top + scrollTop) + "px");
				}else {
					_dialogDom.css("top", (_opts.top) + "px");
				}
			}
			if(_opts.bottom) {
				if(scrollBottom) {
					_dialogDom.css("bottom", (_opts.bottom + scrollBottom) + "px");
				}else {
					_dialogDom.css("bottom", (_opts.bottom) + "px");
				}
			}
		}
		_dialogDom.css({
			"position": "absolute",
			"z-index": 10000
		});
		
		$this.show();
		_dialogDom.show();
		_dialogDom.focus();
		
		//如果设置了延迟时间，则在对应的时间后隐藏对话框
		if(_opts.delay > 0 && $("#UUDialog" + _id).size()> 0) {
			$.fn.UUDialog_timer[_id] = setTimeout(function(){
				$("#UUDialog" + _id).remove();
				$("#FFModal"+ _id).remove();
				_opts.close();
			},_opts.delay);
		}
		
		/*******************/
		$("#closeFFDialog"+_id).click(function(){
			if(_opts.cancelButton == "true"){
				$("#UUDialog" + _id).remove();
				$("#FFModal"+ _id).remove();
				_opts.close();
				return;
			}else{
				_opts.close();
				$("body").append($this);
				$this.hide();
			 	$("#UUDialog" + _id).remove();
				$("#FFModal"+ _id).remove();
				return;
			}
		});
		/*******************/
		
		
		function createModal() {
			
			
			if(_opts.modal == true) {
				var scrollWidth = document.body.scrollWidth + "px";
				
				//var scrollHeight =  ((window.screen.availHeight > document.body.scrollHeight) ?  window.screen.availHeight : document.body.scrollHeight) + "px";
				var scrollHeight = (document.documentElement.scrollHeight || document.body.scrollHeight) + "px";
				if($("#FFModal" + _id).length > 0) {return};
				var _modalDemo = $('<div id="FFModal'+_id+'" style="display: none;background: rgb(0, 0, 0) none repeat scroll 0% 0%; position: absolute; top: 0pt; left: 0pt; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; z-index: 2000; opacity: 0.3; filter: alpha(opacity = 30); height: '+ scrollHeight + '; width: 100%;"> '+
					'<iframe height="100%" frameborder="0" width="100%" style="position: absolute; top: 0pt; left: 0pt; z-index: 1;"/>' +
					'<div style="position: absolute; top: 0pt; left: 0pt; width: 100%; height: 100%; background-color: rgb(0, 0, 0); z-index: 2;"/>' +
					'</div>' +
				'');
				$('body').append(_modalDemo);
				_modalDemo.show();
				this.modalFrame = _modalDemo;
			}
		}
		
		function FFClose() {
			_dialogDom.hide();
			$("#FFModal"+ _id).remove();
		};
		
		function hideFFModal() {
			$("#FFModal"+ _id).remove()
		}
		
	};
	
	
	
	function FFOpen() {
	};
	
	function FFClose() {
	};
	
	
	
	function confirm() {};
	function cancel() {};
	function close() {};

	$.fn.UUDialog.defaults = {
		title: "标题栏",
		delay: -1,	
		width:"",						//延迟关闭时间，默认为-1，不自动关闭
		position: "center",
		left: "",
		right: "",
		top: "",
		bottom: "",
		buttons:{
			confirm:{
				show: false,
				text: "确定",
				preventDefault: false,
				callback: confirm
			},
			cancel: {
				show: false,
				text: "取消",
				preventDefault: false,
				callback: cancel
			},
			close: {
				show: false,
				text: "关闭",
				preventDefault: false,
				callback: close
			}
		},
		status: "",
		open: FFOpen,
		close: FFClose
	};
	
})(jQuery);

(function ($) {
	
})(jQuery);