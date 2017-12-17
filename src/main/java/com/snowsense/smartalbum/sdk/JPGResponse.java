package com.snowsense.smartalbum.sdk;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

import java.io.File;

public class JPGResponse{
    private File file = new File("test.jpg");
    private String error;

    public File getFile(){
        return file;
    }

    public String getError(){return error;}


    @Override
    public String toString(){
        return file.toString();

    }

}
