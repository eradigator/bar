package kz.epam.javalab22.bar.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    public MessageManager(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("message", locale);
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

