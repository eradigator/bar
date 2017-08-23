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

    private static final Logger log = Logger.getLogger(CalcStrengthCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        User user;

        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        int summ = a + b;

        request.setAttribute("result", summ);
        //log.info("Пользователь: " + login + " добавлен");

        /*
        } else{
        request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.loginerror"));
        }
        */

        request.setAttribute("content", "calculator");
        page=ConfigurationManager.getProperty(Const.PAGE_INDEX);
        return page;
        }
        }
