package kz.epam.javalab22.bar.entity;

/**
 * Created by erad on 10.07.2017.
 */
public class Liquor extends Component {

    private double strength;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public double getStrength() {
        return 0;
    }

    @Override
    public void setStrength(double strength) {
        this.strength = strength;
    }
}
