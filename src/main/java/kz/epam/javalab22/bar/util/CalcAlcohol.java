package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.sql.Connection;
import java.util.Map;

/**
 * Created by erad on 10.07.2017.
 */

public class CalcAlcohol {

    public int calcStrength(Map<Integer, Double> componentsAndAmountsMap) {

        final int VALUE_100_ML = Const.ONE_HUNDRED;
        double strength100Ml = Const.ZERO;
        double totalAmount = Const.ZERO;
        double resultStrength;

        Connection connection = ConnectionPool.getInstance().getConnection();
        ComponentDao componentDao = new ComponentDao(connection);

        for (Map.Entry<Integer, Double> pair : componentsAndAmountsMap.entrySet()) {
            double strength = componentDao.getComponent(pair.getKey()).getStrength();
            double amount = pair.getValue();
            strength100Ml += (strength * amount)/VALUE_100_ML;
            totalAmount += amount;
        }

        ConnectionPool.getInstance().returnConnection(connection);

        resultStrength = (strength100Ml * VALUE_100_ML) / totalAmount;

        return (int) resultStrength;
    }

    public int totalAmount(Map<Integer,Double> componentsAndAmountsMap) {

        double totalAmount=0;
        for (Map.Entry<Integer, Double> pair : componentsAndAmountsMap.entrySet()) {
            double amount = pair.getValue();
            totalAmount += amount;
        }

        return (int) totalAmount;
    }

    public double calcCost(Map<Integer, Double> componentsAndAmountsMap) {

        double cost = Const.ZERO;

        Connection connection = ConnectionPool.getInstance().getConnection();
        ComponentDao componentDao = new ComponentDao(connection);

        for (Map.Entry<Integer, Double> pair : componentsAndAmountsMap.entrySet()) {
            double amount = pair.getValue();
            cost += componentDao.getComponent(pair.getKey()).getPrice() * amount;
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return cost;
    }


}
