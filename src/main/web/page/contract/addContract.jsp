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
        });
    </script>
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
                <td>合同名称</td>
                <td>
                    <select name="contractName" id="contractName"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">合同编号：</td>
                <td><input type="text" name="contractId" id="contractId"></td>
            </tr>
            <tr>
                <td>合同类别:</td>
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
                <td>合同属性:</td>
                <td>
                    <input type="radio" name="contractProperty" value="0">新签
                    <input type="radio" name="contractProperty" value="2">改签
                    <input type="radio" name="contractProperty" value="1">续签
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">签约对象：</td>
                <td>
                    <input name="contractSignCompany" type="text" id="contractSignCompany">
                </td>
            </tr>
            <tr>
                <td>经办部门：</td>
                <td>
                    <select name="department" id="dep"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">合同金额：</td>
                <td><input type="text" name="contractMoney" maxlength="12" id="contractMoney"
                           onchange="checkNum(this.value)">元
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">质保金：</td>
                <td><input type="text" name="performanceBond" value="0.00" maxlength="10" id="performanceBond"
                           onchange="checkNum(this.value)">元
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <%--TODO:一行--%>
                <td style="color: #ab1e1e">经办人：</td>
                <td><input type="text" name="contractOperator" id="contractOperator"></td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同生效时间：</td>
                <td>
                    <input type="text" name="contractBeginDate" id="begin" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen'})">
                </td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td style="color: #ab1e1e">合同到期时间：</td>
                <td>
                    <input type="text" name="contractEndDate" id="end" class="Wdate"
                           onfocus="WdatePicker({skin:'whyGreen'})">
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同原件份数：</td>
                <td><input type="text" name="count" id="count" onchange="checkNum(this.value)"></td>
                <td>&nbsp; &nbsp; &nbsp;</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同主要内容：</td>
                <td colspan="4">
                    <textarea cols="60" rows="3" name="mainContent" id="mainContent"></textarea>
                </td>
            </tr>
            <%--TODO:Attachment--%>
            <tr>
                <td>附件：</td>
                <td colspan="3">
                    <input type="file" name="attachmentName" onchange="verifyFileName()">
                    <input type="hidden" name="fileName"/>
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td colspan="4">
                    <textarea cols="60" rows="3" name="remark"></textarea>
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
