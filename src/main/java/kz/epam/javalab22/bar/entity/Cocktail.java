package kz.epam.javalab22.bar.entity;

import java.util.*;

/**
 * Created by erad on 10.07.2017.
 */
public class Cocktail {

    private String name;
    private String imgPath;
    private BuildMethod buildMethod;
    private Glass glass;
    private Map<String, Integer> components = new LinkedHashMap<>();
    private Image image;
    private int imageId;

    public Cocktail(String name) {
        this.name = name;
    }


    public Cocktail(String name, BuildMethod buildMethod, Glass glass, Image image) {
        this.name = name;
        this.buildMethod = buildMethod;
        this.glass = glass;
        this.image = image;
    }

    public Cocktail(String name, Map<String,Integer> components, BuildMethod buildMethod, Glass glass, int imageId) {
        this.name = name;
        this.buildMethod = buildMethod;
        this.glass = glass;
        this.components = components;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildMethod getBuildMethod() {
        return buildMethod;
    }

    public void setBuildMethod(BuildMethod buildMethod) {
        this.buildMethod = buildMethod;
    }

    public Glass getGlass() {
        return glass;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public void addComponent(String string, Integer amount) {
        components.put(string, amount);
    }

    public Map<String, Integer> getComponents() {
        return components;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {


        String s = "";
        for (Map.Entry entry : components.entrySet()) {
            s += entry.getKey().toString() + ":" + entry.getValue().toString() + ";" + "</br>";
        }


        return "Cocktail{" +
                "name='" + name + '\'' + "</br>" +
                ", buildMethod=" + buildMethod +
                ", glass=" + glass +
                ", components=" + s +
                '}';
    }


}
