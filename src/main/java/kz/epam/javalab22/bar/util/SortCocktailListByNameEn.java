package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.entity.Cocktail;
import java.util.Comparator;

public class SortCocktailListByNameEn implements Comparator<Cocktail> {

    @Override
    public int compare(Cocktail o1, Cocktail o2) {

        String o1Name = o1.getCocktailName().getNameEn();
        String o2Name = o2.getCocktailName().getNameEn();

        return o1Name.compareTo(o2Name);
    }
}
