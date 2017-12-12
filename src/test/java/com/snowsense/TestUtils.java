package com.snowsense;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.snowsense.face.sdk.GrpFaceTestBase.IMG01_LEONARDO_DICAPRIO;
import static com.snowsense.face.sdk.GrpFaceTestBase.IMG02_LEONARDO_DICAPRIO;

public class TestUtils {

    public static File getFileFromResource(String resourceName) {
        if (!resourceName.startsWith("/")) {
            resourceName = "/" + resourceName;
        }
        URL url = new TestUtils().getClass().getResource(resourceName);
        return new File(url.getPath());
    }

    public static File getFileFromResource2(Class clazz, String resourceName) {
        URL url = clazz.getClassLoader().getResource(resourceName);
        return new File(url.getFile());
    }

    public static File getFileFromResource3(Class clazz, String resourceName) {
        URL url = clazz.getResource(resourceName);
        return new File(url.getFile());
    }

    public static File getFileFromResource4(Class clazz, String resourceName) {
        try {
            Path path = Paths.get(clazz.getClassLoader().getResource(resourceName).toURI());
            if (path != null) {
                return path.toFile();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static File getFileFromResource5(Class clazz, String resourceName) {
        try {
            Path path = Paths.get(clazz.getResource("/" + resourceName).toURI());
            if (path != null) {
                return path.toFile();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
