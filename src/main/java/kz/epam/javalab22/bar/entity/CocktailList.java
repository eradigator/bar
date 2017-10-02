package kz.epam.javalab22.bar.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vten
 */
public class CocktailList {

    private List<Cocktail> cocktailList = new ArrayList<>();

    public CocktailList() {
    }

    public CocktailList(List<Cocktail> cocktailList) {
        this.cocktailList = cocktailList;
    }

    public List<Cocktail> getCocktailList() {
        return cocktailList;
    }

    public int getSize() {
        return cocktailList.size();
    }
}
