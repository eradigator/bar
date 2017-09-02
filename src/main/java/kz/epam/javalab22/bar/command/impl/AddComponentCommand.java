package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.SessionRequestContent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class AddComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddComponentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        Map<String, Object> sessionAttributes = new HashMap<>();
        String page;

        String name_RU = request.getParameter("name_RU");
        String name_EN = request.getParameter("name_EN");
        int componentType = Integer.parseInt(request.getParameter("componentType"));
        double strength = Double.parseDouble(request.getParameter("strength"));
        double price = Double.parseDouble(request.getParameter("price"));

        sessionRequestContent.extractValues(request);
        String s = sessionRequestContent.getRequestParameters().get("name_RU")[0];



        ComponentName componentName = new ComponentName(name_EN, name_RU);
        Boolean result = new ComponentNameDao().create(componentName);

        int nameId = new ComponentNameDao().getId(componentName);
        Component component = new Component(nameId, componentType, strength, price);
        Connection connection = ConnectionPool.getInstance().getConnection();
        new ComponentDao().insert(component);

        page = ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER);

        /*else {
            log.info(login + ": неудачная попытка входа");
            String message = MessageManager.getProperty("message.loginerror");
            request.setAttribute("errorLoginPassMessage", message);
            page = ConfigurationManager.getProperty(Const.PAGE_LOGIN);
        }*/
        return page;
    }
}
