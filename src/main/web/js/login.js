//最大化窗口
var screenWidth = screen.width;
var screenHeight = screen.height;
// 考虑到XP,IE7等系统,moveTo必须在resizeTo前面
window.moveTo(0, 0); // 移动窗口
window.resizeTo(screenWidth, screenHeight - 25); // 调整窗口大小

$().ready(function (e) {

    $(window).bind("resize scroll ready", function () {
        var winH = $(window).height();
        var conH = $(".main_con").height();
        var t = winH / 2 - conH / 2;
        $(".main_con").css({
            top: t
        });
    });

    $(function () {
        $('#slider').carouFredSel({
            items: 1,
            scroll: 1,
            scroll: {
                duration: 800,
                fx: 'slide',
                timeoutDuration: 4500,
                pauseOnHover: "resume"
            },
            pagination: '#slider_page'
        });
    });

});

function loginidclick(obj) {
    if ((window.event.keyCode == 13) || (window.event.keyCode == 9)) {
        window.event.keyCode = 9;
    }
}

function loginpassclick(obj) {
    if ((window.event.keyCode == 13) || (window.event.keyCode == 9)) {
        window.event.keyCode = 9;
        submitForm();
    }
}

function submitForm() {

    $('#divMsg').text("");

    if ($('#userId').val() == "") {
        $('#divMsg').text("用户名不能为空！");
        $('#userId').focus();
        return false;
    }
    if ($('#password').val() == "") {
        $('#divMsg').text("密码不能为空！");
        $('password').focus();
        return false;
    }

    document.getElementById("ffLogin").submit();
}