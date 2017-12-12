package com.snowsense.smartalbum.sdk.rest.model;

import com.snowsense.Base64Provider;

import java.io.File;

public abstract class BaseEntry {
    private String url;

    abstract protected String getPrefix();

    public BaseEntry(String url) {
        this.url = url;
    }

    public BaseEntry(File file) {
        this.url = getPrefix() + encodeFileToBase64Binary(file);
    }

    public String getUrl() {
        return url;
    }

    private String encodeFileToBase64Binary(File file){
        return Base64Provider.getBase64Coder().encodeFile(file);
    }

}
