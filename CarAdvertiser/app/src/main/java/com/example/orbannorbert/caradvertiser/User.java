package com.example.orbannorbert.caradvertiser;

import java.util.ArrayList;

/**
 * Created by OrbanNorbert on 2017. 11. 17..
 */

public class User {
    private String password;
    private boolean denied;
    private String email;
    private String firstName;
    private String id;
    private String lastName;
    private String phoneNumber;
    private String profileImage;
    private ArrayList<String> vehicleIds;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDenied() {
        return denied;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public ArrayList<String> getVehicleIds() {
        return vehicleIds;
    }



    public void setDenied(boolean denied) {
        this.denied = denied;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setVehicleIds(ArrayList<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public User()
    {

    }
    public User(UserBuilder builder){
        this.denied = builder.denied;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.profileImage = builder.profileImage;
        this.vehicleIds = builder.vehicleIds;
    }
    
    public static class UserBuilder{
        private String authentificationId;
        private boolean denied;
        private String email;
        private String firstName;
        private String id;
        private String lastName;
        private String phoneNumber;
        private String profileImage;
        private ArrayList<String> vehicleIds;
        
        public UserBuilder(String authentificationId, String firstName, String lastName, String phoneNumber){
            this.authentificationId = authentificationId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.denied = false;
        }

        public boolean isDenied() {
            return denied;
        }

        public void setDenied(boolean denied) {
            this.denied = denied;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public ArrayList<String> getVehicleIds() {
            return vehicleIds;
        }

        public void setVehicleIds(ArrayList<String> vehicleIds) {
            this.vehicleIds = vehicleIds;
        }

        public UserBuilder email(String email){
            this.email = email;
            return this;
        }

        public  UserBuilder profileImage(String profileImage){
            this.profileImage = profileImage;
            return this;
        }

        public  UserBuilder vehicleIds(ArrayList<String> vehicleIds){
            this.vehicleIds = vehicleIds;
            return this;
        }


    }
}
