package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.logic.LoginLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.SessionRequestContent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AddComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddComponentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        HashMap<String, Object> sessionAttributes = new HashMap<>();
        String page;

        String name_RU = request.getParameter("name_RU");
        String name_EN = request.getParameter("name_EN");
        int componentType = Integer.parseInt(request.getParameter("componentType"));
        double strength = Double.parseDouble(request.getParameter("strength"));
        double price = Double.parseDouble(request.getParameter("price"));

        page = ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER);

        /*System.out.println(name_RU);
        System.out.println(name_EN);
        System.out.println(componentType);
        System.out.println(strength);
        System.out.println(price);*/

        ComponentName componentName = new ComponentName(name_EN, name_RU);
        Boolean result = new ComponentNameDao().create(componentName);
        System.out.println(result);

        int nameId = new ComponentNameDao().getId(componentName);
        Component component = new Component(nameId,componentType,strength,price);
        new ComponentDao().insert(component);

        /*if (new LoginLogic().checkLogin(login, pass)) {

            sessionAttributes.put("username", login);
            sessionAttributes.put("role", "admin");

            sessionRequestContent.setSessionAttributes(sessionAttributes);
            sessionRequestContent.insertAttributes(request);



            log.info(login + " залогинился");

        } else {
            log.info(login + ": неудачная попытка входа");
            String message = MessageManager.getProperty("message.loginerror");
            request.setAttribute("errorLoginPassMessage", message);
            page = ConfigurationManager.getProperty(Const.PAGE_LOGIN);
        }*/
        return page;
    }
}
