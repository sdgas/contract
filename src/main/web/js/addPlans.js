/**
 * Created by 斌 on 2014/8/16.
 */
/**
 * 新增行 若添加多行则使用for循环
 */
function addRow() {

    // 得到表格对象
    var tableObj = document.getElementById("mainTb");
    // 得到tbody对象
    var tableBodyObj = document.getElementById("mainBody");
    var newRowObj = document.createElement("tr");
    var newCheckBox = document.createElement("td");
    var newtext1 = document.createElement("td");
    var newtext2 = document.createElement("td");
    var newtext3 = document.createElement("td");
    var newtext4 = document.createElement("td");
    var newtext5 = document.createElement("td");
    var newtext6 = document.createElement("td");
    newCheckBox.innerHTML = '<input type="Checkbox" name="checkbox" onclick = "checkBoxSel()">';
    newtext1.innerHTML = '<input name="materialIds" type="text" class="search" ondblclick="findMaterial(this.id)" id="materialId'
    + num + '"/>';
    newtext2.innerHTML = '<input type="text" readonly="readonly" id="materialName'
    + num + '"/>';
    newtext3.innerHTML = '<input type="text" readonly="readonly" id="standard'
    + num + '"/>';
    newtext4.innerHTML = '<input type="text" readonly="readonly" id="materialType'
    + num + '"/>';
    newtext5.innerHTML = '<input type="text" readonly="readonly" id="materialUnit'
    + num + '"/>';
    newtext6.innerHTML = '<input type="text" name="amount" onchange="check(this.id)" id="amount'
    + num + '">';
    // 新增的tr节点下增加td节点
    newRowObj.appendChild(newCheckBox);
    newRowObj.appendChild(newtext1);
    newRowObj.appendChild(newtext2);
    newRowObj.appendChild(newtext3);
    newRowObj.appendChild(newtext4);
    newRowObj.appendChild(newtext5);
    newRowObj.appendChild(newtext6);
    // tbody节点下增加tr节点
    tableBodyObj.appendChild(newRowObj);

    // objects为json数据源对象
    $(".search").autocomplete(objects, {
        delay: 100,
        minChars: 2, // 表示在自动完成激活之前填入的最小字符
        max: 50, // 表示列表里的条目数
        matchContains: true, // 表示包含匹配,相当于模糊匹配
        scrollHeight: 200, // 表示列表显示高度,默认高度为180

        formatItem: function (row) {
            return row.name;
        },
        formatMatch: function (row) {
            return row.name;
        },
        formatResult: function (row) {
            return row.value;
        }
    });

    num += 1;
}

// 批量删除指定的行
function deleteRow() {
    var Tblen;
    // 得到所有 checkbox 对象
    var checkbox = document.getElementsByName("checkbox");
    // 得到所有提交的checkbox个数
    var checkboxlen = checkbox.length;
    // 得到删除按钮对象
    var delbutton = document.getElementById("delete");
    for (var i = 0; i < checkboxlen; i++) {
        // 得到表格行数
        Tblen = this.mainTb.rows.length;
        // 最终保留一行记录
        if (Tblen == 2) {
            document.getElementsByName("checkbox")[0].checked = false;
            alert("最后一种材料不能删除！");
            // 删除按钮不可用
            delbutton.disabled = true;
            return false;
        }
        // 如果当前的checkbox选中：删除当前节点，由于删除节点后，下面的节点上移，游标（记录行指针）也应该上移
        if (checkbox[i].checked) {
            document.getElementById("mainTb").deleteRow(i + 1);
            // 游标（记录行指针）上移
            i--;
        }
        // 重新统计checkbox个数
        checkboxlen = checkbox.length;

        if (checkboxlen == 1)
            break;
    }
    // 删除操作后按钮状态是不可用
    delbutton.disabled = true;
    num -= 1;
}

// checkbox按钮单击事件处理函数：是否至少选中一行记录，是可以删除操作；如果没有选择，删除按钮不可用
function checkBoxSel() {
    // 得到删除按钮对象
    var delbutton = document.getElementById("delete");
    if (checkselect()) {
        delbutton.disabled = false;
    } else {
        delbutton.disabled = true;
    }
}

