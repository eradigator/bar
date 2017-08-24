package kz.epam.javalab22.bar.operation;

import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Component;

import java.util.Map;

/**
 * Created by erad on 10.07.2017.
 */
public class CalcAlcohol {

    public int calcAlcohol(Cocktail cocktail) {

        final int VALUE_100_ML = 100;
        final int INITIAL_VALUE = 0;
        int strength100Ml = INITIAL_VALUE;
        int totalAmount = INITIAL_VALUE;
        int strength;

        /*for (Map.Entry<Component, Integer> map : cocktail.getComponents().entrySet()) {
            strength100Ml += (map.getKey().getStrength() * map.getValue()) / VALUE_100_ML;
            totalAmount += map.getValue();
        }*/
        strength = (strength100Ml * VALUE_100_ML) / totalAmount;

        return strength;
    }
}
