package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentCommand;
import kz.epam.javalab22.bar.logic.CocktailLogic;
import kz.epam.javalab22.bar.logic.ComponentLogic;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DelComponentCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(DelComponentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);

        if (new ComponentLogic(reqHandler).delComponent()) {
            reqHandler.addAttribute("delComponentMessage", "Компонент удален");
            log.info("Компонент: id" + reqHandler.getParam("componentToDel") + " удален");
        } else {
            request.setAttribute("delComponentMessage", "Компонент не удален");
        }

        return new PageComponentCommand().execute(request);
    }
}
