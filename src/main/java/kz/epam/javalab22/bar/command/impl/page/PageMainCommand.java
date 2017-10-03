package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.UIText;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class PageMainCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        Connection connection = ConnectionPool.getInstance().getConnection();
        int textId = Integer.parseInt(ConfigurationManager.getProperty(Const.PROP_UI_TEXT_FOR_MAIN_PAGE));
        UIText uiText = new UITextDao(connection).get(textId);
        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute(Const.ATTR_UI_TEXT, uiText);
        reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_MAIN);
        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }

}
