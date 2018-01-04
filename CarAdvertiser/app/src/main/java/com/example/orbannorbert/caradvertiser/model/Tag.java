package com.example.orbannorbert.caradvertiser.model;

import java.util.Map;

public class Tag {

    private String tag;
    private Map<String,String> vehicleIds; //key-title

    public Tag(String tag, String title ,String vehicleid) {
        this.tag = tag;
        this.vehicleIds.put(title,vehicleid);
    }
}
