package kz.epam.javalab22.bar.servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.LocaleUtils;



public class ReqWrapper {
    private Map<String, Object> requestAttributes;
    private Map<String, String> nonArrayRequestParameters;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private Locale locale;

    private HttpServletRequest request;

    public ReqWrapper() {
    }

    public ReqWrapper(HttpServletRequest request) {
        this.request = request;



        locale = LocaleUtils.toLocale(request.getSession().getAttribute("locale").toString());

        requestParameters = this.request.getParameterMap();
        nonArrayRequestParameters = convertArrayValueToString(requestParameters);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public Map<String, String> getNonArrayRequestParameters() {
        return nonArrayRequestParameters;
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

    public Object getSessionAttribute(String name) {
        return request.getSession().getAttribute(name);
    }

    public void addSessionAttribute(String name, Object entity) {
        request.getSession().setAttribute(name,entity);
    }

    private Map<String,String> convertArrayValueToString(Map<String,String[]> map) {

        Map<String,String> resultMap = new HashMap<>();

        for (Map.Entry<String,String[]> pair : map.entrySet()) {
            resultMap.put(pair.getKey(),pair.getValue()[0]);
        }
        return resultMap;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}
