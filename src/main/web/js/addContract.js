$(document).ready(function () {
    departmentService.findAllDepartment(getResult);
    contractTypeService.findAll(getContractType);
    contractNameService.findAll(getContractName);

    $('#uploadify').uploadify({
        uploader: 'Attachment.action',           // 服务器端处理地址
        swf: '/contract/uploadify/uploadify.swf',    // 上传使用的 Flash
        width: 60,                          // 按钮的宽度
        height: 23,                         // 按钮的高度
        buttonText: "选择文件",              // 按钮上的文字
        buttonCursor: 'hand',               // 按钮的鼠标图标
        fileObjName: 'uploadify',            // 上传参数名称 后台<span style="color:#ff0000;">action里面的属性uploadify</span>
        // 两个配套使用
        fileTypeExts: "*.jpg;*.png;*.doc;*.pdf",             // 扩展名
        fileTypeDesc: "请选择 jpg png doc pdf文件",     // 文件说明
        auto: false,                // 选择之后，自动开始上传
        multi: true,               // 是否支持同时上传多个文件
        queueSizeLimit: 5,          // 允许多文件上传的时候，同时上传文件的个数
        removeCompleted: false,  //上传完后保存列表
        fileSizeLimit: 10240  //限制上传文件大小10M（单位：KB）
    });

    //合并行
    mergeTable("t1", 0, 4);

    //placeholder功能的实现
    var doc = document,
        inputs = doc.getElementsByTagName('input'),
        supportPlaceholder = 'placeholder'in doc.createElement('input'),

        placeholder = function (input) {
            var text = input.getAttribute('placeholder'),
                defaultValue = input.defaultValue;
            if (defaultValue == '') {
                input.value = text
            }
            input.onfocus = function () {
                if (input.value === text) {
                    this.value = ''
                }
            };
            input.onblur = function () {
                if (input.value === '') {
                    this.value = text
                }
            }
        };

    if (!supportPlaceholder) {
        for (var i = 0, len = inputs.length; i < len; i++) {
            var input = inputs[i],
                text = input.getAttribute('placeholder');
            if (input.type === 'text' && text) {
                placeholder(input)
            }
        }
    }

    enterKey();
    getType();
});

function getContract() {
    var contractId = $('#contractId')[0].value;
    var reg = /^[A-Z|a-z]{2}\d{2}-[A-Z|a-z]{2,4}-\d{3,4}$/;
    if (!reg.test(contractId)) {
        alert("请输入正确的合同编号");
        return false;
    }
    $.ajax({
        type: 'POST',
        url: "Attachment!contractIdGet.action",
        data: {
            contract: contractId
        },
        dataType: "json"
    })
}

/* tableId:表格的ID,mergeColIndex:需要合并的列序号,beginRowIndex:合并开始的行序号 */
function mergeTable(tableId, mergeColIndex, beginRowIndex) {
    var table = document.getElementById(tableId);
    if (table != null) {
        var totalRows = table.rows.length;
        for (var i = beginRowIndex; i < totalRows; i++) {
            var rowSpan = 1;
            var cell = table.rows[i].cells[mergeColIndex].innerHTML;
            for (var j = i + 1; j < totalRows; j++) {
                if (table.rows[j].cells[mergeColIndex].innerHTML == cell) {
                    rowSpan++;
                    table.rows[i].cells[mergeColIndex].rowSpan = rowSpan;//设置rowSpan
                    table.rows[j].cells[mergeColIndex].style.display = "none";//当前行被合并了，所以这里设置为none
                } else {
                    break;
                }
            }
            i = i + rowSpan - 1;//跳到最后一个相同的行，然后再+1就是另一个不相同的行啦
        }
    }
}

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
    $("#displayTr2").css('display', 'none');
}
function displayNone() {
    $("#displayTr2").css('display', '');
    $("#displayTr").css('display', 'none');
    $("#budgetType").val("");
    $("#budgetMoney").val("");
}

