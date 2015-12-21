	//全局变量
	var	GLOBAL_IMAGE_ADDRESS = "http://58.215.81.45/"
	
	/*转义字符函数   & < >  / */
	function escapeHTML(html)
	{	
		html = html.replace(/&/g,'&amp;').replace(/>/g,'&gt;').replace(/</g,'&lt;').replace(/"/g,'&quot;');
		return html ;
	}
	
	/*头像url地址函数*/
	function portrait(type,memberId)
	{
		memberId = memberId + "" ;
	
		while(memberId.length<6)
		{
			
			memberId = "0"+memberId ;
		}
		var path ;
		var path1 = memberId.substring(memberId.length-6,memberId.length-4);
		var path2 = memberId.substring(memberId.length-4,memberId.length-2);
		
		if(type == "image")
		{
			path = "images" ;
		}
		else if(type =="thumbnails")
		{
			path = "thumbnails" ;
		}
		else
		{
			path = "thumbnails" ;
		}
		
		var photoURL = GLOBAL_IMAGE_ADDRESS+path+"/product/"+path1+"/"+path2+"/"+memberId+".jpg" ;
		
		return photoURL ;
	}
	
	/*交易记录图片url地址函数*/
	function portrait_exchange(type,memberId)
	{
		memberId = memberId + "" ;
	
		while(memberId.length<6)
		{
			
			memberId = "0"+memberId ;
		}
		var path ;
		var path1 = memberId.substring(memberId.length-6,memberId.length-4);
		var path2 = memberId.substring(memberId.length-4,memberId.length-2);
		
		if(type == "image")
		{
			path = "images" ;
		}
		else if(type =="thumbnails")
		{
			path = "thumbnails" ;
		}
		else
		{
			path = "thumbnails" ;
		}
		
		var photoURL = GLOBAL_IMAGE_ADDRESS+path+"/exchange/"+path1+"/"+path2+"/"+memberId+".jpg" ;
		
		return photoURL ;
	}
	
	if (navigator.platform == "Win32" && navigator.appName == "Microsoft Internet Explorer" && window.attachEvent) {
	    window.attachEvent("onload", enableAlphaImages);
	}
	
	function enableAlphaImages(){
	    var rslt = navigator.appVersion.match(/MSIE (\d+\.\d+)/, '');
	    var itsAllGood = (rslt != null && Number(rslt[1]) >= 5.5);
	    if (itsAllGood) {
	        for (var i = 0; i <
	        document.all.length; i++) {
	            var obj = document.all[i];
	            var bg = obj.currentStyle.backgroundImage;
	            var img = document.images[i];
	            if (bg && bg.match(/customer_service_1.png/i) != null) {
	                var img = bg.substring(5, bg.length - 2);
	                var offset = obj.style["background-position"];
	                obj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img + "', sizingMethod='crop')";
	                obj.style.backgroundImage = "url('image/bake.gif')";
	                obj.style["background-position"] = offset; // reapply
	            }
	            else 
	                if (img && img.src.match(/customer_service_1.png$/i) != null) {
	                    var src = img.src;
	                    img.style.width = img.width + "px";
	                    img.style.height = img.height + "px";
	                    img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + src + "', sizingMethod='crop')"
	                    img.src = "image/bake.gif";
	                }
	        }
	    }
	}
	
	
	//格式化日期
	function formatDate(v,pattern){  
		if(v instanceof Date){
			var y = v.getFullYear();  
			var m = v.getMonth() + 1;  
			var d = v.getDate();  
			var h = v.getHours();  
			var i = v.getMinutes();  
			var s = v.getSeconds(); 
			var ms = v.getMilliseconds();     
			if(h>0 || i>0 || s>0) 
			if(m<10) m = '0' + m;
			if(d<10) d = '0' + d;
			if(h<10) h = '0' + h;
			if(i<10) i = '0' + i;
			if(s<10) s = '0' + s;
			return y + '-' + m + '-' + d + ' ' + h + ':' + i;  
		
		}
		return '';  
	}  
	
	//加入收藏夹
	function AddFavorite(sURL, sTitle)
    {
        try
        {
            window.external.addFavorite(sURL, sTitle);
        }
        catch (e)
        {
            try
            {
                window.sidebar.addPanel(sTitle, sURL, "");
            }
            catch (e)
            {
                alert("加入收藏失败，请使用Ctrl+D进行添加");
            }
        }
    }
    
    //等比例缩放图片
    function suitSize(w , h, obj){
		var imgW,imgH ;
	
		imgW = $(obj).attr("width");
		imgH = $(obj).attr("height");
		if(imgW <= w && imgH <= h){
			return;
		}
		
		var suitW, suitH;
		var scaleW = imgW/w;
		var scaleH = imgH/h;
		if(scaleW > scaleH){
			suitW = w;
			suitH = imgH/scaleW;
		}else{
			suitH = h;
			suitW = imgW/scaleH;
		}
		$(obj).height(suitH);
		$(obj).width(suitW);
	}
    
    //获得Cookie的值
    function getCookie(sName)   
	{     
	    var aCookie = document.cookie.split("; "); 
	    for (var i=0; i < aCookie.length;i++)   
	    {      
	        var aCrumb = aCookie[i].split("="); 
	
	        if (sName == aCrumb[0])     
	        	return unescape(aCrumb[1]);   
	    }       
	    return null;  
	}
	
	function login() {
		var href = window.location.href;
		href = href.substring(0,href.lastIndexOf(".action"));
		var hostPos = href.lastIndexOf("/");
		var target = window.location.href.substring(hostPos);
		href = href.substring(0,hostPos) + "/member_login.action?targetURL=" + target;
		window.location.href = href;
	}
	
	DateFormat = (function(){ 
		var SIGN_REGEXP = /([yMdhsm])(\1*)/g; 
		var DEFAULT_PATTERN = 'yyyy-MM-dd'; 
		function padding(s,len){ 
		 var len =len - (s+'').length; 
		 for(var i=0;i<len;i++){s = '0'+ s;} 
		 return s; 
		};
		return({ 
		 format: function(date,pattern){ 
		 pattern = pattern||DEFAULT_PATTERN; 
		 return pattern.replace(SIGN_REGEXP,function($0){ 
		 switch($0.charAt(0)){ 
		 case 'y' : return padding(date.getFullYear(),$0.length); 
		 case 'M' : return padding(date.getMonth()+1,$0.length); 
		 case 'd' : return padding(date.getDate(),$0.length); 
		 case 'w' : return date.getDay()+1; 
		 case 'h' : return padding(date.getHours(),$0.length); 
		 case 'm' : return padding(date.getMinutes(),$0.length); 
		 case 's' : return padding(date.getSeconds(),$0.length); 
		 } 
		 }); 
		 }, 
		 parse: function(dateString,pattern){ 
		 var matchs1=pattern.match(SIGN_REGEXP); 
		 var matchs2=dateString.match(/(\d)+/g); 
		 if(matchs1.length==matchs2.length){ 
		 var _date = new Date(1970,0,1); 
		 for(var i=0;i<matchs1.length;i++){
		 var _int = parseInt(matchs2[i]);
		 var sign = matchs1[i];
		 switch(sign.charAt(0)){ 
		 case 'y' : _date.setFullYear(_int);break; 
		 case 'M' : _date.setMonth(_int-1);break; 
		 case 'd' : _date.setDate(_int);break; 
		 case 'h' : _date.setHours(_int);break; 
		 case 'm' : _date.setMinutes(_int);break; 
		 case 's' : _date.setSeconds(_int);break; 
		 }
		 } 
		 return _date; 
		 } 
		 return null; 
		 } 
		}); 
		})(); 
   