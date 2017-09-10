package kz.epam.javalab22.bar.command.impl.component;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentManagerCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DelComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelComponentCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        if (new ComponentLogic(reqWrapper).delComponent()) {
            reqWrapper.addAttribute(Const.ATTR_DEL_COMPONENT_MESSAGE, "Компонент удален");
            log.info(Const.LOG_COMPONENT + Const.DIV_SPACE + reqWrapper.getParam(Const.PARAM_COMPONENT_TO_DEL) +
                    Const.LOG_HAS_BEEN_DELETED);
        } else {
            reqWrapper.addAttribute(Const.ATTR_DEL_COMPONENT_MESSAGE, "Компонент не удален");
        }

        return new PageComponentManagerCommand().execute(reqWrapper);
    }

}
