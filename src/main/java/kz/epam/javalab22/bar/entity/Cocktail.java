package kz.epam.javalab22.bar.entity;

/**
 * @author vten
 */
public class Cocktail {

    int id;
    private CocktailName cocktailName;
    private Method method;
    private int methodId;
    private Glass glass;
    private Image image;
    private int imageId;
    private Mix mix;
    private double strength;
    private boolean isFavorite;

    public Cocktail() {
    }

    public Cocktail(int id) {
        this.id = id;
    }

    public Cocktail(CocktailName cocktailName, int methodId, Glass glass, Image image) {
        this.cocktailName = cocktailName;
        this.methodId = methodId;
        this.glass = glass;
        this.image = image;
    }

    public Cocktail(int id, CocktailName cocktailName, Mix mix, Method method, Glass glass, double strength, int imageId) {
        this.id = id;
        this.cocktailName = cocktailName;
        this.method = method;
        this.glass = glass;
        this.mix = mix;
        this.strength = strength;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CocktailName getCocktailName() {
        return cocktailName;
    }

    public int getMethodId() {
        return methodId;
    }

    public Method getMethod() {
        return method;
    }

    public Glass getGlass() {
        return glass;
    }

    public Image getImage() {
        return image;
    }

    public Mix getMix() {
        return mix;
    }

    public int getImageId() {
        return imageId;
    }

    public double getStrength() {
        return strength;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
