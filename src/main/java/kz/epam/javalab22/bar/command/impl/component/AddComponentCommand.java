package kz.epam.javalab22.bar.command.impl.component;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentManagerCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class AddComponentCommand implements ActionCommand {

    private ReqWrapper reqWrapper;

    @Override
    public String execute(ReqWrapper reqWrapper) {

        this.reqWrapper = reqWrapper;

        Connection connection = ConnectionPool.getInstance().getConnection();
        ComponentLogic componentLogic = new ComponentLogic(reqWrapper, connection);
        addMessage(!componentLogic.checkForExistence() && componentLogic.addComponent());
        ConnectionPool.getInstance().returnConnection(connection);
        return new PageComponentManagerCommand().execute(reqWrapper);
    }

    private void addMessage(boolean success) {
        MessageManager messageManager = new MessageManager(reqWrapper.getLocale());

        if (success) {
            String message = messageManager.getProperty(Const.PROP_COMPONENT_ADDED);
            reqWrapper.addAttribute(Const.ATTR_RESULT, message);
        } else {
            if (null != reqWrapper.getAttribute(Const.ATTR_ERROR)) {
                String message = messageManager.getProperty(Const.PROP_ERROR);
                reqWrapper.addAttribute(Const.ATTR_ERROR, message);
            }
        }
    }

}
