package com.snowsense;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class FilePathTest {
    FilePath fp = new FilePath();
    Class<FilePath> clazz = FilePath.class;

    @Test
    public void testGetInputFilePath1() {
        fp.setURL(clazz.getResource(""));
        assertEquals("/D:/workspace/Test/target/test-classes/com/test/", fp.getPath());
    }

    @Test
    public void testGetInputFilePath2() {
        fp.setURL(clazz.getClassLoader().getResource(""));
        assertEquals("/D:/workspace/Test/target/test-classes/", fp.getPath());
    }

    @Test
    public void testGetInputFilePath3() {
        fp.setURL(clazz.getResource("FilePath.class"));
        assertEquals("/D:/workspace/Test/target/classes/com/test/FilePath.class", fp.getPath());
    }

    @Test
    public void testGetInputFilePath4() {
        fp.setURL(clazz.getClassLoader().getResource("com/test/FilePath.class"));
        assertEquals("/D:/workspace/Test/target/classes/com/test/FilePath.class", fp.getPath());
    }

    @Test
    public void testGetInputFilePath5() {
        fp.setURL(clazz.getResource("test.txt"));
        assertEquals("/D:/workspace/Test/target/classes/com/test/test.txt", fp.getPath());
    }

    @Test
    public void testGetInputFilePath6() {
        fp.setURL(clazz.getClassLoader().getResource("com/test/test.txt"));
        assertEquals("/D:/workspace/Test/target/classes/com/test/test.txt", fp.getPath());
    }

    public class FilePath {
        private java.net.URL url = null;

        public String getPath() {
            return url != null ? url.getPath() : "";
        }

        public void setURL(final java.net.URL url) {
            this.url = url;
        }
    }
}
