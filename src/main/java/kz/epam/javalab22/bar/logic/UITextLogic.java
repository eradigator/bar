package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.UITextDao;
import kz.epam.javalab22.bar.entity.UIText;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import java.sql.Connection;

public class UITextLogic {

    private ReqWrapper reqWrapper;
    private Connection connection;

    public UITextLogic(ReqWrapper reqWrapper, Connection connection) {
        this.reqWrapper = reqWrapper;
        this.connection = connection;
    }

    public void addUiText(int textId) {
        UIText uiText = new UITextDao(connection).get(textId);
        reqWrapper.addAttribute(Const.ATTR_UI_TEXT, uiText);
    }
}
