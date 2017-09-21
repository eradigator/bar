package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.entity.Cocktail;
import java.util.Comparator;

public class SortCocktailListByNameRu implements Comparator<Cocktail> {

    @Override
    public int compare(Cocktail o1, Cocktail o2) {

        String o1Name = o1.getCocktailName().getNameRu();
        String o2Name = o2.getCocktailName().getNameRu();

        return o1Name.compareTo(o2Name);
    }
}
