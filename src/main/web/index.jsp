<%--
  Created by IntelliJ IDEA.
  User: he_bin
  Date: 2014/5/12
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>合同管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content－Type" content="text/html;charset=UTF-8">
    <meta http-equiv="window-target" content="_top">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <link rel="stylesheet" href="<%=basePath%>/css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">


    <%--加载dwr--%>
    <%--  <script src='<%=basePath%>dwr/util.js' type="text/javascript"></script>
          <script src='<%=basePath%>dwr/engine.js' type="text/javascript"></script>
          <script src='<%=basePath%>dwr/interface/userService.js' type="text/javascript"></script>--%>

</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="#" method="post">

        <div class="alert" style="width: 450px;margin: 20px 0px 20px 180px;">
        </div>
    </form>
    <div
            style="display:block; float:left;border:1px solid #FFFFFF;width: 400px;height: 300px">
        <table>
            <tr>
                <td>
                    <h4>临近到期合同</h4>
                </td>
            </tr>
            <s:iterator value="overDate" var="o">
                <tr>
                    <td>
                        <a href="<%=basePath%>/Contract!findOneContract.action?contractId=${o.contractId}">${o.contractId} ${o.contractName.contractName}</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </div>
    <div
            style="display:block; float:left;border:1px solid #FFFFFF;width: 400px;height: 300px">
        <table>
            <tr>
                <td>
                    <h4>质保金到期（一周后）</h4>
                </td>
            </tr>
            <s:iterator value="overPerformanceBond" var="o">
                <tr>
                    <td>
                        <a href="<%=basePath%>/Contract!findOneContract.action?contractId=${o.contractId}">${o.contractId} ${o.contractName.contractName}</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </div>
   <%-- <div
            style="display:block; float:left;border:1px solid #FFFFFF;width: 400px;height: 300px">
        <table>
            <tr>
                <td>
                    <h4>履约保证金到期（一周后）</h4>
                </td>
            </tr>
            <s:iterator value="overPerformanceBond" var="o">
                <tr>
                    <td>
                        <a href="<%=basePath%>/Contract!findOneContract.action?contractId=${o.contractId}">${o.contractId} ${o.contractName.contractName}</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </div>
    <div
            style="display:block; float:left;border:1px solid #FFFFFF;width: 400px;height: 300px">
    </div>--%>
</div>

<div id="content"></div>

<%@include file="/page/share/footer.jsp" %>
</body>
</html>
