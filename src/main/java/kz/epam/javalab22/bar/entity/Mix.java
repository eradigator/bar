package kz.epam.javalab22.bar.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author vten
 */
public class Mix {

    private Map<Component, Integer> mix = new LinkedHashMap<>();

    public Map<Component, Integer> getMix() {
        return mix;
    }
}
