package org.sdgas.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


//字符编码过滤器
public class SetCodeFilter implements Filter {

    private final static Logger logger = LogManager.getLogger(SetCodeFilter.class.getName());

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        try {
            req.setCharacterEncoding("UTF-8");
            rep.setCharacterEncoding("UTF-8");
            filterChain.doFilter(req, rep);
        } catch (UnsupportedEncodingException e) {
            logger.error("编码不支持!", e);
        } catch (IOException e) {
            logger.error("IO出现问题!", e);
        } catch (ServletException e) {
            logger.error("Servlet出现问题!", e);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
