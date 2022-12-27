package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());
    private static final Properties PROPERTIES = new Properties();
    private static final String SEPARATOR_KEY = "app.separator";
    private static final String HOST = "app.host";
    private static final String PORT = "app.port";
    private static final String PACKAGE = "app.package";
    private static final String PACKAGE_SEPARATOR = "app.separator.package";
    private static final String DIR_SEPARATOR = "app.separator.dir";
    private static final String CLASS_FILE_SUFFIX = "app.class.suffix";
    private static final String BAD_PACKAGE_ERROR = "Не удалось получить ресурсы по пути '%s'. Вы уверены, что пакет '%s' существует?";

    static {
        loadProperties();
    }

    public static String getAppSeparator() {
        return get(SEPARATOR_KEY);
    }

    public static String getHost() {
        return get(HOST);
    }

    public static int getPort() {
        int port = System.getenv("PORT")!= null ?
                Integer.parseInt(System.getenv("PORT")) : Integer.parseInt(get(PORT));
        System.out.printf("PORT: %s%n", port);
        return port;
//        return Integer.parseInt(get(PORT));
    }

    public static String getPackage() {
        return get(PACKAGE);
    }

    public static char getPackageSeparator() {
        return get(PACKAGE_SEPARATOR).charAt(0);
    }

    public static char getDirSeparator() {
        return get(DIR_SEPARATOR).charAt(0);
    }

    public static String getClassFileSuffix() {
        return get(CLASS_FILE_SUFFIX);
    }

    public static String getErrorMessageThenInvalidPathToTokenPackage() {
        return BAD_PACKAGE_ERROR;
    }

    /**
     * Метод получения значений из файла application.properties
     * @throws IOException может произойти, если файл с настройками не существует,
     * либо путь к файлу указан неверно.
     */
    private static void loadProperties() {
        try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
            getProperties().load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Проверьте правильность пути к файлу настроек приложения: ", e);
        }
    }

    public static Properties getProperties() {
        return PROPERTIES;
    }

    /**
     * Возвращает значение property по ключу
     * @param key
     * @return
     */
    private static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

}
