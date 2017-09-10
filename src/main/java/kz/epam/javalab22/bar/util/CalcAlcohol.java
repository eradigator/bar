package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.Mix;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;

import java.sql.Connection;
import java.util.Map;

/**
 * Created by erad on 10.07.2017.
 */

public class CalcAlcohol {

    public double calcStrength(Mix mix) {

        final int VALUE_100_ML = Const.N_100;
        double strength100Ml = Const.N_0;
        double totalAmount = Const.N_0;
        double resultStrength;

        Connection connection = ConnectionPool.getInstance().getConnection();
        ComponentDao componentDao = new ComponentDao(connection);

        for (Map.Entry<Component, Integer> pair : mix.getMix().entrySet()) {
            double strength = componentDao.getComponent(pair.getKey().getId()).getStrength();
            double amount = pair.getValue();
            strength100Ml += (strength * amount) / VALUE_100_ML;
            totalAmount += amount;
        }

        ConnectionPool.getInstance().returnConnection(connection);

        resultStrength = (strength100Ml * VALUE_100_ML) / totalAmount;

        return resultStrength;
    }

    public double calcStrength(Map<Integer, Double> mix) {

        final int VALUE_100_ML = Const.N_100;
        double strength100Ml = Const.N_0;
        double totalAmount = Const.N_0;
        double resultStrength;

        Connection connection = ConnectionPool.getInstance().getConnection();
        ComponentDao componentDao = new ComponentDao(connection);

        for (Map.Entry<Integer, Double> pair : mix.entrySet()) {
            double strength = componentDao.getComponent(pair.getKey()).getStrength();
            double amount = pair.getValue();
            strength100Ml += (strength * amount) / VALUE_100_ML;
            totalAmount += amount;
        }

        ConnectionPool.getInstance().returnConnection(connection);

        resultStrength = (strength100Ml * VALUE_100_ML) / totalAmount;

        return resultStrength;
    }

    public int totalAmount(Map<Integer, Double> mix) {

        double totalAmount = 0;
        for (Map.Entry<Integer, Double> pair : mix.entrySet()) {
            double amount = pair.getValue();
            totalAmount += amount;
        }

        return (int) totalAmount;
    }

    public double calcCost(Map<Integer, Double> mix) {

        double cost = Const.N_0;

        Connection connection = ConnectionPool.getInstance().getConnection();
        ComponentDao componentDao = new ComponentDao(connection);

        for (Map.Entry<Integer, Double> pair : mix.entrySet()) {
            double amount = pair.getValue();
            cost += componentDao.getComponent(pair.getKey()).getPrice() * amount;
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return cost;
    }


}
