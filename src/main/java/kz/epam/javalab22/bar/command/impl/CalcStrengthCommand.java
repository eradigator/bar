package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCalcCommand;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Mix;
import kz.epam.javalab22.bar.exception.GetMixFromRequestException;
import kz.epam.javalab22.bar.logic.CalculatorLogic;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.CalcAlcohol;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class CalcStrengthCommand implements ActionCommand {

    private static final Logger log = org.apache.log4j.Logger.getLogger(CalcStrengthCommand.class);

    @Override
    public String execute(ReqWrapper reqWrapper) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            Mix mix = new CalculatorLogic(reqWrapper).getMixFromRequest();
            CalcAlcohol calcAlcohol = new CalcAlcohol(connection);
            int strength = (int) calcAlcohol.calcStrength(mix);
            int totalAmount = calcAlcohol.totalAmount(mix);
            int cost = (int) calcAlcohol.calcCost(mix);

            reqWrapper.addAttribute(Const.ATTR_STRENGTH, strength);
            reqWrapper.addAttribute(Const.ATTR_AMOUNT, totalAmount);
            reqWrapper.addAttribute(Const.ATTR_COST, cost);
            reqWrapper.addAttribute(Const.ATTR_MIX, mix.getMix());

        } catch (GetMixFromRequestException e) {
            log.warn(Const.LOG_GET_MIX_FROM_REQUEST_EXC);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return new PageCalcCommand().execute(reqWrapper);
    }
}
