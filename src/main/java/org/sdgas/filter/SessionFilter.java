package org.sdgas.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用于检查用户是否登录了系统的过滤器
 */
public class SessionFilter implements Filter {
    private String redirectURL = "";
    private List<String> notCheckURLList = null;

    @Override
    public void destroy() {
        this.notCheckURLList.clear();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        /**
         * loginKey为用户登录成功后写到session中的，在这里通过获取loginKey判断是否登录成功
         * 如果登录成功，则跳转到登录前浏览的页面，如果登录前是从login.jsp过来的，则不跳转
         */
        Object loginKey = session.getAttribute("loginKey");
        if (loginKey != null && loginKey.toString().equals("success")) {
            session.removeAttribute("loginKey");
            Object uri = session.getAttribute("requestURI");
            if (uri != null) {
                response.sendRedirect(uri.toString());
                return;
            }
        }
        /**
         * 判断是否是不需要执行过滤的页面，如果是则直接放行
         */
        String requestURI = request.getRequestURI();
        for (String url : this.notCheckURLList) {
//            logger.trace(url);
            if (requestURI.contains(url)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        /**
         * 如果用户未登录，记录目前访问的页面，并跳转到登录页面
         */
        if (session.getAttribute("person") == null) {
            if (requestURI.endsWith("jsp"))
                session.setAttribute("requestURI", requestURI);
            response.sendRedirect(request.getContextPath() + "/" + redirectURL);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化配置的参数
        redirectURL = filterConfig.getInitParameter("redirectURL");
        String notCheckURLStr = filterConfig.getInitParameter("notCheckURLList");
        if (notCheckURLStr != null) {
            notCheckURLList = new ArrayList<String>();
            notCheckURLList.clear();
            String[] urls = notCheckURLStr.split(",");
            Collections.addAll(notCheckURLList, urls);
        }
    }
}