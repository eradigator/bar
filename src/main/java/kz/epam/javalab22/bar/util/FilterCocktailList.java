package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.CocktailList;

/**
 * @author vten
 */
public class FilterCocktailList {

    public CocktailList getNonAlco(CocktailList cocktailList) {
        return getList(cocktailList,0,0);
    }

    public CocktailList getAllAlco(CocktailList cocktailList) {
        return getList(cocktailList,0,100);
    }

    public CocktailList getLowAlco(CocktailList cocktailList) {
        return getList(cocktailList, 0.1, 10);
    }

    public CocktailList getMiddleAlco(CocktailList cocktailList) {
        return getList(cocktailList, 10, 20);
    }

    public CocktailList getStrongAlco(CocktailList cocktailList) {
        return getList(cocktailList, 20, 100);
    }

    private CocktailList getList(CocktailList cocktailList, double lowRage, double highRange) {

        CocktailList filteredCocktailList = new CocktailList();

        for (Cocktail cocktail : cocktailList.getCocktailList()) {
            if (cocktail.getStrength() >= lowRage && cocktail.getStrength() <= highRange) {
                filteredCocktailList.getCocktailList().add(cocktail);
            }
        }

        return filteredCocktailList;
    }
}
