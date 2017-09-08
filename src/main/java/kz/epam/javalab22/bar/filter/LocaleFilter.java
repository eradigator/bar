package kz.epam.javalab22.bar.filter;

import kz.epam.javalab22.bar.constant.Const;

import javax.servlet.*;
import java.io.IOException;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (servletRequest.getAttribute(Const.STR_LOCALE) == null) {
            servletRequest.setAttribute(Const.STR_LOCALE, Const.STR_RU_RU);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
