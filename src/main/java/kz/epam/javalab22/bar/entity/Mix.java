package kz.epam.javalab22.bar.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vten on 29.08.2017.
 */
public class Mix {

    private Map<Component, Integer> mix = new LinkedHashMap<>();

    public Map<Component, Integer> getMix() {
        return mix;
    }

    public void setMix(Map<Component, Integer> mix) {
        this.mix = mix;
    }
}
