/**
 * 引用其他js(微信自带浏览器无效)
 */
//document.write("<script language='javascript' src='/static/script/ruan/ruan_dialog.js'></script>");


/**
 * 保留n位小数，如：2，会在2后面补上.00 即2.00
 * @param src源数
 * @param n小数位数
 * @returns
 */
function rKeepNDecimal(src,n) {
	var temp = parseFloat(src);
    if (isNaN(temp)) {
        return src;
    }
    temp = Math.round(src*100)/100;
    var sTemp = temp.toString();
    var rs = sTemp.indexOf('.');
    if (rs < 0) {
        rs = sTemp.length;
        sTemp += '.';
    }
    while (sTemp.length <= (rs + n)) {
    	sTemp += '0';
    }
    return sTemp;
}

/**
* 将源字符串中间某部分替换成指定的字符
* sourceString源字符串
* 保留源字符串前keepTheFirst位
* 保留源字符串后keepTheLast位
* 指定的替换字符replaceChar
* return 除了要保留的，其他字符替换成指定的字符
*/
function rAppointChar(sourceString,keepTheFirst,keepTheLast,replaceChar){
	//String.slice(start,end)一个新的字符串。从start开始(包括start)到end结束(不包括end)为止的所有字符
	var beReplaced = sourceString.slice(keepTheFirst,sourceString.length-keepTheLast);
	var replace = "";
	for(var i=0; i<beReplaced.length; i++){
		replace += replaceChar;
	}
	return sourceString.replace(beReplaced,replace);
}

/*
* 定时获取新订单数量	1*60*60*1000

$(function(){
	getNewOrderNum();
});
setInterval("getNewOrderNum()",1*60*60*1000);
function getNewOrderNum(){
	$.getJSON('/order/getNewOrderNum',{sellerId:DDENG.userId,nowTime:1*60*60},function(data){
		if("success"==data.code){
			if(0>=data.data){
				$('#J-nOdNum').attr("style","display:none").html("");
			}else{
				if(99<data.data){
					data.data = "N+";
				}
				$('#J-nOdNum').attr("class","com-n-order-num").attr("style","display:block").html(data.data);
			}
		}else{
			$('#J-nOdNum').attr("style","display:none");
		}
	});
}
*/

/**
* 查检是否为数字 字母
* flag：0=数字和字母(默认)，1=数字，2=字母(不区分大小写)，3=字母(大写)，4=字母(小写)，5=数字与字母(大写)，6=数字与字母(小写)
* value：被查检的串
*/
function rCheckNumCharacter(flag,value){
	var reg = /^[A-Za-z0-9]*$/;
	if(null==flag||''==flag||0==flag){	//数字和字母
		reg = /^[A-Za-z0-9]*$/;
	}else if(1==flag){					//数字
		reg = /^[0-9]*$/;
	}else if(2==flag){					//字母(不区分大小写)
		reg = /^[A-Za-z]*$/;
	}else if(3==flag){					//字母(大写)
		reg = /^[A-Z]*$/;
	}else if(4==flag){					//字母(小写)
		reg = /^[a-z]*$/;
	}else if(5==flag){					//数字与字母(大写)
		reg = /^[0-9A-Z]*$/;
	}else if(6==flag){					//数字与字母(小写)
		reg = /^[0-9a-z]*$/;
	}
	
	if(reg.test(value)){
		return true;
	}else{
		return false;
	}
}

/**
* 查检二级域名，rThis=当前标签的this
*/
function rCheckDomain(rThis){
	var inputValue = $.trim($(rThis).val());
	if(4>inputValue.length||33<=inputValue.length){						//域名长度范围4-32个字符
		alert("域名长度范围4-32个字符！");
		$(rThis).attr("value","");
		$(rThis).focus();
		return false;
	}
	
	if(rCheckNumCharacter(1,inputValue)){								//不能纯数字
		alert("域名不能纯数字！");
		$(rThis).attr("value","");
		$(rThis).focus();
		return false;
	}
	
	if(!rCheckNumCharacter(6,inputValue)){								//数字与小写字母
		alert("域名只能使用：26个小英文字母、0-9个数字！");
		$(rThis).attr("value","");
		$(rThis).focus();
		return false;
	}
	
	if(!rCheckNumCharacter(4,inputValue.substring(0,1))){				//必须以字母开头
		alert("域名必须以字母开头！");
		$(rThis).attr("value","");
		$(rThis).focus();
		return false;
	}
	
	if('-'==inputValue.substring(0,1)||'-'==inputValue.substring(inputValue.length-1,inputValue.length)){//头尾不能使用-字符
		alert("域名头尾不能使用-字符！");
		$(rThis).attr("value","");
		$(rThis).focus();
		return false;
	}
	
	return true;
}

