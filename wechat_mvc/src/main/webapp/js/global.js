

		function switchSysBar(){
			if (document.getElementById("switchPoint").src.indexOf('img/bar1.gif') != -1)
			{
				document.getElementById("switchPoint").src="img/bar2.gif"
				document.getElementById("nav_list").style.display="none"
				document.getElementById("bar").style.left="0"
				document.getElementById("mainDiv").style.width="980px"
				document.getElementById("location").style.width="980px"

			}
			else{
				document.getElementById("switchPoint").src="img/bar1.gif"
				document.getElementById("nav_list").style.display=""
				document.getElementById("bar").style.left="181px"
				document.getElementById("mainDiv").style.width="790px"
				document.getElementById("location").style.width="790px"
			}
		}

    function initTree(t) {
	var tree=document.getElementById(t);
	tree.style.display="none";
	var lis=tree.getElementsByTagName("li");
	//alert(lis.length);
	for(var i=0;i<lis.length;i++) {
		lis[i].id="li"+i;
		var uls=lis[i].getElementsByTagName("ul");
		if(uls.length!=0) {
			uls[0].id="ul"+i;
			uls[0].style.display="block";
			var as=lis[i].getElementsByTagName("a");
			as[0].id="a"+i;
			as[0].className="folder";
			as[0].href="#this";
			as[0].tget=uls[0];
			as[0].onclick=function() {
				openTag(this,this.tget);
			}
		}
	}
	tree.style.display="block";
}
function openTag(a,t) {
	if(t.style.display=="block") {
		t.style.display="none";
		a.className="folder";
		a.getElementsByTagName('span')[0].style.background = 'url(img/nav_bg.gif) no-repeat';
		a.style.padding='4px 0px 3px 0px';
	} else {
		t.style.display="block";
		a.className="";
		a.getElementsByTagName('span')[0].style.background = 'url(img/nav_bg1.gif) no-repeat 0px 4px ';
		a.style.padding='4px 0px 4px 0px';
	}
}


function checkTdBg(obj){ 

                var obj_td = document.getElementsByTagName('li'); 
				var classname
                for( var int_i = 0 ; int_i < obj_td.length ; int_i ++ ){ 
					classname=obj_td[int_i].className;
                    if( obj_td[int_i]== obj){ 
                        obj_td[int_i].className = 'tdBg'; 
						
                    } 
                    else{ 
						if(obj_td[int_i].className == 'tdBg'){
							 obj_td[int_i].className = ''; 
							}
						else{
							obj_td[int_i].className = classname; 
							}
						
                      
                    } 
                } 
            } 

function ShowHidden(ccname,bbname){
	var objElement;	
	objElement = document.getElementById(ccname);	
	if(objElement.style.display == 'none'){
	  objElement.style.display = 'block';
	  bbname.className='';
	  bbname.innerHTML='<b>隐藏搜索条件</b>';
	  var isOpen=true;
	} else {
	  objElement.style.display = 'none';
	  bbname.className='disn';
	  bbname.innerHTML='<b>查看搜索条件</b>';
	  var isOpen=false;
	}
	return isOpen;
}


