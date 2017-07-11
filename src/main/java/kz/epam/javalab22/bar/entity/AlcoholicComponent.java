package kz.epam.javalab22.bar.entity;

/**
 * Created by erad on 11.07.2017.
 */
public class AlcoholicComponent extends Component {

    private double strength;

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public void setStrength(double strength) {
        this.strength = strength;
    }

}
