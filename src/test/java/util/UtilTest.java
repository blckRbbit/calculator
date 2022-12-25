package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    @Test
    void getAppSeparator() {
        String example = " ";
        String result = Util.getAppSeparator();
        assertEquals(example, result);
    }

    @Test
    void getHost() {
        String host = "localhost";
        String test = Util.getHost();
        assertEquals(host, test);
    }

    @Test
    void getPort() {
        int port = 8007;
        int test = Util.getPort();
        assertEquals(port, test);
    }

    @Test
    void getPackage() {
        String path = "math.action.impl";
        String test = Util.getPackage();
        assertEquals(path, test);
    }

    @Test
    void getPackageSeparator() {
        char separator = '.';
        char test = Util.getPackageSeparator();
        assertEquals(separator, test);
    }

    @Test
    void getDirSeparator() {
        char separator = '/';
        char test = Util.getDirSeparator();
        assertEquals(separator, test);
    }

    @Test
    void getClassFileSuffix() {
        String suffix = ".class";
        String test = Util.getClassFileSuffix();
        assertEquals(suffix, test);
    }

    @Test
    void getErrorMessageThenInvalidPathToTokenPackage() {
        String error = "Не удалось получить ресурсы по пути '%s'. Вы уверены, что пакет '%s' существует?";
        String test = Util.getErrorMessageThenInvalidPathToTokenPackage();
        assertEquals(error, test);
    }

}
