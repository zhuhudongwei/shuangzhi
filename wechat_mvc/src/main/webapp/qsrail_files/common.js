var currentPage;
var currentBasePage;
var newsID;
var scaleY = 1;
var deviceType;
var animationTimer;
var navigationStack = new Array();
var navigationCurrentIndex = -1;
var backPage = '';
var indexPage = '';
//setTimeout(function(){scaleY = $(window).height()/$(window).width()/1008*640;scaleY = Math.round(scaleY*100)/100;$('head').append('<style>.scaleY{-webkit-transform-origin:0 0;-webkit-transform:scale(1,'+scaleY+');}</style>');},1000);
var userAgent = navigator.userAgent.toLowerCase();
if(userAgent.match(/android/i)=='android')
{
	deviceType = 'android';
}else if(userAgent.match(/iphone os/i)=='iphone os')
{
	deviceType = 'iphone';
}else if(userAgent.match(/ipad/i)=='ipad')
{
	deviceType = 'ipad';
}else if(userAgent.match(/iemobile\/10\.0/))
{
	deviceType = 'wp';
}
var rootSite = '/external/html/';

var newsclass=new Array();
newsclass[1]="电力矩阵";
newsclass[2]="制造业未来";
newsclass[3]="健康中国";
newsclass[4]="未来城市";
newsclass[5]="西门子领导力";
newsclass[6]="公司新闻";
newsclass[7]="图片亲闻";
newsclass[8]="创新新闻";
newsclass[9]="西门子历史";
newsclass[10]="中国历史";
newsclass[11]="爱绿计划";
newsclass[12]="企业责任";
newsclass[13]="员工志愿者协会";
newsclass[14]="未来之窗";
newsclass[15]="未来之窗";
newsclass[16]="业务领域";

