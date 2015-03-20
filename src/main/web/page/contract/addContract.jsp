<%--
  Created by IntelliJ IDEA.
  User: 120378
  Date: 2015/3/05
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>新增合同</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content－Type" content="text/html;charset=UTF-8">
    <meta http-equiv="window-target" content="_top">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <link rel="stylesheet" href="<%=basePath%>css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/addContract.js"></script>

    <%--uploadify--%>
    <script type="text/javascript" src="<%=basePath%>uploadify/jquery.uploadify.js"></script>
    <script type="text/javascript" src="<%=basePath%>uploadify/jquery.uploadify.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>uploadify/uploadify.css">
    <%--加载dwr--%>
    <script src='<%=basePath%>dwr/util.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/engine.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/interface/departmentService.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/interface/contractTypeService.js' type="text/javascript"></script>
    <script src='<%=basePath%>dwr/interface/contractNameService.js' type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            departmentService.findAllDepartment(getResult);
            contractTypeService.findAll(getContractType);
            contractNameService.findAll(getContractName);

            $('#uploadify').uploadify({
                uploader: 'Attachment.action',           // 服务器端处理地址
                swf: '<%=basePath%>uploadify/uploadify.swf',    // 上传使用的 Flash
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
        });

        function getContract() {
            var contractId = $('#contractId')[0].value;
            $.ajax({
                type: 'POST',
                url: "Attachment!contractIdGet.action",
                data: {
                    contract: contractId
                },
                dataType: "json"
            })
        }
    </script>
    <style type="text/css">
        /*修改进度条长度*/
        .uploadify-queue {
            width: 300px;
        }
    </style>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="Contract.action" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td colspan="5" align="center">
                    <span style="font-size: x-large">新增合同</span>
                </td>
            </tr>
            <tr>
                <td colspan="5"></td>
            </tr>
            <tr>
                <td>合同名称：</td>
                <td>
                    <select name="contractName" id="contractName"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">合同编号：</td>
                <td><input type="text" name="contractId" id="contractId" onchange="getContract()"></td>
            </tr>
            <tr>
                <td>合同类别：</td>
                <td>
                    <select name="contractType" id="contractType"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td>预算计划：</td>
                <td>
                    <input type="radio" name="budget" value="0" onclick="display()">预算内
                    <input type="radio" name="budget" value="1" onclick="displayNone()">预算外
                </td>
            </tr>
            <tr style="display:none" id="displayTr">
                <td>预算类别：</td>
                <td>
                    <input type="text" name="budgetType">
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td>预算剩余金额：</td>
                <td>
                    <input type="text" name="budgetMoney" onchange="checkNum(this.value)">元
                </td>
            </tr>
            <tr>
                <td>合同属性：</td>
                <td>
                    <input type="radio" name="contractProperty" value="0">新签
                    <input type="radio" name="contractProperty" value="1">续签
                    <input type="radio" name="contractProperty" value="2">改签
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">合同原件份数：</td>
                <td><input type="text" name="count" id="count" onchange="checkNum(this.value)"></td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">签约对象：</td>
                <td colspan="4">
                    <textarea cols="65" rows="1" name="contractSignCompany" type="text"
                              id="contractSignCompany"></textarea>
                </td>
            </tr>
            <tr>
                <td style="width: 120px">供应商确定方式：</td>
                <td><select name="biddingType"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px" id="biddingType">
                    <option value="" style="text-align: center"> ---------请选择---------</option>
                    <option style="text-align: center" value="0">公开招标</option>
                    <option style="text-align: center" value="1">依法邀请招标</option>
                    <option style="text-align: center" value="2">内部邀请招标</option>
                    <option style="text-align: center" value="3">直接发包</option>
                    <option style="text-align: center" value="4">询价比价</option>
                    <option style="text-align: center" value="5">其他</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>经办部门：</td>
                <td>
                    <select name="department" id="dep"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">经办人：</td>
                <td><input type="text" name="contractOperator" id="contractOperator"></td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同生效日期：</td>
                <td>
                    <input type="text" name="contractBeginDate" id="begin" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen'})">
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">合同到期日期：</td>
                <td>
                    <input type="text" name="contractEndDate" id="end" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen'})">
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同主要内容：</td>
                <td colspan="4">
                    <textarea cols="65" rows="3" name="mainContent" id="mainContent"></textarea>
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同签订金额：</td>
                <td>
                    <span style="color: #ab1e1e">单价金额：</span>
                    <input type="text" name="unit_price" maxlength="12" id="unit_price"
                           onchange="checkNum(this.value)" style="width: 60px" value="0.0"> 元
                </td>
                <td>&nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">合同总金额：</td>
                <td>
                    <input type="text" name="contractMoney" maxlength="12" id="contractMoney"
                           onchange="checkNum(this.value)" style="width: 160px"> 元
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同应收（付）款金额:</td>
                <td>
                    <input name="receiveOrPay" type="radio" value="0">付款
                    <input name="receiveOrPay" type="radio" value="1">收款
                    <input type="text" name="receivableOrPayMoney" maxlength="12" onchange="checkNum(this.value)"
                           style="width: 105px">元
                </td>
                <td>&nbsp; &nbsp;</td>
                <td>款项类型：</td>
                <td>
                    <input type="text" name="#">
                </td>
            </tr>
            <%--todo:未确定--%>
            <tr>
                <td style="color: #ab1e1e">结算金额:</td>
                <td>
                    <input name="receiveOrPay" type="radio" value="0">付款
                    <input name="receiveOrPay" type="radio" value="1">收款
                    <input type="text" name="receivableOrPayMoney" maxlength="12" onchange="checkNum(this.value)"
                           style="width: 105px">元
                </td>
                <td>&nbsp; &nbsp;</td>
                <td>是否超合同结算：</td>
                <td>
                    <input name="#" type="radio" value="2">是，已审核
                    <input name="#" type="radio" value="1">是，未审核
                    <input name="#" type="radio" value="0" checked="checked">否
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">质保期：</td>
                <td>
                    <select name="guaranteePeriod" style="font-family: '微软雅黑';font-size: 16px;width: 180px"
                            id="guaranteePeriod">
                        <option value="" style="text-align: center">---------请选择---------</option>
                        <option value="12" style="text-align: center">无须质保</option>
                        <option value="0" style="text-align: center">半年</option>
                        <option value="1" style="text-align: center">一年</option>
                        <option value="2" style="text-align: center">两年</option>
                        <option value="3" style="text-align: center">三年</option>
                        <option value="4" style="text-align: center">四年</option>
                        <option value="5" style="text-align: center">五年</option>
                        <option value="6" style="text-align: center">六年</option>
                        <option value="7" style="text-align: center">七年</option>
                        <option value="8" style="text-align: center">八年</option>
                        <option value="9" style="text-align: center">九年</option>
                        <option value="10" style="text-align: center">十年</option>
                        <option value="11" style="text-align: center">终身</option>
                    </select>
                </td>
                <td>&nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">质保金：</td>
                <td>
                    <input type="text" name="performanceBond" value="0.00" maxlength="10" id="performanceBond"
                           onchange="checkNum(this.value)" style="width: 165px">元
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td colspan="4">
                    <textarea cols="65" rows="3" name="remark"></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>附件：</td>
                <td colspan="3">
                    <input type="file" name="uploadify" id="uploadify"/>
                    <a href="javascript:$('#uploadify').uploadify('upload', '*')">上传文件</a>
                </td>

            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td colspan="5" align="center">
                    <input type="submit" value="添加" onclick="return confirm();">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>
