/**
 * Person class
 * 
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

import java.util.Map;

public class Person {
    private String personId;
    private String personName;
    private Map<String, String> data;

    public Person(String personId, String personName, Map<String, String> data) {
        this.personId = personId;
        this.personName = personName;
        this.data = data;
    }

    public String getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public Map<String, String> getData() {
        return data;
    }
}
