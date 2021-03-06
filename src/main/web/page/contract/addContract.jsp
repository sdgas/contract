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
            + path;
    String realPath = request.getSession().getServletContext().getRealPath("/");
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

    <link rel="stylesheet" href="<%=basePath%>/css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <script src="<%=basePath%>/js/jquery.min.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/addContract.js"></script>

    <script type="text/javascript" src="<%=basePath%>/js/sufix.js"></script>

    <%--加载dwr--%>
    <script src='<%=basePath%>/dwr/util.js' type="text/javascript"></script>
    <script src='<%=basePath%>/dwr/engine.js' type="text/javascript"></script>
    <script src='<%=basePath%>/dwr/interface/departmentService.js' type="text/javascript"></script>
    <script src='<%=basePath%>/dwr/interface/contractTypeService.js' type="text/javascript"></script>
    <script src='<%=basePath%>/dwr/interface/contractNameService.js' type="text/javascript"></script>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        /*修改进度条长度*/
        .uploadify-queue {
            width: 300px;
        }

        .mergeTable {
            width: 100%;
            border: 1px solid;
            border-collapse: collapse;
            table-layout: fixed;
        }

        .mergeTable th, .mergeTable td {
            border: 1px solid;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="menu">
    <%@ include file="/page/share/menu.jsp" %>
</div>
<div id="content">
    <form action="<%=basePath%>/Contract.action" method="post" enctype="multipart/form-data" onsubmit="return checkFile(this)">
        <table id="t1" class="mergeTable">
            <tr>
                <td colspan="6" align="center">
                    <span style="font-size: x-large">新增合同</span>
                </td>
            </tr>
            <tr>
                <td>经办部门：</td>
                <td colspan="2">
                    <select name="department" id="dep"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td style="color: #ab1e1e">经办人：</td>
                <td colspan="2"><input type="text" name="contractOperator" id="contractOperator"></td>
            </tr>
            <tr>
                <td>合同名称：</td>
                <td colspan="2">
                    <select name="contractName" id="contractName"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td style="color: #ab1e1e">合同编号：</td>
                <td colspan="2"><input type="text" name="contractId" id="contractId" onchange="getContract()"></td>
            </tr>
            <tr>
                <td>合同类别：</td>
                <td colspan="2">
                    <select name="contractType" id="contractType"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px"></select>
                </td>
                <td>预算计划：</td>
                <td colspan="2">
                    <input type="radio" name="budget" value="0" onclick="display()" id="budgetIN">预算内
                    <input type="radio" name="budget" value="1" onclick="displayNone()" id="budgetOUT">预算外
                </td>
            </tr>
            <tr style="display:none" id="displayTr">
                <td>预算类别：</td>
                <td colspan="2">
                    <input type="text" name="budgetType" id="budgetType">
                </td>
                <td>预算剩余金额：</td>
                <td colspan="2">
                    <input type="text" name="budgetMoney" onchange="checkNum(this.value)" id="budgetMoney">元
                </td>
            </tr>
            <tr style="display:none" id="displayTr2">
                <td>上报审核：</td>
                <td colspan="2">
                    <input type="radio" name="outBudget" value="0">未通过审批
                    <input type="radio" name="outBudget" value="1">已通过审批
                </td>
                <td>审核备注：</td>
                <td colspan="2">
                    <input type="text" name="SHremark">
                </td>
            </tr>
            <tr>
                <td>合同属性：</td>
                <td colspan="2">
                    <input type="radio" name="contractProperty" value="0" checked="checked">新签
                    <input type="radio" name="contractProperty" value="1">续签
                    <input type="radio" name="contractProperty" value="2">改签
                    <input type="radio" name="contractProperty" value="3">其他
                </td>
                <td style="color: #ab1e1e">合同原件份数：</td>
                <td colspan="2">
                    <select name="count" style="font-family: '微软雅黑';font-size: 16px;width: 180px" id="count">
                        <option value="" style="text-align: center"> ---------请选择---------</option>
                        <option value="2" style="text-align: center"> 2</option>
                        <option value="3" style="text-align: center"> 3</option>
                        <option value="4" style="text-align: center"> 4</option>
                        <option value="5" style="text-align: center"> 5</option>
                        <option value="6" style="text-align: center"> 6</option>
                        <option value="7" style="text-align: center"> 7</option>
                        <option value="8" style="text-align: center"> 8</option>
                        <option value="9" style="text-align: center"> 9</option>
                    </select>
                </td>
            </tr>
            <tr rowspan="3">
                <td>签约对象</td>
                <td style="color: #ab1e1e">甲方：</td>
                <td colspan="4">
                    <textarea cols="55" rows="1" name="contractSignCompany" type="text"
                              id="contractSignCompany"></textarea>
                </td>
            </tr>
            <tr>
                <td>签约对象</td>
                <td style="color: #ab1e1e">乙方：</td>
                <td colspan="4">
                    <textarea cols="55" rows="1" name="contractSignCompany" type="text"></textarea>
                </td>
            </tr>
            <tr>
                <td>签约对象</td>
                <td style="color: #ab1e1e">第三方：</td>
                <td colspan="4">
                    <textarea cols="55" rows="1" name="contractSignCompany" type="text"></textarea>
                </td>
            </tr>
            <tr>
                <td style="width: 120px">供应商确定方式：</td>
                <td colspan="2"><select name="biddingType"
                                        style="font-family: '微软雅黑';font-size: 16px;width: 180px" id="biddingType">
                    <option value="" style="text-align: center"> ---------请选择---------</option>
                    <option style="text-align: center" value="0">公开招标</option>
                    <option style="text-align: center" value="1">依法邀请招标</option>
                    <option style="text-align: center" value="2">内部邀请招标</option>
                    <option style="text-align: center" value="3">直接发包</option>
                    <option style="text-align: center" value="4">询价比价</option>
                    <option style="text-align: center" value="5">其他</option>
                    <option style="text-align: center" value="-1">无</option>
                </select>
                </td>

                <td style="color: #ab1e1e">对方单位是否盖章：</td>
                <td colspan="2">
                    <input name="stamp" type="radio" value="1" checked="checked">是
                    <input name="stamp" type="radio" value="0">否
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同版本提供:</td>
                <td colspan="2">
                    <select name="contractProvide" style="font-family: '微软雅黑';font-size: 16px;width: 180px"
                            onchange="others(this.selectedIndex)">
                        <option value="0" style="text-align: center">我司</option>
                        <option value="1" style="text-align: center">外单位</option>
                    </select>
                </td>
                <td style="color: #ab1e1e">合同版本：</td>
                <td colspan="2">
                    <input name="version" type="radio" value="1" checked="checked">格式合同
                    <input name="version" type="radio" value="0">非格式合同
                </td>

            </tr>
            <tr>
                <td style="color: #ab1e1e">合同生效日期：</td>
                <td colspan="2">
                    <input type="text" name="contractBeginDate" id="begin" class="Wdate"
                           onclick="WdatePicker({skin:'whyGreen'})">
                </td>
                <td style="color: #ab1e1e">合同到期日期：</td>
                <td colspan="2">
                    <input type="text" name="contractEndDate" id="end" class="Wdate"
                           onclick="WdatePicker({skin:'whyGreen'})">
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同主要内容：</td>
                <td colspan="5">
                    <textarea cols="65" rows="3" name="mainContent" id="mainContent"></textarea>
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同签订金额：</td>
                <td colspan="2">
                    <span style="color: #ab1e1e">单价金额：</span>
                    <input type="text" name="unit_priced" maxlength="12" id="unit_price"
                           onchange="checkNum(this.value)" style="width: 60px" value="0.0"> 元
                </td>
                <td style="color: #ab1e1e">合同总价金额：</td>
                <td colspan="2">
                    <input type="text" name="contractMoney" maxlength="12" id="contractMoney"
                           onchange="checkNum2(this.value)" style="width: 155px"> 元
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同应收（付）款金额:</td>
                <td colspan="2">
                    <input name="receivableOrPay" type="radio" value="0" checked="checked">付款
                    <input name="receivableOrPay" type="radio" value="1">收款
                    <input type="text" name="receivableOrPayMoney" maxlength="12" id="receivableOrPayMoney"
                           onchange="checkNum(this.value)"
                           style="width: 105px">元
                </td>
                <td>应付（收）时间:</td>
                <td colspan="2">
                    <input type="text" placeholder="yyyy-mm-dd,yyyy-mm-dd" name="paymentDate" id="paymentDate"
                           onchange="checkDate()">
                </td>
            </tr>
            <tr>
                <td>验收时间：</td>
                <td colspan="2">
                    <input type="text" name="acceptance" class="Wdate"
                           onclick="WdatePicker({skin:'whyGreen'})" id="acceptance">
                </td>
                <td>款项类型：</td>
                <td colspan="2">
                    <select name="paymentType" style="font-family: '微软雅黑';font-size: 16px;width: 180px"
                            onchange="others(this.selectedIndex)" id="paymentTypeA">
                        <option value="" style="text-align: center">---------请选择---------</option>
                        <option value="工程费" style="text-align: center">工程费</option>
                        <option value="履约保证金" style="text-align: center">履约保证金</option>
                        <option value="履约保函" style="text-align: center">履约保函</option>
                        <option value="咨询费" style="text-align: center">咨询费</option>
                        <option value="容量气价" style="text-align: center">容量气价</option>
                        <option value="货款" style="text-align: center">货款</option>
                        <option value="租赁费" style="text-align: center">租赁费</option>
                        <option value="培训费" style="text-align: center">培训费</option>
                        <option value="其他" style="text-align: center">其他</option>
                    </select>
                    <input name="paymentType" id="paymentType" style="display:none">
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">质保金：</td>
                <td colspan="2">
                    <input type="text" name="performanceBond" value="0.00" maxlength="10" id="performanceBond"
                           onchange="checkNum(this.value)" style="width: 155px">元
                </td>
                <td>律师咨询：</td>
                <td colspan="2">
                    <select name="lawer" style="font-family: '微软雅黑';font-size: 16px;width: 180px">
                        <option value="0" style="text-align: center">否</option>
                        <option value="1" style="text-align: center">是</option>
                    </select>
            </tr>
            <tr>
                <td style="color: #ab1e1e">质保期：</td>
                <td colspan="2">
                    <select name="guaranteePeriod" style="font-family: '微软雅黑';font-size: 16px;width: 180px"
                            id="guaranteePeriod">
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
                <td>质保金到期日期:</td>
                <td colspan="2">
                    <input type="text" name="duePerformanceBond" class="Wdate"
                           onclick="WdatePicker({skin:'whyGreen'})">
                </td>
            </tr>
            <tr>
                <td>发票：</td>
                <td colspan="2">
                    <select name="invoice"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px" id="invoice">
                        <option value="" style="text-align: center"> ---------请选择---------</option>
                        <option style="text-align: center" value="17">17%增值税专用发票</option>
                        <option style="text-align: center" value="11">11%增值税专用发票</option>
                        <option style="text-align: center" value="13">13%增值税专用发票</option>
                        <option style="text-align: center" value="6">6%增值税专用发票</option>
                        <option style="text-align: center" value="3">3%增值税专用发票</option>
                        <option style="text-align: center" value="0">普通发票</option>
                        <option style="text-align: center" value="-1">无发票</option>
                    </select>
                </td>
                <td>印花税：</td>
                <td colspan="2">
                    <select name="stampTax"
                            style="font-family: '微软雅黑';font-size: 16px;width: 180px">
                        <option value="" style="text-align: center"> ---------请选择---------</option>
                        <option style="text-align: center" value="0">未购买</option>
                        <option style="text-align: center" value="1">已购买</option>
                        <option style="text-align: center" value="2">无须购买</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>合同违约条款描述：</td>
                <td colspan="5">
                    <textarea cols="65" rows="3" name="remark"></textarea>
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">支持文件：</td>
                <td colspan="5" style="text-align: left">
                    &nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="政府批文">政府批文&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="固定资产请购单">固定资产请购单&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="固定资产出租出借表">固定资产出租出借表&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="集团流转单">集团流转单&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="事前审批表">事前审批表&nbsp;&nbsp;<br>&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="供应商询价采购单">供应商询价采购单&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="供应商报价单">供应商报价单&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="港投报价单">港投报价单&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="预结算价格审核资料">预结算价格审核资料&nbsp;&nbsp;<br>&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="上一年度生效合同复印件">上一年度生效合同复印件&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="顺燃-内部管理规定">顺燃-内部管理规定&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="顺燃-内部请示">顺燃-内部请示&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="营业执照">营业执照&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="税务登记证">税务登记证&nbsp;&nbsp;<br>&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="组织机构代码证">组织机构代码证&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="生产许可证">生产许可证&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="签约用户-申请">签约用户-申请&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="身份证复印件">身份证复印件&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="设计图">设计图&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="开户许可证">开户许可证&nbsp;&nbsp;<br>&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="核准变更登记通知书">核准变更登记通知书&nbsp;&nbsp;
                    <input type="checkbox" name="supportFile" value="培训申请表">培训申请表&nbsp;&nbsp;
                </td>
            </tr>
            <tr>
                <td>附件：</td>
                <td colspan="5">
                    <input type="hidden" name="path" value="<%=realPath%>"/>
                    <input type="file" name="upload" style="height: 25px;" onchange="verifyFileName()"/>
                    <input type="hidden" name="fileName"/>
                    <div id="sufix_result"></div>
                </td>
            </tr>
            <tr>
                <td colspan="6" align="center">
                    <input type="submit" value="提交" onclick="return confirm();">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>
