package com.example.orbannorbert.caradvertiser;

import java.util.ArrayList;

/**
 * Created by OrbanNorbert on 2017. 11. 15..
 */

public class Vehicle {
    //@Serialable
    private String bodyType;
    private String description;
    private double engine;
    private String fuelType;
    private int horsePower;
    private String id;
    private String shortDescription;
    private ArrayList<String> images;
    private int km;
    private int numberOfDoors;
    private double price;
    private boolean sold;
    private String subType;
    private ArrayList<String> tags;
    private String title;
    private String type;
    private int year;

    public Vehicle()
    {}
    public Vehicle(ArrayList<String> images, double v, String s, String toString, String string, String s1){

    };

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Vehicle(VehicleBuilder builder) {
        this.bodyType = builder.bodyType;
        this.description = builder.description;
        this.engine = builder.engine;
        this.fuelType = builder.fuelType;
        this.horsePower = builder.horsePower;
        this.id = builder.id;
        this.images = builder.images;
        this.km = builder.km;
        this.numberOfDoors = builder.numberOfDoors;
        this.price = builder.price;
        this.sold = builder.sold;
        this.subType = builder.subType;
        this.tags = builder.tags;
        this.title = builder.title;
        this.type = builder.type;
        this.year = builder.year;
        this.shortDescription = builder.shortDescription;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEngine() {
        return engine;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static class VehicleBuilder{
        private String bodyType;
        private String description;
        private double engine;
        private String fuelType;
        private int horsePower;
        private String id;
        private ArrayList<String> images;
        private int km;
        private int numberOfDoors;
        private double price;
        private boolean sold;
        private String subType;
        private ArrayList<String> tags;
        private String title;
        private String type;
        private int year;
        private String shortDescription;

        VehicleBuilder(ArrayList<String> images, double price, String subType, String title, String type,String shortDescription){
            this.images = images;
            this.price = price;
            this.subType = subType;
            this.title = title;
            this.type = type;
            this.sold = false;
            this.shortDescription=shortDescription;
        }

        public VehicleBuilder bodyType(String bodyType){
            this.bodyType = bodyType;
            return this;
        }

        public VehicleBuilder description(String description){
            this.description = description;
            return this;
        }

        public VehicleBuilder engine(double engine){
            this.engine = engine;
            return this;
        }

        public VehicleBuilder fuelType(String fuelType){
            this.fuelType = fuelType;
            return this;
        }

        public VehicleBuilder horsePower(int horsePower){
            this.horsePower = horsePower;
            return this;
        }

        public VehicleBuilder km(int km){
            this.km = km;
            return this;
        }

        public VehicleBuilder numberOfDoors(int numberOfDoors){
            this.numberOfDoors = numberOfDoors;
            return this;
        }

        public VehicleBuilder tags(ArrayList<String> tags){
            this.tags = tags;
            return this;
        }

        public VehicleBuilder year(int year) {
            this.year = year;
            return this;
        }
    }
}

