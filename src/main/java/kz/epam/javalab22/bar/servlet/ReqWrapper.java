package kz.epam.javalab22.bar.servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.User;
import org.apache.commons.lang.LocaleUtils;



public class ReqWrapper {
    private Locale locale;

    private HttpServletRequest request;

    public ReqWrapper() {
    }

    public ReqWrapper(HttpServletRequest request) {
        this.request = request;
        locale = LocaleUtils.toLocale(request.getSession().getAttribute(Const.ATTR_LOCALE).toString());
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return (User) request.getSession().getAttribute(Const.ATTR_USER);
    }

    public String getParam(String name) {
        return request.getParameter(name);
    }

    public String[] getParams(String name) {
        return request.getParameterValues(name);
    }

    public void addAttribute(String name, Object entity) {
        request.setAttribute(name,entity);
    }

    public void addSessionAttribute(String name, Object entity) {
        request.getSession().setAttribute(name,entity);
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}
