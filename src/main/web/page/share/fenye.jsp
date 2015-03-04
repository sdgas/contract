<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/page/share/taglib.jsp" %>

<span style="color: #FFFFFF; margin: 5px;">
当前页:第${pageView.currentPage}页|总记录数${pageView.totalRecord}条|每页显示${pageView.maxResult}条|总页数${pageView.totalPage}页
</span>
<c:forEach begin="${pageView.pageIndex.startIndex}"
           end="${pageView.pageIndex.endIndex}" var="wp">
    <c:if test="${pageView.currentPage==wp}">
        <span style="font-weight: bold;"><span style="color: #FFFFFF;margin-right: 4px; ">第${wp}页 </span></span>
    </c:if>
    <c:if test="${pageView.currentPage!=wp}">
        <c:if test="${pageView.currentPage==2}">
            <a href="javascript:topage('1')" class="a03"><span
                    style="color: #FFFFFF;margin-right: 4px; ">第1页 </span></a>
        </c:if>
        <a href="javascript:topage('${wp}')" class="a03"><span
                style="color: #FFFFFF;margin-right: 4px; ">第${wp}页 </span></a>
    </c:if>
</c:forEach>