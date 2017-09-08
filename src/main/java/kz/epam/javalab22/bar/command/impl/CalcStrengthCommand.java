package kz.epam.javalab22.bar.command.impl;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCalcCommand;
import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.CalcAlcohol;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalcStrengthCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String[] components = request.getParameterValues("ingredient");
        String[] amounts = request.getParameterValues("amountOfIngredient");
        String[] componentNames = request.getParameterValues("ingredientName");

        Map<Integer, Double> mix = new LinkedHashMap<>();
        for (int i = Const.N_0; i < components.length; i++) {
            mix.put(Integer.parseInt(components[i]), Double.parseDouble(amounts[i]));
        }

        Map<String, Integer> outMap = new LinkedHashMap<>();
        for (int i = Const.N_0; i < components.length; i++) {
            outMap.put(componentNames[i], Integer.parseInt(amounts[i]));
        }

        CalcAlcohol calcAlcohol = new CalcAlcohol();
        int strength = (int) calcAlcohol.calcStrength(mix);
        int totalAmount = calcAlcohol.totalAmount(mix);
        int cost = (int) calcAlcohol.calcCost(mix);

        ReqWrapper reqWrapper = new ReqWrapper(request);
        reqWrapper.addAttribute("strength",strength);
        reqWrapper.addAttribute("amount",totalAmount);
        reqWrapper.addAttribute("cost",cost);
        reqWrapper.addAttribute("outMap",outMap);

        return new PageCalcCommand().execute(request);
    }
}