function checkselect() {
    // 得到所有 checkbox 对象
    var checkbox = document.getElementsByName("checkbox");
    // 得到所有提交的checkbox个数
    var chklength = checkbox.length;
    var flag = false;
    for (var i = 0; i < chklength; i++) {
        if (checkbox[i].checked == true) {
            flag = true;
            break;
        }
    }
    if (flag == true) {
        return true;
    } else {
        return false;
    }
}

// 页面加载body内容时执行
function loadpage() {
    var delbutton = document.getElementById("delete");

    // 初始“删除”按钮不可用
    delbutton.disabled = true;
}

function search() {
    // 模糊匹配
    $(".search").autocomplete(objects, {
        delay: 100,
        minChars: 2, // 表示在自动完成激活之前填入的最小字符
        max: 50, // 表示列表里的条目数
        matchContains: true, // 表示包含匹配,相当于模糊匹配
        scrollHeight: 200, // 表示列表显示高度,默认高度为180

        formatItem: function (row) {
            return row.name;
        },
        formatMatch: function (row) {
            return row.name;
        },
        formatResult: function (row) {
            return row.value;
        }
    });
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

// function IsValidDate(DateStr) {
// var myDate = new Date();
// var month = myDate.getMonth() > 9 ? myDate.getMonth() + 1 : "0" +
// (myDate.getMonth() + 1);
// var day = myDate.getDate() > 9 ? myDate.getDate() : "0" + myDate.getDate();
// var date = myDate.getFullYear() + "" + month + "" + day;
// if (DateStr < date)
// alert("需求日期必须大于当前日期！");
// }

function getAllMaterial(materials) {
    // 组装json数据源对象objects
    for (var i = 0; i < materials.length; i++) {
        var na = materials[i].materialId + "，" + materials[i].materialName
            + "，" + materials[i].materialType;
        var val = materials[i].materialId;
        if (na.charAt(na.length - 1) == "，")
            na = na.substring(0, na.length - 1);
        objects[i] = {
            name: na,
            value: val
        };
    }
}

function findMaterial(m) {
    index = m.charAt(m.length - 1);
    var materialId = $("#materialId" + index).attr("value");
    if (materialId != "")
        materialService.findByMaterialId(materialId, getMaterialResult);
}

function getMaterialResult(material) {
    $("#materialName" + index).val(material.materialName);
    $("#standard" + index).val(material.standard);
    $("#materialType" + index).val(material.materialType);
    $("#materialUnit" + index).val(material.materialUnit);
}

function check(a) {
    var i = a.charAt(a.length - 1);
    var r = /^[1-9]?[0-9]*[\.]?[0-9]*$/;
    if (!r.test($("#amount" + i).attr("value"))) {
        $("#amount" + i).val("");
        alert("物料数量只能为数字！");
    }
}

function checkReq() {
    var project = $('#project').attr("value");
    var dep = $('#dep').find("option:selected").val();
    var dt = $('#demandType').find("option:selected").val();
    var demandDate = $('#demandDate').attr("value");
    if (project == "") {
        alert("请输入项目名称！");
        return false;
    } else if (dep == "") {
        alert("请选择需求部门！");
        return false;
    } else if (demandDate == "") {
        alert("请输入需求日期！");
        return false;
    } else if (dt == "") {
        alert("请选择需求类型！");
        return false;
    } else {
        return true;
    }
}

// 获取目录对应格式
function verifyFileName() {
    // 解决中文乱码问题的方法
    var uploadFile = document.forms[0].attachment.value;
    document.forms[0].fileName.value = uploadFile;
    if ("" != uploadFile) {
        uploadFile = uploadFile.substring(uploadFile.lastIndexOf(".") + 1)
            .toLowerCase();
        if (uploadFile != "rar" && uploadFile != "zip") {
            document.forms[0].fileName.value = "";
            alert("请上传压缩包！后缀名为：zip、rar");
        }
    }
}