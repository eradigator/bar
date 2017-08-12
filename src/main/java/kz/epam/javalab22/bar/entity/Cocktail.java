package kz.epam.javalab22.bar.entity;

import java.util.*;

/**
 * Created by erad on 10.07.2017.
 */
public class Cocktail {

    private String name;
    private BuildMethod buildMethod;
    private Glass glass;
    private Map<Component, Integer> components = new HashMap<Component, Integer>();

    public Cocktail(String name) {
        this.name = name;
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

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public void addComponent(Component component, Integer amount) {
        components.put(component, amount);
    }

    public Map<Component, Integer> getComponents() {
        return components;
    }

    @Override
    public String toString() {


        String s="";
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
