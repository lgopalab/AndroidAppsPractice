package com.example.mahi.tripsapp.classes;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mahi on 4/21/2017.
 */

public class User implements Serializable {

    String first_name, last_name, gender, image_url,email,ID;
    public Map<String, Boolean> friends = new HashMap<>();
    public Map<String, Boolean> sent = new HashMap<>();
    public Map<String, Boolean> pending = new HashMap<>();
    public Map<String, Boolean> trips = new HashMap<>();


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, Boolean> friends) {
        this.friends = friends;
    }

    public Map<String, Boolean> getSent() {
        return sent;
    }

    public void setSent(Map<String, Boolean> sent) {
        this.sent = sent;
    }

    public Map<String, Boolean> getPending() {
        return pending;
    }

    public void setPending(Map<String, Boolean> pending) {
        this.pending = pending;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("first_name", first_name);
        result.put("last_name", last_name);
        result.put("email", email);
        result.put("gender", gender);
        result.put("image_url", image_url);
        result.put("ID", ID);
        result.put("friends", friends);
        result.put("sent", sent);
        result.put("pending", pending);

        return result;
    }


}
