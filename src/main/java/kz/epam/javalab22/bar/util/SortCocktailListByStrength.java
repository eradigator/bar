package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.entity.Cocktail;
import java.util.Comparator;

public class SortCocktailListByStrength implements Comparator<Cocktail> {

    @Override
    public int compare(Cocktail o1, Cocktail o2) {
        return Double.compare(o1.getStrength(), o2.getStrength());
    }
}
