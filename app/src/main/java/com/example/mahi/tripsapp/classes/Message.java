package com.example.mahi.tripsapp.classes;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mahi on 4/23/2017.
 */

public class Message {

    String sent_by,time,image_url,message;
    public Map<String, Boolean> delete_list = new HashMap<>();


    public String getSent_by() {
        return sent_by;
    }

    public void setSent_by(String sent_by) {
        this.sent_by = sent_by;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Boolean> getDelete_list() {
        return delete_list;
    }

    public void setDelete_list(Map<String, Boolean> delete_list) {
        this.delete_list = delete_list;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("sent_by", sent_by);
        result.put("image_url", image_url);
        result.put("time", time);
        result.put("message", message);
        result.put("delete_list", delete_list);

        return result;
    }
}
