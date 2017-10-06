package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.exception.ForbiddenPageException;
import kz.epam.javalab22.bar.logic.UserLogic;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class PageUserManagerCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(PageUserManagerCommand.class);
    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
        String page = new PageMainCommand().execute(reqWrapper);

        if (checkForAdmin()) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            new UserLogic(reqWrapper, connection).addUserList();
            ConnectionPool.getInstance().returnConnection(connection);

            reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_USER_MANAGER);
            page = ConfigurationManager.getProperty(Const.PAGE_INDEX);
        }

        return page;
    }

    private boolean checkForAdmin() {

        boolean isAdmin = false;

        if (new UserLogic(reqWrapper).checkForAdmin()) {
            isAdmin = true;
        } else {
            try {
                throw new ForbiddenPageException();
            } catch (ForbiddenPageException e) {
                log.warn(Const.LOG_FORBIDDEN_PAGE);
            }
        }

        return isAdmin;
    }

}
