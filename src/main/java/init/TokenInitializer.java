package init;

import math.action.Token;
import math.action.impl.NumberToken;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import static util.Util.*;

public final class TokenInitializer {
    private static final String PACKAGE = getPackage();

    private static final char PKG_SEPARATOR = getPackageSeparator();

    private static final char DIR_SEPARATOR = getDirSeparator();

    private static final String CLASS_FILE_SUFFIX = getClassFileSuffix();

    private static final String BAD_PACKAGE_ERROR = getErrorMessageThenInvalidPathToTokenPackage();

    public static Set<Token> getAppTokens() {
        Set<Token> tokens = new HashSet<>();
        List<Class<?>> classes = scan();
        for (Class<?> clazz : classes) {
            if (clazz.getSimpleName().equals("NumberToken")) {
                try {
                    for (int i = 1; i < 10; i++) {
                        tokens.add(prepare(clazz, String.valueOf(i)));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                tokens.add(prepare(clazz));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return tokens;
    }

    private static Token prepare(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        return (Token) clazz.newInstance();
    }

    private static NumberToken prepare(Class<?> clazz, String number) throws Exception {
        Class<?>[] params = {String.class};
        return (NumberToken) clazz.getConstructor(params).newInstance(number);
    }

    private static List<Class<?>> scan() {
        String path = PACKAGE.replace(PKG_SEPARATOR, DIR_SEPARATOR);

        URL url = Thread.currentThread().getContextClassLoader().getResource(path);

        if (url == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, path, path));
        }

        File dir = new File(url.getFile());
        File[] files = dir.listFiles();

        List<Class<?>> classes = new ArrayList<>();

        if (url.getFile().contains("jar")) {
            try {
                loadClassesFromJar(url, classes);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            for (File file : Objects.requireNonNull(files)) {
                classes.addAll(loadTokenClass(file, PACKAGE));
            }
        }
        return classes;
    }

    private static void loadClassesFromJar(URL url, List<Class<?>> classes) throws URISyntaxException {
        URI uri = url.toURI();
        try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            List<Path> paths = Files.list(fileSystem.getPath("/math/action/impl")).collect(Collectors.toList());

            for (Path path : paths) {
                loadTokenClass(path, classes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadTokenClass(Path path, List<Class<?>> classes) {
        String className = path.toString()
                .split("\\.")[0]
                .replace("/", ".")
                .substring(1);
        try {
            classes.add(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Class<?>> loadTokenClass(File file, String path) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = path + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                classes.addAll(loadTokenClass(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int end = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, end);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classes;
    }
}
