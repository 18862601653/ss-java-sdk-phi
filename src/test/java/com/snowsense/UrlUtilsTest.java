package com.snowsense;

import com.snowsense.utils.UrlUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UrlUtilsTest {
    @Test
    public void testValidateUrl(){
        String url = "http://www.baidu.com";
        boolean result = UrlUtils.validateUrl(url);
        assertEquals(true,result);
    }

    @Test
    public void testIsHeicImg(){
        assertEquals(true,UrlUtils.isHeicImg("https://github.com/SnowSense/archive/raw/master/test/ios.heic"));
        assertEquals(false,UrlUtils.isHeicImg("https://github.heic.com/SnowSense/archive/raw/master/test/ios"));
    }
}
