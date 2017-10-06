package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.Mix;

import java.sql.Connection;
import java.util.Map;

/**
 * @author vten
 */

public class CalcAlcohol {

    private Connection connection;

    public CalcAlcohol(Connection connection) {
        this.connection = connection;
    }

    public double calcStrength(Mix mix) {

        final int VALUE_100_ML = Const.N_100;
        double strength100Ml = Const.N_0;
        double totalAmount = Const.N_0;
        double resultStrength;

        ComponentDao componentDao = new ComponentDao(connection);

        for (Map.Entry<Component, Integer> pair : mix.getMix().entrySet()) {
            double strength = componentDao.getComponent(pair.getKey().getId()).getStrength();
            double amount = pair.getValue();
            strength100Ml += (strength * amount) / VALUE_100_ML;
            totalAmount += amount;
        }

        resultStrength = (strength100Ml * VALUE_100_ML) / totalAmount;
        return resultStrength;
    }

    public int totalAmount(Mix mix) {
        double totalAmount = Const.N_0;
        for (Map.Entry<Component, Integer> pair : mix.getMix().entrySet()) {
            double amount = pair.getValue();
            totalAmount += amount;
        }

        return (int) totalAmount;
    }

    public double calcCost(Mix mix) {
        double cost = Const.N_0;
        ComponentDao componentDao = new ComponentDao(connection);

        for (Map.Entry<Component, Integer> pair : mix.getMix().entrySet()) {
            double amount = pair.getValue();
            cost += componentDao.getComponent(pair.getKey().getId()).getPrice() * amount;
        }

        return cost;
    }


}
