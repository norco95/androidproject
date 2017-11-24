package com.example.orbannorbert.caradvertiser;

/**
 * Created by OrbanNorbert on 2017. 11. 23..
 */

public class ViewModel {

    private long id;
    private String text;
    private String image;
    private String description;

    public ViewModel(){};
    public ViewModel(long id, String text, String image) {
        this.id = id;
        this.text = text;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}