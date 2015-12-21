/**
 * 回到顶部
 */
$(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
        $(".wap-fiexd-box").addClass('wap-fiexd-show');
    }else{
        $(".wap-fiexd-box").removeClass('wap-fiexd-show');
    };
});
$("#scroll_Top").click(function(){$("html,body").animate({scrollTop: 0},300)});