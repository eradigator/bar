package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentTypeDao;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class PageComponentCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqHandler reqHandler = new ReqHandler(request);

        Map<Integer, String> componentTypes = new ComponentTypeDao().getComponentTypes();

        reqHandler.addAttribute("componentTypes",componentTypes);
        return ConfigurationManager.getProperty(Const.PAGE_COMPONENT_MANAGER);
    }
}
