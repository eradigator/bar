package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCalcCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.CalcAlcohol;

import java.util.LinkedHashMap;
import java.util.Map;

public class CalcStrengthCommand implements ActionCommand {

    @Override
    public String execute(ReqWrapper reqWrapper) {

        String[] components = reqWrapper.getParams(Const.PARAM_INGREDIENT);
        String[] amounts = reqWrapper.getParams(Const.PARAM_AMOUNT_OF_INGREDIENT);
        String[] componentNames = reqWrapper.getParams(Const.PARAM_INGREDIENT_NAME);

        if (null != components && null != amounts && null != componentNames) {

            Map<Integer, Double> mix = new LinkedHashMap<>();
            for (int i = Const.N_0; i < components.length; i++) {
                mix.put(Integer.parseInt(components[i]), Double.parseDouble(amounts[i]));
            }

            Map<String, Integer> calcResult = new LinkedHashMap<>();
            for (int i = Const.N_0; i < components.length; i++) {
                calcResult.put(componentNames[i], Integer.parseInt(amounts[i]));
            }

            CalcAlcohol calcAlcohol = new CalcAlcohol();
            int strength = (int) calcAlcohol.calcStrength(mix);
            int totalAmount = calcAlcohol.totalAmount(mix);
            int cost = (int) calcAlcohol.calcCost(mix);

            reqWrapper.addAttribute(Const.ATTR_STRENGTH, strength);
            reqWrapper.addAttribute(Const.ATTR_AMOUNT, totalAmount);
            reqWrapper.addAttribute(Const.ATTR_COST, cost);
            reqWrapper.addAttribute(Const.ATTR_CALC_RESULT, calcResult);

        } else {
            MessageManager messageManager = new MessageManager(reqWrapper.getLocale());
            String message = messageManager.getProperty(Const.PROP_NO_COMPONENT_SELECTED);
            reqWrapper.addAttribute(Const.ATTR_ERROR, message);
        }

        return new PageCalcCommand().execute(reqWrapper);
    }

}
