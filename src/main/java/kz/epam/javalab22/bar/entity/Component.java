package kz.epam.javalab22.bar.entity;

/**
 * Created by erad on 11.07.2017.
 */

public class Component {

    private int nameId;
    private int type;
    private double strength;
    private double price;

    public Component(int nameId, int type, double strength, double price) {
        this.nameId = nameId;
        this.type = type;
        this.strength = strength;
        this.price = price;
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

    public double getPrice() {
        return price;
    }
}
