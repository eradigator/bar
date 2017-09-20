package kz.epam.javalab22.bar.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vten
 */
public class Favorite {

    private int userId;
    private List<Integer> cocktailIds = new ArrayList<>();

    public Favorite() {
    }

    public Favorite(int userId, List<Integer> cocktailIds) {
        this.userId = userId;
        this.cocktailIds = cocktailIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getCocktailIds() {
        return cocktailIds;
    }

    public void setCocktailIds(List<Integer> cocktailIds) {
        this.cocktailIds = cocktailIds;
    }
}
