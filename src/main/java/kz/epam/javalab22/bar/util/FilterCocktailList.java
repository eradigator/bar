package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.entity.Cocktail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vten on 15.09.2017.
 */
public class FilterCocktailList {

    public List<Cocktail> getNonAlco(List<Cocktail> cocktailList) {
        List<Cocktail> filteredCocktailList = new ArrayList<>();

        for (Cocktail cocktail : cocktailList) {
            if (cocktail.getStrength() == 0) {
                filteredCocktailList.add(cocktail);
            }
        }

        return filteredCocktailList;
    }

    public List<Cocktail> getAllAlco(List<Cocktail> cocktailList) {
        return getList(cocktailList,0,100);
    }

    public List<Cocktail> getLowAlco(List<Cocktail> cocktailList) {
        return getList(cocktailList, 0, 10);
    }

    public List<Cocktail> getMiddleAlco(List<Cocktail> cocktailList) {
        return getList(cocktailList, 10, 20);
    }

    public List<Cocktail> getStrongAlco(List<Cocktail> cocktailList) {
        return getList(cocktailList, 20, 100);
    }

    private List<Cocktail> getList(List<Cocktail> cocktailList, int lowRage, int highRange) {

        List<Cocktail> filteredCocktailList = new ArrayList<>();

        for (Cocktail cocktail : cocktailList) {
            if (cocktail.getStrength() > lowRage && cocktail.getStrength() <= highRange) {
                filteredCocktailList.add(cocktail);
            }
        }

        return filteredCocktailList;
    }
}
