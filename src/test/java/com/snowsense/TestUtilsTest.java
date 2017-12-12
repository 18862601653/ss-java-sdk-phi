package com.snowsense;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.snowsense.face.sdk.GrpFaceTestBase.IMG01_LEONARDO_DICAPRIO;

public class TestUtilsTest {
    @Test
    public void testPath() {
        System.out.println(this.getClass().getClassLoader().getResource("."));
        System.out.println(this.getClass().getResource("."));
    }

    @Test
    public void testGetResource() {
        System.out.println(TestUtils.getFileFromResource("/" + IMG01_LEONARDO_DICAPRIO));
    }

    @Test
    public void testGetResource2() {
        System.out.println(this.getClass().getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        TestUtils.getFileFromResource2(this.getClass(), IMG01_LEONARDO_DICAPRIO);
    }

    @Test
    public void testGetResource3() {
        TestUtils.getFileFromResource3(this.getClass(), IMG01_LEONARDO_DICAPRIO);
    }

    @Test
    public void testGetResource3_2() {
        TestUtils.getFileFromResource3(this.getClass(), "/" + IMG01_LEONARDO_DICAPRIO);
    }

    @Test
    public void testGetResource4() {
        TestUtils.getFileFromResource4(this.getClass(), IMG01_LEONARDO_DICAPRIO);
    }

    @Test
    public void testGetResource5() {
        TestUtils.getFileFromResource5(this.getClass(), IMG01_LEONARDO_DICAPRIO);
    }
}
