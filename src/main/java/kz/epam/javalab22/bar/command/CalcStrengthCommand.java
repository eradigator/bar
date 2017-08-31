package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.user.Role;
import kz.epam.javalab22.bar.entity.user.User;
import kz.epam.javalab22.bar.logic.AddUserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CalcStrengthCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        double a = Double.parseDouble(request.getParameter("a"));
        double b = Double.parseDouble(request.getParameter("b"));
        double summ = a + b;

        request.setAttribute("result", summ);
        request.setAttribute("content", "calculator");
        page = ConfigurationManager.getProperty(Const.PAGE_INDEX);
        return page;
    }
}
