<%--<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>合同管理系统</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <link href="css/landing_page.css" rel="stylesheet"
          type="text/css" media="screen"/>

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/jquery.carouFredSel-6.2.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/login.js"></script>

</head>
<body>

<div class="main_con">
    <div class="slider">
        <div id="slider">
            <img width="965" height="405" src="img/background1.jpg">
        </div>
        <div class="pager" id="slider_page"></div>
    </div>

    <div class="login_area">
        <div class="logo_bar">
           <%--<img width="265" height="80" src="img/login.jpg">--%>
        </div>
        <div class="login_bar">
            <form id="ffLogin" action="Login.action" method="post">
                <div class="i">
                    <input id="userId" class="ipt usr_name" name="userId" type="text"
                           maxlength="10" onkeydown="loginidclick(this);">
                </div>
                <div class="i">
                    <input id="password" class="ipt usr_pwd" name="pwd"
                           type="password" maxlength="22" onkeydown="loginpassclick(this);">
                </div>
                <div class="i">
                    <div id="divMsg" style="color: red;"></div>
                </div>

                <a class="login_btn" onclick="submitForm();">登录</a>
            </form>
        </div>
    </div>

    <div class="footer">
        <p>Copyright © 2015 佛山市顺德区港华燃气有限公司 版权所有</p>
    </div>
</div>
</body>
</html>
