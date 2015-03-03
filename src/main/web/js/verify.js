function verifySamePassword() {
    var password = $("#password").val();
    var sure_password = $("#sure_password").val();
    var regStr = /^\S+$/;
    if (password == "" || sure_password == "") {
        $("#password_result").html("密码不允许为空");
        return false;
    } else if (!regStr.test(password)) {
        $("#password_result").html("密码不允许有空格");
        return false;
    } else if (sure_password != password) {
        $("#password_result").html("密码不一致");
        return false;
    } else {
        $("#password_result").html("");
        return true;
    }
}

function verifyPassword() {
    var password = $("#original_password").val();
    var regStr = /^\S+$/;
    if (password == "") {
        $("#password_result1").html("原密码不允许为空");
        return false;
    }
    if (!regStr.test(password)) {
        $("#password_result1").html("原密码不允许有空格");
        return false;
    } else {
        $("#password_result1").html("");
        return true;
    }

}

function checkPassword(objForm) {
    return verifyPassword() && verifySamePassword();
}