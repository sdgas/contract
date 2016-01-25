//获取目录对应格式
function verifyFileName() {
    //解决中文乱码问题的方法
    var uploadFile = document.forms[0].upload.value;
    if ("" != uploadFile) {
        uploadFile = uploadFile.substring(uploadFile.lastIndexOf(".") + 1).toLowerCase();
        if (uploadFile != 'rar') {
            $("#sufix_result").html("上传文件格式错误，请上传rar文件!");
            return false;
        }
    }
}

function categoryMessage(message) {
    $("#sufix_result").html(message);
}

function checkFile(objForm) {
    var form = objForm;
    var uploadFile = form.upload.value;

    if (uploadFile != "" && uploadFile != null) {
        if (document.getElementById("sufix_result").innerHTML.toString() != "") {
            return false;
        }
        form.fileName.value = uploadFile.substring(uploadFile.lastIndexOf("\\") + 1).toLowerCase();
    } else {
        $("#sufix_result").html("没有上传文件");
        return false;
    }
    return true;

}






