<%--
  Created by IntelliJ IDEA.
  User: 120378
  Date: 2015/3/05
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="org.sdgas.model.*" %>
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
    <title>合同信息</title>
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

    <script type="text/javascript">

        $(document).ready(function () {

            mergeTable("t1", 0, 4);
            mergeTable("t2", 0, 1);//一次性付款
            mergeTable("t2", 1, 2);//一次性付款金额
            mergeTable("t2", 2, 1);//分期付款
            mergeTable("t2", 6, 2);//留空

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

    </script>
    <style type="text/css">

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
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="#" method="post" enctype="multipart/form-data">
        <table id="t1" class="mergeTable">
            <tr>
                <td colspan="5" align="center">
                    <span style="font-size: x-large">合同</span>
                </td>
            </tr>
            <tr>
                <td>合同名称：</td>
                <td colspan="2">
                    ${contract.contractName.contractName}
                </td>
                <td style="color: #ab1e1e">合同编号：</td>
                <td>
                    ${contract.contractId}
                </td>
            </tr>
            <tr>
                <td>合同类别：</td>
                <td colspan="2">
                    ${contract.contractType.contractType}
                </td>
                <td>预算计划：</td>
                <td>
                    <c:set var="in" value="<%=Budget.IN %>"/>
                    <c:set var="out" value="<%=Budget.OUT %>"/>
                    <c:if test="${contract.budget eq in}">
                        <span>预算内</span>
                    </c:if>
                    <c:if test="${contract.budget eq out}">
                        <span>预算外</span>
                    </c:if>
                </td>
            </tr>
            <c:if test="${contract.budget eq in}">
                <tr>
                    <td>预算类别：</td>
                    <td colspan="2">
                            ${contract.budgetType}
                    </td>
                    <td>预算剩余金额：</td>
                    <td>
                            ${contract.budgetMoney}元
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>合同属性：</td>
                <td colspan="2">
                    <c:set var="new" value="<%=ContractProperty.NEW %>"/>
                    <c:set var="renew" value="<%=ContractProperty.RENEW %>"/>
                    <c:set var="change" value="<%=ContractProperty.CHANGE %>"/>
                    <c:if test="${contract.contractProperty eq new}"> 新签 </c:if>
                    <c:if test="${contract.contractProperty eq renew}"> 续签 </c:if>
                    <c:if test="${contract.contractProperty eq change}"> 改签 </c:if>
                </td>
                <td style="color: #ab1e1e">合同原件份数：</td>
                <td>
                    ${contract.count}
                </td>
            </tr>
            <tr>
                <td>签约对象</td>
                <td style="color: #ab1e1e">甲方：</td>
                <td colspan="3">
                    ${oneSign}
                </td>
            </tr>
            <tr>
                <td>签约对象</td>
                <td style="color: #ab1e1e">乙方：</td>
                <td colspan="3">
                    ${twoSign}
                </td>
            </tr>
            <tr>
                <td>签约对象</td>
                <td style="color: #ab1e1e">第三方：</td>
                <td colspan="3">
                    ${threeSign}
                </td>
            </tr>
            <tr>
                <td style="width: 120px">供应商确定方式：</td>
                <td colspan="2">

                    <c:set var="OPEN" value="<%=BiddingType.OPEN %>"/>
                    <c:set var="LAW_INVITE" value="<%=BiddingType.LAW_INVITE %>"/>
                    <c:set var="IN_INVITE" value="<%=BiddingType.IN_INVITE %>"/>
                    <c:set var="DIRECT" value="<%=BiddingType.DIRECT %>"/>
                    <c:set var="COMPARE" value="<%=BiddingType.COMPARE %>"/>
                    <c:set var="OTHERS" value="<%=BiddingType.OTHERS %>"/>

                    <c:if test="${contract.biddingType eq OPEN}"> 公开招标 </c:if>
                    <c:if test="${contract.biddingType eq LAW_INVITE}"> 依法邀请招标 </c:if>
                    <c:if test="${contract.biddingType eq IN_INVITE}"> 内部邀请招标 </c:if>
                    <c:if test="${contract.biddingType eq DIRECT}"> 直接发包 </c:if>
                    <c:if test="${contract.biddingType eq COMPARE}"> 询价比价 </c:if>
                    <c:if test="${contract.biddingType eq OTHERS}"> 其他 </c:if>

                </td>

                <td style="color: #ab1e1e">合同版本：</td>
                <td>
                    <c:if test="${contract.version eq 1}"> 格式合同 </c:if>
                    <c:if test="${contract.version eq 0}"> 非格式合同 </c:if>
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">对方单位是否盖章：</td>
                <td colspan="2">
                    <c:if test="${contract.stamp eq 0}"> 否 </c:if>
                    <c:if test="${contract.stamp eq 1}"> 是 </c:if>
                </td>
                <td style="color: #ab1e1e">支持文件：</td>
                <td>
                    ${contract.supportFile}
                </td>
            </tr>
            <tr>
                <td>经办部门：</td>
                <td colspan="2">
                    ${contract.department.departmentName}
                </td>
                <td style="color: #ab1e1e">经办人：</td>
                <td>
                    ${contract.contractOperator}
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同生效日期：</td>
                <td colspan="2">
                    <fmt:formatDate value="${contract.contractBeginDate}" type="date"/>
                </td>
                <td style="color: #ab1e1e">合同到期日期：</td>
                <td>
                    <fmt:formatDate value="${contract.contractEndDate}" type="date"/>
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同主要内容：</td>
                <td colspan="4">
                    ${contract.mainContent}
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同签订金额：</td>
                <td colspan="2">
                    <span style="color: #ab1e1e">单价金额：</span>
                    ${contract.unit_price}元
                </td>
                <td style="color: #ab1e1e">合同总价金额：</td>
                <td>
                    ${contract.contractMoney}元
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同应收（付）款金额:</td>
                <td colspan="2">
                    <c:set var="RECEIVE" value="<%=ReceiveOrPay.RECEIVE %>"/>
                    <c:set var="PAY" value="<%=ReceiveOrPay.PAY %>"/>

                    <c:if test="${contract.receivableOrPay eq RECEIVE}">
                        ${contract.receivableOrPayMoney}元
                    </c:if>
                    <c:if test="${contract.receivableOrPay eq PAY}">
                        -${contract.receivableOrPayMoney}元
                    </c:if>

                </td>
                <td>应付（收）时间:</td>
                <td>
                    ${contract.paymentDate}
                </td>
            </tr>
            <tr>
                <td>验收时间：</td>
                <td>
                    <fmt:formatDate value="${contract.acceptance}" type="date"/>

                </td>
                <td>款项类型：</td>
                <td colspan="2">
                    ${contract.paymentType}
                </td>
            </tr>
            <tr>
                <td style="color: #ab1e1e">合同版本提供:</td>
                <td>
                    <c:if test="${contract.contractProvide eq 0}">
                        我司
                    </c:if>
                    <c:if test="${contract.contractProvide eq 1}">
                        外单位
                    </c:if>
                </td>
                <td>律师咨询：</td>
                <td colspan="2">

                    <c:if test="${contract.lawer eq 0}">
                    否
                    </c:if>
                    <c:if test="${contract.lawer eq 1}">
                    是
                    </c:if>
            </tr>
            <tr>
                <td>是否超合同结算：</td>
                <td>

                    <c:set var="Y" value="<%=SettleAccount.Y %>"/>
                    <c:set var="Y_N" value="<%=SettleAccount.Y_N %>"/>
                    <c:set var="N" value="<%=SettleAccount.N %>"/>

                    <c:if test="${contract.settleAccount eq N}">
                        否
                    </c:if>
                    <c:if test="${contract.settleAccount eq Y_N}">
                        是，未审核
                    </c:if>
                    <c:if test="${contract.settleAccount eq Y}">
                        是，已审核
                    </c:if>
                </td>

                <td style="color: #ab1e1e">质保期：</td>
                <td colspan="2">

                    <c:set var="HALF" value="<%=GuaranteePeriod.HALF %>"/>
                    <c:set var="ONE" value="<%=GuaranteePeriod.ONE %>"/>
                    <c:set var="TWO" value="<%=GuaranteePeriod.TWO %>"/>
                    <c:set var="THREE" value="<%=GuaranteePeriod.THREE %>"/>
                    <c:set var="FOUR" value="<%=GuaranteePeriod.FOUR %>"/>
                    <c:set var="FIVE" value="<%=GuaranteePeriod.FIVE %>"/>
                    <c:set var="SIX" value="<%=GuaranteePeriod.SIX %>"/>
                    <c:set var="SEVEN" value="<%=GuaranteePeriod.SEVEN %>"/>
                    <c:set var="EIGHT" value="<%=GuaranteePeriod.EIGHT %>"/>
                    <c:set var="NINE" value="<%=GuaranteePeriod.NINE %>"/>
                    <c:set var="TEN" value="<%=GuaranteePeriod.TEN %>"/>
                    <c:set var="LIFELONG" value="<%=GuaranteePeriod.LIFELONG %>"/>
                    <c:set var="NO" value="<%=GuaranteePeriod.NO %>"/>

                    <c:if test="${contract.guaranteePeriod eq HALF}">
                        半年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq ONE}">
                        一年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq TWO}">
                        两年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq THREE}">
                        三年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq FOUR}">
                        四年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq FIVE}">
                        五年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq SIX}">
                        六年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq SEVEN}">
                        七年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq EIGHT}">
                        八年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq NINE}">
                        九年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq TEN}">
                        十年
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq LIFELONG}">
                        终身
                    </c:if>
                    <c:if test="${contract.guaranteePeriod eq NO}">
                        无需担保
                    </c:if>
                </td>

            </tr>
            <tr>
                <td style="color: #ab1e1e">质保金：</td>
                <td>
                    ${contract.performanceBond}元
                </td>

                <td>质保金到期日期:</td>
                <td colspan="2">
                    <fmt:formatDate value="${contract.duePerformanceBond}" type="date"/>
                </td>
            </tr>
            <tr>
                <td>发票：</td>
                <td colspan="2">

                    <c:if test="${contract.invoice eq 17}">
                        17%增值税专用发票
                    </c:if>
                    <c:if test="${contract.invoice eq 11}">
                        11%增值税专用发票
                    </c:if>
                    <c:if test="${contract.invoice eq 13}">
                        13%增值税专用发票
                    </c:if>
                    <c:if test="${contract.invoice eq 6}">
                        6%增值税专用发票
                    </c:if>
                    <c:if test="${contract.invoice eq 3}">
                        3%增值税专用发票
                    </c:if>
                    <c:if test="${contract.invoice eq 0}">
                        普通发票
                    </c:if>
                </td>
                <td>印花税：</td>
                <td>
                    <c:if test="${contract.stampTax eq 0}">
                        未购买
                    </c:if>
                    <c:if test="${contract.stampTax eq 1}">
                        已购买
                    </c:if>
                    <c:if test="${contract.stampTax eq 2}">
                        无需购买
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td colspan="4">
                    ${contract.remark}
                </td>
            </tr>
            <tr>
                <td>附件：</td>
                <td colspan="4">
                    <s:iterator value="attachments" var="a">
                        <a href="FileDownload.action?flag=1&fileName=${a.attachmentName}">${a.attachmentName}</a>
                    </s:iterator>
                </td>
            </tr>
        </table>
    </form>
    <%--todo:录入收付款信息--%>
    <form action="Payment.action" method="post">
        <input type="hidden" name="contract" value="${contract.contractId}">
        <table id="t2" class="mergeTable">
            <tr>
                <td colspan="5">
                    <c:if test="${contract.receivableOrPay eq RECEIVE}">
                        <span style="font-size: x-large">收款进度</span>
                        <c:set var="r" value="收"/>
                    </c:if>
                    <c:if test="${contract.receivableOrPay eq PAY}">
                        <span style="font-size: x-large">付款进度</span>
                        <c:set var="r" value="付"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <%--<td>一次性${r}款</td>
                <td>${r}款金额</td>--%>
                <td>${r}款信息</td>
                <td>应${r}款日期</td>
                <td>实${r}款日期</td>
                <td>${r}款金额</td>
                <td>备注</td>
            </tr>
            <s:iterator value="date" var="a" status="i">
                <tr>
                        <%--<td>一次性${r}款</td>
                        <td>
                            <c:if test="${once eq 'F'}">0.0元</c:if>
                            <c:if test="${once ne 'F'}">
                                <input type="text" name="payMoney" style="width: 120px">元
                            </c:if>
                        </td>--%>
                    <td>${r}款信息</td>
                    <td>${a}</td>
                    <td>
                        <c:if test="${pn gt i.index}">${paymentDates[i.index]}</c:if>
                        <c:if test="${pn eq 0 or pn le i.index}">
                            <input type="text" name="paymentDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen'})"
                                   style="width: 120px">
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${pn gt i.index}">${paymentMoneys[i.index]}</c:if>

                        <c:if test="${pn eq 0 or pn le i.index}">
                            <input type="text" name="payMoney" maxlength="10" style="width: 120px">元
                        </c:if>

                    </td>
                    <td>
                        <c:if test="${pn gt i.index}">${paymentRemarks[i.index]}</c:if>

                        <c:if test="${pn eq 0 or pn le i.index}">
                            <textarea rows="3" cols="15" name="remark"></textarea>
                        </c:if>
                    </td>
                </tr>
            </s:iterator>
            <c:if test="">
                <tr>
                    <td colspan="5">
                        <input name="tijiao" value="添加" type="submit">
                    </td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>
