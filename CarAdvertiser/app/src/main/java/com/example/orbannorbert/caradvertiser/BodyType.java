package com.example.orbannorbert.caradvertiser;

/**
 * Created by OrbanNorbert on 2017. 11. 17..
 */

public class BodyType {
    private String id;
    private String name;

    public BodyType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }




    public void setName(String name) {
        this.name = name;
    }
}