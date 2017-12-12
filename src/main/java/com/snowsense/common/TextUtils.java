/**
 * TextUtils class provides helping methods for string comparison.
 * 
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.common;

public class TextUtils {

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean equals(String s1, String s2) {
        return s1 != null ? s1.equals(s2) : s2 == null;
    }

    public static String join(String[] strArr, String delimiter) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                strBuilder.append(delimiter);
            }
            strBuilder.append(strArr[i]);
        }

        return strBuilder.toString();
    }
}
