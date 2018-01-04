package com.example.orbannorbert.caradvertiser.model;

public class ViewModel {

    private long id;
    private String text;
    private String image;
    private String description;
    private double price;

    public ViewModel(){}

    public ViewModel(long id, String text, String image,double price) {
        this.id = id;
        this.text = text;
        this.image = image;
        this.price=price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}