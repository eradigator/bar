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

    public Component() {
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
}
