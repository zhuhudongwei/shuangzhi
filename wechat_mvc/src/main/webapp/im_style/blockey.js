//  //屏蔽鼠标右键、Ctrl+N、Shift+F10、F11、F5刷新
//document.oncontextmenu= function(){event.returnValue=false;}//屏蔽鼠标右键 
//window.onhelp= function(){return false} //屏蔽F1帮助 
//document.onselectstart = function(){event.returnValue=false;};//屏蔽内容选中
//document.onkeydown= function(){
//  if ((window.event.altKey)&& 
//      ((window.event.keyCode==37)||   //屏蔽 Alt+ 方向键 ← 
//       (window.event.keyCode==39)))   //屏蔽 Alt+ 方向键 → 
//  { 
//     alert("不准你使用ALT+方向键前进或后退网页！"); 
//     event.returnValue=false; 
//  } 
//     /* 注：这还不是真正地屏蔽 Alt+ 方向键， 
//     	因为 Alt+ 方向键弹出警告框时，按住 Alt 键不放， 
//     	用鼠标点掉警告框，这种屏蔽方法就失效了。*/ 
//  if ((event.keyCode==116)||                 //屏蔽 F5 刷新键 
//      (event.ctrlKey && event.keyCode==82)){ //Ctrl + R 
//     event.keyCode=0; 
//     event.returnValue=false; 
//     } 
//  if (event.keyCode==112){event.keyCode=0;event.returnValue=false;}  //屏蔽F1 
//  if (event.keyCode==113){event.keyCode=0;event.returnValue=false;}  //屏蔽F2
//  if (event.keyCode==114){event.keyCode=0;event.returnValue=false;}  //屏蔽F3
//  if (event.keyCode==115){event.keyCode=0;event.returnValue=false;}  //屏蔽F4
//  if (event.keyCode==117){event.keyCode=0;event.returnValue=false;}  //屏蔽F6
//  if (event.keyCode==118){event.keyCode=0;event.returnValue=false;}  //屏蔽F7
//  if (event.keyCode==119){event.keyCode=0;event.returnValue=false;}  //屏蔽F8
//  if (event.keyCode==120){event.keyCode=0;event.returnValue=false;}  //屏蔽F9
//  if (event.keyCode==121){event.keyCode=0;event.returnValue=false;}  //屏蔽F10
//  if (event.keyCode==122){event.keyCode=0;event.returnValue=false;}  //屏蔽F11
//  if (event.keyCode==113){event.keyCode=0;event.returnValue=false;}  //屏蔽F12
//  if (event.ctrlKey && event.keyCode==78) event.returnValue=false;   //屏蔽 Ctrl+n 
//  if (event.shiftKey && event.keyCode==121)event.returnValue=false;  //屏蔽 shift+F10 
//  if (window.event.srcElement.tagName == "A" && window.event.shiftKey)  
//      window.event.returnValue = false;             //屏蔽 shift 加鼠标左键新开一网页 
//};