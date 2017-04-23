package com.example.mahi.tripsapp.classes;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mahi on 4/23/2017.
 */

public class Trip implements Serializable {

    String ID,created_by,title,location,image_url;
    public Map<String, Boolean> members = new HashMap<>();
    public Map<String, Boolean> unsubscribe_list = new HashMap<>();
    public Map<String, Message> messages = new HashMap<>();

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Map<String, Boolean> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Boolean> members) {
        this.members = members;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Map<String, Message> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Message> messages) {
        this.messages = messages;
    }

    public Map<String, Boolean> getUnsubscribe_list() {
        return unsubscribe_list;
    }

    public void setUnsubscribe_list(Map<String, Boolean> unsubscribe_list) {
        this.unsubscribe_list = unsubscribe_list;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("created_by", created_by);
        result.put("image_url", image_url);
        result.put("title", title);
        result.put("location", location);
        result.put("image_url", image_url);
        result.put("ID", ID);
        result.put("members", members);
        result.put("unsubscribe_list", unsubscribe_list);
        result.put("messages", messages);

        return result;
    }
}
