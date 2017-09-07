package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.*;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.jdbc.Connect;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageCocktailManagerCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        Connection connection = ConnectionPool.getInstance().getConnection();

        List<CocktailName> cocktailNames = new CocktailNameDao(connection).getList();
        List<ComponentName> componentNames = new ComponentNameDao(connection).getList();
        List<Method> methods = new MethodDao().getList();
        List<Glass> glasses = new GlassDao().getList();

        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute("cocktailNames", cocktailNames);
        reqWrapper.addAttribute("componentNames", componentNames);
        reqWrapper.addAttribute("methods", methods);
        reqWrapper.addAttribute("glasses",glasses);

        return ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER);
    }
}
