package kz.epam.javalab22.bar.util;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.CocktailList;

/**
 * @author vten
 */
public class FilterCocktailList {

    public CocktailList getNonAlco(CocktailList cocktailList) {
        return getList(cocktailList, Const.ALC_AMOUNT_0,Const.ALC_AMOUNT_0);
    }

    public CocktailList getAll(CocktailList cocktailList) {
        return getList(cocktailList,Const.ALC_AMOUNT_0,Const.ALC_AMOUNT_100);
    }

    public CocktailList getLowAlco(CocktailList cocktailList) {
        return getList(cocktailList, Const.ALC_AMOUNT_0_1, Const.ALC_AMOUNT_10);
    }

    public CocktailList getMiddleAlco(CocktailList cocktailList) {
        return getList(cocktailList, Const.ALC_AMOUNT_10, Const.ALC_AMOUNT_20);
    }

    public CocktailList getStrongAlco(CocktailList cocktailList) {
        return getList(cocktailList, Const.ALC_AMOUNT_20, Const.ALC_AMOUNT_100);
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
