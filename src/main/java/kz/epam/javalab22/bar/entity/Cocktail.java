package kz.epam.javalab22.bar.entity;

import java.util.*;

/**
 * Created by erad on 10.07.2017.
 */
public class Cocktail {

    private BuildMethod buildMethod;
    private Glass glass;
    private Map<AlcoholicComponent, Integer> components = new HashMap<AlcoholicComponent, Integer>();


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

    public void addComponent(AlcoholicComponent component, Integer amount) {
        components.put(component,amount);
    }

    public Map<AlcoholicComponent, Integer> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "buildMethod=" + buildMethod +
                ", glass=" + glass +
                '}';
    }
}
