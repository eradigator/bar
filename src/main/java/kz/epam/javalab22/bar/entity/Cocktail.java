package kz.epam.javalab22.bar.entity;

/**
 * Created by erad on 10.07.2017.
 */
public class Cocktail {

    private String name;
    private CocktailName cocktailName;
    private Method method;
    private int methodId;
    private Glass glass;
    //private Map<String, Integer> components = new LinkedHashMap<>();
    private Image image;
    private int imageId;
    private Mix mix;

    public Cocktail(String name) {
        this.name = name;
    }

    public Cocktail(String name, int methodId, Glass glass, Image image) {
        this.name = name;
        this.methodId = methodId;
        this.glass = glass;
        this.image = image;
    }

 /*   public Cocktail(String name, Map<String,Integer> components, Method method, Glass glass, int imageId) {
        this.name = name;
        this.method = method;
        this.glass = glass;
        this.components = components;
        this.imageId = imageId;
    }*/

    public Cocktail(CocktailName cocktailName, Mix mix, Method method, Glass glass, int imageId) {
        this.cocktailName = cocktailName;
        this.method = method;
        this.glass = glass;
        this.mix = mix;
        this.imageId = imageId;
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

   /* public void addComponent(String string, Integer amount) {
        components.put(string, amount);
    }

    public Map<String, Integer> getComponents() {
        return components;
    }*/

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

}
