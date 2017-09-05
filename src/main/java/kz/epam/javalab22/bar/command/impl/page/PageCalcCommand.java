package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import kz.epam.javalab22.bar.util.CalcAlcohol;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageCalcCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);

        Map<Integer, String> components = new ComponentDao().getComponents();
        reqHandler.addAttribute("components",components);

        reqHandler.addAttribute("content", "calculator");
        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
