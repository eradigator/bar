package kz.epam.javalab22.bar.entity;

import java.util.Map;

/**
 * Created by vten on 29.08.2017.
 */
public class Mix {

    private Map<Integer,Double> mix;

    public Mix(Map<Integer, Double> mix) {
        this.mix = mix;
    }

    public Map<Integer, Double> getMix() {
        return mix;
    }

    public void setMix(Map<Integer, Double> mix) {
        this.mix = mix;
    }
}
