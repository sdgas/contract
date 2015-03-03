<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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

    <title>信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>

    <link rel="stylesheet" href="<%=basePath%>css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
</head>

<body>
<%@ include file="/page/share/menu.jsp" %>

<div id="content" style="height: 600px;">
    <h2>反馈</h2>
    <hr>
		<span
                style="font-family: '微软雅黑';display: block;margin: 0 auto;text-align:center;width: 800px;height: 300px;margin-top: 60px;">${resultMessage}</span>

</div>

<%@ include file="/page/share/footer.jsp" %>

</body>
</html>
