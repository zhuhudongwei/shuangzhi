 $(document).ready(function(){
				
				//保存文本输入框和匹配的结果列表的位置，当文本输入框改变位置时，结果列表的位置也能跟着改变
				var _input_begin_offset_top = $("#input-friend-name").offset().top;
				var _matched_current_position_top = $("#matched-friend-list").css("top");
				var _receverList_height = $("#receiverList").height() + 2;
				var friends;
				var canDelete = false;
				
                $("#receiverList").click(function(){
					
					//让文本输入框获得焦点						
                    $(".input-friend-name").focus();
					var _top = $("#input-friend-name").offset().top - _input_begin_offset_top + _receverList_height;
					$("#matched-friend-list").css("top", _top + "px");
	
		$(".kuang_b05").hide();	
	
					//根据服务器端返回的数据，输出匹配的结果列表
                    
                });
				
				
				//处理键盘事件
				$("#input-friend-name").keyup(function(event) {
					
					//记录当前选中项，和下一个选中项
					var currentRow;
					var nextRow;
					switch(event.keyCode) {
						case 8:
						
							//alert($("#input-friend-name").val().length);
							if($(".token").length == 0) 
								break;
							if(canDelete) {
								var last = $(".token").length - 1;
								$($(".token")[last]).remove();
								$("#matched-friend-name").remove();
								if($("#input-friend-name").val().length == 0) {
									canDelete = true;
								} else {
									canDelete = false;
								}
							}
							if($("#input-friend-name").val().length == 0) {
								canDelete = true;
							} else {
								canDelete = false;
							}
							break;
							
						//处理向上箭头被按下后的事件	
						case 38:
							if ($(".matched-friend-list li").length > 0) {
								$(".matched-friend-list li").each(function(index, domElemet){
									if ($(this).attr("class") == "hover") {
										currentRow = index;
										return;
									}
								});
								if (currentRow == 0) 
									nextRow = $(".matched-friend-list li").length - 1;
								else 
									nextRow = currentRow - 1;
								
								//先清除列表上的hover类，再添加hover类	
								$(".matched-friend-list li").removeClass("hover");
								
								$($(".matched-friend-list li")[nextRow]).addClass("hover");
							}
							break;
						
						//处理向下箭头被按下后的事件(移动匹配的列表)
						case 40:
							if ($(".matched-friend-list li").length > 0) {
								$(".matched-friend-list li").each(function(index, domElemet){
									if ($(this).attr("class") == "hover") {
										currentRow = index;
										return;
									}
								});
								nextRow = (currentRow + 1) % $(".matched-friend-list li").length;
								
								//先清除列表上的hover类，再添加hover类	
								$(".matched-friend-list li").removeClass("hover");
								
								$($(".matched-friend-list li")[nextRow]).addClass("hover");
							}
							break;
						
						//处理Enter键被按下的事件，将当前被选择的条目，加入到输入框中
						case 13:
							if($(".matched-friend-list li").length == 0)
								break;
							$(".matched-friend-list li").each(function(index,domElemet) {
								if($(this).attr("class") == "hover") {
									currentRow = index;
									return;
								}
							});
							var _data = friends[currentRow];
							var isRepeat = false;
							var _friend = $(this);
							if ($(".token").length == 0) {
								isRepeat = false;
							}
							else {
								$(".token").each(function(){
									if ($(this).attr("fid") == _data.id) {
										isRepeat = true;
										return;
									} 
								});
							}
							if (!isRepeat) {
								$("<a fid=" + _data.id + " class='token' href='#'><span><span><span><span>" + _data.name + "<span id='x_" + _data.id + "' class='x'>&nbsp</span></span></span></span></span></a>").insertBefore("#input-friend-name");
								$("<input type='hidden' name='receiverIds' value='" + _data.id + "'/>").insertBefore("#input-friend-name");
								$("<input type='hidden' name='receiverNames' value='" + _data.name + "'/>").insertBefore("#input-friend-name");
								$("#x_" + _data.id).bind("click", _data.id, deleteToken);
							}
							var _top = $("#input-friend-name").offset().top - _input_begin_offset_top + _receverList_height;
							$("#matched-friend-list").css("top", _top + "px");

							$("#input-friend-name").val("");
							$(".matched-friend-list").hide();
							$(".matched-friend-list ul").empty();
							break;
						default:
							canDelete = false;
							$("#matched-friend-name").remove();
							$.postJSON("listFriendsByName.action",{"name":$("#input-friend-name").val()},createMatchedFriendList);
							break;
					}
				});
				
				//点击删除的图标时，删除相应的元素，并且停止事件冒泡
				var deleteToken = function(event) {
					$("a[fid='" + event.data + "']").remove();
					event.stopPropagation(); 
				}
				
				$("#multi-select").click(function(){
					if($(".m_of_l").length == 0) {
							createFriendList();
						}
						$(".kuang_b05").slideDown("slow"); 
						
					//checkSelected();
					
					
					
				});
				
				var checkSelected = function() {
					$("#all-friend-list dl dd li").removeClass("selected");
					$(".token").each(function () {
						var fid = $(this).attr("fid");
						$("#all-friend-list dl dd li[fid='" + fid +"']").addClass("selected");	
					});
				}
				
				//生成匹配的好友DOM树，并且绑定事件
				var createMatchedFriendList = function (data) {
				//alert($(data));
					if(!data.members)
						return;
					if(data.members.length > 0) {
						$(".matched-friend-list ul").empty();
						friends = data.members;
						$.each(data.members, function(index, data){
	                        if (data) {
	                            $("<li fid='" + data.id + "'><span class='id hidden'>" + data.id + "</span><span class='pinyin hidden'>" + data.pinyin + "</span><span class='name'>" + data.name + "</span></li>").appendTo(".matched-friend-list ul");
								
								//为查询到的结果列表加载hover事件（兼容IE6）
								$(".matched-friend-list li").hover(
									function(){
										$(".matched-friend-list li").removeClass("hover");
										$(this).addClass("hover");
									}, 
									function(){
								});
								
								
	                            $(".matched-friend-list").show();
								
								
								//为结果列表注册鼠标单击事件
								$("#matched-friend-list li[fid='" + data.id + "']").click(function() {
									//标记是否已经有重复的好友被选中
									var isRepeat = false;
									var _friend = $(this);
									//第一次输入时
									if ($(".token").length == 0) {
										isRepeat = false;
									}
									else {
										$(".token").each(function(){
											if ($(this).attr("fid") == _friend.attr("fid")) {
												isRepeat = true;
												return;
											} 
										});
									}
									
									if(!isRepeat) {
										$("<a fid=" + data.id + " class='token' href='#'><span><span><span><span>" + data.name + "<span id='x_" + data.id + "' class='x'>&nbsp</span></span></span></span></span></a>").insertBefore("#input-friend-name");
										$("<input type='hidden' name='receiverIds' value='" + data.id + "'/>").insertBefore("#input-friend-name");
										$("<input type='hidden' name='receiverNames' value='" + data.name + "'/>").insertBefore("#input-friend-name");
										$("#x_" + data.id).bind("click", data.id, deleteToken);
										friends = null;
									}
									
									//根据文本输入框，更新结果列表的位置
									var _top = $("#input-friend-name").offset().top - _input_begin_offset_top + _receverList_height;
									$("#matched-friend-list").css("top", _top + "px");
									$("#input-friend-name").val("");
									$(".matched-friend-list").hide();
									$(".matched-friend-list ul").empty();
								});
	                        }
	                    });
						//结果列表中默认选中第一个结果
						$($(".matched-friend-list li")[0]).addClass("hover");
					} else {
						$(".matched-friend-list ul").empty();
						$("<li><span class='name'>没有符合的结果</span></li>").appendTo(".matched-friend-list ul");
						$(".matched-friend-list").show();
						$($(".matched-friend-list li")[0]).addClass("hover");
					}
				}
				
				
				//生成好友分组的DOM树，并且绑定事件
			var createFriendList = function() {
				$(".kuang_b05").slideDown("slow"); 			
				
						
							
				$.postJSON("listFriendsByName.action",{"name":" "},function(data){
					$.each(data.members, function(index, data){
				var isExist =false;
				$(".token").attr("fid")
				if (!isExist) {
					$(".kuang_b05_m_of").append("<div class='m_of_l'><input type=\"checkbox\" id = "+data.id+" >"+data.name+"&nbsp;&nbsp;</div>");
				}
					});				
				});
			
			/*	$.each(groups, function(index, data) {
						if(data) {
							var groupName = data.name;
						 	var groupDom = $("<dl></dl>");
							var groupNameDom = $("<dt>" + data.name +　"</dt>");
							groupNameDom.toggle(
								function() {
									$(this).toggleClass("expanded");
									$(this).siblings().hide("normal");
								},
								function() {
									$(this).toggleClass("expanded");
									$(this).siblings().show("normal");
								}
							);
							var groupFriendsDom = $("<dd></dd>");
							$.each(data.friends, function(index, data) {
								var groupFriendDom = $("<li class='selected' fid='" + data.id + "'>" + data.name + "</li>");
								groupFriendDom.click(function() {
									if ($(this).attr("class") == "selected") {
//											alert("(奇数次点击--已经被选择了");
											$("a[fid='" + data.id + "']").remove();
											$("a[fid='" + data.id + "']").empty();
										}
										else {
//											alert("奇数次点击--未被选择");
											$("<a fid=" + data.id + " class='token' href='#'><span><span><span><span>" + data.name + "<span id='x_" + data.id + "' class='x'>&nbsp</span></span></span></span></span></a>").insertBefore("#input-friend-name");
											$("<input type='hidden' name='receiverIds' value='" + data.id + "'/>");
											$("<input type='hidden' name='receiverNames' value='" + data.name + "'/>");
											$("#x_" + data.id).bind("click", data.id, deleteToken);
											friends = null;
										}
										$(this).toggleClass("selected");
								});
								groupFriendsDom.append(groupFriendDom);
							});
						}
						groupDom.append(groupNameDom).append(groupFriendsDom).appendTo("#all-friend-list");
					});*/
				}
				
				/*var bindEvent = function() {
					$("#all-friend-list dl dt").toggle (
						function() {
							$(this).toggleClass("expanded");
							$(this).siblings().hide("normal");
						},
						function() {
							$(this).toggleClass("expanded");
							$(this).siblings().show("normal");
						}
					);
					
					$("#all-friend-list dl dd li").toggle (
						function() {
							$(this).toggleClass("selected");
						},
						function() {
							$(this).toggleClass("selected");
						}
					);
				}
				
				$("#all-friend-list dl dd li").blur(function() {
					$(this).hide()
				});*/
            });