package kz.epam.javalab22.bar.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static final String FILENAME = "config";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);

    // класс извлекает информацию из файла config.properties
    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
