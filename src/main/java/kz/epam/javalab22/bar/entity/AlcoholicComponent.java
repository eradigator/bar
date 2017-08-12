package kz.epam.javalab22.bar.entity;

/**
 * Created by erad on 11.07.2017.
 */
public class AlcoholicComponent extends Component {

    private String name;
    private double strength;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return name;
    }
}
