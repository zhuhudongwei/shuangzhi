jQuery.fn.extend({	
	showMessage: function(messageContent,time) {
		var browserWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		var browserHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
		var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
		var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
		this.attr("style","");
		if(messageContent.length > 15)
			this.addClass('msg2');
		if(messageContent.length > 30)
			this.addClass('msg3');
		
		this.html("<div id='msg_content' class='flora'><div id='msg_top'><span>操作提示</span><a onfocus='this.blur()'>×</a></div>	<img src='image/tip_icon.jpg' /><span id='msg_tip'></span></div><div id='msg_bg'></div><input type='hidden' id='messageTip' />");  
		$("#msg_tip").html(messageContent);
		$("#msg_top>a").click( function() { $('#msg').hide(); } ); 
		var width = this.width();
		var height = this.height();
		this.css({left: (browserWidth / 2) - width / 2 + scrollX,top: (browserHeight / 2) - 150 + scrollY});
		this.show();
		setTimeout(function(){ $('#msg').hide();}, time);
		} 
});


