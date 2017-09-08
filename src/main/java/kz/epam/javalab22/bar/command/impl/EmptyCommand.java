package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageMainCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    private static final Logger log = org.apache.log4j.Logger.getLogger(ConnectionPool.class);

    @Override
    public String execute(HttpServletRequest request) {

        log.info("Пришла пустая команда");
        request.setAttribute("content", "main");
        return new PageMainCommand().execute(request);
    }
}
