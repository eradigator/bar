package kz.epam.javalab22.bar.command.impl.page;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class PageCalcCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        ReqWrapper reqWrapper = new ReqWrapper(request);

        //Map<Integer, String> components = new ComponentDao().getComponents();

        Connection connection = ConnectionPool.getInstance().getConnection();
        List<ComponentName> componentNames = new ComponentNameDao(connection).getList();
        ConnectionPool.getInstance().returnConnection(connection);

        reqWrapper.addAttribute("componentNames",componentNames);

        reqWrapper.addAttribute("content", "calculator");
        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
