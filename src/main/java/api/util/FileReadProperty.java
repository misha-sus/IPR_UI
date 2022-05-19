package api.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Чтение properties
 */
public class FileReadProperty {
    private static final Properties property = new Properties();
    public static final String HOST = getHost();
    public static final String LOGIN = getLogin();
    public static final String PASSWORD = getPassword();
    public static final String API_URL = getApiURL();
    private static String setProperty(String key) {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(key);
    }

    private static String getHost() {
        return setProperty("db.host");
    }

    private static String getLogin() {
        return setProperty("db.login");
    }

    private static String getPassword() {
        return setProperty("db.password");
    }

    private static String getApiURL() { return setProperty("ap.url");
    }
}