// 获取目录对应格式
function verifyFileName() {
    // 解决中文乱码问题的方法
    $("#fileName")[0].value = $("#attachmentName")[0].value;
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
    /*} else if ($("#begin")[0].value == "") {
        alert("请填写合同生效时间!");
        return false;
    } else if ($("#end")[0].value == "") {
        alert("请填写合同失效时间!");
        return false;*/
    } else if ($("#count")[0].value == "") {
        alert("请填写合同原件份数!");
        return false;
    } else if ($("#mainContent")[0].value == "") {
        alert("请填写合同主要内容!");
        return false;
    } else if ($("#contractOperator")[0].value == "") {
        alert("请填写合同经办人!");
        return false;
    } else if ($("#guaranteePeriod")[0].value == "") {
        alert("请选择质保期!");
        return false;
    } else if ($("#biddingType")[0].value == "") {
        alert("请选择供应商确定方式!");
        return false;
    } else if ($("#contractName")[0].value == "") {
        alert("请选择合同名称!");
        return false;
    } else if ($("#contractType")[0].value == "") {
        alert("请选择合同类别!");
        return false;
    } else if ($("#invoice")[0].value == "") {
        alert("请选择发票类别!");
        return false;
    } else if ($("#supportFile")[0].value == "") {
        alert("请选择支持文件!");
        return false;
    } else if ($("#budgetIN")[0].value == "" || $("#budgetOUT")[0].value == "") {
        alert("请选择预算类别!");
        return false;
    } else if ($("#paymentDate")[0].value == "yyyy-mm-dd,yyyy-mm-dd") {
        alert("请选择应收（付）时间!");
        return false;
    } else if ($("#dep")[0].value == "") {
        alert("请选择经办部门!");
        return false;
    } else if ($("#paymentTypeA")[0].value == "") {
        alert("请选择款项类型!");
        return false;
    } else
        return true;
}

function checkNum(num) {
    if (!/^\d+(\.\d+)?$/.test(num)) {
        alert("请输入正确的金额");
        return false;
    }
    return true;
}

function checkNum2(num) {
    if (!/^\d+(\.\d+)?$/.test(num)) {
        alert("请输入正确的金额");
        return false;
    }
    $('#receivableOrPayMoney')[0].value = num;
    return true;
}

function others(selectedVal) {
    if (selectedVal == 9) {
        $("#paymentType").css('display', '');
    }
}

function checkDate() {
    var payment = new Array;
    if ($("#paymentDate")[0].value.indexOf(",") > 0)
        payment = $("#paymentDate")[0].value.split(",");
    else if ($("#paymentDate")[0].value.indexOf("，") > 0)
        payment = $("#paymentDate")[0].value.split("，");

    var reg = /^\d{4}-\d{1,2}-\d{1,2}$/;
    for (var i = 0; payment.length; i++) {
        alert("d" + payment[i].trim());
        if (payment[i].trim() != "" && !reg.test(payment[i])) {
            alert("请输入正确的付款时间");
            return false;
        }
    }
    return true;
}


var elemType;//选中控件类型
var elemObj; //选中控件对象
function enterKey() {
//获取页面所有控件
    var elems = document.forms[0].elements;
    var len = elems.length;
//设置回车键跳跃的控件类型
    var elemTypeAry = ["text", "textarea", "select-one", "button", "radio", "checkbox"];
    var elemTypeStr = elemTypeAry.join("");
    for (var i = 0; i < len; i++) {
//获取控件类型
        elemType = elems[i].type;
//若是列表中的类型控件则为其注册事件
        if (elemTypeStr.indexOf(elemType) != -1) {
//控件获得焦点后为全局变量赋值
            elems[i].onfocus = function () {
//若当前按钮为typeBtn则不为其注册onfocus事件
                if (this.id == "typeBtnId")return;
//获得当前控件类型
                elemType = this.type;
//获得当前控件对象
                elemObj = this;
            }
//若按键为Enter则改为Tab即跳到下一控件
            elems[i].onkeydown = function () {
                if (event.keyCode == 13)event.keyCode = 9
            };
        }
    }
}
//显示当前选中控件类型
function getType() {
//由于点击按钮后按钮获得焦点所需要将焦点重置为之前的控件上
    elemObj.focus();
}