/**
* 图片地址(根据图片址，选择图片服务器和大小)
* path：图片地址，如：u2/product/140519/214/l/c4beb7c8-4b63-48bb-8635-eb364f13fe47.jpg
* size：想要的图片尺寸，如：l m s t n，默认是l
* return：http://img02.ddeng.com/u2/product/140519/214/t/c4beb7c8-4b63-48bb-8635-eb364f13fe47.jpg
*/
var r_deng_image_path = function(path,size){
	if(undefined==path || null==path || ''==$.trim(path)){
		return 'http://s.ddeng.com/static/images/seller-l.jpg';//http://s.ddeng.com/static/images/seller-s.jpg
	}
	
	if(/http:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\;/?%&=]*)?/gi.test(path)){
		return path;
	}else{
		if(path.substring(0,1)=='n'){
			path = DDENG.image_server.replace(/img[0-9][1-9]/,'img01') + path;
		}else if(path.substring(0,1)=='u'){
			path = DDENG.image_server.replace(/img[0-9][1-9]/,'img02') + path;
		}else if(path.substring(0,1)=='v'){
			path = DDENG.image_server.replace(/img[0-9][1-9]/,'img03') + path;
		}else if(path.substring(0,1)=='w'){
			path = DDENG.image_server.replace(/img[0-9][1-9]/,'img04') + path;
		}else if(path.substring(0,1)=='x'){
			path = DDENG.image_server.replace(/img[0-9][1-9]/,'img05') + path;
		}else if(path.substring(0,1)=='y'){
			path = DDENG.image_server.replace(/img[0-9][1-9]/,'img06') + path;
		}else if(path.substring(0,1)=='z'){
			path = DDENG.image_server.replace(/img[0-9][1-9]/,'img07') + path;
		}
      
		if(undefined==size || null==size || ''==$.trim(size)){
			
		}else{
			path = path.replace('/l/','/'+size+'/');
		}
		
		return path;
	}
};

/**
* 生成二维码
*  width：宽
*  height：高
*  url：连接
*/
var r_qrc_create = false;
var r_qrc_show = false;
function rQRCode(width,height,url){
	if(!r_qrc_create){
		$("#rQRCode").qrcode({
			render: "ruan", //table方式
			width: width, //宽度
			height:height, //高度
			text: url //任意内容
		});
		r_qrc_create = true;
	}
	
	if(r_qrc_show){
		$("#rQRCode").css("display","none");
		r_qrc_show = false;
	}else{
		$("#rQRCode").css("display","block");
		r_qrc_show = true;
	}
}

/**
 * 检查客户端
 * 
 * 测试：
 * alert("语言版本: "+rCheckBrowser.language);
 * alert(" 是否为移动终端: "+rCheckBrowser.versions.mobile);
 * alert(" ios终端: "+rCheckBrowser.versions.ios);
 * alert(" android终端: "+rCheckBrowser.versions.android);
 * alert(" 是否为iPhone: "+rCheckBrowser.versions.iPhone);
 * alert(" 是否iPad: "+rCheckBrowser.versions.iPad);
 * alert(navigator.userAgent);
 * if(rCheckBrowser.versions.android || rCheckBrowser.versions.iPhone || rCheckBrowser.versions.iPad || rCheckBrowser.versions.mobile || rCheckBrowser.versions.ios){
 * 	alert("是移动设备");
 * }else{
 * 	alert("不是移动设备");
 * }
 */
var rCheckBrowser = {
	versions:function(){
		var u = navigator.userAgent, app = navigator.appVersion;
		return {																								//移动终端浏览器版本信息
			trident: u.indexOf('Trident') > -1, 																//IE内核
			presto: u.indexOf('Presto') > -1, 																	//opera内核
			webKit: u.indexOf('AppleWebKit') > -1, 																//苹果、谷歌内核
			gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, 										//火狐内核
			mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), 								//是否为移动终端
			ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), 													//ios终端
			android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, 										//android终端或者uc浏览器
			iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, 											//是否为iPhone或者QQHD浏览器
			iPad: u.indexOf('iPad') > -1, 																		//是否iPad
			webApp: u.indexOf('Safari') == -1 																	//是否web应该程序，没有头部与底部
		};
	}(),
	         
	language:(navigator.browserLanguage || navigator.language).toLowerCase()
}

/**
 * 查检是否移动端(手机)
 * 
 * 测试：
 * rIsMobile.isMobile()
 * 
 * @returns Boolean
 */
var rIsMobile = {
	Android: function() {
		return navigator.userAgent.match(/Android/i) ? true : false;
	},
	BlackBerry: function() {
		return navigator.userAgent.match(/BlackBerry/i) ? true : false;
	},
	iOS: function() {
		return navigator.userAgent.match(/iPhone|iPad|iPod/i) ? true : false;
	},
	Windows: function() {
		return navigator.userAgent.match(/IEMobile/i) ? true : false;
	},
	isMobile: function() {
		return (rIsMobile.Android() || rIsMobile.BlackBerry() || rIsMobile.iOS() || rIsMobile.Windows());
	}
};

/**
 * 是否滚动到底部
 * @returns Boolean
 */
function rRollBottom() {
    var scrollTop = 0, clientHeight = 0, scrollHeight = 0;
    
    if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
    } else if (document.body) {
        scrollTop = document.body.scrollTop;
    }
    
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;
    } else {
        clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;
    }
    
    scrollHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    
    return (scrollTop + clientHeight + 800 >= scrollHeight);
}

