function getResult(departments) {
    var select_list = '<option value="" style="text-align: center">'
        + '---------请选择---------' + '</option>';
    for (var i = 0; i < departments.length; i++) {
        select_list += '<option style="text-align: center" value="'
        + departments[i].departmentId + '">'
        + departments[i].departmentName + "</option>";
    }
    $("#dep").html(select_list);
}

function getContractType(contractTypes) {
    var select_list = '<option value="" style="text-align: center">'
        + '---------请选择---------' + '</option>';
    for (var i = 0; i < contractTypes.length; i++) {
        select_list += '<option style="text-align: center" value="'
        + contractTypes[i].id + '">'
        + contractTypes[i].contractType + "</option>";
    }
    $("#contractType").html(select_list);
}

function getContractName(contractNames) {
    var select_list = '<option value="" style="text-align: center">'
        + '---------请选择---------' + '</option>';
    for (var i = 0; i < contractNames.length; i++) {
        select_list += '<option style="text-align: center" value="'
        + contractNames[i].id + '">'
        + contractNames[i].contractName + "</option>";
    }
    $("#contractName").html(select_list);
}

function display() {
    $("#displayTr").css('display', '');
}
function displayNone() {
    $("#displayTr").css('display', 'none');
}

// 获取目录对应格式
function verifyFileName() {
    // 解决中文乱码问题的方法
    document.forms[0].fileName.value = document.forms[0].attachmentName.value;
}

function confirm() {
    if ($("#contractId")[0].value == "") {
        alert("请填写合同编号!");
        return false;
    } else if ($("#contractSignCompany")[0].value == "") {
        alert("请填写签约公司!");
        return false;
    } else if ($("#contractMoney")[0].value == "") {
        alert("请填写合同总金额!");
        return false;
    } else if ($("#performanceBond")[0].value == "") {
        alert("请填写合同质保金!");
        return false;
    } else if ($("#begin")[0].value == "") {
        alert("请填写合同生效时间!");
        return false;
    } else if ($("#end")[0].value == "") {
        alert("请填写合同失效时间!");
        return false;
    } else if ($("#count")[0].value == "") {
        alert("请填写合同原件份数!");
        return false;
    } else if ($("#mainContent")[0].value == "") {
        alert("请填写合同主要内容!");
        return false;
    } else if ($("#contractOperator")[0].value == "") {
        alert("请填写合同经办人!");
        return false;
    } else
        return true;
}

function checkNum(num) {
    return /^[0-9]+(\\.[0-9]{1,2})?$/.test(num);
}