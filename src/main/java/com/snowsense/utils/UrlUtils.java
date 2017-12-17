package com.snowsense.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class UrlUtils {

    /**
     *  validate the url: whether it is legal and links to a valid file
     *
     * @param url the url need to validate
     * @return true:the url is valid , otherwise, the url is invalid
     */
    public static boolean validateUrl(String url){
        boolean flag = isNetUrl(url)||isLocalUrl(url);
        if(flag == false){
            return flag;
        }
        try{
           urlToFile(url);
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * to check whether the url starts with http or ftp
     * @param url
     * @return
     */
    public static boolean isNetUrl(String url){
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    /**
     * to check whether the url is a local path
     * @param url
     * @return
     */
    public static boolean isLocalUrl(String url){
        String regex = "^[A-z]:\\\\\\S+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    /**
     * transfer the url to the file that it links to
     *
     * @param url the url links to one file
     * @return a new file which is the same as the url links to
     */
    public static File urlToFile(String url) throws IOException {
        File file = new File("test");
        FileUtils.copyURLToFile(new URL(url),file);
        return file;
    }

    /**
     * to check whether the url links to a image with the format of heic
     *
     * @param url
     * @return
     */
    public static boolean isHeicImg(String url){
        boolean flag = validateUrl(url);
        if(!flag){
            return flag;
        }
        return url.contains(".heic");
    }
}
