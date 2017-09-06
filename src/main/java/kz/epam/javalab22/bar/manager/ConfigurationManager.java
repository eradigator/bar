package kz.epam.javalab22.bar.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
