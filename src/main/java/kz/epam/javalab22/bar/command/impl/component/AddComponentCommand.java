package kz.epam.javalab22.bar.command.impl.component;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentManagerCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class AddComponentCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        Connection connection = ConnectionPool.getInstance().getConnection();
        ComponentLogic componentLogic = new ComponentLogic(reqWrapper,connection);

        if (!componentLogic.checkForExistence()) {
            if (componentLogic.addComponent()) {
                String message = messageManager.getProperty(Const.PROP_COMPONENT_ADDED);
                reqWrapper.addAttribute(Const.ATTR_RESULT, message);
            } else {
                String message = messageManager.getProperty(Const.PROP_ERROR);
                reqWrapper.addAttribute(Const.ATTR_ERROR, message);
            }
        } else {
            String message = messageManager.getProperty(Const.PROP_COMPONENT_EXIST);
            reqWrapper.addAttribute(Const.ATTR_ERROR, message);
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return new PageComponentManagerCommand().execute(reqWrapper);
    }

}
