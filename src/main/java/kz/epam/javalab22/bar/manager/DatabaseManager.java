package kz.epam.javalab22.bar.manager;

import java.util.ResourceBundle;

public class DatabaseManager {

    private static final String FILENAME = "dbConnection";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(FILENAME);

    public DatabaseManager() {
    }

    public String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
