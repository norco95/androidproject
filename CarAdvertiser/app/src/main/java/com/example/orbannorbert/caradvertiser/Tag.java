package com.example.orbannorbert.caradvertiser;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by OrbanNorbert on 2017. 11. 17..
 */

public class Tag {

    private String tag;
    private Map<String,String> vehicleIds; //key-title

    public Tag(String tag, String title ,String vehicleid) {
        this.tag = tag;
        this.vehicleIds.put(title,vehicleid);
    }
}
