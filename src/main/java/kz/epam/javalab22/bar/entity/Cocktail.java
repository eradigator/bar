package kz.epam.javalab22.bar.entity;

/**
 * Created by erad on 10.07.2017.
 */
public class Cocktail {

    int id;
    private String name;
    private CocktailName cocktailName;
    private Method method;
    private int methodId;
    private Glass glass;
    private Image image;
    private int imageId;
    private Mix mix;
    private double strength;


    public Cocktail() {
    }

    public Cocktail(int id) {
        this.id = id;
    }

    public Cocktail(String name, int methodId, Glass glass, Image image) {
        this.name = name;
        this.methodId = methodId;
        this.glass = glass;
        this.image = image;
    }

    public Cocktail(CocktailName cocktailName, int methodId, Glass glass, Image image) {
        this.cocktailName = cocktailName;
        this.methodId = methodId;
        this.glass = glass;
        this.image = image;
    }

    public Cocktail(int id, CocktailName cocktailName, Mix mix, Method method, Glass glass, double strength, int imageId) {
        this.id=id;
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

    public String getName() {
        return name;
    }

    public CocktailName getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(CocktailName cocktailName) {
        this.cocktailName = cocktailName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Glass getGlass() {
        return glass;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Mix getMix() {
        return mix;
    }

    public void setMix(Mix mix) {
        this.mix = mix;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
