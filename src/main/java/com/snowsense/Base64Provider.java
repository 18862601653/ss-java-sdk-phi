package com.snowsense;

import java.io.*;
import java.util.Base64;

public class Base64Provider {
    private static Base64Coder sBase64Coder = new JavaBase64Coder();

    public static Base64Coder getBase64Coder() {
        return sBase64Coder;
    }

    public static void setBase64Coder(Base64Coder base64Coder) {
        sBase64Coder = base64Coder;
    }

    public static abstract class Base64Coder {
        public String encodeFile(File file) {
            String encodedfile = null;
            try {
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStreamReader.read(bytes);
                return encode(bytes);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return encodedfile;
        }

        public abstract String encode(byte[] bytes);
    }

    public static class JavaBase64Coder extends Base64Coder {

        @Override
        public String encode(byte[] bytes) {
            try {
                return new String(Base64.getEncoder().encode(bytes), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
