package kz.epam.javalab22.bar.servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private Map<String, Object> requestAttributes;
    private Map<String, String> nonArrayRequestParameters;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(HashMap<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public Map<String, String> getNonArrayRequestParameters() {
        return nonArrayRequestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    // метод извлечения информации из запроса
    public void extractValues(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        nonArrayRequestParameters = convertArrayValueToString(requestParameters);

    }

    // метод добавления в запрос данных для передачи в jsp
    public void insertAttributes(HttpServletRequest request) {

        for (Map.Entry<String, Object> entry : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(entry.getKey(), entry.getValue());
        }

    }

    private Map<String,String> convertArrayValueToString(Map<String,String[]> map) {

        Map<String,String> resultMap = new HashMap<>();

        for (Map.Entry<String,String[]> pair : map.entrySet()) {
            resultMap.put(pair.getKey(),pair.getValue()[0]);
        }
        return resultMap;
    }

}
