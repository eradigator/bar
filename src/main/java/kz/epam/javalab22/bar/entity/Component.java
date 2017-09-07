package kz.epam.javalab22.bar.entity;

/**
 * Created by erad on 11.07.2017.
 */

public class Component {

    private int id;
    private int nameId;
    private int type;
    private double strength;
    private double price;
    private ComponentName componentName;

    public Component() {
    }

    public Component(int id) {
        this.id = id;
    }

    public Component(int id, double strength, double price) {
        this.id = id;
        this.strength = strength;
        this.price = price;
    }

    public Component(int nameId, int type, double strength, double price) {
        this.nameId = nameId;
        this.type = type;
        this.strength = strength;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNameId() {
        return nameId;
    }

    public int getType() {
        return type;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ComponentName getComponentName() {
        return componentName;
    }

    public void setComponentName(ComponentName componentName) {
        this.componentName = componentName;
    }
}
