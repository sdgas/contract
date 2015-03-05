<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-3-20
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>

<div id="head_line"></div>
<div id="head" style="width: 1050px">

    <a href="index.jsp" style="border: none;"> <img
            src="<%=basePath%>img/logo.bmp"
            style="border: none;margin-right: 10px;width: 300px;height: 80px;"/>
    </a>

    <div style="width:400px;margin-top: 20px;margin-bottom: 0px;margin-left:340px;text-align: right">
        <c:if test="${session.person == null}">
            <%--<span style="font-size:15px; font-family:'微软雅黑'; color: #000000; position: absolute;top: 3px;right: 22%;">--%>
			<span style="font-size:15px; font-family:'微软雅黑'; color: #000000;">
				&nbsp&nbsp你好！&nbsp&nbsp <a href="<%=basePath%>login.jsp">登陆</a>
			</span>
        </c:if>
        <c:if test="${session.person != null}">
            <%--<span style="font-family:'微软雅黑'; color: #000000; position: absolute;top: 3px;right: 18%;">欢迎 ${person.position.positionName}&nbsp&nbsp${person.userName}&nbsp--%>
			<span style="font-family:'微软雅黑'; color: #000000;">欢迎
				${person.position.positionName}&nbsp&nbsp${person.userName}&nbsp; <a
                        href="<%=basePath%>page/message/alterPwd.jsp">修改密码</a> <a
                        href="Login!loginOut.action">退出</a>
			</span>
        </c:if>
    </div>

    <%--导航菜单开始--%>
    <%--按钮1--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            供应商管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#">增加供应商信息</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="Supplier.action"> 查询供应商信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="Supplier.action"> 修改供应商信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 导入供应商信息 </a>
            </li>
        </ul>
    </div>

    <%--按钮2--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            物料管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#"> 添加物料信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 物料信息查询 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 修改物料信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 导入物料信息 </a>
            </li>
        </ul>
    </div>

    <%--按钮3--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            物料管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#"> 添加物料信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 物料信息查询 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 修改物料信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 导入物料信息 </a>
            </li>
        </ul>
    </div>

    <%--按钮4--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            需求计划管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#"> 物料需求计划申请 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 需求计划综合查询 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 需求计划审核 </a>
            </li>
        </ul>
    </div>

    <%--按钮5--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            请购管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#"> 请购单填写 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 请购单综合查询 </a>
            </li>
        </ul>
    </div>

    <%--按钮6--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            采购管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#"> 采购单管理填写 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 采购单查询管理 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#"> 采购到货情况管理 </a>
            </li>
        </ul>
    </div>

    <%--按钮5--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            查询 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#"> 供应商销售查询 </a>
            </li>
        </ul>
    </div>
</div>
<%--<div style="margin-top: 10px;" class="btn-group">

    <ul class="nav nav-pills">

            <li class="dropdown"><a class="dropdown-toggle"
                data-toggle="dropdown" href="#"> 供应商管理 <b class="caret"></b>
            </a>
                <ul class="dropdown-menu">
                    <li><a href="<%=basePath%>page/supplier/addSupplier.jsp">
                            增加供应商信息 </a></li>
                    <li><a href="Supplier.action"> 查询供应商信息 </a></li>
                    <li><a href="Supplier.action"> 修改供应商信息 </a></li>
                    <li><a href="<%=basePath%>page/supplier/DateOperation.jsp">
                            导入供应商信息 </a></li>
                </ul></li>

            <li class="dropdown"><a class="dropdown-toggle"
                data-toggle="dropdown" href="#"> 物料管理 <b class="caret"></b>
            </a>
                <ul class="dropdown-menu">
                    <!-- links -->
                    <li><a href="<%=basePath%>page/material/addMaterial.jsp">
                            添加物料信息 </a></li>
                    <li><a href="Material.action"> 物料信息查询 </a></li>
                    <li><a href="Material.action"> 修改物料信息 </a></li>
                    <li><a href="<%=basePath%>page/material/DateOperation.jsp">
                            导入物料信息 </a></li>
                </ul></li>
        </c:if>
        <li class="dropdown"><a class="dropdown-toggle"
            data-toggle="dropdown" href="#"> 需求计划管理 <b class="caret"></b>
        </a>
            <ul class="dropdown-menu">
                <!-- links -->
                <li><a href="<%=basePath%>page/dp/addPlans.jsp"> 物料需求计划申请
                </a></li>
                <li><a href="DemandPlans.action"> 需求计划综合查询 </a></li>
                <c:if
                    test="${person.remarks=='物料采购'||person.position.positionId!=4}">
                    <li><a href="DemandPlans.action?flag=2"> 需求计划审核 </a></li>
                </c:if>
            </ul></li>
        <c:if test="${person.remarks=='物料采购'||person.position.positionId!=4}">
            <li class="dropdown"><a class="dropdown-toggle"
                data-toggle="dropdown" href="#"> 请购管理 <b class="caret"></b>
            </a>
                <ul class="dropdown-menu">
                    <!-- links -->
                    <li><a href="PurchaseRequisition!findAll.action"> 请购单填写 </a>
                    </li>
                    <li><a href="PurchaseRequisition!findAll.action?flag=2">
                            请购单综合查询 </a></li>
                </ul></li>

            <li class="dropdown"><a class="dropdown-toggle"
                data-toggle="dropdown" href="#"> 采购管理 <b class="caret"></b>
            </a>
                <ul class="dropdown-menu">
                    <!-- links -->
                    <li><a href="PurchaseRequisition!findOrder.action">
                            采购单管理填写 </a></li>
                    <li><a href="Order.action"> 采购单查询管理 </a></li>
                    <li><a href="Order!arrival.action"> 采购到货情况管理 </a></li>
                </ul></li>

            <li class="dropdown"><a class="dropdown-toggle"
                data-toggle="dropdown" href="#"> 查询 <b class="caret"></b>
            </a>
                <ul class="dropdown-menu">
                    <!-- links -->
                    <li><a href="<%=basePath%>page/supplier/statistics.jsp">
                            供应商销售查询 </a></li>
                </ul></li>
        </c:if>
    </ul>
</div>--%>