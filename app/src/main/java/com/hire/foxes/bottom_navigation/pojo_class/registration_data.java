package com.hire.foxes.bottom_navigation.pojo_class;

public class registration_data {

    String username,email;
    int phone_number;

    public registration_data(String username, String email, int phone_number) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}
