package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import kz.epam.javalab22.bar.util.CalcAlcohol;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalcStrengthCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String[] components = request.getParameterValues("ingredient");
        String[] amounts = request.getParameterValues("amountOfIngredient");

        Map<Integer, Double> map = new LinkedHashMap<>();
        for (int i = Const.ZERO; i < components.length; i++) {
            map.put(Integer.parseInt(components[i]), Double.parseDouble(amounts[i]));
        }

        CalcAlcohol calcAlcohol = new CalcAlcohol();
        int strength = calcAlcohol.calcStrength(map);
        int totalAmount = calcAlcohol.totalAmount(map);
        int cost = (int) calcAlcohol.calcCost(map);

        ReqHandler reqHandler = new ReqHandler(request);
        reqHandler.addAttribute("result", "крепость: " + strength + " выход: " +
                totalAmount + " мл" + " Цена: " + cost);

        reqHandler.addAttribute("content","calculator");

        return ConfigurationManager.getProperty(Const.PAGE_INDEX);
    }
}
