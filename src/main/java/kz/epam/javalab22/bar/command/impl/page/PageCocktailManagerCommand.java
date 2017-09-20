package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.*;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.UserCheck;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.util.List;

public class PageCocktailManagerCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(PageCocktailManagerCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String page = new PageMainCommand().execute(reqWrapper);

        if (new UserCheck(reqWrapper).checkForAdmin()) {

            Connection connection = ConnectionPool.getInstance().getConnection();

            List<CocktailName> cocktailNames = new CocktailNameDao(connection).getList();
            List<ComponentType> componentTypes = new ComponentTypeDao(connection).getList();
            List<Component> components = new ComponentDao(connection).getList();
            List<Method> methods = new MethodDao(connection).getList();
            List<Glass> glasses = new GlassDao(connection).getList();

            ConnectionPool.getInstance().returnConnection(connection);

            reqWrapper.addAttribute(Const.ATTR_COCKTAIL_NAMES, cocktailNames);
            reqWrapper.addAttribute(Const.ATTR_COMPONENT_TYPES, componentTypes);
            reqWrapper.addAttribute(Const.ATTR_COMPONENTS, components);
            reqWrapper.addAttribute(Const.ATTR_METHODS, methods);
            reqWrapper.addAttribute(Const.ATTR_GLASSES, glasses);
            reqWrapper.addAttribute(Const.ATTR_CONTENT, Const.VAL_COCKTAIL_MANAGER);

            page = ConfigurationManager.getProperty(Const.PAGE_INDEX);

        } else {
            log.info(Const.LOG_FORBIDDEN_PAGE);
        }

        return page;
    }


}