$("a").attr("data-transition","slide");
$(document).ready(function(){
		if(deviceType=='iphone' || deviceType=='ipad')
		{
			$('input[type=text],input[data-type=search]').live('focus',function(){$('.tool').css('position','absolute');});
			$('input[type=text],input[data-type=search]').live('blur',function(){$('.tool').css('position','fixed');});
			$('a[target=_blank]').live('click',function(){WeixinJSBridge.call('showToolbar');linkURL = this.href;setTimeout(function(){location.href=linkURL;},500);return false;});
		}
		if(deviceType=='wp')
			{
				$('.tool').css('position','absolute');
			}
		//$.mobile.page.prototype.options.domCache= true;
		$.mobile.defaultPageTransition = "none";
		//$.mobile.hashListeningEnabled = false;
		$.mobile.changePage.defaults.changeHash = false;
		$.mobile.linkBindingEnabled = false;
		$.event.special.swipe.horizontalDistanceThreshold = 100;
		$.mobile.back = function(){
			history.back();
			return false;
			navigationCurrentIndex--;
			//backPage = navigationStack.pop();
			backPage = navigationStack[navigationCurrentIndex];
			console.log('backPage:'+backPage);
			var navigationStackLength = navigationStack.length;
			//alert(navigationCurrentIndex);
			//alert(navigationStack[navigationCurrentIndex]);
			console.log('navigationCurrentIndex:'+navigationCurrentIndex);
			console.log('navigationStack:'+navigationStack[navigationCurrentIndex]);
			if(navigationCurrentIndex>=0)
			{
				localStorage.setItem('isHistoryPage','1');
				$.mobile.changePage(navigationStack[navigationCurrentIndex],{transition:'slide',changeHash:false,reverse:true});
			}else{
				navigationCurrentIndex = 0;
			}
		};
		$.mobile.forward = function(){
				history.go(1);
				return false;
				//alert(navigationCurrentIndex);
				localStorage.setItem('isHistoryPage','1');
				navigationCurrentIndex++;
				if(navigationStack.length && navigationCurrentIndex>=navigationStack.length)
				{
					navigationCurrentIndex = navigationStack.length-1;
				}
				$.mobile.changePage(navigationStack[navigationCurrentIndex],{transition:'slide',changeHash:false});
		};
		$.mobile.index = function(){
			$.mobile.changePage('1.php',{transition:'none'});
			//$.mobile.changePage(localStorage.getItem('indexPage'),{transition:'none'});
		};
		$.mobile.refresh = function(){
			//alert(currentPage);
			localStorage.setItem('currentPage',currentPage);
			//alert(localStorage.getItem('currentPage'));
			location.href=location.href;
		};
		$.mobile.changePage = function(to,options){
			location.href=to;
		};
});
$(document).bind("pagechange",function(event,data) {

		$('.ui-listview-filter').find("input").bind("keyup",function(){
			
			if($(this).val()!=''){
				$(this).css("font-size",'50px');
				$(this).parent().css('background-image','none');
				$(this).css("text-indent",'0px');
				$(this).css("margin-left",'0');
			}else{
				$(this).css("font-size",'24px');
				$(this).parent().css('background-image','url(images/icon_search.png)');
				$(this).css("text-indent",'160px');
				$(this).css("margin-left",'-85px');

			}
		});
$('.box-search').find("input").bind("keyup",function(){
	if($(this).val()!=''){
		$(this).css("font-size",'50px');
	}else{
		$(this).css("font-size",'24px');;
	}
});


										 var historyCurrentPage = localStorage.getItem('currentPage');
                     var url = data.toPage.jqmData('url');
                     currentPage = url;
                     var endPosition = url.indexOf('?');
                     if(endPosition>=0)
                     {
                   	 }else if((endPosition = url.indexOf('#'))>=0)
                   	 {
                   	 }else{
                   	 	endPosition = url.length;
                   	 }
                     currentBasePage = url.substring(url.lastIndexOf('/')+1,endPosition);
                     localStorage.setItem('currentPage','');
                     if(localStorage.getItem('isHistoryPage')!='1')
                     {
                     	if(navigationStack[navigationCurrentIndex]!=url)
                     	{
                     		navigationStack.push(url);
                     		navigationCurrentIndex = navigationStack.length-1;
                     	}
                     }
                     localStorage.setItem('isHistoryPage','');
                     if(currentBasePage.indexOf('index_')>=0)
                     {
                     	navigationStack = new Array();
                     	navigationStack[0] = currentBasePage;
                     	navigationCurrentIndex = 0;
                   	 }
                   	 if(deviceType=='android')
                   	 {
                   	 	setTimeout(function(){WeixinJSBridge.call('hideToolbar');},2000);
                   	 }
                     switch(currentBasePage)
                     {
											//西博园首页内容构造
                     	case 'index_1.html':
                     		indexPage = 'index_1.html';
                     		localStorage.setItem('indexPage',indexPage);
                     		setTimeout(function(){document.title = '西博园';},500);
                     		break;
						case 'office.html':
							$.ajax({
								   type: "POST",
								   url: "index.php?a=office",
								   dataType:"json",
								   data: "",
								   success: function(msg){
									   for(var i=0;i<msg.length;i++){
										   var zi=msg[i]['a6'].toUpperCase();
										   var tostr='<li data-icon="nobg" province="" city="'+msg[i]['a3']+'"><a data-role="none" data-transition="slide" href="javascript:;" onClick="chace(this)" class="close">'+msg[i]['a1']+'</a></li> <li class="info" data-icon="nobg">'+msg[i]['a4']+'<br>'+msg[i]['a5']+'<br>'+msg[i]['a6']+'<br></li>';
										   
										   var tostr='<li data-role="none" data-icon="nobg"><a data-role="none" data-transition="slide" href="javascript:;" onClick="chace(this)">'+msg[i]['a1']+' </a></li><li data-role="none" class="info" data-icon="nobg">'+msg[i]['a3']+'<br />'+msg[i]['a4']+'<br>'+msg[i]['a5']+'<br /><a data-role="none" data-transition="slide" href="tel:'+msg[i]['a7']+'" class="btn">联系</a></li>';
										   $("#item_"+zi).after(tostr);
									   }
								   }
								});
						break;
						
						case 'office_1.html':

							$('#search_1').prev().after('<div style="color:gray;font-size:20px;text-align:left;">*请输入关键字进行查询</div>');
							
							//toUpperCase
								$.ajax({
								   type: "POST",
								   url: "index.php?a=office1",
								   dataType:"json",
								   data: "",
								   success: function(msg){
									   for(var i=0;i<msg.length;i++){
										   var zi=msg[i]['a7'].toUpperCase();
										   var tostr='<li data-icon="nobg" province="" city="'+msg[i]['a3']+'"><a data-role="none" data-transition="slide" href="javascript:;" onClick="chace(this)" class="close">'+msg[i]['a1']+'</a></li> <li class="info" data-icon="nobg">'+msg[i]['a4']+'<br>'+msg[i]['a5']+'<br>'+msg[i]['a6']+'<br></li>';
										   $("#item_"+zi).after(tostr);
									   }
								   }
								});
						break;
                     	//工具箱首页内容构造
                     	case 'index_2.html':
                     		indexPage = 'index_2.html';
                     		localStorage.setItem('indexPage',indexPage);
                     		setTimeout(function(){document.title = '工具箱';},500);
                     		break;
                     	//新鲜事首页内容构造
                     	case 'index_3.html':
                     		indexPage = 'index_3.html';
                     		localStorage.setItem('indexPage',indexPage);
                     		setTimeout(function(){document.title = '新鲜事';},500);
                     		break;
                     	//新一天1101
                     	case '1101.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1101').bind('swipeleft',function(event){$.mobile.changePage('1102.html',{transition:'slide'});});
                     		$('#nextday1101').bind('swiperight',function(event){});
                     		break;
                     	//新一天1102
                     	case '1102.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1102').bind('swipeleft',function(event){$.mobile.changePage('1103.html',{transition:'slide'});});
                     		$('#nextday1102').bind('swiperight',function(event){$.mobile.changePage('1101.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1103
                     	case '1103.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1103').bind('swipeleft',function(event){$.mobile.changePage('1104.html',{transition:'slide'});});
                     		$('#nextday1103').bind('swiperight',function(event){$.mobile.changePage('1102.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1104
                     	case '1104.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1104').bind('swipeleft',function(event){$.mobile.changePage('1105.html',{transition:'slide'});});
                     		$('#nextday1104').bind('swiperight',function(event){$.mobile.changePage('1103.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1105
                     	case '1105.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1105').bind('swipeleft',function(event){$.mobile.changePage('1106.html',{transition:'slide'});});
                     		$('#nextday1105').bind('swiperight',function(event){$.mobile.changePage('1104.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1106
                     	case '1106.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1106').bind('swipeleft',function(event){$.mobile.changePage('1107.html',{transition:'slide'});});
                     		$('#nextday1106').bind('swiperight',function(event){$.mobile.changePage('1105.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1107
                     	case '1107.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1107').bind('swipeleft',function(event){$.mobile.changePage('1108.html',{transition:'slide'});});
                     		$('#nextday1107').bind('swiperight',function(event){$.mobile.changePage('1106.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1108
                     	case '1108.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1108').bind('swipeleft',function(event){$.mobile.changePage('1109.html',{transition:'slide'});});
                     		$('#nextday1108').bind('swiperight',function(event){$.mobile.changePage('1107.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1109
                     	case '1109.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1109').bind('swipeleft',function(event){$.mobile.changePage('1110.html',{transition:'slide'});});
                     		$('#nextday1109').bind('swiperight',function(event){$.mobile.changePage('1108.html',{transition:"slide",reverse:true});});
                     		break;
                     	//新一天1110
                     	case '1110.html':
                     		WeixinJSBridge.call('hideToolbar');
                     		$('#nextday1110').bind('swipeleft',function(event){$.mobile.changePage('1101.html',{transition:'slide'});});
                     		$('#nextday1110').bind('swiperight',function(event){$.mobile.changePage('1109.html',{transition:"slide",reverse:true});});
                     		break;
						case 'news2.html'://新闻列表
								if(getQuery("classID")){
									classID = getQuery("classID");
								}else{
									classID = localStorage.getItem('classID');
								}
								if(classID=='7')
								{
									document.title='图片新闻';
								}else if(classID=='6')
								{
									document.title='公司新闻';
								}else if(classID=='8')
								{
									document.title='创新新闻';
								}								$('.category').find('a').removeClass('current');
								$('.category').find("a[classID='"+classID+"']").addClass('current');
								if(!classID)classID=6;
								$('.box-news').html('');
								var responseData;
								$.ajax({
								   type: "POST",
								   url: "index.php?a=showlistjson",
								   data: "class="+classID+"&page=1",
								   dataType:"json",
								   async: true,
								   success: function(msg){
								   responseData=msg;

for(var i=0;i<responseData.length;i++){
									if(i==0){
										//'+subString(responseData[i]['content'],45,1)+'
										$('<div onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');location.href=\'new2_info.html?newsID='+responseData[i]['id']+'\';" class="headline" style="overflow:hidden;height: 300px;">\
                <img src="'+responseData[i]['pic']+'" alt="" />\
                <p class="title"><span class="big" style="line-height:30px;">'+subString(responseData[i]['title'],100,1)+'</span></p>\
            </div>').appendTo('.box-news');
									}else{
										$('<dl class="list" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');location.href=\'new2_info.html?newsID='+responseData[i]['id']+'\';">\
            	<dt class="photo"><img src="'+responseData[i]['pic']+'" alt="" /></dt>\
                <dd class="info">\
                	<p class="one">'+subString(responseData[i]['title'],38,1)+'</p>\
                    <p class="two">'+subString(responseData[i]['content'],52,1).replace(/\&nbsp;/g,"")+'</p>\
					<p class="three">'+subString(responseData[i]['addtime'],7)+'</p>\
                </dd>\
            </dl>').appendTo('.box-news');
										
										/*$('<p><a data-role="none" data-transition="slide" href="news_info.html" class="one" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');"><img src="images/img_3.jpg" alt="" /><span class="title">'+responseData[i]['title']+'</span></a></p>').appendTo('#box-info2b');*/
									}
								}




								   }
								});
								
                     		break;
							case 'info8.html'://新闻列表
								classID=17;
								$('.box-news').html('');
								var responseData;
								$.ajax({
								   type: "POST",
								   url: "index.php?a=showlistjson",
								   data: "class="+classID+"&page=1",
								   dataType:"json",
								   async: true,
								   success: function(msg){
								   responseData=msg;
		/*						 
$('<div onclick="$.mobile.changePage(\'exhibition.html\');return false;" class="headline" style="overflow:hidden;height: 300px;">\
                <img src="http://www.wechat.siemens.com.cn/external/html/images/siemens_wechat.jpg" alt="" />\
                <p class="title"><span class="big" style="line-height:38px;font-size:32px;">'+subString("当月热门案例（点击阅读）",100,1)+'</span></p>\
            </div>').appendTo('.box-news');*/
for(var i=0;i<responseData.length;i++){
									if(i==0){
										$('<div onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');$.mobile.changePage(\'new2_info.html?newsID='+responseData[i]['id']+'\');return false;" class="headline" style="overflow:hidden;height: 300px;">\
                <img src="'+responseData[i]['pic']+'" alt="" />\
                <p class="title"><span class="big" style="line-height:38px;font-size:32px;">'+subString(responseData[i]['title'],100,1)+'</span></p>\
            </div>').appendTo('.box-news');
			
			$('<dl class="list" onclick="$.mobile.changePage(\'exhibition.html\');return false;">\
            	<dt class="photo"><img src="http://www.wechat.siemens.com.cn/external/html/images/siemens_wechat.jpg" alt="" /></dt>\
                <dd class="info">\
                	<p class="one">创新，成就可持续发展的未来</p>\
                    <p class="two"></p>\
					<p class="three">2014-04</p>\
                </dd>\
            </dl>').appendTo('.box-news');
									}else{
										$('<dl class="list" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');$.mobile.changePage(\'new2_info.html?newsID='+responseData[i]['id']+'\');return false;">\
            	<dt class="photo"><img src="'+responseData[i]['pic']+'" alt="" /></dt>\
                <dd class="info">\
                	<p class="one">'+subString(responseData[i]['title'],38,1)+'</p>\
                    <p class="two">'+subString(responseData[i]['content'],52,1).replace(/\&nbsp;/g,"")+'</p>\
					<p class="three">'+subString(responseData[i]['addtime'],7)+'</p>\
                </dd>\
            </dl>').appendTo('.box-news');
										
									}
									/*
										$('<dl class="list" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');$.mobile.changePage(\'new2_info.html?newsID='+responseData[i]['id']+'\');return false;">\
            	<dt class="photo"><img src="'+responseData[i]['pic']+'" alt="" /></dt>\
                <dd class="info">\
                	<p class="one">'+subString(responseData[i]['title'],38,1)+'</p>\
                    <p class="two">'+subString(responseData[i]['content'],52,1).replace(/\&nbsp;/g,"")+'</p>\
					<p class="three">'+subString(responseData[i]['addtime'],7)+'</p>\
                </dd>\
            </dl>').appendTo('.box-news');*/
										
									
}
								   }
								});
								
                     		break;
							case 'explore.html':
							classID=22;
							$('.box-news').html('');
								var responseData;
								$.ajax({
								   type: "POST",
								   url: "index.php?a=showlistjson",
								   data: "class="+classID+"&page=1",
								   dataType:"json",
								   async: true,
								   success: function(msg){
								   responseData=msg;
								   newsID=responseData[0]['id'];
										$.ajax({
										   type: "POST",
										   url: "index.php?a=shownewsinfojson",
										   data: "id="+newsID,
										   dataType:"json",
										   async: true,
										   success: function(msg){
										   responseData=msg;
										   document.title=responseData.title;
										   $('#news_title').html(responseData.title);
										   
											$('#news_content').html(responseData.content);
											if(responseData.linkurl!='')$('#news_link').attr('href',responseData.linkurl);
										   }
										});
										

								   }
								});
							break;
							case 'info9.html'://新闻列表
								classID=19;
								$('.box-news').html('');
								var responseData;
								$.ajax({
								   type: "POST",
								   url: "index.php?a=showlistjson",
								   data: "class="+classID+"&page=1",
								   dataType:"json",
								   async: true,
								   success: function(msg){
								   responseData=msg;
for(var i=0;i<responseData.length;i++){
									if(i<4){
										$('<a href="javascript:;" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');$.mobile.changePage(\'beautifulpic.html?newsID='+responseData[i]['id']+'\');return false;"><img src="'+responseData[i]['pic']+'" alt=""><p class="title">'+subString(responseData[i]['title'],38,1)+'</p></a>').prependTo('#slides');
										if(i!=3){
									$('<dl class="coptic-list" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');$.mobile.changePage(\'beautifulpic.html?newsID='+responseData[i]['id']+'\');return false;">\
        	<dt class="photo"><img src="'+responseData[i]['pic']+'" alt="" /></dt>\
            <dd class="content">\
            	<p class="title">'+subString(responseData[i]['title'],38,1)+'</p>\
                <p class="brief">'+subString(responseData[i]['content'],52,1).replace(/\&nbsp;/g,"")+'</p>\
                <p class="date">'+subString(responseData[i]['addtime'],7)+'</p>\
            </dd>\
        </dl>').appendTo('.wrapper');
										}
									}else{
										$('<dl class="coptic-list" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');$.mobile.changePage(\'beautifulpic.html?newsID='+responseData[i]['id']+'\');return false;">\
        	<dt class="photo"><img src="'+responseData[i]['pic']+'" alt="" /></dt>\
            <dd class="content">\
            	<p class="title">'+subString(responseData[i]['title'],38,1)+'</p>\
                <p class="brief">'+subString(responseData[i]['content'],52,1).replace(/\&nbsp;/g,"")+'</p>\
                <p class="date">'+subString(responseData[i]['addtime'],7)+'</p>\
            </dd>\
        </dl>').appendTo('.wrapper');
										
									}	
}
$('#slides').slidesjs({
        width: 560,
        height: 300,
        navigation: false
      });
								   }
								});
								
                     		break;
							case 'exhibition_list.html'://新加案例列表
								classID=18;
								$('.box-news').html('');
								var responseData;
								$.ajax({
								   type: "POST",
								   url: "index.php?a=showlistjson",
								   data: "class="+classID+"&page=1",
								   dataType:"json",
								   async: true,
								   success: function(msg){
								   responseData=msg;
for(var i=0;i<responseData.length;i++){
									
										$('<dl class="list" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');$.mobile.changePage(\'exhibition_info.html?newsID='+responseData[i]['id']+'\');return false;">\
            	<dt class="photo"><img src="'+responseData[i]['pic']+'" alt="" /></dt>\
                <dd class="info">\
                	<p class="one">'+subString(responseData[i]['title'],38,1)+'</p>\
                    <p class="two">'+subString(responseData[i]['content'],52,1).replace(/\&nbsp;/g,"")+'</p>\
					<p class="three">'+subString(responseData[i]['addtime'],7)+'</p>\
                </dd>\
            </dl>').appendTo('.box-news');
										
									
								}
								   }
								});
								
                     		break;
                     	//西博园热点话题页内容构造
                     	case 'hot_topic.html':

                     		break;
						//西博园热点话题详情内容构造
                     	case 'newshot_info.html':
                     		if(!newsID && location.href.indexOf('newsID=')>=0)
                     		{
                     			newsID = getQuery('newsID');
                     		}else{
                     			newsID = localStorage.getItem('newsID');
                     		}
                     		if(newsID && location.href.indexOf('newsID=')==-1)
                     		{
                     			location.href=currentBasePage+'?newsID='+newsID;
                     		}
                     		
                     		var responseData;
							$.ajax({
							   type: "POST",
							   url: "index.php?a=shownewsinfojson",
							   data: "id="+newsID,
							   dataType:"json",
							   async: true,
							   success: function(msg){
							   responseData=msg;
							   document.title=responseData.title;
							   if(newsclass[responseData.class]){
									if(responseData.class=='5'||responseData.class=='3'){$('.downlaod-search').hide();}
									//$('#news_title').html(newsclass[responseData.class]);
									$('#titleval').html(responseData.title);
							   }else{
									$('#news_title').html(responseData.title);
							   }
							   
								$('#news_content').html(responseData.content);
								$('#news_link').attr('href',responseData.linkurl);
								if($('#news_link').attr('href')==''){$('#news_link').hide()}else{$('#news_link').show()}
							   }
							});
                     		
                     		break;
                     	//西博园未来之窗内容构造
                     	case 'info2.html':
								var responseData;
								$.ajax({
								   type: "POST",
								   url: "index.php?a=showlistjson",
								   data: "class=15&page=1",
								   dataType:"json",
								   async: true,
								   success: function(msg){
								   responseData=msg;
											$('#box-info2a').html('');
											$('#box-info2b').html('');
											for(var i=0;i<responseData.length;i++){
												if(i==responseData.length-1){
													$('<p data-role="none"><a data-role="none" data-transition="slide" href="news_info.html" class="one" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');"><img src="'+responseData[i]['pic']+'" alt="" /><span class="title">'+responseData[i]['title']+'</span></a></p>').appendTo('#box-info2b');
												}else if(i%2==0){
													$('<p data-role="none"><a data-role="none" data-transition="slide" href="news_info.html" class="one" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');"><img src="'+responseData[i]['pic']+'" alt="" /><span class="title">'+responseData[i]['title']+'</span></a></p>').appendTo('#box-info2a');
												}else{
													$('<p data-role="none"><a data-role="none" data-transition="slide" href="news_info.html" class="one" onclick="newsID='+responseData[i]['id']+';localStorage.setItem(\'newsID\','+responseData[i]['id']+');"><img src="'+responseData[i]['pic']+'" alt="" /><span class="title">'+responseData[i]['title']+'</span></a></p>').appendTo('#box-info2b');
												}
											}
								   }
								});
								
                     		break;
                     	//西博园新闻详情内容构造
                     	case 'news_info.html':
                     		if(!newsID && location.href.indexOf('newsID=')>=0)
                     		{
                     			newsID = getQuery('newsID');
                     		}else{
                     			newsID = localStorage.getItem('newsID');
                     		}
                     		if(newsID && location.href.indexOf('newsID=')==-1)
                     		{
                     			location.href=currentBasePage+'?newsID='+newsID;
                     		}
                     		var responseData;
							$.ajax({
							   type: "POST",
							   url: "index.php?a=shownewsinfojson",
							   data: "id="+newsID,
							   dataType:"json",
							   async: true,
							   success: function(msg){
							   responseData=msg;
							   document.title=responseData.title;
							   $('#news_title').html(responseData.title);
								$('#news_content').html(responseData.content);
								$('#news_link').attr('href',responseData.linkurl);
								if($('#news_link').attr('href')==''){$('#news_link').hide()}else{$('#news_link').show()}
							   }
							});
                     		
                     		break;
						case 'exhibition_info.html':
                     		if(!newsID && location.href.indexOf('newsID=')>=0)
                     		{
                     			newsID = getQuery('newsID');
                     		}else{
                     			newsID = localStorage.getItem('newsID');
                     		}
                     		if(newsID && location.href.indexOf('newsID=')==-1)
                     		{
                     			location.href=currentBasePage+'?newsID='+newsID;
                     		}
                     		var responseData;
							$.ajax({
							   type: "POST",
							   url: "index.php?a=shownewsinfojson",
							   data: "id="+newsID,
							   dataType:"json",
							   async: true,
							   success: function(msg){
							   responseData=msg;
							   document.title='热门案例';
							   dataForWeixin.title='热门案例——'+responseData.title;
							   dataForWeixin.MsgImg='http://www.wechat.siemens.com.cn'+responseData.toppic+'?a=1';
							   dataForWeixin.TLImg='http://www.wechat.siemens.com.cn'+responseData.toppic+'?a=1';
							   dataForWeixin.desc='';//responseData.content.replace(/<[^>].*?>/g,"").replace(/&[^>].*?;/g,"").replace(" ",'');
							   
							   $('#news_title').html(responseData.title);
								$('#news_content').html(responseData.content);
								$('#news_link').attr('href',responseData.linkurl);
								if($('#news_link').attr('href')==''){$('#news_link').hide()}else{$('#news_link').show()}
							   }
							});
                     		
                     	break;
						case 'info3.html'://企业责任
                     		if(!newsID && location.href.indexOf('newsID=')>=0)
                     		{
                     			newsID = getQuery('newsID');
                     		}else{
                     			newsID = localStorage.getItem('newsID');
                     		}
                     		if(newsID && location.href.indexOf('newsID=')==-1)
                     		{
                     			location.href=currentBasePage+'?newsID='+newsID;
                     		}
							$('.category').find('a').removeClass('current');
							$('.category').find("a[newid='"+newsID+"']").addClass('current');
                     		var responseData;
							if(newsID=='16'){
								$('#news_link').hide();
							}else{
								$('#news_link').show();
							}
							$.ajax({
							   type: "POST",
							   url: "index.php?a=shownewsinfojson",
							   data: "id="+newsID,
							   dataType:"json",
							   async: false,
							   success: function(msg){
							   responseData=msg;
							   document.title=responseData.title;
							   $('#news_title').html(responseData.title);
								$('#news_content').html(responseData.content);
								$('#news_link').attr('href',responseData.linkurl);
							   }
							});
                     		
                     		break;
						case 'new2_info.html'://新闻列表详情
                     		if(location.href.indexOf('newsID=')>=0)
                     		{
                     			newsID = getQuery('newsID');
								//alert(location.href);
								//alert('0'+newsID);
                     		}else{
                     			newsID = localStorage.getItem('newsID');
								//alert('1'+newsID);
                     		}
                     		if(newsID && location.href.indexOf('newsID=')==-1)
                     		{
                     			location.href=currentBasePage+'?newsID='+newsID;
                     		}
							$('.category').find('a').removeClass('current');
							$('.category').find("a[newid='"+newsID+"']").addClass('current');
                     		var responseData;
							$.ajax({
							   type: "POST",
							   url: "index.php?a=shownewsinfojson",
							   data: "id="+newsID,
							   dataType:"json",
							   async: true,
							   success: function(msg){
							   responseData=msg;
							   document.title=responseData.title;
							   dataForWeixin.title=responseData.title;
							   //dataForWeixin.url='http://www.wechat.siemens.com.cn/external/html/new2_info.html?newsID='+newsID;
							   dataForWeixin.MsgImg='http://www.wechat.siemens.com.cn'+responseData.toppic+'?a=1';
							   dataForWeixin.TLImg='http://www.wechat.siemens.com.cn'+responseData.toppic+'?a=1';
							   if(newsID!=900&&newsID!=899&&newsID!=901&&newsID!=902){
							   dataForWeixin.desc=subString(responseData.content,"200").replace(/<[^>]+>/g,"").replace(/ /g,'').replace(/&nbsp;/g,'').replace(/&bull;/g,'');
							   }else{
								   dataForWeixin.desc=subString(responseData.content,"200").replace(/<[^>]+>/g,"").replace(/ /g,'').replace("“",'').replace("”",'').replace('"','').replace(/&nbsp;/g,'').replace(/&bull;/g,'');
								}
							  //alert('http://www.wechat.siemens.com.cn'+responseData.toppic+'?a=1');
							  //$("body").html("<a href='http://www.wechat.siemens.com.cn"+responseData.toppic+"?a=1'>http://www.wechat.siemens.com.cn"+responseData.toppic+"?a=1</a>");
							  //str.replace(/<[^>]+>/g,"")
							   $('#news_title').html(responseData.title);
                     		$('#news_content').html(responseData.content);
                     		$('#news_link').attr('href',responseData.linkurl);
								if(responseData.linkurl==''){
									$('#news_link').hide();
								}else{
									$('#news_link').show();
								}
							   }
							});
                     		
                     		break;
							//业务领域内容详情
						case 'info6.html':
							if(!newsID && location.href.indexOf('newsID=')>=0)
           		{
           			newsID = getQuery('newsID');
           		}else{
           			newsID = localStorage.getItem('newsID');
           		}
							if(newsID && location.href.indexOf('newsID=')==-1)
           		{
           			location.href=currentBasePage+'?newsID='+newsID;
           		}
							$('.category').find('a').removeClass('current');
							$('.category').find("a[newid='"+newsID+"']").addClass('current');
                     		var responseData;
							$.ajax({
							   type: "POST",
							   url: "index.php?a=shownewsinfojson",
							   data: "id="+newsID,
							   dataType:"json",
							   async: true,
							   success: function(msg){
							   responseData=msg;
							   document.title=responseData.title;
							   $('#news_title').html(responseData.title);
                     		$('#news_content').html(responseData.content);
                     		$('#news_link').attr('href',responseData.linkurl);
							   }
							});
                     		
							break;	
						case 'info4.html':	//关于公司
							$('.category').find('a').eq(0).addClass('current');
						break;
						case 'mail.html'://视频,报告下载邮件发送
							var searchVal='video';
							param = localStorage.getItem('param');
							if(param.search(searchVal)!='-1'){
								$('.category').find('a').removeClass('current');
								$('.category').find("a").eq(1).addClass('current');
							}else{
								$('.category').find('a').removeClass('current');
								$('.category').find("a").eq(0).addClass('current');
							}
						break;
						//产品速查页面内容构造
						case 'product_search.html':
							break;
						
                   	 }
								});

document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	//if(deviceType!='android')
	//{
		WeixinJSBridge.call('hideToolbar');
	//}
});

 
function subString(str, len, hasDot) 
{ 
    var newLength = 0; 
    var newStr = ""; 
    var chineseRegex = /[^\x00-\xff]/g; 
    var singleChar = ""; 
    var strLength = str.replace(chineseRegex,"**").length; 
    for(var i = 0;i < strLength;i++) 
    { 
        singleChar = str.charAt(i).toString(); 
        if(singleChar.match(chineseRegex) != null) 
        { 
            newLength += 2; 
        }     
        else 
        { 
            newLength++; 
        } 
        if(newLength > len) 
        { 
            break; 
        } 
        newStr += singleChar; 
    } 
     
    if(hasDot && strLength > len) 
    { 
        newStr += "..."; 
    } 
    return newStr; 
} 

function getQuery(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) 
        return unescape(r[2]); 
    return null;
}