package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    private static final Logger log = org.apache.log4j.Logger.getLogger(ConnectionPool.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty(Const.PAGE_INDEX);
        request.setAttribute("content", "catalog");

        log.info("Пришла пустая команда");
        return page;
    }
}
