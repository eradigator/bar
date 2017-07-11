package kz.epam.javalab22.bar.operation;

import kz.epam.javalab22.bar.entity.AlcoholicComponent;
import kz.epam.javalab22.bar.entity.Cocktail;

import java.util.Map;

/**
 * Created by erad on 10.07.2017.
 */
public class CalcAlcohol {

    public int calcAlcohol(Cocktail cocktail) {

        int strength = 0;

        for (Map.Entry<AlcoholicComponent, Integer> map : cocktail.getComponents().entrySet()) {
            strength += map.getKey().getStrength() * map.getValue();
        }

        return strength;
    }
}
