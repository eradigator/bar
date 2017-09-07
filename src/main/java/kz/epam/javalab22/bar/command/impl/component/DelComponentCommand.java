package kz.epam.javalab22.bar.command.impl.component;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentCommand;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DelComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelComponentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        if (new ComponentLogic(reqWrapper).delComponent()) {
            reqWrapper.addAttribute("delComponentMessage", "Компонент удален");
            log.info("Компонент: id" + reqWrapper.getParam("componentToDel") + " удален");
        } else {
            request.setAttribute("delComponentMessage", "Компонент не удален");
        }

        return new PageComponentCommand().execute(request);
    }
}
