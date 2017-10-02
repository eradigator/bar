package kz.epam.javalab22.bar.filter;

import kz.epam.javalab22.bar.constant.Const;

import javax.servlet.*;
import java.io.IOException;

public class ContentFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (null == servletRequest.getAttribute(Const.ATTR_CONTENT)) {
            servletRequest.setAttribute(Const.ATTR_CONTENT, Const.VAL_MAIN);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
