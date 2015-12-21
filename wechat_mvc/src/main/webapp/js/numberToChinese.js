function number_to_chinese(num)
{
	var newString=""; 
	for(var i = num.length - 1; i >= 0; i--)
	{
		var tmpnewchar = "";
		perNO = num.charAt(i);
		switch(perchar){
   			case "0": tmpnewchar="零" + tmpnewchar ;break;
    		case "1": tmpnewchar="一" + tmpnewchar ;break;
   			case "2": tmpnewchar="二" + tmpnewchar ;break;
    		case "3": tmpnewchar="三" + tmpnewchar ;break;
    		case "4": tmpnewchar="四" + tmpnewchar ;break;
    		case "5": tmpnewchar="五" + tmpnewchar ;break;
    		case "6": tmpnewchar="六" + tmpnewchar ;break;
    		case "7": tmpnewchar="七" + tmpnewchar ;break;
    		case "8": tmpnewchar="八" + tmpnewchar ;break;
    		case "9": tmpnewchar="九" + tmpnewchar ;break;
    	}
    	
    	switch(num.length-i-1){
    		case 0: tmpnewchar = tmpnewchar +"张" ;break;
    		case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"十" ;break;
    		case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"百" ;break;
    		case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"千" ;break;
   		 	case 4: tmpnewchar= tmpnewchar +"万" ;break;
    		case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"十" ;break;
    		case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"百" ;break;
    		case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"千" ;break;
    		case 8: tmpnewchar= tmpnewchar +"亿" ;break;
    		case 9: tmpnewchar= tmpnewchar +"十" ;break;
    	}
    	
    	newString = tmpnewchar + newString;
	}
	
	return newString;
}