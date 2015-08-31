<%--
  Created by IntelliJ IDEA.
  User: 120378
  Date: 2015-03-18
  Time: 8:27
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
<html>
<head>
    <base href="<%=basePath%>">
    <title>相关制度</title>
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
    <script src="<%=basePath%>/js/jquery.min.js"></script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <div style="height: 800px">
        <table>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <span style="font-size: large">制度下载列表</span>
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <a href="<%=basePath%>/FileDownload.action">合同管理流程.pdf</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>
